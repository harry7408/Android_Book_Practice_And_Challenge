package com.example.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.criminalintent.databinding.ActivityMainBinding
import java.util.*

private const val TAG="MainActivity"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val currentFragment=supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment==null) {
            val fragment = CrimeListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit()
        }
    }

    /**
     * TODO
     *  Fragment 교체가 이루어 진다
     * @param crimeId
     */
    override fun onCrimeSelected(crimeId: UUID) {
        val fragment=CrimeFragment.newInstance(crimeId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }
}