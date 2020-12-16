package com.example.kami;

import android.os.Parcel;
import android.os.Parcelable;

public class TipsData implements Parcelable {
    private int logoBerita;
    private String tglBerita;
    private String judulBerita;
    private String descBerita;

    public TipsData() {
    }

    public TipsData(int logoBerita, String tglBerita, String judulBerita, String descBerita) {
        this.logoBerita = logoBerita;
        this.tglBerita = tglBerita;
        this.judulBerita = judulBerita;
        this.descBerita = descBerita;
    }

    public int getLogoBerita() {
        return logoBerita;
    }

    public void setLogoBerita (int logoBerita) {
        this.logoBerita = logoBerita;
    }

    public String getTglBerita() {
        return tglBerita;
    }

    public void setTglBerita(String tglBerita) {
        this.tglBerita = tglBerita;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getDescBerita() {
        return descBerita;
    }

    public void setDescBerita(String descBerita) {
        this.descBerita = descBerita;
    }

    protected TipsData(Parcel in){
        logoBerita=in.readInt();
        tglBerita=in.readString();
        judulBerita=in.readString();
        descBerita=in.readString();
    }


    public static final Creator<TipsData> CREATOR = new Creator<TipsData>() {
        @Override
        public TipsData createFromParcel(Parcel in) {
            return new TipsData(in);
        }

        @Override
        public TipsData[] newArray(int size) {
            return new TipsData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(logoBerita);
        dest.writeString(tglBerita);
        dest.writeString(judulBerita);
        dest.writeString(descBerita);
    }
}
