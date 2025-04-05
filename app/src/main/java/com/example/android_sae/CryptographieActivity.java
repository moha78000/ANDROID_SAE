package com.example.android_sae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        // Activer Edge-to-Edge pour une expérience utilisateur moderne
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cryptographie);

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
}
