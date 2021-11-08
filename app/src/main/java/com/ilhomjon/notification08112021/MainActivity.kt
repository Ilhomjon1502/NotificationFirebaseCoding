package com.ilhomjon.notification08112021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ilhomjon.notification08112021.Retrofit.ApiClient
import com.ilhomjon.notification08112021.Retrofit.ApiService
import com.ilhomjon.notification08112021.Retrofit.Models.Data
import com.ilhomjon.notification08112021.Retrofit.Models.MyResponse
import com.ilhomjon.notification08112021.Retrofit.Models.Sender
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task->
            if (!task.isSuccessful){
                Log.d(TAG, "onCreate: token falled")
                return@OnCompleteListener
            }
            val token = task.result
            Log.d(TAG, token ?: "")
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        })

       val apiService = ApiClient.getRetrofit("https://fcm.googleapis.com/").create(ApiService::class.java)

        findViewById<Button>(R.id.btn_send).setOnClickListener {
            apiService.sendNotification(
                Sender(
                    Data(
                        "Ilhomjondan",
                        R.drawable.ic_launcher_foreground,
                        "Kod orqali notification",
                        "New Message",
                        "Ilhomjonga"
                    ),
                    "drPxYsonTFC8Fxj7uB-QqZ:APA91bHOBAVsG1QKDyYV6G6ODMLb--kc_lXUYsXPkSRtgaEnXPd2XrF0_IV5ABi1yreKuSpfnWnEQSKK7U59I4zVnvzjTlBJThFLuijodK2npLQaBDSNOTdZonszRYEdzsPk1hopft14"
                )
            ).enqueue(object : Callback<MyResponse> {
                override fun onResponse(call: Call<MyResponse>, response: Response<MyResponse>) {
                    if (response.isSuccessful){
                        Toast.makeText(this@MainActivity, "Succsefull", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}