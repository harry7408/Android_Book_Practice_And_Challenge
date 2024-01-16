package com.choi.beatbox.sounds

import android.content.res.AssetManager

private const val TAG = "BeatBox"
private const val SOUND_FOLDER = "sample_sounds"

class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>

    init {
        // 초기화 작업
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {

        val soundNames: Array<String>

        try {
            soundNames = assets.list(SOUND_FOLDER)!!
        } catch (e: Exception) {
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUND_FOLDER/$filename"
            // 파일 이름 조정 하는 부분
            val sound = Sound(assetPath)
            sounds.add(sound)
        }
        return sounds
    }
}