package com.example.budgetplanner3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.NfcAdapter;

import java.util.ArrayList;
import java.util.List;

public class Baza extends SQLiteOpenHelper {

    public static final int DB_VER = 1;
    public static final String DB_NAME = "baza.db";

    private static final String TABLE_STANJE_NAME = "stanje";
    private static final String COLUMN_STANJE_ID = "id";
    public static final String COLUMN_STANJE_KATEGORIJA = "kategorija";
    public static final String COLUMN_STANJE_IZNOS = "iznos";
    public static final String COLUMN_STANJE_TIP = "tip";
    public static final String TOTAL_INPUT = "ukupno";

    public static final String CREATE_SQL =
            "create table " + TABLE_STANJE_NAME +
                    "(" + COLUMN_STANJE_ID + " integer primary key autoincrement, " +
                    COLUMN_STANJE_KATEGORIJA + " text not null, " +
                    COLUMN_STANJE_IZNOS + " text not null, " +
                    COLUMN_STANJE_TIP + " text not null, " +
                    TOTAL_INPUT + " text not null);";

    public static final String DROP_SQL = "drop table " + TABLE_STANJE_NAME + ";";

    public Baza(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_SQL);
        onCreate(db);
    }

    public void insert (String kategorija, String iznos, String tip) {
        ContentValues values = new ContentValues();
        values.put (COLUMN_STANJE_KATEGORIJA, kategorija);
        values.put(COLUMN_STANJE_IZNOS, iznos);
        values.put(COLUMN_STANJE_TIP, tip);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_STANJE_NAME, null, values);
    }


    public void delete() {
        ContentValues values = new ContentValues();
        values.remove(COLUMN_STANJE_KATEGORIJA);
        values.remove(COLUMN_STANJE_IZNOS);
        values.remove(COLUMN_STANJE_TIP);

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STANJE_NAME, null, null);
    }

    public double getZbroj () {
        double sum = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String zbrojUpit = "select sum(" +
                COLUMN_STANJE_IZNOS +
                ") as total from " + TABLE_STANJE_NAME +
                " where " + COLUMN_STANJE_TIP +
                "=?;";
        Cursor c = db.rawQuery(zbrojUpit, null);
        if ( c.moveToFirst())
            sum = c.getDouble(c.getColumnIndex("ukupno"));
        return sum;

    }


    public List <Prihodi> prihodiList () {
        List<Prihodi> prihodiL = new ArrayList<Prihodi>();
        String upit = "select " +
                COLUMN_STANJE_KATEGORIJA + ", " +
                COLUMN_STANJE_IZNOS +
                " from " + TABLE_STANJE_NAME +
                " where " + COLUMN_STANJE_TIP +
                "=?;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(upit, new String[]{"Prihodi"});

        if (c.moveToFirst()) {
            do{
                Prihodi prihodi = new Prihodi(
                        c.getString(0),
                        c.getString(1));
                prihodiL.add(prihodi);
            }while (c.moveToNext());
        }
        return prihodiL;
    }


    public List <Troskovi> troskoviList () {
        List<Troskovi> troskoviL = new ArrayList<Troskovi>();
        String upit = "select " +
                COLUMN_STANJE_KATEGORIJA + ", " +
                COLUMN_STANJE_IZNOS +
                " from " + TABLE_STANJE_NAME +
                " where " + COLUMN_STANJE_TIP +
                "=?;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(upit, new String[]{"Troskovi"});

        if (c.moveToFirst()) {
            do{
                Troskovi troskovi = new Troskovi(
                        c.getString(0),
                        c.getString(1));
                troskoviL.add(troskovi);
            }while (c.moveToNext());
        }
        return troskoviL;
    }


}

