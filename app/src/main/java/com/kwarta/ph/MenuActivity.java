package com.kwarta.ph;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.kwarta.ph.util.SharedPref;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private NavController navController;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        type = SharedPref.getUserType(getApplicationContext());
        Toast.makeText(this, type, Toast.LENGTH_SHORT).show();


        init();
        listener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    private void init(){

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        MenuItem homeauc = navigationView.getMenu().getItem(1);
        MenuItem homebidder = navigationView.getMenu().getItem(0);
        MenuItem postitem = navigationView.getMenu().getItem(2);
        MenuItem history = navigationView.getMenu().getItem(3);
        MenuItem bidhistory = navigationView.getMenu().getItem(4);
        MenuItem favorites = navigationView.getMenu().getItem(5);
        MenuItem start ;

        if(type.equals("bidder")){


            postitem.setVisible(false);
            history.setVisible(false);
            homeauc.setVisible(false);

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_homebidder,R.id.nav_bidhistory, R.id.nav_favorites, R.id.nav_notifications,
                    R.id.nav_settings,R.id.nav_logout)
                    .setDrawerLayout(drawer)
                    .build();
        }else{

            bidhistory.setVisible(false);
            favorites.setVisible(false);
            homebidder.setVisible(false);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home,R.id.nav_postanitem, R.id.nav_history, R.id.nav_notifications,
                    R.id.nav_settings,R.id.nav_logout)
                    .setDrawerLayout(drawer)
                    .build();
        }


        setSupportActionBar(toolbar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.mobile_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        if (type.equals("bidder")) {
            navGraph.setStartDestination(R.id.nav_homebidder);
        } else {
            navGraph.setStartDestination(R.id.nav_home);
        }
        navController.setGraph(navGraph);


    }
    private void listener(){
        //fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.nav_logout:
//                new AlertDialog.Builder(getApplicationContext())
//                        .setMessage("Do you want to logout?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();
//
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        })
//                        .setCancelable(false)
//                        .show();
//
//                break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MenuActivity.this)
                .setTitle("Logout")
                .setMessage("Do you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }
}
