package com.bartonpeter.talentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostContentActivity extends AppCompatActivity {

    public static final String USER_KEY = "USER_KEY";


    private EditText mContentText;
    private Button mPostButton;
    private DatabaseReference mDatabaseReference;
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);

        mContentText = findViewById(R.id.content);
        mPostButton = findViewById(R.id.postButton);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                mUsername = null;
            } else {
                mUsername = extras.getString(USER_KEY);
            }
        } else {
            mUsername = (String) savedInstanceState.getSerializable(USER_KEY);
        }

        Log.d("TalentApp","username: " + mUsername);
    }


    public void postContent(View v){

        String input = mContentText.getText().toString();

        if(!input.equals("")){
            Content content = new Content(input, mUsername);
            mDatabaseReference.child("posts").push().setValue(content);
            mContentText.setText("");
            Toast.makeText(this,"Your post is uploaded!",Toast.LENGTH_SHORT).show();
            Log.d("TalentApp","post: " + content.getPost() + "author: " + content.getAuthor());
        }
    }



}
