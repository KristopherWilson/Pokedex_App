package com.example.pokedex

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(var pokemonList: Pokemon, var mainActivity: MainActivity) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>()
{
    private val pokemonDictionary = linkedMapOf<String, Int>()
    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var textViewName: TextView
        var buttonInfo: Button
        var checkMark: CheckBox

        init {
            textViewName = itemView.findViewById(R.id.textViewName)
            buttonInfo = itemView.findViewById(R.id.buttonInfo)
            checkMark = itemView.findViewById(R.id.checkBoxPokedex)
        }
    }//end inner class PersonViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_pokedex, parent, false)
        for (i in 0..(pokemonList.results.size - 1))
        {
            pokemonDictionary[pokemonList.results[i].name] = 0
        }
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return pokemonList.results.size
    }//end getItemCount

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int)
    {
        holder.textViewName.text = pokemonList.results[position].name.toUpperCase()
        holder.checkMark.alpha = 0f

        holder.buttonInfo.setOnClickListener{
            mainActivity.infoButtonClicked(pokemonList.results[position])
        }//end buttonInfo holder

        holder.itemView.setOnClickListener {
            notifyDataSetChanged()
        }//end itemView holder

        holder.checkMark.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
            {
                holder.itemView.setBackgroundColor(Color.parseColor("#0BA28E"))
                holder.textViewName.setTextColor(Color.parseColor("#ffffff"))
                pokemonDictionary.replace(pokemonList.results[position].name,1)
                println(pokemonDictionary)
            }
            else{
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"))
                holder.textViewName.setTextColor(Color.parseColor("#000000"))
                pokemonDictionary.replace(pokemonList.results[position].name,0)
            }
        }//end checkMark holder

        for (i in pokemonDictionary)
        {
            if (i.value == 1)
            {
                holder.itemView.setBackgroundColor(Color.parseColor("#0BA28E"))
                holder.textViewName.setTextColor(Color.parseColor("#ffffff"))
            }
            else{
                holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"))
                holder.textViewName.setTextColor(Color.parseColor("#000000"))
            }
        }
    }//end onBindViewHolder

}//end PokemonAdapter