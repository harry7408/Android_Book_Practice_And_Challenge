package com.example.jpub_practice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import kotlin.coroutines.coroutineContext

private const val TAG="QuizViewModel"
class QuizViewModel(application: Application) : AndroidViewModel(application) {
    /** 초기화 여부 확인 하는 방법
     init {
         초기화 블럭 내부는 인스턴스 생성 시 자동으로 실행 된다
        Log.d(TAG, "ViewModel 초기화")
    }

    인스턴스 소멸되기 전에 호출되는 함수 -> 리소스 반환할 게 있거나 하면 여기서 처리
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel 파괴")
    }
    */

    var currentIndex=0
    var isCheated=false

    private val questionBank= listOf<Question>(
        Question(R.string.question1,answer=true, isChecked = false),
        Question(R.string.question2, answer = true, isChecked = false),
        Question(R.string.question3, answer = false, isChecked = false),
        Question(R.string.question4, answer = false, isChecked = false),
        Question(R.string.question5, answer = true, isChecked = false),
        Question(R.string.question6, answer = true, isChecked = false),
    )

     val currentQuestionAnswer : Boolean
        get()=questionBank[currentIndex].answer

     val currentQuestionText : Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionChecked : Boolean
        get()= questionBank[currentIndex].isChecked

    val currentQuestionSize :Int
        get()=questionBank.size


    fun moveToNext() {
        currentIndex=(currentIndex+1) % questionBank.size
    }

    fun moveToPrevious() {
        if (currentIndex==0) {
            FancyToast.makeText(getApplication(),"첫번째 문제입니다"
                , FancyToast.LENGTH_SHORT, FancyToast.ERROR,false).show()
        }
        else currentIndex=(currentIndex-1)%questionBank.size
    }

    fun checkSolved() {
        questionBank[currentIndex].isChecked=true
    }
}