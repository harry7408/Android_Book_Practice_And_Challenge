package com.example.jpub_practice

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.coroutines.coroutineContext

private const val TAG="QuizViewModel"
class QuizViewModel : ViewModel() {
    init {
        /* 초기화 블럭 내부는 인스턴스 생성 시 자동으로 실행 된다 */
        Log.d(TAG, "ViewModel 초기화")
    }

    /* 인스턴스 소멸되기 전에 호출되는 함수 -> 리소스 반환할 게 있거나 하면 여기서 처리 */
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel 파괴")
    }
}