package com.example.appconsumoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        val btnJoke = findViewById<Button>(R.id.btnJoke)
        btnJoke.setOnClickListener {
            loadJoke()
        }
}

    private fun loadJoke() {
        val retrofit=Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerKumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jokeService: JokeService
        jokeService=retrofit.create(JokeService::class.java)

        val request = jokeService.getJoke("json")
        request.enqueue(object : Callback<Joke>{
            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Log.d("jokeActivity", t.toString())
            }
            override fun  onResponse(call: Call<Joke>, response: Response<Joke>){
                if (response.isSuccessful){
                    val tvBroma= findViewById<TextView>(R.id.tvBroma)
                    tvBroma.text = response.body()!!.joke

                }
            }
        })


        //tvBroma.text ="Broma encontrada !!!"
    }
}
