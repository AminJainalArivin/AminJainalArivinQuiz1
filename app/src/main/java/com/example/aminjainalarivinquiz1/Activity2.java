package com.example.aminjainalarivinquiz1;


import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class Activity2 extends AppCompatActivity {
    TextView tvNama, tvKode,tvNbarang, tvhargabarang,tvTotalharga, tvDiscountMember, tvdiscountharga, tvtotal, btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tvNama = findViewById(R.id.tvNama);
        tvKode = findViewById(R.id.tvKode);
        tvNbarang = findViewById(R.id.tvNbarang);
        tvhargabarang = findViewById(R.id.tvhargabarang);
        tvTotalharga = findViewById(R.id.tvTotalHarga);
        tvDiscountMember = findViewById(R.id.tvDiscountMember);
        tvdiscountharga = findViewById(R.id.tvdiscountharga);
        tvtotal = findViewById(R.id.tvtotal);
        btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("Nama");
        String namaBarang = intent.getStringExtra("namaBarang");
        String KodeBarang = intent.getStringExtra("KodeBarang");
        double Totalharga = intent.getDoubleExtra("Total Harga",0);
        double hargabarang = intent.getDoubleExtra("HargaBarang", 0);
        double Discount = intent.getDoubleExtra("discount", 0);
        double discountharga = intent.getDoubleExtra("discountharga", 0);
        double total = intent.getDoubleExtra("TotalPembayaran", 0);



        tvNama.setText(getString(R.string.nama) + "" + nama);
        tvKode.setText(getString(R.string.kode_barang) + "" + KodeBarang);
        tvNbarang.setText(getString(R.string.nama_barang) + "" + namaBarang);
        tvTotalharga.setText(getString(R.string.total_harga) + "" + Totalharga);
        tvhargabarang.setText(getString(R.string.harga_barang) + "" + hargabarang);
        tvdiscountharga.setText(getString(R.string.discount_member) + "" + Discount);
        tvDiscountMember.setText(getString(R.string.discount_harga) + "" + discountharga);
        tvtotal.setText(getString(R.string.total_harga_dibayar) + "" + total);


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareTransactionInfo(nama, KodeBarang, namaBarang, hargabarang, Totalharga, discountharga,Discount, total );

            }
        });
    }
    private void shareTransactionInfo(String nama, String KodeBarang, String namaBarang, double hargabarang, double Totalharga,double Discount, double discountharga, double total){
        String message = "Detail Transaksi:\n"+
                "Nama: " + nama + "\n" +
                "KodeBarang: " + KodeBarang + "\n" +
                "namaBarang: " + namaBarang + "\n" +
                "HargaBarang: " + hargabarang + "\n" +
                "Totalharga: " + Totalharga + "\n" +
                "discount: " + Discount + "\n" +
                "discount Harga: " + discountharga + "\n" +
                "Total Harga: " + total;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent,"Bagikan Melalui"));
    }
}

