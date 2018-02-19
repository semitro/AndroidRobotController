package smt.vt.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    private UDPClient client;


    private AutoCompleteTextView ipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            client = new UDPClient(InetAddress.getByName("192.168.0.112"),5445);
        }catch (SocketException | UnknownHostException e){
            Toast.makeText(this, e.getMessage(), 800).show();
            Log.e("ex", e.getMessage());
        }


        final Button buttonF = (Button)findViewById(R.id.buttonF);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand('w');
            }
        });

        final Button buttonB = (Button)findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand('s');
            }
        });

        final Button buttonL = (Button)findViewById(R.id.buttonL);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand('a');
            }
        });

        final Button buttonR = (Button)findViewById(R.id.buttonR);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand('d');
            }
        });
        final Button buttonStop = (Button)findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand('x');
            }
        });
        final Button buttonBoost = (Button)findViewById(R.id.buttonBoost);
        buttonBoost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand('u');
            }
        });
    }


    private void sendCommand(final char fill){

        Log.i("Sending command:", "" + fill);
            // Network in the other thread is required by the вos.
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        client.sendMsg(new byte[]{(byte)fill});
                    } catch (IOException e) {
                        Log.e("Exception", e.toString());
                    }
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public void settingsClick(MenuItem item) {
        // Prepare the settings activity
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
    }
}
