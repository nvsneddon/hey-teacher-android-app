package com.example.nathaniel.heyteacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import org.json.JSONException;
import org.json.JSONObject;

public class TeacherScreen extends AppCompatActivity {

    private int roomNumber;

    public boolean checkNumberAvailability(int roomNr){
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);
        do {
            Random rand = new Random();
            roomNumber = rand.nextInt(10000);
        } while(!checkNumberAvailability(roomNumber));

    }
}
