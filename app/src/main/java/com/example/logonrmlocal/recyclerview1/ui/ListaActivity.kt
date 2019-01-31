package com.example.logonrmlocal.recyclerview1.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.logonrmlocal.recyclerview1.API.getPokemonAPI
import com.example.logonrmlocal.recyclerview1.R
import com.example.logonrmlocal.recyclerview1.model.Pokemon
import com.example.logonrmlocal.recyclerview1.model.PokemonResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    private var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        carregarDados()
    }

    private fun exibenaLista(pokemons:List<Pokemon>){
        rvPokemons.adapter = ListaPokemonAdapter(this, pokemons,{
            Toast.makeText(this, it.nome, Toast.LENGTH_LONG).show()
        } )
        rvPokemons.layoutManager = LinearLayoutManager (this)

    }

    private fun  exibeErro(erro:String) {
        Toast.makeText(this, erro, Toast.LENGTH_LONG).show()
    }

    private fun carregarDados(){
        getPokemonAPI()
                .buscar(150)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<PokemonResponse> {
                    override fun onComplete() {
                        Log.i("ListaActivity", "Complete")

                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: PokemonResponse) {
                        exibenaLista(t.content)
                    }

                    override fun onError(e: Throwable) {
                        exibeErro(e.message!!)
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}

