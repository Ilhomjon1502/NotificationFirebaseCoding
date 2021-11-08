package com.ilhomjon.notification08112021.Retrofit

import com.ilhomjon.notification08112021.Retrofit.Models.MyResponse
import com.ilhomjon.notification08112021.Retrofit.Models.Sender
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-type:application/json",
        "Authorization:key=AAAAMSTinRw:APA91bHGWRaHCaedDQ-P8_tPh0agN3tbLX4te2DtYpzuwqxjjyX098e6gHH4g8V7IAmHgpwBjw8XPrsvsjwyZrNptWyiHO66b5wDZp2opYdJOf3843Gq41C4jB0SPtwJB7MO9nP2i-WV"
    )
    @POST("fcm/send")
    fun sendNotification(@Body sender: Sender): Call<MyResponse>
}