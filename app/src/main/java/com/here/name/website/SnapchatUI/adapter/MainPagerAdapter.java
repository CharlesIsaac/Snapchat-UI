package com.here.name.website.SnapchatUI.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.here.name.website.SnapchatUI.fragments.ChatFragment;
import com.here.name.website.SnapchatUI.fragments.Emptyfragment;
import com.here.name.website.SnapchatUI.fragments.StoryFragment;


/**
 * Created by Charles on 6/26/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter{


    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ChatFragment.create();
            case 1:
                return Emptyfragment.create();
            case 2:
                return StoryFragment.create();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
