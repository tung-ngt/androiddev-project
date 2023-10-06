package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.util.Log;
import android.view.View;


import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.tungngt.dev.MyApplication;
import com.tungngt.dev.R;
import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.databinding.ActivityMainBinding;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.domain.UserEntity;
import com.tungngt.dev.ui.adapter.ServerListAdapter;
import com.tungngt.dev.viewmodel.MainViewModel;
import com.tungngt.dev.viewmodel.ServerListViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;
    private AppContainer appContainer;
    private ServerListViewModel serverListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        appContainer = ((MyApplication) getApplication()).appContainer;
        mainViewModel = new ViewModelProvider(this, appContainer.getMainViewModelFactory())
                .get(MainViewModel.class);


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
        List<ServerEntity> serverList = new ArrayList<>();
        ServerListAdapter serverListAdapter = new ServerListAdapter();

        RecyclerView recyclerView = activityMainBinding.drawer.getHeaderView(0)
                .findViewById(R.id.rcServers);

        serverListViewModel = new ViewModelProvider(
                this,
                appContainer.getServerListViewModelFactory()
        ).get(ServerListViewModel.class);

        serverListAdapter.setOnServerClicked(this::connectToServer);

        serverListViewModel.loadServers().observe(this, servers -> {
            serverListAdapter.differ.submitList(new ArrayList<>(servers));
        });
        recyclerView.setAdapter(serverListAdapter);

        serverListAdapter.differ.submitList(serverList);

        Button button = (Button) activityMainBinding.drawer.getHeaderView(0).findViewById(R.id.seeMore);
        button.setOnClickListener(v -> {
            seeAllServer();
        });

        Button editUserButton = (Button) activityMainBinding.drawer.getHeaderView(0).findViewById(R.id.editUser);
        editUserButton.setOnClickListener(v -> {
            goToUserSetting();
        });

    }

    private void goToUserSetting() {
        Intent intent = new Intent(this, UserSettingActivity.class);

        Pair<View, String>[] sharedTransitionPairs = new Pair[1];
        View profile = activityMainBinding.drawer.getHeaderView(0).findViewById(R.id.shapeableImageView);
        sharedTransitionPairs[0] = new Pair<>(
                profile,
                ViewCompat.getTransitionName(profile)
        );

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedTransitionPairs
        );
        startActivity(intent, optionsCompat.toBundle());
    }

    private void onFragmentChange(NavController navController, NavDestination navDestination, Bundle bundle) {
        int fragmentId = navDestination.getId();
        MaterialToolbar topAppBar = activityMainBinding.topAppBar;

        if (fragmentId == R.id.channelFrag) {

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                ServerEntity server = (ServerEntity) extras.getSerializable("server");

                if (server != null) {
                    topAppBar.setTitle(server.getName());
                    topAppBar.setSubtitle(server.getUrl());
                }
            }

//            topAppBar.setTitle(R.string.server_name);
//            topAppBar.setSubtitle(R.string.server_url);
//            topAppBar.getMenu().clear();
//            topAppBar.inflateMenu(R.menu.channels_top_menu);
            activityMainBinding.appbar.setLiftOnScrollTargetViewId(R.id.channelList);


        } else if (fragmentId == R.id.peopleFrag) {
            topAppBar.setTitle("6" + " " + getResources().getString(R.string.online));
            topAppBar.getMenu().clear();
            activityMainBinding.appbar.setLiftOnScrollTargetViewId(R.id.rcActiveUserInServer);
        }
    }

    private void connectToServer(ServerEntity server, ServerListAdapter.ServerListViewHolder holder) {
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

    public AppContainer getAppContainer() {
        return appContainer;
    }

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }
}