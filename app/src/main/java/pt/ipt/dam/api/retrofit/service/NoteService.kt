package pt.ipt.dam.api.retrofit.service

import android.telecom.Call
import pt.ipt.dam.api.model.Note

/**
 * specify the url that we want to access
 */
interface NoteService {
    @get("api/notes")
    fun list(): Call<List<Note>>
}