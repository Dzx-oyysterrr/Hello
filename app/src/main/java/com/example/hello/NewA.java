package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NewA extends AppCompatActivity {
    EditText inf1;
    EditText inf2;
    EditText inf3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent intent= getIntent();
        float dollar2 = intent.getFloatExtra("dollar_rate_key",0.0f);
        float eu2 = intent.getFloatExtra("eu_rate_key",0.0f);
        float won2 = intent.getFloatExtra("won_rate_key",0.0f);

        inf1 = (EditText) findViewById(R.id.inf1);
        inf2 = (EditText) findViewById(R.id.inf2);
        inf3 = (EditText) findViewById(R.id.inf3);

        inf1.setText(String.valueOf(dollar2));
        inf2.setText(String.valueOf(eu2));
        inf3.setText(String.valueOf(won2));

        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);

        dollar2 = sharedPreferences.getFloat("dollar_rate1",0.0f);
        eu2 = sharedPreferences.getFloat("eu_rate1",0.0f);
        won2 = sharedPreferences.getFloat("won_rate1",0.0f);

        SharedPreferences sp = getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("dollar_rate1",dollar2);
        editor.putFloat("eu_rate1",eu2);
        editor.putFloat("won_rate1",won2);
        editor.apply();

    }
    public void save(View v){
        float newdollar = Float.parseFloat(inf1.getText().toString());
        float neweu = Float.parseFloat(inf2.getText().toString());
        float newwon = Float.parseFloat(inf3.getText().toString());

        Intent intent = getIntent();
        Bundle bd = new Bundle();
        bd.putFloat("key_dollar",newdollar);
        bd.putFloat("key_eu",neweu);
        bd.putFloat("key_won",newwon);
        intent.putExtras(bd);
        setResult(2,intent);
        finish();
    }


}
