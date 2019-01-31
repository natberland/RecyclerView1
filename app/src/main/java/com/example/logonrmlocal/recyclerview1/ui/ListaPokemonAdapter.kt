package com.example.logonrmlocal.recyclerview1.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.logonrmlocal.recyclerview1.API.getPicassoAuth
import com.example.logonrmlocal.recyclerview1.R
import com.example.logonrmlocal.recyclerview1.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_row.view.*


class ListaPokemonAdapter(
        private val context: Context,
        private val pokemons: List<Pokemon>,
        private val listener: (Pokemon) -> Unit
) :
        RecyclerView.Adapter<ListaPokemonAdapter.PokemonViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.pokemon_row, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindView(pokemons[position], listener)
        }

    class PokemonViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView){

        fun bindView(pokemon: Pokemon, listener: (Pokemon) -> Unit) = with(itemView) {
            tvPokemon.text = pokemon.nome
            getPicassoAuth(itemView.context)
                    .load("https://pokedexdx.herokuapp.com${pokemon.imagem}")
                    .into(ivPokemon)

            setOnClickListener{listener(pokemon)}
        }

    }
}