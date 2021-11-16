package com.espressoit.measurementstationlist

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.espressoit.airquality.model.MeasurementStation
import com.espressoit.measurementstationlist.databinding.FragmentStationListBinding
import com.espressoit.measurementstationlist.util.onQueryTextChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StationListFragment : Fragment() {

    private val viewModel by viewModels<MeasurementStationListViewModel>()

    private val binding: FragmentStationListBinding
        get() = _binding!!

    private var _binding: FragmentStationListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setHasOptionsMenu(true)
    }

    private fun setupRecyclerView() {
        val stationAdapter = StationAdapter(::onMeasurementStationItemClick)

        binding.stationRecycler.apply {
            adapter = stationAdapter
            setHasFixedSize(true)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.distinctUntilChangedBy { it.stations }.collect { viewState ->
                    stationAdapter.submitList(viewState.stations)
                }
            }
        }
    }

    private fun onMeasurementStationItemClick(model: MeasurementStation) {
        viewModel.navigateToDetailsView(model.id)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_station_list, menu)

        (menu.findItem(R.id.action_search).actionView as SearchView).onQueryTextChange { query ->
            viewModel.updateSearchQuery(query)
        }
    }
}
