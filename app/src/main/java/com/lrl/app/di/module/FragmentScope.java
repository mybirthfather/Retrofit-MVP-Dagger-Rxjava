package com.lrl.app.di.module;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ZHP on 2017/6/24.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
