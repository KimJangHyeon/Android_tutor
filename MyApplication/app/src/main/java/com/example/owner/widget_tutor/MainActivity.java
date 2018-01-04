package com.example.owner.widget_tutor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = (ToggleButton)findViewById(R.id.toggleButton);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toggleButton: {
                if(((ToggleButton)v).isChecked()) {
                    /** 구독 */
                    Log.e("ToggleButton", "ischecked");

                }else {
                    /** 구독 해제 */
                    Log.e("ToggleButton", "isunchecked");
                }
                break;
            }
        }
    }
}
