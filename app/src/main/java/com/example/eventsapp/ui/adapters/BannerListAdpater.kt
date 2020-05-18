package com.example.eventsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eventsapp.data.model.BannerObject
import com.example.eventsapp.databinding.ItemBannerBinding
import com.example.eventsapp.utils.loadImage

class BannerListAdpater() :
    ListAdapter<BannerObject, BannerListAdpater.NormalViewHolder>(BannerDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NormalViewHolder(
        ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NormalViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class NormalViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (bindingAdapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(bindingAdapterPosition)
        }

        fun bind(banner: BannerObject) {
            binding.ivBanner.loadImage(banner.vertical_cover_image)
        }
    }


    private class BannerDC : DiffUtil.ItemCallback<BannerObject>() {
        override fun areItemsTheSame(
            oldItem: BannerObject,
            newItem: BannerObject
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: BannerObject,
            newItem: BannerObject
        ): Boolean = oldItem == newItem
    }
}
