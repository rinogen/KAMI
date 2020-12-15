package com.example.kami.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;

import static com.example.kami.database.DatabaseContract.AUTHORITY;
import static com.example.kami.database.DatabaseContract.RiwayatColumns.CONTENT_URI;
import static com.example.kami.database.DatabaseContract.TABLE_NAME;


/* Atur DatabaseProvider di AndroidManifest*/
public class DatabaseProvider extends ContentProvider {
    public DatabaseProvider() {}

    // Integer digunakan sebagai identifier antara select all dan select by id
    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;
    private RiwayatHelper riwayatHelper;

    // Definisikan UriMatcher untuk mengecek URI yang masuk, apakah bersifat all atau ada tambahan id-nya
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /* URIMatcher untuk mempermudah dalam identifiaksi URI yang masuk dengan format berikut:
    (*) path                path tanpa id
    (*) path/#              path dengan id, # menunjukkan suatu angka
    (*) path/*              path dengan subpath, * menunjukkan suatu kata
    (*) path/* /other/#     path dengan subpath dan id*/
    static {
        // content://com.dicoding.picodiploma.mynotesapp2/note
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTE);

        // content://com.dicoding.picodiploma.mynotesapp2/note/id
        uriMatcher.addURI(AUTHORITY,TABLE_NAME + "/#", NOTE_ID);

        /* Uri matcher untuk mempermudah identifier dengan menggunakan integer, misal:
        uri com.dicoding.picodiploma.mynotesapp2 dicocokan dengan konstanta NOTE atau integer 1
        uri com.dicoding.picodiploma.mynotesapp2/# dicocokan dengan konstanta NOTE_ID atau integer 2*/
    }

    @Override
    // Implement this to initialize your content provider on startup.
    public boolean onCreate() {
        riwayatHelper = RiwayatHelper.getInstance(getContext());
        riwayatHelper.open();

        return true;
    }

    @Override
    /* Method query digunakan ketika ingin menjalankan query Select
    Return cursor*/
    // Implement this to handle query requests from clients.
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;

        /* Dari Uri yang sudah diidentifikasi, kita bisa melakukan aksi menyesuaikan dengan URI yang masuk
        Jadi, kelebihan dari penggunaan URIMatcher ini yaitu dengan cukup satu fungsi,
        kita bisa menentukan aksi yang berbeda hanya dengan memasukkan ContentURI yang berbeda.*/
        switch (uriMatcher.match(uri)) {
            case NOTE:
                cursor = riwayatHelper.queryAll();
                break;

            case NOTE_ID:
                cursor = riwayatHelper.queryById(uri.getLastPathSegment());
                break;

            default:
                cursor = null;
                break;
        }

        return cursor;
    }

    @Override
    // Implement this to handle requests for the MIME type of the data
    public String getType(@NonNull Uri uri) {
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    // Implement this to handle requests to insert a new row.
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        long added;

        switch (uriMatcher.match(uri)) {
            case NOTE:
                added = riwayatHelper.insert(contentValues);
                break;

            default:
                added = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    // Implement this to handle requests to update one or more rows.
    public int update(@NonNull Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int updated;

        switch (uriMatcher.match(uri)) {
            case NOTE_ID:
                updated = riwayatHelper.update(uri.getLastPathSegment(), contentValues);
                break;

            default:
                updated = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return updated;
    }

    @Override
    // Implement this to handle requests to delete one or more rows.
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int deleted;

        switch (uriMatcher.match(uri)) {
            case NOTE_ID:
                deleted = riwayatHelper.deleteById(uri.getLastPathSegment());
                break;

            default:
                deleted = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return deleted;
    }
}
