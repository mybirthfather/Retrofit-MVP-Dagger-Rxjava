package com.lrl.app.di.module.component;


import com.lrl.app.di.module.FragmentScope;
import com.lrl.app.di.module.LogModule;
import com.lrl.app.utils.AppCrashHandlerUtil;
import dagger.Component;
@FragmentScope
@Component(modules = {LogModule.class },dependencies = AppComponent.class)
public interface LogComponent {
    void inject(AppCrashHandlerUtil appCrashHandlerUtil);
}
