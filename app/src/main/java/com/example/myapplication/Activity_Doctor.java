package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Activity_Doctor extends AppCompatActivity {

    //Initialize variables
    private MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctor_main);

        //Assign variable
        meowBottomNavigation = findViewById(R.id.bottom_navigation);

        //Add Menu items to Bottom Navigation
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_person));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_search));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_chat_bubble));

        //Accessing Menu items
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                //Initialize fragment
                Fragment fragment= null;
                //Menu item selector
                switch (item.getId()){
                    case 1://When id is 1 Doctor Home Page
                        fragment = new Fragment_Doctor_Home();
                        break;
                    case 2://When id is 2 Doctor Search Page
                        fragment = new Fragment_Doctor_Search();
                        break;
                    case 3://When id is 3 Doctor Chat page
                        fragment = new Fragment_Doctor_Chat();
                        break;
                }
                //loading Fragment
                loadFragment(fragment);
            }
        });

        //Set Doctor Home Page to Default
        meowBottomNavigation.show(1,true);

        //When Select A Menu Item Do ...
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //
            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        //Replace Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }
}