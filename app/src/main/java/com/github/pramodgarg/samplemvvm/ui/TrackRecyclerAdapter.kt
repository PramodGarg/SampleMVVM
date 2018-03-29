/*
 * Copyright (C) 2018 Pramod Garg
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   You may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *   See the License for the specific language governing permissions and limitations under the License.
 */

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