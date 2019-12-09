package com.dineshredditsample.dagger;

import android.content.Context;


import com.dineshredditsample.helpers.ShoppingApplication;
import com.dineshredditsample.retrofit.API;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {




    private final ShoppingApplication aviapp;

    public AppModule(ShoppingApplication app) {
        this.aviapp = app;
    }
    //provides dependencies (return application class objects)
    @Provides
    @Singleton
    Context provideApplicationContext() {
        return aviapp;
    }

    //provides dependencies (return sharedprference objects)


    //provides dependencies (return API class objects)
    @Provides
    public API provideViduApiInterface(Retrofit retrofit) {
        return retrofit.create(API.class);
    }
}
