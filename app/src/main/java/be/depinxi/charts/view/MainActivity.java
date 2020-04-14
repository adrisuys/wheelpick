package be.depinxi.charts.view;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import be.depinxi.charts.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openChwazi(View v){
        //startActivity(new Intent(this, MainUnityActivity.class));
        String pckg = "com.be.adrisuys.Pickably";
        Intent i = getPackageManager().getLaunchIntentForPackage(pckg);
        startActivity(i);
    }

    public void openWheelpick(View v){
        startActivity(new Intent(this, CategorieActivity.class));
    }

    public void openDice(View v){
        startActivity(new Intent(this, DiceActivity.class));
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }
}
