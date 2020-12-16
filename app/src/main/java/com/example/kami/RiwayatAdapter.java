package com.example.kami;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.kami.database.DatabaseContract.RiwayatColumns.CONTENT_URI;

public class RiwayatAdapter extends  RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RiwayatData> values;
    private LayoutInflater inflater;

    public RiwayatAdapter(Context context, ArrayList<RiwayatData> values){
        this.context=context;
        this.values=values;
        this.inflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_riwayat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatData data=values.get(position);
        holder.namaText.setText(data.getItemNama());
        holder.dateText.setText(data.getItemTanggal());
        holder.hasilText.setText(data.getItemHasil());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Hapus Data")
                        .setMessage("Apakah anda yakin untuk menghapus data ini?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Uri uriWithId = Uri.parse(CONTENT_URI + "/" + data.getId());
                                context.getContentResolver().delete(uriWithId, null, null);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
                return true;
            }
        });

    }

    public ArrayList<RiwayatData> getListRiwayat() {
        return values;
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView namaText;
        TextView dateText;
        TextView hasilText;

        public ViewHolder (View view){
            super (view);
            namaText = view.findViewById(R.id.nama_txt);
            dateText = view.findViewById(R.id.tgl_txt);
            hasilText = view.findViewById(R.id.hasil_txt);

        }
    }
}

