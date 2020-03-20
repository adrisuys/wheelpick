package be.depinxi.charts.presenter;

import java.util.ArrayList;
import java.util.List;

import be.depinxi.charts.model.Categorie;
import be.depinxi.charts.model.Item;
import be.depinxi.charts.view.ItemActivity;
import be.depinxi.charts.view.MainActivity;
import be.depinxi.charts.view.ViewInterface;

public class ItemPresenter {

    private ViewInterface view;
    private List<Item> items;

    public ItemPresenter(ViewInterface mainActivity, List<Item> items) {
        this.view = mainActivity;
        this.items = items;
    }

    public void addItem(String item){
        if (item != ""){
            items.add(new Item(item));
            view.updateList();
        }
    }

    public void removeItem(String item){
        for (Item i : items){
            if (i.getLabel().equals(item)){
                items.remove(i);
            }
        }
        view.updateList();
    }

    public void onBindViewHolder(ItemActivity.Holder holder, int position) {
        String item = items.get(position).getLabel(getTotal());
        holder.displayItem(item);
    }

    private int getTotal() {
        int total = 0;
        for (Item i : items){
            total += i.getNbPicked();
        }
        return total;
    }

    public int getItemCount() {
        return items.size();
    }

    public String atIndex(int adapterPosition) {
        return items.get(adapterPosition).getLabel();
    }

    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) items;
    }
}
