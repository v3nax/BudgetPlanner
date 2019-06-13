package com.example.budgetplanner3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentStanje extends Fragment {

    private Baza baza;
    MainActivity ma;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ma = (MainActivity) getActivity();
        baza = new Baza(getContext());

        View v = inflater.inflate(R.layout.fragment_stanje,
                container, false);


        ListView listaP = v.findViewById(R.id.listaP);
        ListView listaT = v.findViewById(R.id.listaT);


        //PRIHODI DIALOG: -------------------------------------------------------------------
        listaP.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {

                String prihod = ((TextView) view).getText().toString();
                parent.getItemAtPosition(position);
                AlertDialog.Builder adP = new AlertDialog.Builder(
                        getContext());
                adP.setIcon(R.drawable.troskovi);
                adP.setTitle("Odabrali ste");
                adP.setMessage(prihod);
                adP.setPositiveButton("U REDU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                adP.setNegativeButton("OBRIŠI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        brisanjeUnosa();
                    }
                });
                adP.show();

            }
        });

        // TROSKOVI DIJALOG: -------------------------------------------------------------------

        listaT.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String trosak = ((TextView) view).getText().toString();
                parent.getItemAtPosition(position);
                AlertDialog.Builder adP = new AlertDialog.Builder(
                        getContext());
                adP.setIcon(R.drawable.troskovi);
                adP.setTitle("Odabrali ste");
                adP.setMessage(trosak);
                adP.setPositiveButton("U REDU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                adP.setNegativeButton("OBRIŠI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        brisanjeUnosa();
                    }
                });
                adP.show();

            }
        });


        //SET LISTI: ------------------------------------------------------------------------

        ma.setPrihodiLista(listaP);
        ma.setTroskoviLista(listaT);

        return v;
    }

    //METODE: -------------------------------------------------------------------------------

    private void brisanjeUnosa() { // -- Pronadji kako pobrisati samo odabrani red --

        baza.delete();
        ma.osvjeziListe();
    }


    // RIJEŠITI ZBROJEVE!!!!!

    public double getZbrojP(ArrayList<Prihodi> listP) {
        return 0;
    }

    private double zbrojT (ArrayList < Troskovi > listT) {
        return 0 ;
    }
}
