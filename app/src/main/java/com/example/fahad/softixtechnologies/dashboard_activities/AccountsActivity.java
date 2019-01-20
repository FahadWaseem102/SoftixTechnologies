package com.example.fahad.softixtechnologies.dashboard_activities;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fahad.softixtechnologies.Helpers.DatabaseHelper;
import com.example.fahad.softixtechnologies.R;
import com.example.fahad.softixtechnologies.adapter.AccountsAdapter;
import com.example.fahad.softixtechnologies.models.ModelAccounts;
import java.util.ArrayList;

public class AccountsActivity extends AppCompatActivity {

        private DatabaseHelper mdatabaseHelper ;
        private AccountsAdapter customAdapter;
        private ArrayList<ModelAccounts> mModelAccountsarray;
        private ListView listView ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.list_view_groups) ;
        mdatabaseHelper = new DatabaseHelper(this) ;

        try{
        showAccounts();}catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setTitle("Add new account");
                alertDialog.setMessage("Enter the account name here:");
                alertDialog.setIcon(R.drawable.ic_accounts_dialog) ;
                mModelAccountsarray = new ArrayList<>();

                final EditText input = new EditText(AccountsActivity.this);
                final EditText inputBlnc = new EditText(AccountsActivity.this);
                input.setHint("Account Name");
                input.setHintTextColor(getResources().getColor(R.color.colorHintText));
                inputBlnc.setHint("Opening Balance");
                inputBlnc.setHintTextColor(getResources().getColor(R.color.colorHintText));

                LinearLayout layout = new LinearLayout(AccountsActivity.this);
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

                        try {
                            if (input.length() != 0 && inputBlnc.length() != 0) {
                                String result = input.getText().toString();
                                String resultBlnc = inputBlnc.getText().toString();
                                addDataInAcc(result, resultBlnc);
                                Cursor data = mdatabaseHelper.getData();
                                if (data != null) {
                                    while (data.moveToNext()) {
                                        mModelAccountsarray.add(new ModelAccounts(data.getString(1), data.getString(3)));
                                    }
                                    customAdapter = new AccountsAdapter(getApplicationContext(), mModelAccountsarray);
                                    listView.setAdapter(customAdapter);
                                    registerForContextMenu(listView);
                                } else {
                                    Toast.makeText(AccountsActivity.this, "Create a new account", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(AccountsActivity.this, "Please enter both things", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception exc){
                            Toast.makeText(AccountsActivity.this, ""+ exc.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void addDataInAcc(String accName, String opBlnc) {
        boolean insertData = mdatabaseHelper.addDataInAccounts(accName, opBlnc);
        if (insertData) {
            Toast.makeText(this, "Successfully Data inserted !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
        }
    }

    public void showAccounts(){
            Cursor cursor = mdatabaseHelper.getData();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Create new Accounts", Toast.LENGTH_SHORT).show();
            }else {
                while (cursor.moveToNext()) {
                    mModelAccountsarray.add(new ModelAccounts(cursor.getString(1), cursor.getString(3)));
                }
                customAdapter = new AccountsAdapter(AccountsActivity.this, mModelAccountsarray);
                listView.setAdapter(customAdapter);

            }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.list_view_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_item :
                final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo() ;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccountsActivity.this);
                alertDialog.setTitle("Warning !");
                alertDialog.setMessage("The Account will be deleted permanently");
                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int title = info.position ;
                        mdatabaseHelper.deleteRow(mModelAccountsarray.get(title));
                        mModelAccountsarray.remove(info.position) ;
                        customAdapter.notifyDataSetChanged();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
                break;
            case R.id.edit_item :
                Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}