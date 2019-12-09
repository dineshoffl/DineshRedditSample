package com.dineshredditsample.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dineshredditsample.R;
import com.dineshredditsample.fragments.FragmentFavourites;
import com.dineshredditsample.fragments.FragmentPopularList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tbuonomo.morphbottomnavigation.MorphBottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    MorphBottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setFragment(new FragmentPopularList());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.nav_popular){
                    setFragment(new FragmentPopularList());

                }
                if (menuItem.getItemId()==R.id.nav_fav){
                    setFragment(new FragmentFavourites());
                }


                return false;
            }
        });

    }

    protected void setFragment(Fragment fragment) {
        for (Fragment fragments : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragments).commit();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
