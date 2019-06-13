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

public class FragmentTroskovi extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        MainActivity ma = (MainActivity)getActivity();

        View v = inflater.inflate(R.layout.fragment_troskovi,
                container, false);

        //SPINNER
        Spinner spinner = (Spinner) v.findViewById(R.id.spinnerT);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                ma, R.array.entires_troskovi,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        //IZNOS PRIHODI
        EditText iznos = (EditText)v.findViewById(R.id.etIznosT);


        //GUMB
        Button gumb = (Button) v.findViewById(R.id.btnT);

        ma.setTroskoviViews(gumb, spinner, iznos);


        return v;
    }
}
