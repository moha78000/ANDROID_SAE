package com.example.android_sae;

import java.util.HashMap;
import java.util.Map;

public class CryptoCalculator {

    // Grille de substitution
    private static final char[][] SUBSTITUTION_GRID = {
            {'g', 'e', 'h', 'i', 'm', 's'},
            {'c', 'r', 'f', 't', 'd', 'u'},
            {'n', 'k', 'a', 'b', 'j', 'l'},
            {'o', 'p', 'q', 'v', 'w', 'x'},
            {'y', 'z', '0', '1', '2', '3'},
            {'4', '5', '6', '7', '8', '9'}
    };

    private static final char[] SYMBOLS = {'A', 'D', 'F', 'G', 'V', 'X'};

    // Maps pour la substitution
    private static final Map<Character, String> SUBSTITUTION_MAP = new HashMap<>();

    private static final Map<String, Character> REVERSE_SUBSTITUTION_MAP = new HashMap<>();

    static {
        // Remplir les maps pour la substitution et la substitution inverse
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                SUBSTITUTION_MAP.put(SUBSTITUTION_GRID[i][j], "" + SYMBOLS[i] + SYMBOLS[j]);
                REVERSE_SUBSTITUTION_MAP.put("" + SYMBOLS[i] + SYMBOLS[j], SUBSTITUTION_GRID[i][j]);
            }
        }
    }

    /**
     * Chiffrement ADFGVX
     */
    public static String encrypt(String message) {
        message = message.toLowerCase().replaceAll("[^a-z0-9*]", ""); // Nettoyer l'entrée
        StringBuilder substitution = new StringBuilder();

        // Étape 1 : Substitution
        for (char c : message.toCharArray()) {
            if (SUBSTITUTION_MAP.containsKey(c)) {
                substitution.append(SUBSTITUTION_MAP.get(c));
            }
        }

        // Retourner le message substitué sans transposition
        return substitution.toString();
    }

    /**
     * Déchiffrement ADFGVX
     */
    public static String decrypt(String encryptedMessage) {
        encryptedMessage = encryptedMessage.toUpperCase(); // Important : forcer en MAJUSCULES
        StringBuilder original = new StringBuilder();

        // Parcours de chaque paire de lettres dans le message chiffré
        for (int i = 0; i < encryptedMessage.length(); i += 2) {
            if (i + 1 >= encryptedMessage.length()) {
                // Message mal formé (nombre impair de caractères), on peut ignorer ou gérer différemment
                break;
            }

            String pair = encryptedMessage.substring(i, i + 2);

            // Vérifier si la paire existe dans la table de substitution inverse
            if (REVERSE_SUBSTITUTION_MAP.containsKey(pair)) {
                original.append(REVERSE_SUBSTITUTION_MAP.get(pair));
            } else {
                original.append('X'); // Si la paire n'est pas trouvée
            }
        }

        return original.toString();
    }
}
