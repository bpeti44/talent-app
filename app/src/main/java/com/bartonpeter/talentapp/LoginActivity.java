package com.bartonpeter.talentapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_NAME_KEY = "username";


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mLoginButton;
    private Button mRegisterButton;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginButton =  findViewById(R.id.loginButton);
        mRegisterButton =  findViewById(R.id.registerButton);

        mEmailView = findViewById(R.id.email_login);
        mPasswordView = findViewById(R.id.pass_login);


        mAuth = FirebaseAuth.getInstance();


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


    }



    public void signInExistingUser(View v){
        attemptLogin();
    }

    private void attemptLogin() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (email.equals("") || password.equals("")) {
            return;
        }

        Toast.makeText(this, "Login in progress", Toast.LENGTH_SHORT).show();

        // TODO: Use FirebaseAuth to sign in with email & password
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("FlashChat", "signInWithEmail onComplete()" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.d("FlashChat", "signInWithEmail failed: " + task.getException());
                    showErrorDialoge("There was a problem signing in..");

                } else {
                    Log.d("TalentApp","Fasza");
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(myIntent);
                }

            }
        });
    }

    private void showErrorDialoge(String message){
        new AlertDialog.Builder(this)
                .setTitle("Ooops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void registerNewUser(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
