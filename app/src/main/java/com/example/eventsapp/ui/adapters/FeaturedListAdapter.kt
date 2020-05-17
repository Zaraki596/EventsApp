package com.example.eventsapp.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.eventsapp.data.model.Event
import com.example.eventsapp.databinding.ItemFeaturedBinding
import com.example.eventsapp.utils.loadImage

class FeaturedListAdapter : ListAdapter<Event, FeaturedListAdapter.FeaturedViewHolder>(
    EventDC()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FeaturedViewHolder(
        ItemFeaturedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class FeaturedViewHolder(
        private val binding: ItemFeaturedBinding
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
        }

        fun bind(event: Event) {
            if (event.vertical_cover_image.isEmpty()) {
                binding.ivFeatured.loadImage(event.horizontal_cover_image)
            } else {
                binding.ivFeatured.loadImage(event.vertical_cover_image)
            }
            binding.tvCategory.text = event.category_id.name
            binding.tvPrice.text = "\u20B9 ${event.min_price}"
            binding.tvTitle.text = event.name
            binding.tvTiming.text = event.venue_date_string
            binding.tvLocation.text = event.venue_name
        }
    }

    private class EventDC : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(
            oldItem: Event,
            newItem: Event
        ): Boolean = oldItem.slug == newItem.slug

        override fun areContentsTheSame(
            oldItem: Event,
            newItem: Event
        ): Boolean = oldItem == newItem
    }
}