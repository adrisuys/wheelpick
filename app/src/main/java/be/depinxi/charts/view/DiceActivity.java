package be.depinxi.charts.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import be.depinxi.charts.R;
import be.depinxi.charts.model.DataHolder;

public class DiceActivity extends AppCompatActivity {

    private int nbDice;
    private ImageView dice1, dice2, dice3, dice4, dice5, dice6;
    private List<TextView> values;
    private TextView score;
    private LinearLayout bg;
    private int sum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        bg = findViewById(R.id.background);
        DataHolder.setDarkMode(true);
        bg.setBackgroundColor(DataHolder.isDarkModeEnabled() ? Color.BLACK : Color.WHITE);
        nbDice = 1;
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice2.setVisibility(View.INVISIBLE);
        dice3 = findViewById(R.id.dice3);
        dice3.setVisibility(View.INVISIBLE);
        dice4 = findViewById(R.id.dice4);
        dice4.setVisibility(View.INVISIBLE);
        dice5 = findViewById(R.id.dice5);
        dice5.setVisibility(View.INVISIBLE);
        dice6 = findViewById(R.id.dice6);
        dice6.setVisibility(View.INVISIBLE);
        score = findViewById(R.id.score);
        score.setTextColor(DataHolder.isDarkModeEnabled() ? Color.WHITE : Color.BLACK);
        handleTextView();
    }

    private void handleTextView() {
        values = new ArrayList<>();
        values.add((TextView) findViewById(R.id.nbDice1));
        values.add((TextView) findViewById(R.id.nbDice2));
        values.add((TextView) findViewById(R.id.nbDice3));
        values.add((TextView) findViewById(R.id.nbDice4));
        values.add((TextView) findViewById(R.id.nbDice5));
        values.add((TextView) findViewById(R.id.nbDice6));
        for (final TextView tv : values){
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleButtonUI(tv.getText().toString());
                }
            });
            tv.setTextColor(DataHolder.isDarkModeEnabled() ? Color.WHITE : Color.BLACK);
        }
    }

    public void roll(View v){
        sum = 0;
        score.setText("");
        setDiceFace(dice1, rollDices());
        if (nbDice >= 2){
            setDiceFace(dice2, rollDices());
        }
        if (nbDice >= 3){
            setDiceFace(dice3, rollDices());
        }
        if (nbDice >= 4){
            setDiceFace(dice4, rollDices());
        }
        if (nbDice >= 5){
            setDiceFace(dice5, rollDices());
        }
        if (nbDice >= 6){
            setDiceFace(dice6, rollDices());
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                score.setText(String.valueOf(sum));
            }
        }, 2500);
    }

    public void switchDarkMode(View v){
        DataHolder.setDarkMode(!DataHolder.isDarkModeEnabled());
        bg.setBackgroundColor(DataHolder.isDarkModeEnabled() ? Color.BLACK : Color.WHITE);
        for (TextView tv : values){
            tv.setTextColor(DataHolder.isDarkModeEnabled() ? Color.WHITE : Color.BLACK);
        }
        score.setTextColor(DataHolder.isDarkModeEnabled() ? Color.WHITE : Color.BLACK);
    }

    private void setDiceFace(ImageView dice, int value) {
        switch(value){
            case 1 :
                setUIDiceFace(dice, R.drawable.diceroll1);
                break;
            case 2 :
                setUIDiceFace(dice, R.drawable.diceroll2);
                break;
            case 3 :
                setUIDiceFace(dice, R.drawable.diceroll3);
                break;
            case 4 :
                setUIDiceFace(dice, R.drawable.diceroll4);
            break;
            case 5 :
                setUIDiceFace(dice, R.drawable.diceroll5);
            break;
            case 6 :
                setUIDiceFace(dice, R.drawable.diceroll6);
            break;
        }
    }

    private int rollDices(){
        int rdm = (int)(Math.random() * 6) + 1;
        sum += rdm;
        return rdm;
    }

    private void handleButtonUI(String text){
        nbDice = Integer.parseInt(text);
        for (TextView tv : values){
            if (tv.getText().toString().equals(text)){
                tv.setBackgroundResource(R.drawable.circle);
            } else {
                tv.setBackgroundResource(R.drawable.no_circle);
            }
        }
        showDice();
    }

    private void showDice() {
        if (nbDice == 6){
            dice1.setVisibility(View.VISIBLE);
            dice2.setVisibility(View.VISIBLE);
            dice3.setVisibility(View.VISIBLE);
            dice4.setVisibility(View.VISIBLE);
            dice5.setVisibility(View.VISIBLE);
            dice6.setVisibility(View.VISIBLE);
        } else if (nbDice == 5){
            dice1.setVisibility(View.VISIBLE);
            dice2.setVisibility(View.VISIBLE);
            dice3.setVisibility(View.VISIBLE);
            dice4.setVisibility(View.VISIBLE);
            dice5.setVisibility(View.VISIBLE);
            dice6.setVisibility(View.INVISIBLE);
        } else if (nbDice == 4){
            dice1.setVisibility(View.VISIBLE);
            dice2.setVisibility(View.VISIBLE);
            dice3.setVisibility(View.VISIBLE);
            dice4.setVisibility(View.VISIBLE);
            dice5.setVisibility(View.INVISIBLE);
            dice6.setVisibility(View.INVISIBLE);
        } else if (nbDice == 3){
            dice1.setVisibility(View.VISIBLE);
            dice2.setVisibility(View.VISIBLE);
            dice3.setVisibility(View.VISIBLE);
            dice4.setVisibility(View.INVISIBLE);
            dice5.setVisibility(View.INVISIBLE);
            dice6.setVisibility(View.INVISIBLE);
        } else if (nbDice == 2){
            dice1.setVisibility(View.VISIBLE);
            dice2.setVisibility(View.VISIBLE);
            dice3.setVisibility(View.INVISIBLE);
            dice4.setVisibility(View.INVISIBLE);
            dice5.setVisibility(View.INVISIBLE);
            dice6.setVisibility(View.INVISIBLE);
        } else {
            dice1.setVisibility(View.VISIBLE);
            dice2.setVisibility(View.INVISIBLE);
            dice3.setVisibility(View.INVISIBLE);
            dice4.setVisibility(View.INVISIBLE);
            dice5.setVisibility(View.INVISIBLE);
            dice6.setVisibility(View.INVISIBLE);
        }
    }

    private void setUIDiceFace(final ImageView dice, final int drawable){
        Glide.with(this).asGif().load(drawable).listener(new RollDiceListener()).into(dice);
    }

    private class RollDiceListener implements RequestListener {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            return true;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            if (resource instanceof GifDrawable){
                ((GifDrawable)resource).setLoopCount(1);
            }
            return false;
        }
    }
}
