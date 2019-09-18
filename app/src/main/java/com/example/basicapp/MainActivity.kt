package com.example.basicapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var name = "Ximena"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val sAdd = getString(R.string.adding)



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Realiza una acción $name", Snackbar.LENGTH_LONG)
                .setAction("Action"){
                    text_view_id.text = String.format(sAdd,sum())
                }.show()
        }
    }

    private fun sum(): Int {
        val a = 10
        val b = 5
        return a + b
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val intentSetting = Intent(this, SettingsActivity::class.java)
        val intentWeather = Intent(this, WeatherActivity::class.java)
        when (item.itemId) {
            R.id.action_settings -> {
                startActivity(intentSetting)
                return true
            }
            R.id.action_weather -> {
                startActivity(intentWeather)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
