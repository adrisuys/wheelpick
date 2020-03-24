package be.depinxi.charts.model;

import java.io.Serializable;
import java.util.List;

public class Categorie implements Serializable {

    private String label;
    private List<String> items;

    public Categorie(String label, List<String> items) {
        this.label = label;
        this.items = items;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
