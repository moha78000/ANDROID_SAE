package com.example.android_sae;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CryptographieActivity extends AppCompatActivity {

    // Déclaration des composants de l'interface
    EditText inputMessage;
    Button btnChiffrer, btnDechiffrer;
    TextView resultCrypto;

    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryptographie);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_p);
        setSupportActionBar(myToolbar);

        // Activer Edge-to-Edge pour une expérience utilisateur moderne
        EdgeToEdge.enable(this);



        // Gérer les marges pour les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des composants de l'interface
        inputMessage = findViewById(R.id.inputMessage);
        btnChiffrer = findViewById(R.id.btnChiffrer);
        btnDechiffrer = findViewById(R.id.btnDechiffrer);
        resultCrypto = findViewById(R.id.resultCrypto);

        // Configuration du bouton "Chiffrer"
        btnChiffrer.setOnClickListener(v -> {
            String msg = inputMessage.getText().toString();

            // Vérification des champs vides avant le chiffrement
            if (msg.isEmpty()) {
                inputMessage.setError("Veuillez entrer un message.");
                return;
            }

            // Utilisation de CryptoCalculator pour chiffrer le message
            resultCrypto.setText(CryptoCalculator.encrypt(msg));
        });

        // Configuration du bouton "Déchiffrer"
        btnDechiffrer.setOnClickListener(v -> {
            String msg = inputMessage.getText().toString();

            // Vérification des champs vides avant le déchiffrement
            if (msg.isEmpty()) {
                inputMessage.setError("Veuillez entrer un message.");
                return;
            }

            // Utilisation de CryptoCalculator pour déchiffrer le message
            resultCrypto.setText(CryptoCalculator.decrypt(msg));  // Le paramètre 'key' n'est pas nécessaire ici
        });

        btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Création de l'Intent pour revenir à l'accueil
                Intent intent = new Intent(CryptographieActivity.this, MainActivity.class);
                startActivity(intent); // Lancement de la nouvelle activité
            }
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
