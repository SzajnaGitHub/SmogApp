package com.espressoit.measurementstationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.espressoit.airquality.model.MeasurementStation
import com.espressoit.measurementstationlist.databinding.ItemStationBinding

class StationAdapter(
    private val itemClickListener: (MeasurementStation) -> Unit
) : ListAdapter<MeasurementStation, StationAdapter.StationViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val binding = ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class StationViewHolder(private val binding: ItemStationBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.invoke(getItem(position))
                }
            }
        }

        fun bind(model: MeasurementStation) {
            binding.model = model
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<MeasurementStation>() {

        override fun areItemsTheSame(oldItem: MeasurementStation, newItem: MeasurementStation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MeasurementStation, newItem: MeasurementStation): Boolean {
            return oldItem == newItem
        }
    }
}
