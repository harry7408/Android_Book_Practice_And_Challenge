package com.example.jpub_practice

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jpub_practice.databinding.ActivityMainBinding
import com.shashank.sony.fancytoastlib.FancyToast
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        private const val KEY_INDEX = "INDEX"
    }

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }

    private var solvedCount = 0
    private var correctCount = 0
    private var cheatCount = 3

    /**
     * StartActivityForResult 으로 대체 (deprecated 된 함수 사용 지양하기 위해)
     */
    private val checkCheating = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isCheated = result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        Log.e(TAG, "${result.data} + $isCheated")
        if (result.resultCode == Activity.RESULT_OK && isCheated) {
            quizViewModel.isCheated = true
            cheatCount--
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d(TAG, getString(R.string.onCreate_message))
        setContentView(binding.root)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0

        quizViewModel.currentIndex = currentIndex

        initQuestion()
        initCheatCount()

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
            refreshCheating()
            forbidCheat()
            initCheatCount()
        }

        binding.questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            initQuestion()
            refreshButton()
            refreshCheating()
            forbidCheat()
            initCheatCount()
        }

        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            initQuestion()
            refreshButton()
            refreshCheating()
            initCheatCount()
        }
        /**
         * startActivityForResult : Deprecated (위에 주석 달린 부분으로 대체)
         */
        binding.cheatButton.setOnClickListener { view ->
            val currentAnswer = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this, currentAnswer)
            val options = ActivityOptionsCompat.makeClipRevealAnimation(
                view, 0, 0, view.width, view.height
            )
            checkCheating.launch(intent, options)
        }
    }

    private fun initQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.text = resources.getText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        quizViewModel.checkSolved()
        solvedCount++

        val message = when {
            quizViewModel.isCheated -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_answer
            else -> R.string.false_answer
        }
        if (message == R.string.correct_answer) {
            correctCount++
        }

        FancyToast.makeText(
            this, resources.getString(message),
            FancyToast.LENGTH_LONG,
            FancyToast.DEFAULT, false
        ).show()
    }

    private fun refreshButton() {
        if (quizViewModel.currentQuestionChecked) {
            binding.trueButton.apply {
                isEnabled = false
                alpha = 0.5f
            }
            binding.falseButton.apply {
                isEnabled = false
                alpha = 0.5f
            }
        } else {
            binding.trueButton.apply {
                isEnabled = true
                alpha = 1.0f
            }
            binding.falseButton.apply {
                isEnabled = true
                alpha = 1.0f
            }
        }
    }

    private fun refreshCheating() {
        quizViewModel.isCheated = false
    }

    private fun forbidCheat() {
        if (cheatCount == 0) {
            binding.cheatButton.apply {
                isEnabled = false
                alpha = 0.5f
            }
        }
    }

    private fun initCheatCount() {
        binding.cheatCountTextView.apply {
            text = getString(R.string.cheat_count, cheatCount)
        }
    }

    private fun showUserScore() {
        if (solvedCount == quizViewModel.currentQuestionSize) {
            FancyToast.makeText(
                this,
                "점수는 ${((correctCount.toDouble() / 6.0) * 100).roundToInt()}% 입니다",
                FancyToast.LENGTH_SHORT,
                FancyToast.INFO,
                false
            ).show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, getString(R.string.onStart_message))
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, getString(R.string.onResume_message))
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, getString(R.string.onPause_message))
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, getString(R.string.onStop_message))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, getString(R.string.onDestroy_message))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, getString(R.string.onSavedInstanceSate_message))
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }
}