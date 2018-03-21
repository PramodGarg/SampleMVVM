package com.github.pramodgarg.samplemvvm.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by pramod on 09/11/17.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
