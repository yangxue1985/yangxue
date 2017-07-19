package com.innkp.innovate;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MineFragment extends Fragment {
    private static final String TAG = MineFragment.class.getName();
    private static MineFragment fragment = new MineFragment();


    static MineFragment newInstance() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_page, container, false);
        Log.d(TAG, "MineFragment created.");
        return view;
    }

}
