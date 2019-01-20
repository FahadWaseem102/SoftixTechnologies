package com.example.fahad.softixtechnologies.dashboard_activities;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.fahad.softixtechnologies.R;

import java.util.ArrayList;

public class GroupsActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter ;
    ArrayList<String> addArray = new ArrayList<String>() ;
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.list_view_groups) ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setTitle("Add new Group");
                alertDialog.setMessage("Enter the group name here:");
                alertDialog.setIcon(R.drawable.ic_groups_dialog) ;
                adapter = new ArrayAdapter<String>(GroupsActivity.this, android.R.layout.simple_expandable_list_item_1,addArray) ;
                listView.setAdapter(adapter);

                final EditText input = new EditText(GroupsActivity.this);
                final EditText inputBlnc = new EditText(GroupsActivity.this);
                input.setHint("Group Name");
                input.setHintTextColor(getResources().getColor(R.color.colorHintText));
                inputBlnc.setHint("Opening Balance");
                inputBlnc.setHintTextColor(getResources().getColor(R.color.colorHintText));

                LinearLayout layout = new LinearLayout(GroupsActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT) ;
                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT) ;
                lp1.setMargins(20,0,20,30);
                lp2.setMargins(20,0,20,20);
                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());

                input.setWidth(px);
                inputBlnc.setWidth(px);

                layout.addView(input, lp1);
                layout.addView(inputBlnc, lp2);

                alertDialog.setView(layout);
                alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result = input.getText().toString();
                        if(input.length() > 0)
                        {
                            adapter.add(result);
                        }
                        String resultBlnc = inputBlnc.getText().toString();
                        if(inputBlnc.length() > 0)
                        {
                            adapter.add(resultBlnc);
                        }

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialogue = alertDialog.create() ;
                alertDialogue.show();
            }
        });
    }
}