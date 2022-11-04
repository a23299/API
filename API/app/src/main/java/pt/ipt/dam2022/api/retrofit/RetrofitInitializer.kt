package pt.ipt.dam2022.api.retrofit

import pt.ipt.dam2022.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit=Retrofit.Builder()
        .baseUrl("https://adamastor.ipt.pt/DAM-API/api/notes")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun noteService() = retrofit.create(NoteService::class.java)
}