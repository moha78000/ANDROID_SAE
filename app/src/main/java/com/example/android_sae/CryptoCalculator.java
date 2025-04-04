package com.example.android_sae;

import java.util.Arrays;
import java.util.Comparator;
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

    // Générer la table de substitution (lettre -> code ADFGVX)
    private static final Map<Character, String> SUBSTITUTION_MAP = new HashMap<>();

    static {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                SUBSTITUTION_MAP.put(SUBSTITUTION_GRID[i][j], "" + SYMBOLS[i] + SYMBOLS[j]);
            }
        }
    }

    /**
     * Chiffrement ADFGVX
     */
    public static String encrypt(String message, String key) {
        message = message.toLowerCase().replaceAll("[^a-z0-9*]", ""); // Nettoyer l'entrée
        StringBuilder substitution = new StringBuilder();

        // Étape 1 : Substitution
        for (char c : message.toCharArray()) {
            if (SUBSTITUTION_MAP.containsKey(c)) {
                substitution.append(SUBSTITUTION_MAP.get(c));
            }
        }

        // Étape 2 : Transposition
        return transpose(substitution.toString(), key);
    }

    /**
     * Déchiffrement ADFGVX
     */
    public static String decrypt(String encryptedMessage, String key) {
        // Étape 1 : Inverser la transposition
        String restored = inverseTranspose(encryptedMessage, key);

        // Étape 2 : Inverser la substitution
        StringBuilder original = new StringBuilder();
        for (int i = 0; i < restored.length(); i += 2) {
            String pair = restored.substring(i, i + 2);
            original.append(getCharacterFromPair(pair));
        }

        return original.toString();
    }

    /**
     * Effectue la transposition selon la clé donnée
     */
    private static String transpose(String message, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) message.length() / cols);
        char[][] grid = new char[rows][cols];

        // Remplissage ligne par ligne
        for (int i = 0, k = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = (k < message.length()) ? message.charAt(k++) : 'X';
            }
        }

        // Trier la clé alphabétiquement et récupérer l'ordre d'index
        Integer[] order = getColumnOrder(key);

        // Lire colonne par colonne dans l'ordre trié
        StringBuilder result = new StringBuilder();
        for (int col : order) {
            for (int row = 0; row < rows; row++) {
                result.append(grid[row][col]);
            }
        }

        return result.toString();
    }

    /**
     * Inverse la transposition pour retrouver la chaîne d'origine
     */
    private static String inverseTranspose(String message, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) message.length() / cols);
        char[][] grid = new char[rows][cols];

        // Récupérer l'ordre original des colonnes
        Integer[] order = getColumnOrder(key);

        // Remplissage colonne par colonne
        int k = 0;
        for (int col : order) {
            for (int row = 0; row < rows; row++) {
                grid[row][col] = message.charAt(k++);
            }
        }

        // Lire ligne par ligne pour reconstituer le message original
        StringBuilder result = new StringBuilder();
        for (char[] row : grid) {
            result.append(row);
        }

        return result.toString();
    }

    /**
     * Récupérer l'ordre des colonnes basé sur la clé
     */
    private static Integer[] getColumnOrder(String key) {
        Character[] keyChars = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyChars[i] = key.charAt(i);
        }

        Integer[] indexes = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            indexes[i] = i;
        }

        Arrays.sort(indexes, Comparator.comparingInt(i -> keyChars[i]));

        return indexes;
    }

    /**
     * Retrouve le caractère d'origine à partir d'une paire ADFGVX
     */
    private static char getCharacterFromPair(String pair) {
        int row = Arrays.binarySearch(SYMBOLS, pair.charAt(0));
        int col = Arrays.binarySearch(SYMBOLS, pair.charAt(1));
        return SUBSTITUTION_GRID[row][col];
    }
}
