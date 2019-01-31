package com.example.logonrmlocal.recyclerview1.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse (val content: List<Pokemon>)

data class Pokemon(
        @SerializedName("number") val numero: String,
        @SerializedName("name") val nome: String,
        @SerializedName("imageURL") val imagem: String
)
