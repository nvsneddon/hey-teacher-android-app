package com.example.nathaniel.heyteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentRoomCode extends AppCompatActivity {

    private String name;
    private EditText roomField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_room_code);
        name = getIntent().getStringExtra("name");
        roomField = (EditText) findViewById(R.id.room_number_field);
    }

    public void joinRoomWithCode(View view){
        Intent intent = new Intent(this, StudentActivity.class);
        intent.putExtra("roomCode", roomField.getText().toString());
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
