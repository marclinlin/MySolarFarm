package com.example.marcoslin.solarfinal.app.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Marcos Lin on 27/12/2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table datos(nombre text primary key, a1 double, a2 double, a3 double, a4 double, a5 double, a6 double, a7 double, a8 double, a9 double, a10 double, a11 double, a12 double, a13 double, a14 double, a15 double, a16 double, a17 double, a18 double, a19 double, a20 double, a21 double, a22 double, a23 double, a24 double, a25 double, a26 double, a27 double, a28 double, a29 double, a30 double, a31 double, a32 double, a33 double, a34 double, a35 double, a36 double, a37 double, a38 double, a39 double, a40 double, a41 double, a42 double, a43 double, a44 double, a45 double, a46 double, a47 double, a48 double, a49 double, a50 double, a51 double, a52 double, a53 double, a54 double, a55 double, a56 double, a57 double, a58 double, a59 double, a60 double, a61 double, a62 double, a63 double, a64 double, a65 double, a66 double, a67 double, a68 double, a69 double, a70 double, a71 double, a72 double, a73 double, a74 double, a75 double, a76 double, a77 double, a78 double, a79 double, a80 double, a81 double, a82 text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
