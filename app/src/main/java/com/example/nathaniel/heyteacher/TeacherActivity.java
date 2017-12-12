package com.example.nathaniel.heyteacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Random;

public class TeacherActivity extends AppCompatActivity {

    private int roomNumber;
    private Socket socket;
    private String teacherName;

    public boolean checkNumberAvailability(int roomNr){
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        teacherName = getIntent().getStringExtra("name");

        try {
            socket = IO.socket("http://54.148.39.15:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if(!socket.connected()){
            socket.connect();
            do {
                Random rand = new Random();
                roomNumber = rand.nextInt(10000);
            } while (!checkNumberAvailability(roomNumber));
            socket.emit("teacher-room", roomNumber);
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("TeacherActivity", "It worked!!!!1");
                //Toast.makeText(TeacherActivity.this, "This has worked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy(){
        //insert disconnecting code here
        //socket.emit("Disconnect", roomNumber);
        socket.emit("teacher-disconnect", roomNumber);
        socket.disconnect();
        super.onDestroy();
    }

    public void testButton(View view){
        //socket.emit("button", "Test");
        JSONObject res = new JSONObject();
        try {
            res.put("Name", "Nathaniel");
            res.put("Message", "This is my message");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        System.out.println(res.toString());
        //socket.emit("Message", res.toString());
    }
}
