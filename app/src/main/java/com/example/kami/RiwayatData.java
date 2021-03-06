package com.example.kami;

import android.os.Parcel;
import android.os.Parcelable;

public class RiwayatData implements Parcelable {
    private int id;
    private String itemNama;
    private String itemTanggal;
    private String itemHasil;

    public RiwayatData(int id, String itemTanggal, String itemNama, String itemHasil) {
        this.id = id;
        this.itemNama = itemNama;
        this.itemTanggal = itemTanggal;
        this.itemHasil = itemHasil;
    }

    protected RiwayatData(Parcel in) {
        id = in.readInt();
        itemNama=in.readString();
        itemTanggal = in.readString();
        itemHasil = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(itemNama);
        dest.writeString(itemTanggal);
        dest.writeString(itemHasil);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RiwayatData> CREATOR = new Creator<RiwayatData>() {
        @Override
        public RiwayatData createFromParcel(Parcel in) {
            return new RiwayatData(in);
        }

        @Override
        public RiwayatData[] newArray(int size) {
            return new RiwayatData[size];
        }
    };

    public String getItemNama() {
        return itemNama;
    }

    public void setItemNama(String itemNama) {
        this.itemNama = itemNama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemTanggal() {
        return itemTanggal;
    }

    public void setItemTanggal(String itemTanggal) {
        this.itemTanggal = itemTanggal;
    }

    public String getItemHasil() {
        return itemHasil;
    }

    public void setItemHasil(String itemHasil) {
        this.itemHasil = itemHasil;
    }

    public RiwayatData(){
    }




}

