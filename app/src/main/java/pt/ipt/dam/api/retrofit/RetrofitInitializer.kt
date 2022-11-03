package pt.ipt.dam.api.retrofit

import pt.ipt.dam.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * access to API
 * we must specify the URL
 */
class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://adamastor.ipt.pt/DAM-API/") // "http://10.0.0.2/" --> reference to an API that is on localhost
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun noteService() = retrofit.create(NoteService::class.java)
}