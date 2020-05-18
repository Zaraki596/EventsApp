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
import com.example.eventsapp.databinding.ItemNormalBinding
import com.example.eventsapp.utils.loadImage

class NormalListAdpater() :
    ListAdapter<Event, NormalListAdpater.NormalViewHolder>(EventDC()) {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalViewHolder {
        ctx = parent.context
        return NormalViewHolder(
            ItemNormalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NormalViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class NormalViewHolder(
        private val binding: ItemNormalBinding
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
                binding.ivDisplay.loadImage(event.horizontal_cover_image)
            } else {
                binding.ivDisplay.loadImage(event.vertical_cover_image)
            }
            binding.tvPrice.text = ctx.getString(R.string.ruppee_symbol, event.min_price)
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
