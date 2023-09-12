package de.wolfwarrior.thwtheorie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChooseTheme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_theme)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun startActivity(num: Int) {
        val intent = Intent(this, Theory::class.java)
        intent.putExtra("Theme", num)
        startActivity(intent)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeOne(view: View) {
        startActivity(1)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeTwo(view: View) {
        startActivity(2)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeThree(view: View) {
        startActivity(3)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeFour(view: View) {
        startActivity(4)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeFive(view: View) {
        startActivity(5)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeSix(view: View) {
        startActivity(6)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeSeven(view: View) {
        startActivity(7)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeEight(view: View) {
        startActivity(8)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeNine(view: View) {
        startActivity(9)
    }

    @Suppress("UNUSED_PARAMETER")
    fun themeTen(view: View) {
        startActivity(10)
    }
}