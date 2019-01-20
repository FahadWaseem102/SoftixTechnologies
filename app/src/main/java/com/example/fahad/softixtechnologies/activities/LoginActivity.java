package com.example.fahad.softixtechnologies.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fahad.softixtechnologies.Helpers.DatabaseHelper;
import com.example.fahad.softixtechnologies.R;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper ;
    EditText etName, etPassword ;
    Button logInButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName = findViewById(R.id.etext_name) ;
        etPassword = findViewById(R.id.etext_password) ;
        logInButton = findViewById(R.id.login_button) ;
        databaseHelper = new DatabaseHelper(this) ;

        databaseHelper.addUser("admin", "admin");
        databaseHelper.addUser("fahad", "12345") ;

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ename = etName.getText().toString() ;
                String epassword = etPassword.getText().toString() ;
                boolean chkemailpass = databaseHelper.checkUsernamePassword(ename,epassword) ;
                if (chkemailpass==true){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class) ;
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }

}

