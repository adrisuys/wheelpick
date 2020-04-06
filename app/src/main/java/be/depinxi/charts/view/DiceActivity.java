package be.depinxi.charts.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import be.depinxi.charts.R;

public class DiceActivity extends AppCompatActivity {

    private int nbDice;
    private ImageView dice1, dice2, dice3, dice4, dice5, dice6;
    private TextView nbDice1, nbDice2, nbDice3, nbDice4, nbDice5, nbDice6;
    List<TextView> values;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
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
        handleTextView();
    }

    private void handleTextView() {
        nbDice1 = findViewById(R.id.nbDice1);
        nbDice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonUI(nbDice1.getText().toString());
            }
        });
        nbDice2 = findViewById(R.id.nbDice2);
        nbDice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonUI(nbDice2.getText().toString());
            }
        });
        nbDice3 = findViewById(R.id.nbDice3);
        nbDice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonUI(nbDice3.getText().toString());
            }
        });
        nbDice4 = findViewById(R.id.nbDice4);
        nbDice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonUI(nbDice4.getText().toString());
            }
        });
        nbDice5 = findViewById(R.id.nbDice5);
        nbDice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonUI(nbDice5.getText().toString());
            }
        });
        nbDice6 = findViewById(R.id.nbDice6);
        nbDice6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonUI(nbDice6.getText().toString());
            }
        });
        values = new ArrayList<>();
        values.add(nbDice1);
        values.add(nbDice2);
        values.add(nbDice3);
        values.add(nbDice4);
        values.add(nbDice5);
        values.add(nbDice6);
    }

    public void roll(View v){
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
    }

    private void setDiceFace(ImageView dice, int value) {
        switch(value){
            case 1 : dice.setBackgroundResource(R.drawable.dice_face_1); break;
            case 2 : dice.setBackgroundResource(R.drawable.dice_face_2); break;
            case 3 : dice.setBackgroundResource(R.drawable.dice_face_3); break;
            case 4 : dice.setBackgroundResource(R.drawable.dice_face_4); break;
            case 5 : dice.setBackgroundResource(R.drawable.dice_face_5); break;
            case 6 : dice.setBackgroundResource(R.drawable.dice_face_6); break;
        }
    }

    private int rollDices(){
        return (int)(Math.random() * 6) + 1;
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
}
