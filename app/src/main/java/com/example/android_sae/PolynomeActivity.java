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

public class PolynomeActivity extends AppCompatActivity {
    // Déclaration des composants de l'interface
    EditText coefA, coefB, coefC;
    Button calculer;
    TextView resultat;

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Activer Edge-to-Edge pour une expérience utilisateur moderne
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_polynome);

        // Gérer les marges pour les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisation des composants de l'interface
        coefA = findViewById(R.id.coefA);
        coefB = findViewById(R.id.coefB);
        coefC = findViewById(R.id.coefC);
        calculer = findViewById(R.id.btnCalculer);
        resultat = findViewById(R.id.resultat);

        // Configuration du bouton "Calculer"
        calculer.setOnClickListener(v -> {
            try {
                // Vérification des champs vides
                if (coefA.getText().toString().isEmpty()) {
                    coefA.setError("Champ requis");
                    return;
                }
                if (coefB.getText().toString().isEmpty()) {
                    coefB.setError("Champ requis");
                    return;
                }
                if (coefC.getText().toString().isEmpty()) {
                    coefC.setError("Champ requis");
                    return;
                }

                // Récupération des valeurs saisies
                double a = Double.parseDouble(coefA.getText().toString());
                double b = Double.parseDouble(coefB.getText().toString());
                double c = Double.parseDouble(coefC.getText().toString());

                // Calcul des racines via PolynomeCalculator
                String res = PolynomeCalculator.calculerRacines(a, b, c);

                // Affichage du résultat
                resultat.setText(res);

            } catch (NumberFormatException e) {
                // Gestion des erreurs de format (par exemple, saisie non numérique)
                resultat.setText("Erreur : Veuillez entrer des nombres valides.");
            }

        });
        btn1 = findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Création de l'Intent pour revenir à l'accueil
                Intent intent = new Intent(PolynomeActivity.this, MainActivity.class);
                startActivity(intent); // Lancement de la nouvelle activité

            }
        });
    }
}
