package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.tungngt.dev.MyApplication;
import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.databinding.ActivityServerListBinding;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.ui.adapter.ServerListAdapter;

import java.util.ArrayList;

import com.tungngt.dev.ui.bottomsheets.AddServerBottomSheet;
import com.tungngt.dev.viewmodel.ServerListViewModel;

public class ServerListActivity extends AppCompatActivity {
    private static final String TAG = "ServerListActivity";
    private ActivityServerListBinding activityServerListBinding;
    private AppContainer appContainer;
    private ServerListViewModel serverListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServerListBinding = ActivityServerListBinding.inflate(getLayoutInflater());
        setContentView(activityServerListBinding.getRoot());

        appContainer =  ((MyApplication) getApplication()).appContainer;

        serverListViewModel = new ViewModelProvider(
                this,
                appContainer.getServerListViewModelFactory()
        ).get(ServerListViewModel.class);


        ServerListAdapter serverListAdapter = new ServerListAdapter();
        serverListAdapter.setOnServerClicked(this::connectToServer);

        serverListViewModel.loadServers().observe(this, servers -> {
            serverListAdapter.differ.submitList(new ArrayList<>(servers));
        });

        activityServerListBinding.rcServer.setAdapter(serverListAdapter);

        activityServerListBinding.addServer.setOnClickListener((view) -> {
                showAddServerBottomSheet();
        });

        activityServerListBinding.searchBar.setOnClickListener((view) -> {
            Intent intent = new Intent(this, SearchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("searchType", SearchActivity.SERVER);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    public void onAdd(String url, Integer port, String name, Integer color) {
        serverListViewModel.addServer(url, port, name, color);
    }

    private void connectToServer(ServerEntity server, ServerListAdapter.ServerListViewHolder holder) {
        appContainer.setCurrentServer(server);
        serverListViewModel.connectToServer(server);

        Intent intent = new Intent(this, AuthenticationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("server", server);
        intent.putExtras(bundle);

        Pair<View, String>[] sharedTransitionPairs = new Pair[1];

        sharedTransitionPairs[0] = new Pair<>(
                holder.serverItemBinding.serverName,
                ViewCompat.getTransitionName(holder.serverItemBinding.serverName)
        );

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedTransitionPairs
        );
        startActivity(intent, optionsCompat.toBundle());
    }

    private void showAddServerBottomSheet() {
        AddServerBottomSheet addServerBottomSheet = new AddServerBottomSheet();
        addServerBottomSheet.setOnAddListener(this::onAdd);
        addServerBottomSheet.show(
                getSupportFragmentManager(),
                AddServerBottomSheet.TAG
        );
    }
}
