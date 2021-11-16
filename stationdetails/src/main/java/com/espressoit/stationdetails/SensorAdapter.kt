package com.espressoit.stationdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.espressoit.airquality.model.Sensor
import com.espressoit.stationdetails.databinding.ItemSensorBinding

class SensorAdapter : ListAdapter<Sensor, SensorAdapter.SensorViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val binding = ItemSensorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SensorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class SensorViewHolder(private val binding: ItemSensorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Sensor) {
            binding.model = model
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Sensor>() {

        override fun areItemsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Sensor, newItem: Sensor): Boolean {
            return oldItem == newItem
        }
    }
}
