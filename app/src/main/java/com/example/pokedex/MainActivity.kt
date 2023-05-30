package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity()
{
    lateinit var mainIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rv = findViewById<RecyclerView>(R.id.rvPokemon)
        rv.layoutManager = LinearLayoutManager(this)

        loadJSON()
    }

    fun loadJSON()
    {
        val url = "https://pokeapi.co/api/v2/pokemon?limit=1281&offset=0"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("ERROR: ", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                var body = response.body?.string()
                val gson = GsonBuilder().create()
                var pokemonData = gson.fromJson(body, Pokemon::class.java)

                runOnUiThread() {
                    val adapter = PokemonAdapter(pokemonData, this@MainActivity)
                    var rv = findViewById<RecyclerView>(R.id.rvPokemon)
                    rv.adapter = adapter
                }
            }

        })
    }

    fun infoButtonClicked(pokemon: Result){
        mainIntent = Intent(this, DetailActivity::class.java)
        mainIntent.putExtra("NAME", pokemon.name)
        startActivity(mainIntent)
    }

    fun boxChecked()
    {

    }
}