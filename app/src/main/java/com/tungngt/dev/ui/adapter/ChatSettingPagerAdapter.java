package com.tungngt.dev.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tungngt.dev.ui.fragment.ChannelInfoFragment;
import com.tungngt.dev.ui.fragment.ChannelPeopleFragment;
import com.tungngt.dev.ui.fragment.PeopleFragment;

public class ChatSettingPagerAdapter extends FragmentStateAdapter {
    public static int NUM_PAGES = 2;
    public ChatSettingPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChannelPeopleFragment();

            case 1:
                return new ChannelInfoFragment();
            default:
                return new PeopleFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
