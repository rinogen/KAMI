package com.example.kami.callback;

import com.example.kami.RiwayatData;

import java.util.ArrayList;

public interface LoadBMICallback {
    void preExecute();
    void postExecute(ArrayList<RiwayatData> riwayat);
}