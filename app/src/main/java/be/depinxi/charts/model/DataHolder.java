package be.depinxi.charts.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHolder {

    private static List<Categorie> cats;
    private static int currentPos;
    private static boolean darkModeEnabled;
    private static Map<String, Integer> odds;

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

    public static Map<String, Integer> getOdds() {
        return odds;
    }

    public static void addOdds(String key){
        if (odds == null){
            odds = new HashMap<>();
        }
        if (odds.containsKey(key)) {
            odds.put(key, odds.get(key) + 1);
        } else{
            odds.put(key, 1);
        }
    }

    public static void setOdds(Map<String, Integer> odds) {
        DataHolder.odds = odds;
    }
}
