package com.tungngt.dev.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.tungngt.dev.databinding.ActivityServerListBinding;
import com.tungngt.dev.ui.adapter.ServerListAdapter;

import java.util.ArrayList;
import java.util.List;

import com.tungngt.dev.model.Server;
import com.tungngt.dev.ui.bottomsheets.AddServerBottomSheet;

public class ServerListActivity extends AppCompatActivity implements AddServerBottomSheet.OnAddListener {

    private ActivityServerListBinding activityServerListBinding;
    private List<Server> serverList; // Declare serverList at the class level
    private ServerListAdapter serverListAdapter; // Declare serverListAdapter at the class level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServerListBinding = ActivityServerListBinding.inflate(getLayoutInflater());
        setContentView(activityServerListBinding.getRoot());

        // Initialize serverList and serverListAdapter
        serverList = new ArrayList<>();
        serverListAdapter = new ServerListAdapter();
        serverListAdapter.setOnServerClicked(this::connectToServer);

        // Add initial servers
        serverList.add(new Server("Server 1", "123", 0xFF78281F));
        serverList.add(new Server("Server 2", "123", 0xFFFF4E50));
        // Add more servers as needed...

        serverListAdapter.differ.submitList(serverList);

        activityServerListBinding.rcServer.setAdapter(serverListAdapter);

        activityServerListBinding.addServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddServerBottomSheet();
            }
        });

        activityServerListBinding.searchBar.setOnClickListener((view) -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onAdd(String serverName) {
        // Create a new Server object with the provided name
        Server newServer = new Server(serverName, "", 0xFF000000); // Replace with appropriate parameters

        // Add the new server to your data source (serverList)
        serverList.add(newServer);

        // Notify the adapter that the data set has changed
        serverListAdapter.notifyDataSetChanged();
    }




    private void connectToServer(Server server, ServerListAdapter.ServerListViewHolder holder) {
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
        addServerBottomSheet.show(
                getSupportFragmentManager(),
                AddServerBottomSheet.TAG
        );
    }
}
