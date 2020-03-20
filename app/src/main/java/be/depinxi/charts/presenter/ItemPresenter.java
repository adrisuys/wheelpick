package be.depinxi.charts.presenter;

import java.util.ArrayList;
import java.util.List;

import be.depinxi.charts.model.Categorie;
import be.depinxi.charts.view.ItemActivity;
import be.depinxi.charts.view.MainActivity;
import be.depinxi.charts.view.ViewInterface;

public class ItemPresenter {

    private ViewInterface view;
    private List<String> items;

    public ItemPresenter(ViewInterface mainActivity, List<String> items) {
        this.view = mainActivity;
        this.items = items;
    }

    public void addItem(String item){
        if (item != ""){
            items.add(item);
            view.updateList();
        }
    }

    public void removeItem(String item){
        items.remove(item);
        view.updateList();
    }

    public void onBindViewHolder(ItemActivity.Holder holder, int position) {
        String item = items.get(position);
        holder.displayItem(item);
    }

    public int getItemCount() {
        return items.size();
    }

    public String atIndex(int adapterPosition) {
        return items.get(adapterPosition);
    }

    public ArrayList<String> getItems() {
        return (ArrayList<String>) items;
    }
}
