package com.here.name.website.SnapchatUI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.here.name.website.SnapchatUI.R;

/**
 * Created by Charles on 6/26/2017.
 */

public class StoryFragment extends BaseFragment {
    public static StoryFragment create(){
        return new StoryFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_story;
    }

    @Override
    public void inOnCreateView(View root, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }
}
