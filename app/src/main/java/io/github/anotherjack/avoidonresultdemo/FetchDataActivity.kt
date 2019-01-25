package io.github.anotherjack.avoidonresultdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_fetch_data.*

class FetchDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_data)

        back.setOnClickListener {
            val intent = Intent()
            intent.putExtra("text",text.text.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }
}
