package com.diatomicsoft.fruits.socket

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diatomicsoft.fruits.R
import kotlinx.coroutines.launch


class SocketActivity : AppCompatActivity() {
    var mess = ""
    var userList: List<User> = mutableListOf()
    lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)


        SocketHandler.setSocket()
        //SocketHandler.establishConnection()
        val mSocket = SocketHandler.getSocket()
        mSocket.connect()

        findViewById<Button>(R.id.btnSocketShare).setOnClickListener {
            var name = findViewById<EditText>(R.id.etSocketName).text.toString()
            var age = findViewById<EditText>(R.id.etSocketAge).text.toString()
            var status = findViewById<EditText>(R.id.etSocketStatus).text.toString()
            var user = User(name, status, age.toInt())
            var res = mSocket.emit("user", user)
            Log.d("userbtn", res.toString())
            findViewById<LinearLayout>(R.id.llPostStatus).visibility = View.GONE

            findViewById<EditText>(R.id.etSocketName).text = null
            findViewById<EditText>(R.id.etSocketAge).text = null
            findViewById<EditText>(R.id.etSocketStatus).text = null
            hideKeyboard(this)

        }

        findViewById<ImageView>(R.id.btnSocketOpenStatus).setOnClickListener {
            findViewById<LinearLayout>(R.id.llPostStatus).visibility = View.VISIBLE
        }



        mSocket.on("user") { args ->
            Log.d("user", "calling")
            if (args[0] != null) {
                var user = args[0]
                Log.d("user", user.toString())
                var usER = convertStringToObject(user.toString(), "User")
                userList += usER
                lifecycleScope.launch { showInRecyclerView() }
                Log.d("user size:", "Size: " + userList.size.toString())

            }
        }




        findViewById<AppCompatButton>(R.id.btnSocketClose).setOnClickListener {
            SocketHandler.closeConnection()
            print("Connection close button click")
        }
        findViewById<TextView>(R.id.btnSocketAdd).setOnClickListener {
            /*emit is used to send
            on is used to get a response
            */
            mSocket.emit("counter")
            print("Add Button clicked");
        }
        mSocket.on("counter") { args ->
            if (args[0] != null) {
                val counter = args[0] as Int
                runOnUiThread {
                    findViewById<TextView>(R.id.tvSocket).setText(counter.toString())
                }
            }
        }
        findViewById<TextView>(R.id.btnSocketSend).setOnClickListener {
            var text = findViewById<EditText>(R.id.etSocketMsg).text.toString()
            mSocket.emit("msg", text)
            findViewById<EditText>(R.id.etSocketMsg).text = null
            hideKeyboard(this)
        }
        //getting Current message
        mSocket.on("e_msg") { args ->
            if (args[0] != null) {
                Log.d("socketLog", args[0].toString())
                var msg = args[0] as String
                mess = mess + msg + "\n"
                runOnUiThread {
                    findViewById<TextView>(R.id.tvSocketMsg).text = mess
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<RecyclerView>(R.id.rvSocket).also { rv ->
           // var userList : List<User> = fatchDataFromSQLite()
            rv.layoutManager = LinearLayoutManager(this)
            rv.setHasFixedSize(true)
            adapter = UserAdapter(userList)
            rv.adapter = adapter
        }
    }

    private fun print(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

    private fun showInRecyclerView() {
        Log.d("rv size", userList.size.toString())
        findViewById<RecyclerView>(R.id.rvSocket).also { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.setHasFixedSize(true)
            adapter = UserAdapter(userList)
            rv.adapter = adapter
            // adapter.notifyDataSetChanged()
        }
    }

    private fun convertStringToObject(data: String, type: String): User {
        var result = data.replace(type, "");
        result = result.replace("(", "")
        result = result.replace(")", "")
        var ary = result.split(",")
        var tmp = ""
        for (i in ary) {
            var value = i.split("=")[1]
            Log.d("value", value)
            tmp = tmp + value + "  "
        }
        var tmpUser = tmp.split("  ")
        var user = User(
            tmpUser[0].trim(),
            tmpUser[1].trim(),
            tmpUser[2].trim().toInt(),
        )
        Log.d("result", user.toString())
        return user
    }
}