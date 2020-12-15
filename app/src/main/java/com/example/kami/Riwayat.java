package com.example.kami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kami.callback.LoadBMICallback;
import com.example.kami.database.DatabaseContract;
import com.example.kami.helper.MappingHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Riwayat extends AppCompatActivity implements LoadBMICallback {
    private Button btnmenu;
    private RiwayatAdapter riwayatAdapter;
    private RecyclerView rvRiwayat;

    private static final String EXTRA_STATE = "EXTRA_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        btnmenu = findViewById(R.id.btn_bckmenu);

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Riwayat.this,MainActivity.class);
                startActivity(intent);
            }
        });

        rvRiwayat = findViewById(R.id.rv_riwayat);
        rvRiwayat.setLayoutManager(new LinearLayoutManager(this));
        rvRiwayat.setHasFixedSize(true);
        riwayatAdapter = new RiwayatAdapter(this, new ArrayList<RiwayatData>());
        riwayatAdapter.notifyDataSetChanged();

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver dataObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(DatabaseContract.RiwayatColumns.CONTENT_URI, true, dataObserver);



        if (savedInstanceState == null) {
            // Proses mengambil data
            /* Kita akan mengambil data dari method queryAll() yang ada di NoteHelper
            Namun, karena nanti di adapter kita akan menggunakan ArrayList,
            sedangkan di sini objek yang di kembalikan berupa Cursor,
            maka dari itu kita harus mengonversi dari Cursor ke Arraylist.
            Kita akan membuat kelas pembantu (MappingHelper) untuk menangani hal ini.*/
            new LoadRiwayatAsync(this, this).execute();
        } else {
            ArrayList<RiwayatData> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                riwayatAdapter = new RiwayatAdapter(this, list);
            }
        }

    }

    @Override
    // Simpan data ketika terjadi rotasi layar
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, riwayatAdapter.getListRiwayat());
    }

    @Override
    public void preExecute() {
    }

    @Override
    public void postExecute(ArrayList<RiwayatData> riwayat) {
        Log.d("TIDAK TERSIMPAN", riwayat.get(0).getItemHasil());
        if (riwayat.size() > 0) {
            riwayatAdapter = new RiwayatAdapter(this, riwayat);
        } else {
            riwayatAdapter = new RiwayatAdapter(this, new ArrayList<RiwayatData>());
        }
        rvRiwayat.setAdapter(riwayatAdapter);
    }

    private static class LoadRiwayatAsync extends AsyncTask<Void, Void, ArrayList<RiwayatData>> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadBMICallback> weakCallback;

        private LoadRiwayatAsync (Context context, LoadBMICallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<RiwayatData> doInBackground(Void... voids) {
            Context context = weakContext.get();
            // Penggunaan ContentResolver
            Cursor dataCursor = context.getContentResolver().query(DatabaseContract.RiwayatColumns.CONTENT_URI, null, null, null, null);
            Log.d("TIDAK TERSIMPAN", "doInBackground()");
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<RiwayatData> notes) {
            super.onPostExecute(notes);
            weakCallback.get().postExecute(notes);
        }
    }

    public static class DataObserver extends ContentObserver {
        private final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadRiwayatAsync(context, (LoadBMICallback) context).execute();
        }
    }


}