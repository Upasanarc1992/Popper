package com.upasana.home.popper;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by home on 1/18/2017.
 */

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(3000);

        Intent intent = new Intent(this, Level_screen.class);
        startActivity(intent);
        finish();
    }
}