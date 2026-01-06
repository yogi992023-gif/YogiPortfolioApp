package com.yogi.portfolio.portfolio.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.yogi.portfolio.databinding.ActivityBlueToothPrintBinding
import java.io.IOException
import java.util.UUID

class BlueToothPrint : AppCompatActivity() {

    lateinit var binding : ActivityBlueToothPrintBinding

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var selectedDevice: BluetoothDevice? = null
    private val sppUUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlueToothPrintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        checkBluetoothPermissions()

        binding.btnSelectPrinter.setOnClickListener {
            FirebaseCrashlytics.getInstance().log("Test crash")
            FirebaseCrashlytics.getInstance().recordException(Exception("Test exception"))
            showPairedPrintersDialog()
        }



        binding.btn.setOnClickListener {
            if (selectedDevice != null) {
                val billData = """
                    *** TEST PRINT ***
                    Printer: ${selectedDevice!!.name}
                    -----------------------------
                    Customer: Raj Kumar
                    Amount: ₹450
                    Date: 07-Oct-2025
                    
                    Thank you for visiting!
                    -----------------------------
                    
                """.trimIndent()

                val bitmap = getBitmapFromView(binding.blueToothLayout)
                val printData = convertBitmapToPrinterBytes(bitmap)

                printToBluetoothPrinterLayout(selectedDevice!!, printData)

             //   printToBluetoothPrinter(selectedDevice!!, billData)
            } else {
                Toast.makeText(this, "Please select a printer first", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun checkBluetoothPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.BLUETOOTH_CONNECT, Manifest.permission.BLUETOOTH_SCAN),
                100
            )
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun showPairedPrintersDialog() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show()
            return
        }

        if (!bluetoothAdapter!!.isEnabled) {
            Toast.makeText(this, "Please enable Bluetooth first", Toast.LENGTH_SHORT).show()
            return
        }

        val pairedDevices = bluetoothAdapter!!.bondedDevices
        if (pairedDevices.isEmpty()) {
            Toast.makeText(this, "No paired printers found", Toast.LENGTH_SHORT).show()
            return
        }

        val deviceNames = pairedDevices.map { it.name }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("Select BLUPRINTS Printer")
            .setItems(deviceNames) { _, which ->
                selectedDevice = pairedDevices.elementAt(which)
                Toast.makeText(this, "Selected: ${selectedDevice?.name}", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    private fun printToBluetoothPrinter(device: BluetoothDevice, text: String) {
        Log.d("BLUETOOTH Name...", "Connecting to: ${device.address}")
        bluetoothAdapter?.cancelDiscovery()

        Thread {
            try {
                // ---- DIRECT CHANNEL CONNECTION ----
                val socket = device.javaClass
                    .getMethod("createRfcommSocket", Int::class.javaPrimitiveType)
                    .invoke(device, 1) as BluetoothSocket

                socket.connect()

                val outputStream = socket.outputStream

                // Reset + print + feed paper
                outputStream.write(byteArrayOf(0x1B, 0x40)) // Initialize printer
                outputStream.write(text.toByteArray(Charsets.UTF_8))
                outputStream.write("\n\n\n".toByteArray())
                Log.e(outputStream.write("\n\n\n".toByteArray()).toString(),"printer deatils")
                outputStream.flush()

                outputStream.close()
                socket.close()

                runOnUiThread {
                    Toast.makeText(this, "Printed successfully ✅", Toast.LENGTH_SHORT).show()
                }

            } catch (e: IOException) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Connection failed: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_SCAN)
    private fun printToBluetoothPrinterLayout(device: BluetoothDevice, data: ByteArray) {
        bluetoothAdapter?.cancelDiscovery()
        Thread {
            try {
                val socket = device.javaClass
                    .getMethod("createRfcommSocket", Int::class.javaPrimitiveType)
                    .invoke(device, 1) as BluetoothSocket

                socket.connect()
                val outputStream = socket.outputStream
                outputStream.write(data)
                outputStream.flush()
                outputStream.close()
                socket.close()

                runOnUiThread {
                    Toast.makeText(this, "Printed layout successfully ✅", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this, "Print failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }.start()
    }

    fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    fun convertBitmapToPrinterBytes(bitmap: Bitmap): ByteArray {
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 384, bitmap.height * 384 / bitmap.width, false)
        val width = scaledBitmap.width
        val height = scaledBitmap.height
        val bytes = mutableListOf<Byte>()

        // Initialize printer
        bytes.addAll(listOf(0x1B.toByte(), 0x40.toByte())) // ESC @

        for (y in 0 until height step 24) {
            bytes.addAll(listOf(0x1B.toByte(), 0x2A.toByte(), 33.toByte())) // ESC * m
            bytes.add((width % 256).toByte())
            bytes.add((width / 256).toByte())

            for (x in 0 until width) {
                for (k in 0..2) { // 24-dot vertical
                    var slice = 0
                    for (b in 0..7) {
                        val yIndex = y + k * 8 + b
                        if (yIndex >= height) continue
                        val pixel = scaledBitmap.getPixel(x, yIndex)
                        val r = (pixel shr 16) and 0xFF
                        val g = (pixel shr 8) and 0xFF
                        val bColor = pixel and 0xFF
                        val luminance = (0.299*r + 0.587*g + 0.114*bColor).toInt()
                        if (luminance < 128) slice = slice or (1 shl (7-b))
                    }
                    bytes.add(slice.toByte())
                }
            }
            bytes.add(0x0A) // Line feed
        }
        return bytes.toByteArray()
    }
}