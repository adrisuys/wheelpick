package be.depinxi.charts.model;

import java.util.List;

public class DataHolder {

    private static List<Categorie> cats;
    private static int currentPos;
    private static boolean darkModeEnabled;

    public static List<Categorie> getCats() {
        return cats;
    }

    public static void setCats(List<Categorie> cats) {
        DataHolder.cats = cats;
    }

    public static int getCurrentPos() {
        return currentPos;
    }

    public static void setCurrentPos(int currentPos) {
        DataHolder.currentPos = currentPos;
    }

    public static boolean isDarkModeEnabled() {
        return darkModeEnabled;
    }

    public static void setDarkMode(boolean darkModeEnabled) {
        DataHolder.darkModeEnabled = darkModeEnabled;
    }
}
