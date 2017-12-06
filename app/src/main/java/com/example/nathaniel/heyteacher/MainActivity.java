package com.example.nathaniel.heyteacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createRoom(View view){
        Toast.makeText(this, "Create room", Toast.LENGTH_SHORT).show();
    }

    public void joinRoom(View view){
        Toast.makeText(this, "Join Room", Toast.LENGTH_SHORT).show();
    }
}
