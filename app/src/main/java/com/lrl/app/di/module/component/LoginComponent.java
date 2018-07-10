package com.lrl.app.di.module.component;

/**
 * Created by ZHP on 2017/7/6.
 */


import com.lrl.app.di.module.FragmentScope;
import com.lrl.app.di.module.LoginModule;
import com.lrl.app.ui.LoginActivity;

import dagger.Component;

@FragmentScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}
