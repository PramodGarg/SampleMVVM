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