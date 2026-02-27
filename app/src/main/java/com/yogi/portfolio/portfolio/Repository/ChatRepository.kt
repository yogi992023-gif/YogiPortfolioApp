import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yogi.portfolio.portfolio.domain.model.Chat.Message
import com.yogi.portfolio.portfolio.domain.model.Chat.User

class ChatRepository {

    private val db = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()

    fun createChatUser(name: String, email: String) {

        val uid = auth.currentUser!!.uid

        val user = User(
            userId = uid,
            name = name,
            email = email
        )

        db.child("users").child(uid).setValue(user)
    }

    suspend fun getUsers(callback: (List<User>) -> Unit) {

        val currentUser = auth.currentUser!!.uid

        db.child("users").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<User>()

                for (data in snapshot.children) {

                    val user = data.getValue(User::class.java)

                    if (user != null && user.userId != currentUser) {
                        list.add(user)
                    }
                }

                callback(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    suspend fun getConversationId(uid1: String, uid2: String): String {

        return if (uid1 < uid2)
            "${uid1}_${uid2}"
        else
            "${uid2}_${uid1}"
    }

    suspend fun sendMessage(receiverId: String, text: String) {

        val senderId = auth.currentUser!!.uid

        val conversationId = getConversationId(senderId, receiverId)

        val ref = db.child("messages").child(conversationId)

        val messageId = ref.push().key!!

        val message = Message(
            senderId = senderId,
            receiverId = receiverId,
            message = text,
            timestamp = System.currentTimeMillis()
        )

        ref.child(messageId).setValue(message)
    }

    suspend fun listenMessages(receiverId: String, callback: (List<Message>) -> Unit) {

        val senderId = auth.currentUser!!.uid

        val conversationId = getConversationId(senderId, receiverId)

        db.child("messages")
            .child(conversationId)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<Message>()

                    for (data in snapshot.children) {

                        val message = data.getValue(Message::class.java)

                        if (message != null)
                            list.add(message)
                    }

                    callback(list)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}