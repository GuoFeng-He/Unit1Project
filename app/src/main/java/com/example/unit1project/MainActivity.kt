package com.example.unit1project


import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random




class MainActivity : AppCompatActivity() {
    private var score: Int = 0
    private var strikes: Int = 0
    private lateinit var scoreText: TextView
    private lateinit var strikesText: TextView
    private lateinit var blueBox: TextView
    private lateinit var orangeBox: TextView
    private lateinit var view: LinearLayout
    private lateinit var infoText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        scoreText = findViewById(R.id.score)
        strikesText = findViewById(R.id.strikes)
        blueBox = findViewById(R.id.blue_box)
        orangeBox= findViewById(R.id.orange_box)
        view= findViewById(R.id.main)
        infoText= findViewById(R.id.text)
        val button: Button = findViewById(R.id.button)


        button.setOnClickListener {
            updateScore("reset")

            button.text = "RESTART"
            infoText.text = "Tap on the larger number!"

            var bigger: Int = updateNumbers()

            blueBox.setOnClickListener {
                if (!endGame()) {
                    if (bigger == 0) {
                        updateScore("score")
                        view.setBackgroundColor(Color.GREEN)
                    } else {
                        updateScore("strikes")
                        view.setBackgroundColor(Color.RED)
                    }
                    if (!endGame()) {
                        bigger = updateNumbers()
                    } else {
                        reset()
                    }
                }
            }

            orangeBox.setOnClickListener {
                if (!endGame()) {
                    if (bigger == 1) {
                        updateScore("score")
                        view.setBackgroundColor(Color.GREEN)
                    } else {
                        updateScore("strikes")
                        view.setBackgroundColor(Color.RED)
                    }
                    if (!endGame()) {
                        bigger = updateNumbers()
                    }else {
                        reset()
                    }
                }
            }
        }
    }

    private fun greaterValue(e: List<Int>): Int {
        return if (e[0] > e[1]) 0 else 1
    }

    private fun randList(): List<Int>{
        val rand = Random.nextInt(100)
        var rand2 = Random.nextInt(100)


        while(rand2 == rand){
            rand2 = Random.nextInt(100)
        }
        return listOf(rand, rand2)
    }

    private fun endGame(): Boolean {
        return score == 10 || strikes == 3
    }

    private fun updateScore(text: String){
        if (text == "score"){
            score++
            if (score != 10) {
                scoreText.setTextColor(Color.YELLOW)
            } else {
                scoreText.setTextColor(Color.GREEN)
                displayToast("You win!")
            }
            strikesText.setTextColor(Color.BLACK)
        } else if (text == "strikes") {
            strikes++
            if (strikes != 3) {
                strikesText.setTextColor(Color.YELLOW)
            } else {
                strikesText.setTextColor(Color.RED)
                displayToast("Sorry, you've lost")
            }
            scoreText.setTextColor(Color.BLACK)
        } else if (text == "reset"){
            score = 0
            strikes = 0
            scoreText.setTextColor(Color.BLACK)
            strikesText.setTextColor(Color.BLACK)
        }

        scoreText.text = "Score: $score"
        strikesText.text = "Strikes: $strikes"
    }

    private fun updateNumbers(): Int{
        val numbers: List<Int> = randList()
        val bigger: Int = greaterValue(numbers)

        blueBox.text = numbers[0].toString()
        orangeBox.text = numbers[1].toString()
        return bigger
    }

    private fun displayToast(v: String){
        val toast: Toast = Toast.makeText(this, v, Toast.LENGTH_SHORT)
        toast.show()

    }
    private fun reset(){
        view.setBackgroundColor(Color.parseColor("#FFFDEF74"))
        blueBox.text = ""
        orangeBox.text = ""
        infoText.text = "Press restart to try again!"
    }
}
