package com.example.kami.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.kami.database.DatabaseContract.TABLE_NAME;

/* Kelas ini untuk menciptakan database dengan tabel yang dibutuhkan
dan menangani ketika terjadi perubahan struktur pada tabel (tejadi pada metode onUpdrade())

Kelas ini menggunakan variabel yang ada pada DatabaseContract untuk mengisi kolom nama tabel.
Begitu juga dengan kelas-kelas lainnya nanti.
Dengan memanfaatkan kelas contract, maka akses nama tabel dan nama kolom tabel menjadi lebih mudah.*/

// Kelas ini disebut DDL (Data Definition Language)
// Perintah SQL yang berhubungan dengan pendefinisian suatu struktur database
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbriwayat";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_RIWAYAT = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.RiwayatColumns._ID,
            DatabaseContract.RiwayatColumns.NAMA,
            DatabaseContract.RiwayatColumns.BMI,
            DatabaseContract.RiwayatColumns.DATE
    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_RIWAYAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
