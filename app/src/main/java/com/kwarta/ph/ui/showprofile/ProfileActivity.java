package com.kwarta.ph.ui.showprofile;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.kwarta.ph.R;
import com.kwarta.ph.model.BiddersDataList;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    private TextView profilename;
    private EditText writeFeedback;
    BiddersDataList biddersDataList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilename = findViewById(R.id.profile_name);
        writeFeedback = findViewById(R.id.et_profile_writefeedback);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        biddersDataList = new Gson().fromJson(getIntent().getStringExtra("profile"),BiddersDataList.class);

        writeFeedback.setHint("Write feedback to "+biddersDataList.getFname()+" "+biddersDataList.getLname());
        profilename.setText(biddersDataList.getFname()+" "+biddersDataList.getLname());
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
