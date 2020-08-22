package com.example.serveyapp_rifatmahmud_v2tech

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serveyapp_rifatmahmud_v2tech.ViewModel.WordViewModel
import com.example.serveyapp_rifatmahmud_v2tech.adapter.WordListAdapter
import com.example.serveyapp_rifatmahmud_v2tech.data.Word
import com.example.serveyapp_rifatmahmud_v2tech.ui.NewSurveyActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var mWordViewModel: WordViewModel? = null
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        mWordViewModel!!.getAllWords().observe(this, object : Observer<List<Word?>?> {
            override fun onChanged(@Nullable words: List<Word?>?) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words)
            }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //val intent = Intent(this@MainActivity, NewWordActivity::class.java)
                //startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)

                val intentServey = Intent(this@MainActivity, NewSurveyActivity::class.java)
                startActivity(intentServey)

            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val word = Word(
                (data?.getStringExtra("com.example.android.wordlistsql.REPLY") ?: ""))
            mWordViewModel!!.insert(word)
        } else {
            Toast.makeText(
                applicationContext,
                "empty_not_saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}