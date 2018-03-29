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

package com.github.pramodgarg.samplemvvm.data.model

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) = Glide.with(view.context).load(url).into(view)

public data class Repo(val name: String?, val open_issues_count: Int?, val stargazers_count: Int?,
                       val description: String?, val html_url: String?,
                       val owner: Owner)