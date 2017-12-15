package com.example.nathaniel.heyteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField = (EditText) findViewById(R.id.editText);
    }

    public void createRoom(View view){
        //Toast.makeText(this, "Create room", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TeacherActivity.class);
        intent.putExtra("name", nameField.getText().toString());
        Log.i("MainActivity", "The name that has been put in is " + intent.getStringExtra("name"));
        startActivity(intent);
    }

    public void joinRoomAsStudent(View view){
        //Toast.makeText(this, "Join Room", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, StudentRoomCode.class);
        intent.putExtra("name", nameField.getText().toString());
        Log.i("MainActivity", "The name that has been put in is " + intent.getStringExtra("name"));
        startActivity(intent);
    }

    public void joinRoomAsTeacher(View view){

    }
}
