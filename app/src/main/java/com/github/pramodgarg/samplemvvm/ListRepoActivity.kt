package com.github.pramodgarg.samplemvvm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.pramodgarg.samplemvvm.databinding.ActivityListRepoBinding
import com.github.pramodgarg.samplemvvm.model.Repo

/**
 * Created by pramod on 20/03/18.
 */
class ListRepoActivity : AppCompatActivity() {
    private val binding
            by lazy { DataBindingUtil.setContentView(this, R.layout.activity_list_repo) as ActivityListRepoBinding }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ListRepoViewModel::class.java)
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