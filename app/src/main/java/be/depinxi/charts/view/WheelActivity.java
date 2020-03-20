package be.depinxi.charts.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.depinxi.charts.R;
import be.depinxi.charts.model.Categorie;
import be.depinxi.charts.model.DataHolder;
import be.depinxi.charts.model.Item;

public class WheelActivity extends AppCompatActivity {

    private PieChart wheel;
    private TextView tv;
    private int degree, degreeOld;
    private List<String> items;
    private List<Categorie> cats;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        degreeOld = 0;
        degree = 0;
        tv = findViewById(R.id.result);
        position = DataHolder.getCurrentPos();
        cats = DataHolder.getCats();
        items = getList(cats.get(position));
        setWheel(items);
    }

    private List<String> getList(Categorie categorie) {
        List<String> ss = new ArrayList<>();
        for (Item i : categorie.getItems()){
            ss.add(i.getLabel());
        }
        return ss;
    }

    private void setWheel(List<String> items){
        wheel = findViewById(R.id.wheel);
        List<PieEntry> entries = getValues(items);
        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setDrawValues(false);
        pieDataSet.setColors(getColors());
        PieData pieData = new PieData(pieDataSet);
        wheel.getLegend().setEnabled(false);
        wheel.getDescription().setEnabled(false);
        wheel.setEntryLabelColor(Color.BLACK);
        wheel.setData(pieData);
    }

    private List<PieEntry> getValues(List<String> items) {
        List<PieEntry> entries = new ArrayList<>();
        for (String item : items){
            entries.add(new PieEntry(1, item));
        }
        return entries;
    }

    public void spin(View v){
        degreeOld = degree % 360;
        degree = new Random().nextInt(360) + 720;
        RotateAnimation rotateAnimation = new RotateAnimation(degreeOld, degree, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tv.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String s = getSector(360 - (degree % 360));
                backUp(s);
                tv.setText(s);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheel.startAnimation(rotateAnimation);
    }

    private void backUp(String s) {
        Categorie cat = cats.get(position);
        for(Item i : cat.getItems()){
            if (i.getLabel().equals(s)){
                i.increment();
                String json = new Gson().toJson(cats);
                SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("cats", json);
                editor.commit();
                return;
            }
        }
    }

    private String getSector(int degrees) {
        int i = 0;
        String text = null;
        float half_sector = 360f / (float)items.size();
        do {
            float start = half_sector * (i);
            float end = half_sector * (i + 1);
            if (degrees >= start && degrees < end) {
                text = items.get(i);
            }
            i++;
        } while (text == null  &&  i < items.size());
        return text;
    }

    private int[] getColors(){
        int color1 = ContextCompat.getColor(this, R.color.light_green);
        int color2 = ContextCompat.getColor(this, R.color.yellow);
        int color3 = ContextCompat.getColor(this, R.color.blue);
        int color4 = ContextCompat.getColor(this, R.color.pale_orange);
        int color5 = ContextCompat.getColor(this, R.color.colorAccent);
        return new int[]{color1, color2, color3, color4, color5};
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ItemActivity.class);
        DataHolder.setCats(cats);
        DataHolder.setCurrentPos(position);
        startActivity(intent);
    }
}
