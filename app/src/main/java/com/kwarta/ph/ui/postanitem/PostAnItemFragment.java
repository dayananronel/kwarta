package com.kwarta.ph.ui.postanitem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kwarta.ph.R;

public class PostAnItemFragment extends Fragment {

    private PostAnItemViewModel postAnItemViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        postAnItemViewModel =
                ViewModelProviders.of(this).get(PostAnItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_postanitem, container, false);
        return root;
    }
}