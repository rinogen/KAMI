package com.example.kami.helper;

import android.database.Cursor;


import com.example.kami.RiwayatData;
import com.example.kami.database.DatabaseContract;
import com.example.kami.database.DatabaseProvider;

import java.util.ArrayList;

public class MappingHelper {
    // Konversi tipe data cursor ke arraylist
    public static ArrayList<RiwayatData> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<RiwayatData> riwayatList = new ArrayList<RiwayatData>();

        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns._ID));
            String nama = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns.NAMA));
            String bmi = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns.BMI));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns.DATE));
            riwayatList.add(new RiwayatData(id, nama, bmi, date));
        }

        return riwayatList;
    }

    // Konversi tipe data cursor ke object (model)
    public static RiwayatData mapCursorToObject(Cursor notesCursor) {
        notesCursor.moveToFirst();
        int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns._ID));
        String nama = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns.NAMA));
        String bmi = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns.BMI));
        String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.RiwayatColumns.DATE));
        return new RiwayatData(id, nama, bmi, date);
    }
}