

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogi.portfolio.portfolio.domain.model.Chat.Message
import com.yogi.portfolio.portfolio.domain.model.Chat.User
import kotlinx.coroutines.launch


class ChatViewModel : ViewModel() {

    private val repo = ChatRepository()

    var users = mutableStateListOf<User>()
        private set

    var messages = mutableStateListOf<Message>()
        private set


    fun createChatUser(name: String, email: String) {

        viewModelScope.launch {

            repo.createChatUser(name, email)
        }
    }

    fun loadUsers() {

        viewModelScope.launch {

            repo.getUsers(){
                users.clear()
                users.addAll(it)
            }
        }
    }

    fun sendMessage(receiverId: String, text: String) {

        viewModelScope.launch {

            repo.sendMessage(receiverId, text)
        }
    }

    fun listenMessages(receiverId: String) {

        viewModelScope.launch {

            repo.listenMessages(receiverId) { list ->

                messages.clear()
                messages.addAll(list)
            }
        }
    }
}