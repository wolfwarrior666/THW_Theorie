package de.wolfwarrior.thwtheorie

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.WHITE
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.androidplot.xy.BarFormatter
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYPlot
import com.androidplot.xy.XYSeries
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {
    lateinit var plot: XYPlot
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.main_menu_toolbar)
        setSupportActionBar(toolbar)


        // initialize our XYPlot reference:
        // initialize our XYPlot reference:
        plot = findViewById<XYPlot>(R.id.myplot)

        val wins: XYSeries = SimpleXYSeries(
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "wins",
            3,
            4
        )
        val losses: XYSeries = SimpleXYSeries(
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
            "losses",
            0,
            1
        )

        // draw wins bars with a green fill:
        // draw wins bars with a green fill:
        val winsFormatter = BarFormatter(Color.GREEN, Color.BLACK)
        plot.addSeries(wins, winsFormatter)

// draw losses bars with a red fill:

// draw losses bars with a red fill:
        val lossesFormatter = BarFormatter(Color.RED, Color.BLACK)
        plot.addSeries(losses, lossesFormatter)
        plot.setBackgroundColor(Color.WHITE)


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