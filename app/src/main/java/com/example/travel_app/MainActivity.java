package com.example.travel_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.Account_page;
import Fragments.Favorite_page;
import Fragments.Home_page;
import Fragments.Store_page;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomnavigationview)
    BottomNavigationView mbottomnavigatorview;
    @BindView(R.id.slideviewpage)
    ViewPager slideviewpage;

    private viewPagerAdapter viewAdapter;
    private ViewPager viewPager;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPager = findViewById(R.id.slideviewpage);
        viewAdapter = new viewPagerAdapter(getSupportFragmentManager());
        viewAdapter.add(new Home_page(), "fragment_home_page");
        viewAdapter.add(new Favorite_page(), "fragment_favorite_page");
        viewAdapter.add(new Store_page(), "fragment_store_page");
        viewAdapter.add(new Account_page(), "fragment_account_page");

        viewPager.setAdapter(viewAdapter);
        mbottomnavigatorview.setSelectedItemId(R.id.home);

        mbottomnavigatorview.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.home:
                                viewPager.setCurrentItem(0);
                                break;

                            case R.id.favorite:
                                viewPager.setCurrentItem(1);
                                break;

                            case R.id.store:
                                viewPager.setCurrentItem(2);
                                break;

                            case R.id.account:
                                viewPager.setCurrentItem(3);
                                break;

                        }
                        return false;
                    }
                });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
            }
                else
                {
                    mbottomnavigatorview.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                mbottomnavigatorview.getMenu().getItem(position).setChecked(true);
                prevMenuItem = mbottomnavigatorview.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
}
    }







