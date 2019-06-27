package com.ibm.firstaidhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class LogIn extends AppCompatActivity {


    private static final String user = "test1", passwordt = "test1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button login = (Button) findViewById(R.id.logInButton);
        final TextView email = (TextView) findViewById(R.id.emailLogInTextBox);
        final TextView password = (TextView) findViewById(R.id.passwordLogInTextBox);
        TextView dontHaveAcc = (TextView) findViewById(R.id.dontHaveAcc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser(email.getText().toString(), password.getText().toString());
            }
        });

        dontHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, SignUp.class));
            }
        });
    }

    private void validateUser(String userEmail, String userPassword){
        if ((userEmail.equals(user) && userPassword.equals(passwordt))){
                    startActivity(new Intent(LogIn.this, MainActivity.class));
        }else{
            Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
