package com.example.aminjainalarivinquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.RemoteActionCompatParcelizer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etKode, etJumlah;
    RadioGroup rgMember;
    RadioButton rbBiasa, rbSilver, rbGold;
    Button btnProses;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etKode = findViewById(R.id.etKode);
        etJumlah = findViewById(R.id.etJumlah);
        rgMember = findViewById(R.id.rgMember);
        rbBiasa = findViewById(R.id.rbBiasa);
        rbSilver = findViewById(R.id.rbSilver);
        rbGold = findViewById(R.id.rbGold);
        btnProses = findViewById(R.id.btnProses);


        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hitungproses();
            }
        });

    }
    private void Hitungproses() {
        String nama = etNama.getText().toString();
        String kodeBarang = etKode.getText().toString();
        int jumlahBarang = Integer.parseInt(etJumlah.getText().toString());
//
        double hargaBarang = 0;
        String namaBarang = "";

        switch (kodeBarang){
            case "IPX":
                namaBarang = "Iphone X ";
                hargaBarang = 5725300;
                break;
            case "PCO":
                namaBarang = "POCO M3 ";
                hargaBarang = 2730551;
                break;
            case "O17":
                namaBarang = "Oppo A17 ";
                hargaBarang = 2500999;
                break;
            default:
                Toast.makeText(this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
                return;
        }

        double totalbayar = hargaBarang * jumlahBarang;

        int selectedMemberId = rgMember.getCheckedRadioButtonId();
        RadioButton selectedMember = findViewById(selectedMemberId);

        double discountmember = 0;
        if (selectedMember != null) {
            String memberType = selectedMember.getText().toString();
            switch (memberType) {
                case "Gold":
                    discountmember = totalbayar * 0.10;
                    break;
                case "Silver":
                    discountmember = totalbayar * 0.05;
                    break;
                case "Biasa":
                    discountmember = totalbayar * 0.02;
                    break;
            }
        }

        if (totalbayar > 10000000) {
             totalbayar = 100000;
        }

        double total = totalbayar - discountmember;
        double discountHarga = (hargaBarang * jumlahBarang)-totalbayar;

        // Mengirim data ke Activity2
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        intent.putExtra("Nama", etNama.getText().toString());
        intent.putExtra("KodeBarang", kodeBarang);
        intent.putExtra("namaBarang",namaBarang);
        intent.putExtra("HargaBarang",hargaBarang);
        intent.putExtra("Total Harga", totalbayar);
        intent.putExtra("discount", discountmember);
        intent.putExtra("discountharga", discountHarga);
        intent.putExtra("TotalPembayaran", total);
        startActivity(intent);
    }
}