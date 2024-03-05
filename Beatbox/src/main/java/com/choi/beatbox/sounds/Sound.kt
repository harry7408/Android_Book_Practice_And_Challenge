package com.choi.beatbox.sounds

private const val WAV = ".wav"


class Sound(val assetPath: String, var soundId: Int? = null) {
    // 파일 이름 가져오기 (뒤에 확장자 제거)
    val name=assetPath.split("/").last().removeSuffix(WAV)
}