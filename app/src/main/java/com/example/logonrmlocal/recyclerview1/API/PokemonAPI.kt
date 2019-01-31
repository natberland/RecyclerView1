package com.example.logonrmlocal.recyclerview1.API

import com.example.logonrmlocal.recyclerview1.model.PokemonResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPI {

    @GET("/api/pokemon/")
    fun buscar(@Query("size") size: Int): Observable<PokemonResponse>

}