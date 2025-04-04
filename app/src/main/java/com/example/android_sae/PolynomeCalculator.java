package com.example.android_sae;

public class PolynomeCalculator {
    public static String calculerRacines(double a, double b, double c) {
        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Racines réelles : x1 = " + x1 + ", x2 = " + x2;
        } else if (delta == 0) {
            double x = -b / (2 * a);
            return "Racine unique : x = " + x;
        } else {
            return "Pas de racines réelles (racines complexes)";
        }
    }
}
