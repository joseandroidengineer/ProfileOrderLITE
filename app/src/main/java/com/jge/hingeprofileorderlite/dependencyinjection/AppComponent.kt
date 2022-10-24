package com.jge.hingeprofileorderlite.dependencyinjection

import com.jge.hingeprofileorderlite.views.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}