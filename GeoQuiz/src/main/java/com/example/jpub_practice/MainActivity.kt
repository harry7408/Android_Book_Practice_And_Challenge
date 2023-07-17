package com.example.jpub_practice

import android.content.Intent
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
        private const val KEY_INDEX="INDEX"
        private const val ANSWER="Answer"
    }

    private lateinit var binding : ActivityMainBinding

    /* by lazy 를 통해 늦 초기화 (사용되기 전 까지 초기화를 늦춘다) */
    private val quizViewModel : QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }

    /* 점수 출력을 위한 변수 */
    private var solvedCount=0
    private var correctCount=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        Log.d(TAG,getString(R.string.onCreate_message))
        setContentView(binding.root)
        
        /* 종료되기 전 문제를 문제 index 를 가져와 저장해둔다
        *  null 이면 첫번째 문제가 나오도록 */
        val currentIndex=savedInstanceState?.getInt(KEY_INDEX,0) ?: 0

        quizViewModel.currentIndex=currentIndex

        /* ViewModelProvider 를 통해 viewModel 과 연결 가능
        *  get( [] ) 을 통해 QuizViewModel 인스턴스를 반환한다
        *  장치 구성이 변경되어(회전 등) 새로운 Activity 가 생성되어도 기존 QuizViewModel 인스턴스가 반환된다*/
//        val quizViewModel : QuizViewModel =
//            ViewModelProvider(this)[QuizViewModel::class.java]
//        Log.d(TAG,"GOT QuizViewModel $quizViewModel")

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
            quizViewModel.moveToNext()
            initQuestion()
            refreshButton()
            showUserScore()
        }

        binding.questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            initQuestion()
            refreshButton()
        }

        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            initQuestion()
            refreshButton()
        }

        binding.cheatButton.setOnClickListener {
            val currentAnswer=quizViewModel.currentQuestionAnswer
            val intent=CheatActivity.newIntent(this,currentAnswer)
            startActivity(intent)
        }
    }

    /* 이 함수가 버튼 눌릴때 마다 호출 되어야 한다 */
    private fun initQuestion() {
        val questionTextResId=quizViewModel.currentQuestionText
        binding.questionTextView.text=resources.getText(questionTextResId)
    }

    private fun checkAnswer (userAnswer:Boolean) {
        val correctAnswer=quizViewModel.currentQuestionAnswer
        quizViewModel.checkSolved()
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
        if (quizViewModel.currentQuestionChecked) {
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
        if (solvedCount==quizViewModel.currentQuestionSize) {
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,getString(R.string.onSavedInstanceSate_message))
        outState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }
}