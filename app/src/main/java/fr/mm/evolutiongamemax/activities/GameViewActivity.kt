package fr.mm.evolutiongamemax.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.system.exitProcess

class GameViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("INFO", "GAMEVIEW ACTIVITY")
        val gameView = GameViewManager(context = this,attrs = null)
        setContentView(gameView)

    }

    override fun onBackPressed() {
        exitProcess(0)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

}