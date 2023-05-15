package lt.paulius.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class Notes : AppCompatActivity() {

    lateinit var addButton: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.fabAddNote)
        setUpOnClickListener()

    }

    private fun setUpOnClickListener() {
        addButton.setOnClickListener {
            startActivity(Intent(this, NoteDetails::class.java))
        }
    }


}
