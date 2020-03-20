package be.depinxi.charts.presenter;

import java.util.ArrayList;
import java.util.List;

import be.depinxi.charts.model.Categorie;
import be.depinxi.charts.model.Item;
import be.depinxi.charts.view.MainActivity;
import be.depinxi.charts.view.ViewInterface;

public class Presenter {

    private ViewInterface view;
    private List<Categorie> items;

    public Presenter(ViewInterface mainActivity, List<Categorie> items) {
        this.view = mainActivity;
        this.items = items;
    }

    public void addItem(String item){
        if (item != ""){
            Categorie cat = new Categorie(item, new ArrayList<Item>());
            items.add(cat);
            view.updateList();
        }
    }

    public void removeItem(String item){
        removeItemWithLabel(item);
        view.updateList();
    }

    private void removeItemWithLabel(String item) {
        for (Categorie cat : items){
            if (cat.getLabel().equals(item)){
                items.remove(cat);
                return;
            }
        }
    }

    public void onBindViewHolder(MainActivity.Holder holder, int position) {
        String item = items.get(position).getLabel();
        holder.displayItem(item);
    }

    public int getItemCount() {
        return items.size();
    }

    public String atIndex(int adapterPosition) {
        return items.get(adapterPosition).getLabel();
    }

    public List<Categorie> getItems() {
        return items;
    }
}
