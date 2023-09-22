package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;


import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ActivityMainBinding;
import com.tungngt.dev.model.Server;
import com.tungngt.dev.ui.adapter.ServerListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    public void SearchButton (View view){
        NavController navController = NavHostFragment.findNavController(getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment));
        navController.navigate(R.id.search_on);
    }

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




        // Drawer
        List<Server> serverList = new ArrayList<>();
        ServerListAdapter serverListAdapter = new ServerListAdapter();



        RecyclerView recyclerView = (RecyclerView) activityMainBinding.drawer.getHeaderView(0)
                .findViewById(R.id.rcServers);

        recyclerView.setAdapter(serverListAdapter);

        serverListAdapter.setOnServerClicked(this::connectToServer);

        serverList.add(new Server("Server 1", "123"));
        serverList.add(new Server("Server 2", "123"));
        serverList.add(new Server("Server 3", "123"));
        serverList.add(new Server("Server 4", "123"));
        serverList.add(new Server("Server 5", "123"));
        serverList.add(new Server("Server 6", "123"));
        serverList.add(new Server("Server 7", "123"));
        serverList.add(new Server("Server 8", "123"));

        serverListAdapter.differ.submitList(serverList);

        Button button = (Button) activityMainBinding.drawer.getHeaderView(0).findViewById(R.id.seeMore);
        button.setOnClickListener(v -> {
            seeAllServer();
        });
    }

    private void onFragmentChange(NavController navController, NavDestination navDestination, Bundle bundle) {
        int fragmentId = navDestination.getId();
        MaterialToolbar topAppBar = activityMainBinding.topAppBar;

        if (fragmentId == R.id.channelFrag) {
            topAppBar.setTitle(R.string.server_name);
            topAppBar.setSubtitle(R.string.server_url);
            topAppBar.getMenu().clear();
            topAppBar.inflateMenu(R.menu.channels_top_menu);
            activityMainBinding.appbar.setLiftOnScrollTargetViewId(R.id.channelList);
        } else if (fragmentId == R.id.peopleFrag) {
            topAppBar.setTitle("6" + " " + getResources().getString(R.string.online));
            topAppBar.getMenu().clear();
            activityMainBinding.appbar.setLiftOnScrollTargetViewId(R.id.rcActiveUserInServer);
        }
    }

    private void connectToServer(Server server, ServerListAdapter.ServerListViewHolder holder) {
        Intent intent = new Intent(this, AuthenticationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("server", server);
        intent.putExtras(bundle);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void seeAllServer() {
        Intent intent = new Intent(this, ServerListActivity.class);
        startActivity(intent);
    }
}