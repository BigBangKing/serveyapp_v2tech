package com.example.serveyapp_rifatmahmud_v2tech.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.serveyapp_rifatmahmud_v2tech.R


class NewWordActivity : AppCompatActivity() {
    public val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"

//    private var mEditWordView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        val mEditWordView: EditText = findViewById(R.id.edit_word)

        val button: Button = findViewById(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val word = mEditWordView.getText().toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    public fun getExtraReply()
    : String {
        return EXTRA_REPLY
    }
}