package com.example.android_sae;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;





import androidx.appcompat.widget.Toolbar;  // <-- C'est la bonne importation p


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView liste1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_p);
        setSupportActionBar(myToolbar);


        liste1 = findViewById(R.id.liste);
        String[] rubriques = {"Module Polynôme", "Module Cryptographie ADFGVX", "Exit"};

        // Utiliser notre layout personnalisé pour les éléments de la ListView
        liste1.setAdapter(new ArrayAdapter<>(this, R.layout.simple_list_item_white, rubriques));

        liste1.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) startActivity(new Intent(this, PolynomeActivity.class));
            else if (position == 1) startActivity(new Intent(this, CryptographieActivity.class));
            else finishAffinity(); // Ferme toutes les activités et quitte l'application
        });
    }

    // Afficher le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Gérer les clics du menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_calcul) {

            startActivity(new Intent(this, PolynomeActivity.class));
            return true;}
        else if (item.getItemId() == R.id.action_crypto){
            startActivity(new Intent(this, CryptographieActivity.class));
            return true;
        }

        else if (item.getItemId() == R.id.action_accueil){
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        else if (item.getItemId() == R.id.action_exit){

            finishAffinity();
            System.exit(0);
            return true;
        }

        else {
            return super.onContextItemSelected(item);
        }


    }
}