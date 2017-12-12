package com.example.nathaniel.heyteacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URISyntaxException;
import java.util.Random;

public class TeacherActivity extends AppCompatActivity {

    private int roomNumber;
    private int roomCode;
    private Socket socket;
    private String teacherName;
    private TextView text;

    public boolean checkNumberAvailability(int roomNr){
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        teacherName = getIntent().getStringExtra("name");
        text = (TextView)findViewById(R.id.room_code);
        //Log.i("Connection", teacherName);

        try {
            socket = IO.socket("http://54.148.39.15:8080");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener(){

            @Override
            public void call(Object... args){
                Log.i("Teacheractivity", "Connected");
                socket.emit("teacher-connect", teacherName);
            }
        });
        socket.on("get-roomcode", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject)args[0];
                try{
                    roomNumber = obj.getInt("roomNr");
                    roomCode = obj.getInt("roomCode");
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                //Toast.makeText(TeacherActivity.this, "Ready to go", Toast.LENGTH_SHORT).show();

                //text.setText(String.valueOf(roomCode));
                System.out.println(roomCode);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(String.valueOf(roomCode));
                    }
                });
                //text.setText(roomCode);
            }
        });
        socket.connect();
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
        System.out.println("The room number is " + roomNumber + " and the room code is " + roomCode);
        //socket.emit("Message", res.toString());
    }
}
