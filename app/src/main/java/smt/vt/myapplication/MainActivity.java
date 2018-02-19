package smt.vt.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import smt.vt.Settings.Settings;

import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;

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
        ((SeekBar)findViewById(R.id.seekBarSpeed)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sendCommand(Settings.getSpeedControlMessage(i+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void sendCommand(final byte[] fill){

        Log.i("Sending command:", Arrays.toString(fill));
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

    public void moveForwardClick(View view) {
        sendCommand(Settings.getMoveForwardMessage());
    }

    public void moveLeftClick(View view) {
        sendCommand(Settings.getMoveLeftMessage());
    }

    public void moveRightClick(View view) {
        sendCommand(Settings.getMoveRightMessage());
    }

    public void stopClick(View view) {
        sendCommand(Settings.getMoveStopMessage());
    }

    public void moveBackClick(View view) {
        sendCommand(Settings.getMoveBackMessage());
    }

    public void moveForwardLeftClick(View view) {
        sendCommand(Settings.getMoveForwardLeftMessage());
    }

    public void moveForwardRightClick(View view) {
        sendCommand(Settings.getMoveForwardRightMessage());
    }

    public void moveBackLeftClick(View view) {
        sendCommand(Settings.getMoveBackLeftMessage());
    }

    public void moveBackRightClick(View view) {
        sendCommand(Settings.getMoveBackRightMessage());
    }
}
