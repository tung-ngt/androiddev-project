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

import com.tungngt.dev.databinding.ActivityServerListBinding;
import com.tungngt.dev.ui.adapter.ServerListAdapter;

import java.util.ArrayList;
import java.util.List;

import com.tungngt.dev.model.Server;
import com.tungngt.dev.ui.bottomsheets.AddServerBottomSheet;

public class ServerListActivity extends AppCompatActivity {
    private ActivityServerListBinding activityServerListBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServerListBinding = ActivityServerListBinding.inflate(getLayoutInflater());
        setContentView(activityServerListBinding.getRoot());



        List<Server> serverList = new ArrayList<>();
        ServerListAdapter serverListAdapter = new ServerListAdapter();
        serverListAdapter.setOnServerClicked(this::connectToServer);

        serverList.add(new Server("Server 1", "123", 0xFF78281F));
        serverList.add(new Server("Server 2", "123", 0xFFFF4E50));
        serverList.add(new Server("Server 3", "123", 0xFF07575B));
        serverList.add(new Server("Server 4", "123", 0xFF727077));
        serverList.add(new Server("Server 5", "123", 0xFFE99787));
        serverList.add(new Server("Server 6", "123", 0xFF90AFC5));
        serverList.add(new Server("Server 7", "123", 0xFF76448A));
        serverList.add(new Server("Server 8", "123", 0xFF943128));
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

    private void connectToServer(Server server, ServerListAdapter.ServerListViewHolder holder) {
        Intent intent = new Intent(this, AuthenticationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("server", server);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showAddServerBottomSheet() {
        AddServerBottomSheet addServerBottomSheet = new AddServerBottomSheet();
        addServerBottomSheet.show(
                getSupportFragmentManager(),
                AddServerBottomSheet.TAG
        );
    }
}
