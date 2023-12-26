package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.quizapp.databinding.ActivityHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    private lateinit var list:ArrayList<QuestionModel>
    private  var totalquestion:Int = 0;
    private var count = 0;
    private var score = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList<QuestionModel>()
        Firebase.firestore.collection("quiz").get()
            .addOnSuccessListener {
                doc->
                list.clear()
                for (i in doc.documents)
                {
                    var questionModel = i.toObject(QuestionModel::class.java)
                    list.add(questionModel!!)
                }
                if (list.isNotEmpty()) {
                    binding.questionId.setText(list[0].question)
                    binding.option1.setText(list[0].option1)
                    binding.option2.setText(list[0].option2)
                    binding.option3.setText(list[0].option3)
                    binding.option4.setText(list[0].option4)
                } else {
                    Log.d("HomeActivity", "List size: ${list.size}")
                }
               
            }
//        list.add(QuestionModel("What is your name?","salim","sony","siam","zilani","sony"))
//        list.add(QuestionModel("What is your name?","sony","salim","siam","zilani","sony"))
//        list.add(QuestionModel("What is your name?","salim","sony","siam","zilani","sony"))
//        list.add(QuestionModel("What is your name?","sony","salim","siam","zilani","sony"))

        totalquestion = list.size
        binding.option1.setOnClickListener {
                nextData(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener {
            nextData(binding.option2.text.toString())

        }
        binding.option3.setOnClickListener {
            nextData(binding.option3.text.toString())

        }
        binding.option4.setOnClickListener {
            nextData(binding.option4.text.toString())

        }
    }

    private fun nextData(selectedAns:String) {
        if(selectedAns.equals(list.get(count).ans))
        {
            score++
        }
        count++;
        if(count<list.size)
        {
            binding.questionId.setText(list.get(count).question)
            binding.option1.setText(list.get(count).option1)
            binding.option2.setText(list.get(count).option2)
            binding.option3.setText(list.get(count).option3)
            binding.option4.setText(list.get(count).option4)
        }
        else{
            val intent =  Intent(this,ResultActivity::class.java)
            intent.putExtra("result",score)
            intent.putExtra("questions",totalquestion)
            startActivity(intent)
            finish()
        }
    }
}