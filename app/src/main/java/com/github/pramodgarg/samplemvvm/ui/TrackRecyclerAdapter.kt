package com.github.pramodgarg.samplemvvm.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.pramodgarg.samplemvvm.R
import com.github.pramodgarg.samplemvvm.data.model.Repo
import com.github.pramodgarg.samplemvvm.databinding.ItemRepoBinding

class TrackRecyclerAdapter(private val mTrackList: List<Repo>) : RecyclerView.Adapter<TrackRecyclerAdapter.TrackVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        val binding: ItemRepoBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false)
        return TrackVH(binding)
    }

    override fun getItemCount() = mTrackList.size

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        holder.bind(mTrackList.get(holder.adapterPosition))
    }

    class TrackVH(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Repo) {
            binding.repo = track
            binding.executePendingBindings()
        }
    }
}