package com.example.kami.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.kami.database.DatabaseContract.TABLE_NAME;

// Kelas ini disebut DML (Data Manipulation Language)
// Perintah SQL yang berhubungan dengan pengolahan data dalam tabel
public class RiwayatHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static RiwayatHelper INSTANCE;

    private static SQLiteDatabase database;

    public RiwayatHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    // Inisiasi database
    public static RiwayatHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RiwayatHelper(context);
                }
            }
        }

        return INSTANCE;
    }

    // Membuka koneksi ke database
    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    // Menutup koneksi ke database
    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    // Untuk melakukan proses CRUD-nya (Create, Read, Update, Delete)
    // Mengambil data dari semua note yang ada di dalam database
    // @return cursor hasil queryAll
    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    // Mengambil data berdasarkan id
    // @return cursor hasil queryAll
    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    // Menyimpan data
    // @return long id dari data yang baru saja di masukkan
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    // Memperbarui data
    // @return int jumlah data yang ter-update
    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    // Menghapus data
    // @return int jumlah data yang ter-delete
    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
