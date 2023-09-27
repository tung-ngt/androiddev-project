package com.tungngt.dev.ui.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import androidx.core.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.databinding.FragmentChannelBinding;


import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.ui.activity.ChatActivity;
import com.tungngt.dev.ui.activity.SearchActivity;
import com.tungngt.dev.ui.adapter.ActiveUserAdapter;

import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.ui.adapter.ChannelAdapter;
import com.tungngt.dev.ui.bottomsheets.AddChannelBottomSheet;


import java.util.ArrayList;
import java.util.List;


public class ChannelFragment extends Fragment implements AddChannelBottomSheet.OnAddListener {
    private FragmentChannelBinding fragmentChannelBinding;
    private List<ChannelItem> channelItemList; // Declare channelItemList at the class level

    private ChannelAdapter channelListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChannelBinding = FragmentChannelBinding.inflate(getLayoutInflater());


        ActiveUserAdapter activeUserAdapter = new ActiveUserAdapter();

        List<ActiveUser> activeUserList = new ArrayList<>();
        activeUserList.add(new ActiveUser("Thanh Tung", "123", 0xFF78281F));
        activeUserList.add(new ActiveUser("Hoai Nhi", "123", 0xFFFF4E50));
        activeUserList.add(new ActiveUser("Viet Tung", "123", 0xFF07575B));
        activeUserList.add(new ActiveUser("Xuan Tung", "123", 0xFF727077));
        activeUserList.add(new ActiveUser("Dang Son", "123", 0xFFE99787));
        activeUserList.add(new ActiveUser("Minh Tung ", "123", 0xFF90AFC5));
        activeUserList.add(new ActiveUser("Nguyen Phan Gia Bao", "123", 0xFF76448A));
        activeUserList.add(new ActiveUser("Ta Quang Sang", "123", 0xFF943128));
        activeUserList.add(new ActiveUser("Duong", "123", 0xFF78281F));
        activeUserList.add(new ActiveUser("Minh Vu", "123", 0xFFFF4E50));
        activeUserList.add(new ActiveUser("Can Trung Hieu", "123", 0xFF07575B));
        activeUserList.add(new ActiveUser("Chu Bao Minh", "123", 0xFF727077));

        channelListAdapter= new ChannelAdapter(
                activeUserAdapter, getContext()
        );
        fragmentChannelBinding.channelList.setAdapter(channelListAdapter);

        channelListAdapter.setOnSearchBarClicked(() -> {

            Intent intent = new Intent(requireContext(), SearchActivity.class);
            startActivity(intent);

        });

        channelListAdapter.setOnActiveUserBarClicked(() -> {
            // TODO: implement click active user
        });

        channelListAdapter.setOnChannelItemClicked((channel, holder) -> {
            ChannelItemBinding channelItemBinding = holder.channelItemBinding;
            Intent intent = new Intent(getContext(), ChatActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("channelItem", channel);
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
                    getActivity(),
                    sharedTransitionPairs
            );
            startActivity(intent, optionsCompat.toBundle());
        });



        channelItemList = new ArrayList<>();
        ChannelItem searchBar = new ChannelItem("1" ,"searchbar", "123", "Thanh Tung ", " something  ", "",0xFF99B898);
        searchBar.type = ChannelItem.SEARCH_BAR;
        channelItemList.add(searchBar);

        ChannelItem activeUser = new ChannelItem("1" ,"searchbar", "123", "Thanh Tung ", " something  ", "",0xFF99B898);
        activeUser.type = ChannelItem.ACTIVE_USER_BAR;
        channelItemList.add(activeUser);

        channelItemList.add(new ChannelItem("3" ,"main", "123", "Thanh Tung: ", " something  ", "2 days", 0xFF99B898));
        channelItemList.add(new ChannelItem("4" ,"help", "123", "Viet Tung:", "  something   ", "2 days", 0xFFFECEAB));
        channelItemList.add(new ChannelItem("5" ,"resources", "123", "D. Thanh Tung:", "  something  ", "2 days", 0xFFF847C));
        channelItemList.add(new ChannelItem("6" ,"pinned", "123", "Minh Tung:", "  something  ", "2 days", 0xFFE84A5F));
        channelItemList.add(new ChannelItem("7" ,"tech", "123", "Sang:", "  something  ", "2 days", 0xFF474747));
        channelItemList.add(new ChannelItem("8" ,"music", "123", "Huy:", "  something  ", "2 days", 0xFFFF4E50));
        channelItemList.add(new ChannelItem("9" ,"movies", "123", "user1:", "  something  ", "2 days", 0xFFFE4365));
        channelItemList.add(new ChannelItem("10" ,"lounge", "123", "user2:", "  something  ", "2 days", 0xFF83AF9B));
        channelItemList.add(new ChannelItem("11" ,"gaming", "123", "user3:", "  something  ", "2 days", 0xFF07575B));
        channelItemList.add(new ChannelItem("12" ,"gaming1", "123", "user3:", "  something  ", "2 days", 0xFF727077));
        channelItemList.add(new ChannelItem("13" ,"gaming2", "123", "user3:", "  something  ", "2 days", 0xFFE99787));
        channelItemList.add(new ChannelItem("14" ,"gaming3", "123", "user3:", "  something  ", "2 days", 0xFF90AFC5));
        channelItemList.add(new ChannelItem("15" ,"gaming4", "123", "user3:", "  something  ", "2 days", 0xFF76448A));
        channelItemList.add(new ChannelItem("16" ,"gaming5", "123", "user3:", "  something  ", "2 days", 0xFF943128));
        channelItemList.add(new ChannelItem("17" ,"gaming6", "123", "user3:", "  something  ", "2 days", 0xFF37474F));
        channelItemList.add(new ChannelItem("18" ,"gaming7", "123", "user3:", "  something  ", "2 days", 0xFF6D4C41));
        channelItemList.add(new ChannelItem("19" ,"gaming8", "123", "user3:", "  something  ", "2 days", 0xFFB6443F));
        channelItemList.add(new ChannelItem("20" ,"gaming9", "123", "user3:", "  something  ", "2 days", 0xFF006C84));

        channelListAdapter.differ.submitList(channelItemList);
        activeUserAdapter.differ.submitList(activeUserList);

    }
    public void updateList(List<ChannelItem> NewList){
        channelItemList.clear();
        channelItemList.addAll(NewList);
    }
    public interface OnChannelListNeededListener {
        ArrayList<ChannelItem> onChannelListNeeded();
    }

    private OnChannelListNeededListener mListener;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View addChannelView = fragmentChannelBinding.addChannel;
        addChannelView.setOnClickListener((view) -> {
                // Open the bottom sheet when "addChannel" is clicked
                showAddChannelBottomSheet();

        });


        return fragmentChannelBinding.getRoot();
    }
    private void showAddChannelBottomSheet() {
        AddChannelBottomSheet addChannelBottomSheet = new AddChannelBottomSheet();
        addChannelBottomSheet.show(
                requireActivity().getSupportFragmentManager(),
                AddChannelBottomSheet.TAG
        );
    }
    public void onAdd(String channelName, String channel_Desc, String channel_custom_text) {
        ChannelItem newChannel = new ChannelItem("21", channelName, "123", channel_Desc, channel_custom_text, "2 days", 0xFFFECEAB); // Replace with appropriate parameters
        // Add the new server to your data source (channelItemList)
        channelItemList.add(newChannel);

        channelListAdapter.notifyDataSetChanged();
    }
}