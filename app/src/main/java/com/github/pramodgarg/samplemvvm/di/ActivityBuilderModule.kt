package com.github.pramodgarg.samplemvvm.di

import com.github.pramodgarg.samplemvvm.di.annotation.PerActivity
import com.github.pramodgarg.samplemvvm.ui.ListRepoActivity
import com.github.pramodgarg.samplemvvm.ui.di.ListRepoModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ListRepoModule::class))
    abstract fun bindSignUpActivity(): ListRepoActivity
}