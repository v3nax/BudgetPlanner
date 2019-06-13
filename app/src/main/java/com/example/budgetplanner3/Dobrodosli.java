package com.example.budgetplanner3;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class Dobrodosli extends Activity {

        private final int SPLASH_DISPLAY_LENGTH = 2000;


        @Override
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.activity_dobrodosli);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent mainIntent = new Intent(Dobrodosli.this, MainActivity.class);
                    Dobrodosli.this.startActivity(mainIntent);
                    Dobrodosli.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }

        public void stop (View v) {
            Toast.makeText(this, "Dobrodošli!", Toast.LENGTH_SHORT).show();

        }
    }