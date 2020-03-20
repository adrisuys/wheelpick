package be.depinxi.charts.model;

import java.io.Serializable;

public class Item implements Serializable {

    private String label;
    private int nbPicked;

    public Item(String label){
        this.label = label;
        nbPicked = 0;
    }

    public void increment(){
        nbPicked++;
    }

    public String getLabel(int total){
        return label + " (" + nbPicked + "/" + total + ")";
    }

    public int getNbPicked() {
        return nbPicked;
    }

    public String getLabel() {
        return label;
    }
}
