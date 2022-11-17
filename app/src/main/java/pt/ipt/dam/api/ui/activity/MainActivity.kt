package pt.ipt.dam.api.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pt.ipt.dam.api.R
import pt.ipt.dam.api.model.APIResult
import pt.ipt.dam.api.model.Note
import pt.ipt.dam.api.retrofit.RetrofitInitializer
import pt.ipt.dam.api.ui.adapter.NoteListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.GregorianCalendar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAdd: Button = findViewById(R.id.btnRandomNote)
        buttonAdd.setOnClickListener {
            addDummyNote()
        }

        val btnGetNotes: Button = findViewById(R.id.btnGetNotes)
        btnGetNotes.setOnClickListener {
            listNotes()
        }

        /*
        val btnGetNotesBA: Button = findViewById(R.id.btnGetNotesBA)
        btnGetNotesBA.setOnClickListener {
            listNotesBA()
        }

        val btnGetNotesJWT: Button = findViewById(R.id.btnGetNotesJWT)
        btnGetNotesJWT.setOnClickListener {
            listNotesJWT()
        }
         */

        val btnClear: Button = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {
            configureList(emptyList())
        }
    }

    private fun listNotes() {
        val call = RetrofitInitializer().noteService().list()
        processList(call)
    }

   /*
    private fun listNotesBA() {
        val call = RetrofitInitializer().noteService().listBA( Credentials.basic("admin", "admin"))
        processList(call)
    }

    private fun listNotesJWT() {
        loginJWT("admin", "admin") {
            Toast.makeText(this,"Token " + it?.token,Toast.LENGTH_SHORT).show()

            val call = RetrofitInitializer().noteService().listJWT(token = "Bearer "+it?.token);
            processList(call)
        }
    }

    private fun loginJWT(username: String, password: String,  onResult: (TokenJWT?) -> Unit){
        val call = RetrofitInitializer().noteService().loginJWT(username, password)
        call.enqueue(
            object : Callback<TokenJWT> {
                override fun onFailure(call: Call<TokenJWT>, t: Throwable) {
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse( call: Call<TokenJWT>, response: Response<TokenJWT>) {
                    val tokenResult = response.body()
                    onResult(tokenResult)
                }
            }
        )
    }
    */

    private fun processList(call: Call<List<Note>>) {
        call.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(call: Call<List<Note>?>?,
                                    response: Response<List<Note>?>?) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<List<Note>?>?, t: Throwable?) {
                t?.printStackTrace()
                t?.message?.let { Log.e("onFailure error", it) }
            }
        })
    }

    private fun configureList(notes: List<Note>) {
        val recyclerView: RecyclerView = findViewById(R.id.note_list_recyclerview)
        recyclerView.adapter = NoteListAdapter(notes, this)
        val layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    private fun addDummyNote() {
        val i = Random(GregorianCalendar.getInstance().timeInMillis).nextInt(100)

        val note = Note("Note $i", "Description $i")

        addNote(note) {
            Toast.makeText(this,"Add " + it?.description,Toast.LENGTH_SHORT).show()
            listNotes()
        }
    }

    private fun addNote(note: Note, onResult: (APIResult?) -> Unit){
        val call = RetrofitInitializer().noteService().addNote(note.title, note.description)
        call.enqueue(
            object : Callback<APIResult> {
                override fun onFailure(call: Call<APIResult>, t: Throwable) {
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse( call: Call<APIResult>, response: Response<APIResult>) {
                    val addedNote = response.body()
                    onResult(addedNote)
                }
            }
        )
    }

}