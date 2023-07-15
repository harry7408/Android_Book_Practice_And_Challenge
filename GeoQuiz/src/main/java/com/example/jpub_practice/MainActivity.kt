package com.example.jpub_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable.Factory
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.jpub_practice.databinding.ActivityMainBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    companion object {
        /* 현업에서는 상수 값은 대문자로만 구성하긴 함 */
        private const val TAG="MainActivity"
    }

    private lateinit var binding : ActivityMainBinding
    private var currentIndex=0
    private val questionBank= listOf<Question>(
        Question(R.string.question1,answer=true, isChecked = false),
        Question(R.string.question2, answer = true, isChecked = false),
        Question(R.string.question3, answer = false, isChecked = false),
        Question(R.string.question4, answer = false, isChecked = false),
        Question(R.string.question5, answer = true, isChecked = false),
        Question(R.string.question6, answer = true, isChecked = false),
    )

    /* 점수 출력을 위한 변수 */
    private var solvedCount=0
    private var correctCount=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,getString(R.string.onCreate_message))
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* ViewModelProvider 를 통해 viewModel 과 연결 가능
        *  get( [] ) 을 통해 QuizViewModel 인스턴스를 반환한다
        *  장치 구성이 변경되어(회전 등) 새로운 Activity 가 생성되어도 기존 QuizViewModel 인스턴스가 반환된다*/
        val quizViewModel : QuizViewModel =
            ViewModelProvider(this)[QuizViewModel::class.java]
        Log.d(TAG,"GOT QuizViewModel $quizViewModel")

        /* 초기 문제 설정 */
        initQuestion()

        binding.trueButton.setOnClickListener {
           checkAnswer(true)
            refreshButton()
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            refreshButton()

        }

        binding.nextButton.setOnClickListener {
            gotoNextQuestion()
            refreshButton()
            showUserScore()
        }

        binding.questionTextView.setOnClickListener {
            gotoNextQuestion()
            refreshButton()
        }

        binding.previousButton.setOnClickListener {
            gotoPreviousQuestion()
            refreshButton()
        }

    }

    private fun gotoNextQuestion() {
        /* 숫자를 계속 늘리기만하면 안된다 */
        currentIndex=(currentIndex+1) % questionBank.size
        initQuestion()
    }

    private fun gotoPreviousQuestion() {
        if (currentIndex==0) {
            FancyToast.makeText(this,getString(R.string.previous_button_error)
                ,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show()
        }
        else currentIndex=(currentIndex-1)%questionBank.size
        initQuestion()
    }

    private fun initQuestion() {
        val questionTextResId=questionBank[currentIndex].textResId
        binding.questionTextView.text=resources.getText(questionTextResId)
    }

    private fun checkAnswer (userAnswer:Boolean) {
        val correctAnswer=questionBank[currentIndex].answer
        questionBank[currentIndex].isChecked=true
        solvedCount++

        if (userAnswer==correctAnswer) {
            FancyToast.makeText(this,resources.getText(R.string.correct_answer)
                ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show()
            correctCount++
        } else {
            FancyToast.makeText(this,resources.getText(R.string.false_answer)
                ,FancyToast.LENGTH_LONG,FancyToast.ERROR ,false).show()
        }
    }

    private fun refreshButton() {
        if (questionBank[currentIndex].isChecked) {
            binding.trueButton.apply {
                isEnabled=false
                alpha=0.5f
            }
            binding.falseButton.apply {
                isEnabled=false
                alpha=0.5f
            }
        } else {
            binding.trueButton.apply {
                isEnabled=true
                alpha=1.0f
            }
            binding.falseButton.apply {
                isEnabled=true
                alpha=1.0f
            }
        }
    }

    private fun showUserScore() {
        if (solvedCount==questionBank.size) {
            FancyToast.makeText(this
                , "점수는 ${((correctCount.toDouble()/6.0)*100).roundToInt()}% 입니다"
                ,FancyToast.LENGTH_SHORT, FancyToast.INFO,false).show()
        }
    }

    /* 생명주기 함수들 override */
    override fun onStart() {
        super.onStart()
        Log.d(TAG,getString(R.string.onStart_message))
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,getString(R.string.onResume_message))
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,getString(R.string.onPause_message))
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, getString(R.string.onStop_message))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,getString(R.string.onDestroy_message))
    }
}