package com.kwarta.ph.ui.postanitem;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kwarta.ph.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostAnItemFragment extends Fragment implements View.OnClickListener {

    private PostAnItemViewModel postAnItemViewModel;

    static final int PICTURE_RESULT = 1;
    String mCurrentPhotoPath;
    ContentValues values;
    private Uri file;
    Bitmap help1;

    ThumbnailUtils thumbnail;

    ImageView postitem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_postanitem, container, false);

        postitem = root.findViewById(R.id.img_postitem);
        values = new ContentValues();
        postitem.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.img_postitem:

                break;

        }
    }

}