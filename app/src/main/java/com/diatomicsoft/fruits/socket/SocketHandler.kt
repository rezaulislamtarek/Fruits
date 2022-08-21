package com.diatomicsoft.fruits.socket

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket

object SocketHandler {
    lateinit var mSocket : Socket

    @Synchronized
    fun setSocket(){
        try {
            mSocket = IO.socket("http://192.168.0.100:3000")
        }catch (e: Exception){
            Log.d("socket",e.toString())
        }
    }

    @Synchronized
    fun getSocket(): Socket{
        return mSocket
    }

    @Synchronized
    fun establishConnection(){
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection(){
        mSocket.disconnect()
    }
}