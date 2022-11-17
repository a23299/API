package pt.ipt.dam.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import pt.ipt.dam.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    //val host = "http://10.0.2.2/"
    //val host = "http://ram.ipt.pt/"
    //val host = "https://adamastor.ipt.pt/DAM-API/api/notes"
    val host = "https://adamastor.ipt.pt/DAM-API/"

    private val gson: Gson = GsonBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder().baseUrl(host).addConverterFactory(GsonConverterFactory.create(gson)).build()

    fun noteService(): NoteService = retrofit.create(NoteService::class.java)
}