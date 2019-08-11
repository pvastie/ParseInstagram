package com.codepath.parseinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";


    private EditText etUsername1;
    private EditText etEmail;
    private EditText etPassword1;
    private Button btnSignUp;
    private Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );


        etUsername1 = findViewById( R.id.etUsername1 );
        etEmail = findViewById( R.id.etEmail );
        etPassword1 = findViewById( R.id.etPassword1 );
        btnSignUp = findViewById( R.id.btnSignUp );
        btnBack = findViewById( R.id.btnBack );


        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( SignupActivity.this, LoginActivity.class );
                startActivity( i );
            }
        } );

        btnSignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername1.getText().toString();
                String password = etPassword1.getText().toString();
                String email = etEmail.getText().toString();



                ParseUser user = new ParseUser();


                user.setUsername( username );
                user.setEmail( email );
                user.setPassword( password );

                user.signUpInBackground( new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.e( TAG, "Error while saving" );
                            e.printStackTrace();
                            return;
                        }

                        Log.d( TAG, "Success!" );

                        etEmail.setText( "" );
                        etPassword1.setText( "" );
                        etUsername1.setText( "" );

                    }
                } );
            }
        } );
    }

}