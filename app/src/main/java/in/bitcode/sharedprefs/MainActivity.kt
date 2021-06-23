package `in`.bitcode.sharedprefs

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var btnStartMain : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mt("onCreate");

        if(savedInstanceState != null) {
            //load data from savedInstanceState
        }

        var prefs = getSharedPreferences("settings_prefs", Activity.MODE_PRIVATE)

        var editor = prefs.edit()
        editor.putString("name", "BitCode")
        editor.putInt("code", 9012)
        editor.commit()


        var name = prefs.getString("name", "NA")
        var code = prefs.getInt("code", -1)

        mt("$name -- $code");


        btnStartMain = findViewById(R.id.btnStartMain)
        btnStartMain.setOnClickListener {
            var intent = Intent(MainActivity@this, MainActivity::class.java)
            intent.putExtra("code", Random.nextInt())
            startActivity(intent)
        }

        var pref1 = getPreferences(Activity.MODE_PRIVATE)
        editor = pref1.edit()
        editor.putString("city", "Pune")
        editor.putInt("pin", 411004)
        editor.commit()

        mt("${pref1.getString("city", "Mum")} -- ${pref1.getInt("pin", -1)}")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mt("onNewIntent : ${intent?.getIntExtra("code", -1)}");
    }

    private fun mt(text : String) {
        Log.e("tag", text)
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}