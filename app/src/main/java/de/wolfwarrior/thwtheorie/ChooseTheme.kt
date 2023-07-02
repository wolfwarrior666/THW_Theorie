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

    fun startActivity(num:Int){
        var intent = Intent(this,Theory::class.java)
        intent.putExtra("Theme",num)
        startActivity(intent)
    }
    fun themeOne(view:View){
        startActivity(1)
    }
    fun themeTwo(view:View){
        startActivity(2)
    }
    fun themeThree(view:View){
        startActivity(3)
    }
    fun themeFour(view:View){
        startActivity(4)
    }
    fun themeFive(view:View){
        startActivity(5)
    }
    fun themeSix(view:View){
        startActivity(6)
    }
    fun themeSeven(view:View){
        startActivity(7)
    }
    fun themeEight(view:View){
        startActivity(8)
    }
    fun themeNine(view:View){
        startActivity(9)
    }
    fun themeTen(view:View){
        startActivity(10)
    }
}