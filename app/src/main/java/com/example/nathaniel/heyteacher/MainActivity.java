package com.example.nathaniel.heyteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        Intent intent = new Intent(this, TeacherScreen.class);
        intent.putExtra("name", nameField.getText());
        startActivity(intent);
    }

    public void joinRoom(View view){
        //Toast.makeText(this, "Join Room", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, StudentActivity.class));
    }
}
