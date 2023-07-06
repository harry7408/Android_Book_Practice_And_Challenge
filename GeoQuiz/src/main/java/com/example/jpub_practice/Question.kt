package com.example.jpub_practice

import androidx.annotation.StringRes

data class Question(
    /* StringRes 어노테이션 : 생성자에서 유효한 문자열 리소스 ID를 제공하는지
    *                       컴파일 시점에서 Lint 가 검사 + 코드의 이해 높여줌 */
    @StringRes
    val textResId: Int,
    val answer: Boolean,
    var isChecked:Boolean,
)
