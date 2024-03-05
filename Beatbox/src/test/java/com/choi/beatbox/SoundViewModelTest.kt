package com.choi.beatbox


import com.choi.beatbox.sounds.BeatBox
import com.choi.beatbox.sounds.Sound
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class SoundViewModelTest {

    private lateinit var beatBox: BeatBox
    private lateinit var sound : Sound
    private lateinit var subject : SoundViewModel

    @Before
    fun setUp() {
        beatBox=mock(BeatBox::class.java)
        sound=Sound("assetPath")
        subject= SoundViewModel()
        subject.sound=sound
    }

    @Test
    fun exposesSoundNameAsTitle() {
        MatcherAssert.assertThat(subject.title, `is`(sound.name))
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        subject.onButtonClicked()
        verify(beatBox).play(sound)

    }
}