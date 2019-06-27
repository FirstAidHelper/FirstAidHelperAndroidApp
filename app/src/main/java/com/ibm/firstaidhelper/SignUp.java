package com.ibm.firstaidhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    private static final String user = "test", passwordt = "test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        TextView alreadyAccount = (TextView) findViewById(R.id.alreadyAccountTextView);
        final EditText emailSignUp = (EditText) findViewById(R.id.emailSignUpTextBox);
        final EditText passwordSignUp = (EditText) findViewById(R.id.passwordSignUpTextBox);
        Button signUp = (Button) findViewById(R.id.signUpButton);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser(emailSignUp.getText().toString(), passwordSignUp.getText().toString());
            }
        });

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, LogIn.class));
            }
        });

    }

    private void validateUser(String userEmail, String userPassword){
        if ((userEmail.equals(user) && userPassword.equals(passwordt))){
            startActivity(new Intent(SignUp.this, UserProfileScrollingActivity.class));
        }
    }
}
