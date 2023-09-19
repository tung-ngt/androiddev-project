package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tungngt.dev.databinding.ListServerBinding;
import com.tungngt.dev.ui.adapter.ServerListAdapter;

import java.util.ArrayList;
import java.util.List;

import com.tungngt.dev.model.Server;
import com.tungngt.dev.ui.adapter.ServerListAdapter;
public class ServerListActivity extends AppCompatActivity {
    private ListServerBinding listServerBinding;
    ServerListAdapter serverListAdapter = new ServerListAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listServerBinding = ListServerBinding.inflate(getLayoutInflater());
        setContentView(listServerBinding.getRoot());
        List<Server> serverList = new ArrayList<>();
        ServerListAdapter serverListAdapter = new ServerListAdapter();
        serverList.add(new Server("Server 1", "123"));
        serverList.add(new Server("Server 2", "123"));
        serverList.add(new Server("Server 3", "123"));
        serverList.add(new Server("Server 4", "123"));
        serverList.add(new Server("Server 5", "123"));
        serverList.add(new Server("Server 6", "123"));
        serverList.add(new Server("Server 7", "123"));
        serverList.add(new Server("Server 8", "123"));
        serverListAdapter.differ.submitList(serverList);
        listServerBinding.rcServer.setAdapter(serverListAdapter);

    }
}
