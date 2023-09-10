package com.example.criminalintent

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criminalintent.databinding.FragmentCrimeListBinding
import java.util.*

private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment() {

    private lateinit var binding: FragmentCrimeListBinding
    private var crimeAdapter: CrimeAdapter? = CrimeAdapter(emptyList()) { }

    private var callbacks: Callbacks? = null

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this)[CrimeListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrimeListBinding.inflate(layoutInflater)
        binding.crimeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = crimeAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got Crimes ${crimes.size}")
                    updateUI(crimes)
                }
            })
    }

    private fun updateUI(crimes: List<Crime>) {
        crimeAdapter = CrimeAdapter(crimes) {
            callbacks?.onCrimeSelected(it.id)
        }
        binding.crimeRecyclerView.adapter = crimeAdapter
        crimeAdapter?.submitList(crimes)
    }

    interface Callbacks {
        fun onCrimeSelected(crimeId:UUID)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }
}



