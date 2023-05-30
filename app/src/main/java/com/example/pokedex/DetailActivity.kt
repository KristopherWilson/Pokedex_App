package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity()
{
    lateinit var detailName: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailName = findViewById(R.id.detailName)

        insertInfo()
    }

    fun insertInfo()
    {
        detailName.text = (intent.getStringExtra("NAME"))?.toUpperCase()
    }
}