package be.depinxi.charts.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import be.depinxi.charts.model.Categorie;
import be.depinxi.charts.model.DataHolder;
import be.depinxi.charts.presenter.ItemPresenter;
import be.depinxi.charts.R;

public class ItemActivity extends AppCompatActivity implements ViewInterface {

    private RecyclerView recyclerView;
    private RelativeLayout background;
    private Adapter adapter;
    private ItemPresenter presenter;
    private EditText input;
    private List<Categorie> cats;
    private List<String> currentList;
    private TextView title;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        background = findViewById(R.id.background);
        background.setBackgroundColor(DataHolder.isDarkModeEnabled() ? Color.BLACK : Color.WHITE);
        String json = getIntent().getStringExtra("items");
        position = DataHolder.getCurrentPos();
        cats = DataHolder.getCats();
        currentList = cats.get(position).getItems();
        title = findViewById(R.id.title_text);
        title.setText(cats.get(position).getLabel());
        presenter = new ItemPresenter(this, currentList);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        input = findViewById(R.id.input);
        overrideEnterForInput();
        displayItems();
    }

    private void overrideEnterForInput() {
        input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    addItem(v);
                    return true;
                }
                return false;
            }
        });
    }

    private void backup(){
        List<Categorie> favs = cats;
        String json = new Gson().toJson(favs);
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cats", json);
        editor.commit();
    }

    private void displayItems() {
        if (adapter == null){
            adapter = new Adapter(presenter);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setPresenter(presenter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateList() {
        displayItems();
    }

    public void goToWheel(View v){
        if (presenter.getItems().size() < 2){
            Toast.makeText(this, "Not enough items !", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, WheelActivity.class);
            intent.putStringArrayListExtra("items", presenter.getItems());
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        DataHolder.setCats(cats);
        DataHolder.setCurrentPos(position);
        startActivity(intent);
    }

    public void addItem(View v){
        String newItem = input.getEditableText().toString();
        presenter.addItem(newItem);
        input.setText("");
        backup();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageButton btn;
        LinearLayout background;

        public Holder(View itemView){
            super(itemView);
            background = itemView.findViewById(R.id.background);
            background.setBackgroundResource(DataHolder.isDarkModeEnabled() ? R.drawable.rounded_rect_black : R.drawable.rounded_rect_white);
            tv = itemView.findViewById(R.id.name);
            tv.setTextColor(DataHolder.isDarkModeEnabled() ? Color.WHITE : Color.BLACK);
            btn = itemView.findViewById(R.id.del_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.removeItem(presenter.atIndex(getAdapterPosition()));
                    backup();
                }
            });
        }

        public void displayItem(String item) {
            tv.setText(item);
        }
    }

    private class Adapter extends RecyclerView.Adapter<Holder> {

        private ItemPresenter presenter;

        public Adapter (ItemPresenter presenter){
            this.presenter = presenter;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new Holder(viewItem);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            presenter.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return presenter.getItemCount();
        }

        public void setPresenter(ItemPresenter presenter) {
            this.presenter = presenter;
        }
    }
}
