package smt.vt.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.SocketException;

public class MainActivity extends AppCompatActivity {

    private UDPClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            client = new UDPClient();
        }catch (SocketException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("ex", e.getMessage());
        }


        final Button buttonF = (Button)findViewById(R.id.buttonF);
        buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand(new byte[]{'@','m','w','@'});
            }
        });

        final Button buttonB = (Button)findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand(new byte[]{'@','m','x','@'});
            }
        });

        final Button buttonL = (Button)findViewById(R.id.buttonL);
//        buttonL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendCommand('a');
//            }
//        });

        final Button buttonR = (Button)findViewById(R.id.buttonR);
//        buttonR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendCommand('d');
//            }
//        });
        final Button buttonStop = (Button)findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCommand(new byte[]{'@','m','s','@'});
            }
        });
        final Button buttonBoost = (Button)findViewById(R.id.buttonBoost);
        buttonBoost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void sendCommand(final byte[] fill){

        Log.i("Sending command:", "" + fill);
            // Network in the other thread is required by the вos.
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        client.sendMsg(fill);
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
