package com.tungngt.dev.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.MyApplication;
import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.databinding.ActivitySearchBinding;
import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.ui.adapter.ChannelSearchAdapter;
import com.tungngt.dev.ui.adapter.ServerListAdapter;
import com.tungngt.dev.viewmodel.SearchViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    public static final int SERVER = 0;
    public static final int CHANNEL = 1;
    private static final String TAG = "SearchActivity";

    private ActivitySearchBinding activitySearchBinding;
    private AppContainer appContainer;
    private SearchViewModel searchViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(activitySearchBinding.getRoot());

        appContainer = ((MyApplication) getApplication()).appContainer;
        searchViewModel = new ViewModelProvider(this, appContainer.getSearchViewModelFactory())
                .get(SearchViewModel.class);


        activitySearchBinding.topAppBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });


        processIntent();


    }

    private void processIntent() {
        Bundle extras = getIntent().getExtras();
        try {
            if (extras == null) throw new Exception("No intent");

            switch (extras.getInt("searchType")) {
                case SERVER:
                    setupServerSearch();
                    break;
                case CHANNEL:
                    setupChannelSearch();
                    break;
                default:
                    throw new Exception("No intent");
            }
        } catch (Exception e) {
            // TODO: implement back if not have intent
            Log.i(TAG, "processIntent: no intent");
        }
    }

    private void setupServerSearch() {
        ServerListAdapter serverListAdapter = new ServerListAdapter();
        serverListAdapter.setOnServerClicked(this::connectToServer);
        activitySearchBinding.searchResult.setAdapter(serverListAdapter);

        searchViewModel.getFilterServerList().observe(this, serverEntities -> {
            serverListAdapter.differ.submitList(new ArrayList<>(serverEntities));
        });

        searchViewModel.getServerList().observe(this, serverEntities -> {
            searchViewModel.onSeverSearch(searchViewModel.getSearchText());
        });

        activitySearchBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchViewModel.onSeverSearch(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        activitySearchBinding.clearButton.setOnClickListener(v -> {
            activitySearchBinding.etSearch.setText("");
        });


    }

    private void connectToServer(ServerEntity server, ServerListAdapter.ServerListViewHolder holder) {
        appContainer.setCurrentServer(server);
        searchViewModel.connectToServer(server);

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

    private void setupChannelSearch() {
        ChannelSearchAdapter channelSearchAdapter = new ChannelSearchAdapter();
        channelSearchAdapter.setOnChannelClicked(this::joinChannel);
        activitySearchBinding.searchResult.setAdapter(channelSearchAdapter);

        searchViewModel.setCurrentSever(appContainer.getCurrentServer());

        searchViewModel.getFilterChannelList().observe(this, channels -> {
            channelSearchAdapter.differ.submitList(new ArrayList<>(channels));
        });

        searchViewModel.getChannelList().observe(this, channels -> {
            searchViewModel.onChannelSearch(searchViewModel.getSearchText());
        });

        activitySearchBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchViewModel.onChannelSearch(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        activitySearchBinding.clearButton.setOnClickListener(v -> {
            activitySearchBinding.etSearch.setText("");
        });

    }

    private void joinChannel(ChannelEntity channel, ChannelSearchAdapter.ChannelViewHolder holder) {
        ChannelItemBinding channelItemBinding = holder.channelItemBinding;
        Intent intent = new Intent(this, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("channel", channel);
        intent.putExtras(bundle);

        Pair<View, String>[] sharedTransitionPairs = new Pair[2];

        sharedTransitionPairs[0] = new Pair<>(
                channelItemBinding.channelTitle,
                ViewCompat.getTransitionName(channelItemBinding.channelTitle)
        );

        sharedTransitionPairs[1] = new Pair<>(
                channelItemBinding.channelImage,
                ViewCompat.getTransitionName(channelItemBinding.channelImage)
        );

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedTransitionPairs
        );
        startActivity(intent, optionsCompat.toBundle());
    }
}
