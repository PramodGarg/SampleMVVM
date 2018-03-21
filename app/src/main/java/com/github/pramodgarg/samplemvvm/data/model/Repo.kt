package com.github.pramodgarg.samplemvvm.data.model

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) = Glide.with(view.context).load(url).into(view)

public data class Repo(val name: String?, val open_issues_count: Int?, val stargazers_count: Int?,
                       val description: String?, val html_url: String?,
                       val owner: Owner)