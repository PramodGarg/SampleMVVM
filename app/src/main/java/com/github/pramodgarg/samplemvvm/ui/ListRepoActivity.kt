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

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.pramodgarg.samplemvvm.R
import com.github.pramodgarg.samplemvvm.data.model.Repo
import com.github.pramodgarg.samplemvvm.databinding.ActivityListRepoBinding
import com.github.pramodgarg.samplemvvm.ui.viewmodel.ListRepoModelFactory
import com.github.pramodgarg.samplemvvm.ui.viewmodel.ListRepoViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class ListRepoActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ListRepoModelFactory

    private val binding
            by lazy { DataBindingUtil.setContentView(this, R.layout.activity_list_repo) as ActivityListRepoBinding }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this, factory).get(ListRepoViewModel::class.java)

        binding.viewModel = viewModel
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        viewModel.repo.observe(this, Observer<List<Repo>> { updateAdapterList(it) })
    }

    private fun updateAdapterList(list: List<Repo>?) {
        if (list == null) {
            binding.rvItems.adapter = TrackRecyclerAdapter(emptyList())
        } else {
            binding.rvItems.adapter = TrackRecyclerAdapter(list)
        }
    }
}