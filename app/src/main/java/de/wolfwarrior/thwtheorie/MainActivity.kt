package de.wolfwarrior.thwtheorie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.main_menu_toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.thw_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.showgithub -> {
                showGitHub()
                true
            }
            R.id.aboutthisapp -> {
                aboutThisApp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    @Suppress("UNUSED_PARAMETER")
    fun showList(view: View) {
        startActivity(Intent(this, ChooseTheme::class.java))
    }

    @Suppress("UNUSED_PARAMETER")
    fun startExam(view: View){
        val intent = Intent(this,Theory::class.java)
        intent.putExtra("Theme",-2)
        startActivity(intent)
    }


    private fun aboutThisApp(){
        val url = "https://github.com/wolfwarrior666/THW_Theorie#thw_theorie"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun showGitHub(){
        val url = "https://github.com/wolfwarrior666/THW_Theorie"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}