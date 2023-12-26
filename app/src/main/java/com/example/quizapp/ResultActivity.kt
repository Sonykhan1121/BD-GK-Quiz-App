package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val score:Int = intent.getIntExtra("result",0)
        val totalquestion:Int = intent.getIntExtra("questions",0)
        val str:String = "Your Score is : "+score+"\n"
        val percentage:Double = (score.toDouble()/totalquestion.toDouble())*100
        val last=str+ "You got "+percentage+" % Correctly."

        binding.scoreId.setText(last)
    }
}