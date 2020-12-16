package com.example.kami.database;

import android.net.Uri;
import android.provider.BaseColumns;

// Mendefinisikan nama tabel dan kolom-kolomnya
// Mempermudah akses nama tabel dan nama kolom di dalam database kita
public class DatabaseContract {
    public static String TABLE_NAME = "riwayat";
    public static final String AUTHORITY = "com.example.kami";
    private static final String SCHEME = "content";

    // Tidak ada kolom id di kelas contract
    // karena kolom id sudah ada secara otomatis di dalam kelas BaseColumns
    // Pemanggilan id menggunakan identifier _ID
    public static final class RiwayatColumns implements BaseColumns{
        public static String NAMA = "nama";
        public static String BMI = "bmi";
        public static String DATE = "date";

        // Untuk membuat URI berupa content://com.ariefzuhri.mynotesapp2/note
        // URI (Uniform Resource Identifier) pada dasarnya adalah sebuah alat identifikasi data
        // URI di bawah untuk mengambil semua data note dari mynotesapp2
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }
}
