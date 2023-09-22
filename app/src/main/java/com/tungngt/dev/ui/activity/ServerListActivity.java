package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.tungngt.dev.databinding.ActivityServerListBinding;
import com.tungngt.dev.ui.adapter.ServerListAdapter;

import java.util.ArrayList;
import java.util.List;

import com.tungngt.dev.model.Server;

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

        serverList.add(new Server("Server 1", "123"));
        serverList.add(new Server("Server 2", "123"));
        serverList.add(new Server("Server 3", "123"));
        serverList.add(new Server("Server 4", "123"));
        serverList.add(new Server("Server 5", "123"));
        serverList.add(new Server("Server 6", "123"));
        serverList.add(new Server("Server 7", "123"));
        serverList.add(new Server("Server 8", "123"));
        serverListAdapter.differ.submitList(serverList);

        activityServerListBinding.rcServer.setAdapter(serverListAdapter);

    }

    private void connectToServer(Server server, ServerListAdapter.ServerListViewHolder holder) {
        Intent intent = new Intent(this, AuthenticationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("server", server);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
