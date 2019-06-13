package com.example.budgetplanner3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FragmentPrihodi extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity ma = (MainActivity)getActivity();

        View v = inflater.inflate(R.layout.fragment_prihodi,
                container, false);


        //SPINNER
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerP);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                ma, R.array.entries_prihodi,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        //IZNOS PRIHODI
        EditText iznos = (EditText)v.findViewById(R.id.etIznosP);


        //GUMB
        Button gumbS = (Button) v.findViewById(R.id.btnPrS);

        ma.setGumbPrihod(gumbS );
        ma.setPrihodiViews(gumbS,  spinner, iznos);

        return v;
    }
}
