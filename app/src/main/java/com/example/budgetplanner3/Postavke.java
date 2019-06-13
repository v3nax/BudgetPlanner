package com.example.budgetplanner3;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Postavke extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postavke);

        Switch tamnaPozadina = (Switch) findViewById(R.id.switch2);
        tamnaPozadina.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    setTheme(Configuration.UI_MODE_NIGHT_YES);
                    Toast.makeText(Postavke.this, "Odabrali ste tamnu temu.", Toast.LENGTH_SHORT).show();
                } else {
                    setTheme(Configuration.UI_MODE_NIGHT_NO);
                    Toast.makeText(Postavke.this, "Odabrali ste svijetlu temu.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        public void spremi (View v){
            Toast.makeText(Postavke.this, "Spremljeno!", Toast.LENGTH_SHORT).show();
            finish();
        }

}
