package com.dineshredditsample.dagger;



import com.dineshredditsample.fragments.FragmentPopularList;
import com.dineshredditsample.helpers.ShoppingApplication;
import com.dineshredditsample.retrofit.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    void inject(ShoppingApplication application);
    Retrofit retrofit();
    void inject(FragmentPopularList fragmentPopularList);



}
