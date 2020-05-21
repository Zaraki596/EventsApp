package com.example.eventsapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.R
import com.example.eventsapp.data.model.Event
import com.example.eventsapp.databinding.ItemFeaturedBinding
import com.example.eventsapp.utils.loadImage

class FeaturedListAdapter : ListAdapter<Event, FeaturedListAdapter.FeaturedViewHolder>(
    EventDC()
) {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        ctx = parent.context
        return FeaturedViewHolder(
            ItemFeaturedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class FeaturedViewHolder(
        private val binding: ItemFeaturedBinding
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (bindingAdapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(bindingAdapterPosition)
        }

        fun bind(event: Event) {
            if (event.vertical_cover_image.isEmpty()) {
                binding.ivFeatured.loadImage(event.horizontal_cover_image)
            } else {
                binding.ivFeatured.loadImage(event.vertical_cover_image)
            }
            binding.tvCategory.text = event.category_id.name
            binding.tvPrice.text = event.min_price.let {
                if(it == 0){
                    "Free"
                }else{
                    ctx.getString(R.string.ruppee_symbol, event.min_price)
                }
            }
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