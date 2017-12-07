package com.example.nathaniel.heyteacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.Random;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

public class TeacherScreen extends AppCompatActivity {

    private int roomNumber;
    private Socket socket;

    public boolean checkNumberAvailability(int roomNr){
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);

        try {
            socket = IO.socket("http://54.148.39.15:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.connect();


        socket.on("test", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String response = (String) args[0];
                Toast.makeText(TeacherScreen.this, response, Toast.LENGTH_SHORT).show();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(socket.connected()){
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show();
        }
        do {
            Random rand = new Random();
            roomNumber = rand.nextInt(10000);
        } while(!checkNumberAvailability(roomNumber));

        socket.emit("room", roomNumber);
    }

    @Override
    protected void onDestroy(){
        //insert disconnecting code here
        socket.disconnect();
        super.onDestroy();
    }

    public void testButton(View view){
        socket.emit("button", "Test");
        JSONObject res = new JSONObject();
        try {
            res.put("Name", "Nathaniel");
            res.put("Message", "This is my message");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        socket.emit("Message", res.toString());
    }
}
