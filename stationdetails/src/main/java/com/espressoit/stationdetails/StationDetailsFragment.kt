package com.espressoit.stationdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.espressoit.stationdetails.databinding.FragmentStationDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StationDetailsFragment : Fragment() {

    private val viewModel by viewModels<StationDetailsViewModel>()

    private val binding: FragmentStationDetailsBinding
        get() = _binding!!

    private var _binding: FragmentStationDetailsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStationDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val sensorAdapter = SensorAdapter()

        binding.stationDetailsSensorRecycler.apply {
            adapter = sensorAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.distinctUntilChangedBy { it.sensors }.collect { viewState ->
                    sensorAdapter.submitList(viewState.sensors)
                    binding.stationDetailsSensorRecycler.scheduleLayoutAnimation()
                }
            }
        }
    }
}
