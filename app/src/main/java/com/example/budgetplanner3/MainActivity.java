package com.example.budgetplanner3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Baza baza;

    private Spinner prihodK;
    private EditText prihodI;

    private Spinner troskoviK;
    private EditText troskoviI;

    private ListView prihodiLista;
    private ListView troskoviLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //TABOVI:
        ViewPager vp = findViewById(R.id.viewpager);
        TabLayout tl = findViewById(R.id.sliding_tabs);

        //ADAPTER FRAGMENATA:
        StanjeAdapter stanjeAdapter = new StanjeAdapter(getSupportFragmentManager());

        vp.setAdapter(stanjeAdapter);
        tl.setupWithViewPager(vp);

        baza = new Baza(this);
    }

    // MOJ MENU: ------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moj_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.postavke:
                Intent myIntent = new Intent(MainActivity.this, Postavke.class);
                MainActivity.this.startActivity(myIntent);
                Toast.makeText(this, "Dobrodošli u postavke.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.zatvori:
                AlertDialog.Builder adP = new AlertDialog.Builder(
                      this );
                adP.setIcon(R.drawable.racuni);
                adP.setTitle("Želite li izaći iz aplikacije?");
                adP.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(MainActivity.this,
                                "Hvala na korištenju aplikacije!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                adP.setNegativeButton("NE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                adP.show();
                break;
        }
        return true;
    }


    // GUMBI PRIHODA: --------------------------------------------------------------

    public void setGumbPrihod(Button btnS) {
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputPrihodaOK()) {
                    baza.insert(prihodK.getSelectedItem().toString(),
                            prihodI.getText().toString(),
                            "Prihodi");
                    osvjeziListe();
                    clearForm();
                    Toast.makeText(MainActivity.this,
                            "Spremili ste prihod! Hvala.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void setPrihodiViews(Button b, Spinner s, EditText et) {
        setGumbPrihod(b);
        this.prihodK = s;
        this.prihodI = et;
    }

    public boolean inputPrihodaOK() {
        if (prihodK.getSelectedItem().toString().equals("Izaberite kategoriju:")
                && prihodI.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this,
                    "Morate izabrati kategoriju i upisati iznos!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (prihodK.getSelectedItem().toString().equals("Izaberite kategoriju:")) {
            Toast.makeText(MainActivity.this,
                    "Morate izabrati kategoriju!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (prihodI.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this,
                    "Morate unijeti iznos!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void setPrihodiLista(ListView lv) {
        this.prihodiLista = lv;
        List<String> prihodi = new ArrayList<String>();
        for (Prihodi x : baza.prihodiList())
            prihodi.add(x.kategorijaPr + ":\n" + x.iznosPr + " HRK");
        osvjeziListu(lv, prihodi);
    }


    // GUMBI TROSKOVA: ------------------------------------------------------------------

    public void setGumbTrosak(Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTroskovaOK()) {
                    baza.insert(troskoviK.getSelectedItem().toString(),
                            troskoviI.getText().toString(),
                            "Troskovi");
                    osvjeziListe();
                    clearForm();
                    Toast.makeText(MainActivity.this,
                            "Spremili ste trosak! Hvala.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setTroskoviViews(Button b, Spinner s, EditText et) {
        setGumbTrosak(b);
        this.troskoviK = s;
        this.troskoviI = et;
    }

    public boolean inputTroskovaOK() {
        if (troskoviK.getSelectedItem().toString().equals("Izaberite kategoriju:")
                && troskoviI.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this,
                    "Morate izabrati kategoriju i upisati iznos!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (troskoviK.getSelectedItem().toString().equals("Izaberite kategoriju:")) {
            Toast.makeText(MainActivity.this,
                    "Morate izabrati kategoriju!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (troskoviI.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this,
                    "Morate unijeti iznos!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void setTroskoviLista(ListView lv) {
        this.troskoviLista = lv;
        List<String> troskovi = new ArrayList<String>();
        for (Troskovi x : baza.troskoviList())
            troskovi.add(x.kategorijaTr + ":\n" + x.iznosTr + " HRK");
        osvjeziListu(lv, troskovi);
    }


    // OSVJEZAVANJE LISTI: -------------------------------------------------------------

    public void osvjeziListu(ListView lv, List<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
    }

    public void osvjeziListe() {
        if (prihodiLista != null) {
            List<String> prihodi = new ArrayList<String>();
            for (Prihodi x : baza.prihodiList())
                prihodi.add(x.kategorijaPr + ":\n" + x.iznosPr + " HRK");
            osvjeziListu(prihodiLista, prihodi);
        }

        if (troskoviLista != null) {
            List<String> troskovi = new ArrayList<String>();
            for (Troskovi x : baza.troskoviList())
                troskovi.add(x.kategorijaTr + ":\n" + x.iznosTr + " HRK");
            osvjeziListu(troskoviLista, troskovi);
        }
    }

    // METODE: -------------------------------------------------------------------------

    private void clearForm () {
        prihodI.setText("");
        prihodK.setSelection(0);
        troskoviI.setText("");
        troskoviK.setSelection(0);

    }

    private double zbroj () {
        return 0;
    }
}
