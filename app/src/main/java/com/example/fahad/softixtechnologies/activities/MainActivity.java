package com.example.fahad.softixtechnologies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fahad.softixtechnologies.Helpers.DatabaseHelper;
import com.example.fahad.softixtechnologies.R;
import com.example.fahad.softixtechnologies.dashboard_activities.AccountsActivity;
import com.example.fahad.softixtechnologies.dashboard_activities.GroupsActivity;
import com.example.fahad.softixtechnologies.dashboard_activities.UsersActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper ;
    Button buttonAccounts, buttonGroups, buttonUsers ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);
        bindViews();
    }

    public void bindViews(){
        buttonAccounts = findViewById(R.id.button_accounts) ;
        buttonGroups = findViewById(R.id.button_groups) ;
        buttonUsers = findViewById(R.id.button_users) ;
        databaseHelper = new DatabaseHelper(this) ;

        buttonAccounts.setOnClickListener(this);
        buttonGroups.setOnClickListener(this);
        buttonUsers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_accounts :
                Intent intent = new Intent(MainActivity.this, AccountsActivity.class) ;
                startActivity(intent);
                break;
            case R.id.button_groups :
                Intent intent1 = new Intent(MainActivity.this, GroupsActivity.class) ;
                startActivity(intent1);
                break;
            case R.id.button_users :
                Intent intent2 = new Intent(MainActivity.this, UsersActivity.class) ;
                startActivity(intent2);
                break;
                default:
                    Toast.makeText(this, "Please select a Field", Toast.LENGTH_SHORT).show();
                    break;
        }
    }
}
