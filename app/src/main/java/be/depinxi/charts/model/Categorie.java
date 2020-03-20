package be.depinxi.charts.model;

import java.io.Serializable;
import java.util.List;

public class Categorie implements Serializable {

    private String label;
    private List<Item> items;

    public Categorie(String label, List<Item> items) {
        this.label = label;
        this.items = items;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
