package pt.ipt.dam.api.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pt.ipt.dam.api.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}