package com.example.android_sae;

public class PolynomeCalculator {
    public static String calculerRacines(double a, double b, double c) {
        double delta = b * b - 4 * a * c;

        if (delta > 0) {
            double x1 = nettoyer((-b + Math.sqrt(delta)) / (2 * a));
            double x2 = nettoyer((-b - Math.sqrt(delta)) / (2 * a));
            return "Racines réelles : x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = nettoyer(-b / (2 * a));
            return "Racine unique : x = " + x;
        } else {
            double real = nettoyer(-b / (2 * a));
            double imag = nettoyer(Math.sqrt(-delta) / (2 * a));
            String imagStr = (imag == 1.0) ? "i" : (imag == -1.0) ? "-i" : imag + "i";
            return "Racines complexes : x1 = " + real + " + " + imagStr + ", x2 = " + real + " - " + imagStr;
        }
    }

    private static double nettoyer(double val) {
        val = Math.round(val * 100.0) / 100.0;
        if (val == -0.0) val = 0.0;
        return val;
    }
}
