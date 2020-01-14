package com.kwarta.ph.ui.postanitem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostAnItemViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PostAnItemViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is post an item fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}