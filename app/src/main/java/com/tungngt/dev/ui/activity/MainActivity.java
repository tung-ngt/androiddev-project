package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.material.appbar.MaterialToolbar;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btnChannel;
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(activityMainBinding.bottomNavView, navHostFragment.getNavController());

        MaterialToolbar topAppBar = activityMainBinding.topAppBar;
        topAppBar.setNavigationOnClickListener(v -> {
            activityMainBinding.drawerLayout.open();
        });

        navHostFragment.getNavController().addOnDestinationChangedListener(
                (navController, navDestination, bundle) -> {
                    onFragmentChange(navController, navDestination, bundle);
                });
        btnChannel = findViewById(R.id.go_to_chanel);
        btnChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: clicked.");
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onFragmentChange(NavController navController, NavDestination navDestination, Bundle bundle) {
        int fragmentId = navDestination.getId();
        MaterialToolbar topAppBar = activityMainBinding.topAppBar;

        if (fragmentId == R.id.channelFrag) {
            topAppBar.setTitle(R.string.channels);
            topAppBar.getMenu().clear();
            topAppBar.inflateMenu(R.menu.channels_top_menu);
        } else if (fragmentId == R.id.peopleFrag) {
            topAppBar.setTitle(R.string.people);
            topAppBar.getMenu().clear();

        }


    }


}