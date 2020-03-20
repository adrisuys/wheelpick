package be.depinxi.charts.view;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.depinxi.charts.R;

public class WheelActivity extends AppCompatActivity {

    private PieChart wheel;
    private TextView tv;
    private int degree, degreeOld;
    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        degreeOld = 0;
        degree = 0;
        tv = findViewById(R.id.result);
        items = getIntent().getStringArrayListExtra("items");
        setWheel(items);
    }

    private void setWheel(List<String> items){
        wheel = findViewById(R.id.wheel);
        List<PieEntry> entries = getValues(items);
        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setDrawValues(false);
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
                tv.setText(getSector(360 - (degree % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheel.startAnimation(rotateAnimation);
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
}
