package com.github.pramodgarg.samplemvvm.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.github.pramodgarg.samplemvvm.data.remote.ApiHelper;
import com.github.pramodgarg.samplemvvm.utils.rx.SchedulerProvider;

/**
 * Created by pramod on 21/03/18.
 */

public class ListRepoModelFactory implements ViewModelProvider.Factory {

    private SchedulerProvider mSchedulerProvider;
    private ApiHelper mApiHelper;

    public ListRepoModelFactory(final SchedulerProvider schedulerProvider, final ApiHelper apiHelper) {
        mSchedulerProvider = schedulerProvider;
        mApiHelper = apiHelper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        ListRepoViewModel model = new ListRepoViewModel(mSchedulerProvider, mApiHelper);
        return (T) model;
    }
}
