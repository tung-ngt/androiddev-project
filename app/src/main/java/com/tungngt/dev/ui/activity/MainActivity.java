package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Context;


import android.view.View;
import android.view.inputmethod.InputMethodManager;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.appbar.MaterialToolbar;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    private DrawerLayout mNavDrawer;
    public void enableDrawer() {
        mNavDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    public void disableDrawer() {
        mNavDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }}

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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