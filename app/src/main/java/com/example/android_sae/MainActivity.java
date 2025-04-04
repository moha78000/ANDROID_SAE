package com.example.android_sae;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView liste1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        liste1 = findViewById(R.id.liste);
        String[] rubriques = {"Module Polynôme", "Module Cryptographie ADFGVX", "Exit"};
        liste1.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rubriques));
        liste1.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) startActivity(new Intent(this, PolynomeActivity.class));
           else if (position == 1) startActivity(new Intent(this, CryptographieActivity.class));
            else finishAffinity(); // Ferme toutes les activités et quitte l'application
        });
    }


}