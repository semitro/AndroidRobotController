package smt.vt.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import smt.vt.Settings.Settings;

import java.net.InetAddress;

/**
 * Created by semitro on 19.02.18.
 */
public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);

        ((EditText)findViewById(R.id.edit_text_ip)).setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.

                                try {
                                    Settings.setRobotIp(InetAddress.getByName(v.getText().toString()));
                                    v.setBackgroundColor(0);
                                } catch (Exception  e) {
                                    v.setBackgroundColor(Color.RED);
                                }
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });

        ((EditText)findViewById(R.id.edit_text_port)).setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.
                                try {
                                    Settings.setRobotPort(Integer.parseInt(v.getText().toString()));
                                    v.setBackgroundColor(0);
                                }catch (NumberFormatException e){
                                    v.setBackgroundColor(Color.RED);
                                }
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //substring, потому что InetAddr добавляет слэш
        ((EditText)findViewById(R.id.edit_text_ip)).setText(Settings.getRobotIp().toString().substring(1));
        ((EditText)findViewById(R.id.edit_text_port)).setText(Settings.getRobotPort().toString());
    }
}
