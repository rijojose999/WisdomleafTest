package com.example.wisdomleaftest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wisdomleaftest.R
import com.example.wisdomleaftest.databinding.ImageItemBinding
import com.example.wisdomleaftest.models.ImageItemResponse

class ImageAdapter (private val onImageItemClicked: (ImageItemResponse) -> Unit) :
    ListAdapter<ImageItemResponse, ImageAdapter.ImageItemViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        val imageItem = getItem(position)
        imageItem?.let {
            holder.bind(it)
        }
    }

    inner class ImageItemViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageItem: ImageItemResponse) {

            binding.text1.text = "Id : ${imageItem.id}"
            binding.text2.text = "Author : ${imageItem.author}"
            binding.downloadUrl.text = "Url : ${imageItem.download_url}"
            binding.root.setOnClickListener {
                //simple animation to show when user clicked
                itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(200).withEndAction {
                    itemView.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
                }.start()

                onImageItemClicked(imageItem)
            }

            Glide.with(binding.image.context)
                .load(imageItem.download_url)
                .fitCenter()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error_image)
                .into(binding.image)
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ImageItemResponse>() {
        override fun areItemsTheSame(oldItem: ImageItemResponse, newItem: ImageItemResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageItemResponse, newItem: ImageItemResponse): Boolean {
            return oldItem == newItem
        }
    }
}