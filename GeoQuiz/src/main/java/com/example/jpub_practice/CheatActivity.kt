package com.example.jpub_practice


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jpub_practice.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN="CHEATED"
private const val CHANGE_FLAG = "CHANGED"
private const val BUTTON_FLAG ="BUTTON_CLICKED"
private const val TAG="CheatActivity"
class CheatActivity : AppCompatActivity() {

    private lateinit var binding :ActivityCheatBinding
    private var answerIsTrue=false


    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProvider(this)[CheatViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAnswerText()
        if (cheatViewModel.buttonFlag) {
            setAnswerShownResult()
        }
        answerIsTrue=intent.getBooleanExtra(ANSWER,false)

        cheatViewModel.answer=answerIsTrue

        binding.AnswerButton.setOnClickListener {
            binding.answerTextView.text=answerIsTrue.toString()
            cheatViewModel.buttonFlag=true
            setAnswerShownResult()
        }
    }

    private fun setAnswerShownResult() {
        val data=Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,cheatViewModel.buttonFlag)
        }
        setResult(Activity.RESULT_OK,data)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,getString(R.string.cheat_onStart_message))
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,getString(R.string.cheat_onResume_message))
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,getString(R.string.cheat_onPause_message))
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,getString(R.string.cheat_onStop_message))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,getString(R.string.cheat_onDestroy_message))
        setAnswerShownResult()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG,getString(R.string.cheat_onSavedInstanceSate_message))

    }

    private fun initAnswerText() {
        if (cheatViewModel.buttonFlag) {
            binding.answerTextView.text=cheatViewModel.answer.toString()
        } else {
            binding.answerTextView.text=""
        }
    }

    companion object {
        private const val ANSWER="Answer"
        fun newIntent(packageContext: Context, answerIsTrue: Boolean) : Intent {
            return Intent(packageContext,CheatActivity::class.java).apply {
                putExtra(ANSWER, answerIsTrue)
            }
        }
    }
}



/* MainActivity 에서 CheatActivity 가 Intent 의 extra 로 무엇을 받는지 알 필요 없다
 =>동반 객체에 인텐트를 요청하는 코드를 별도의 함수로 캡슐화 */