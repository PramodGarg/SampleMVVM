package com.github.pramodgarg.samplemvvm.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.github.pramodgarg.samplemvvm.data.remote.ApiHelper;
import com.github.pramodgarg.samplemvvm.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;


public class ListRepoModelFactory implements ViewModelProvider.Factory {

    private SchedulerProvider mSchedulerProvider;
    private ApiHelper mApiHelper;
    private CompositeDisposable mCompositeDisposable;

    public ListRepoModelFactory(final SchedulerProvider schedulerProvider, final ApiHelper apiHelper,
                                final CompositeDisposable compositeDisposable) {
        mSchedulerProvider = schedulerProvider;
        mApiHelper = apiHelper;
        mCompositeDisposable = compositeDisposable;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        ListRepoViewModel model = new ListRepoViewModel(mSchedulerProvider, mApiHelper, mCompositeDisposable);
        return (T) model;
    }
}
