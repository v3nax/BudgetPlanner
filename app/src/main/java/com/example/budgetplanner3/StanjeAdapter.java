package com.example.budgetplanner3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class StanjeAdapter extends FragmentPagerAdapter {

    private String[] imenaTabova = new String[]{ "Prihodi", "Troškovi", "Stanje" };

    public StanjeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch ( i ) {
            case 0 : return new FragmentPrihodi( );
            case 1 : return new FragmentTroskovi( );
            case 2 : return new FragmentStanje( );
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle( int position ) {
        return imenaTabova[position];
    }

    @Override
    public int getCount( ) {
        return imenaTabova.length;
    }

}
