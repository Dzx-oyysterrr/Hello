package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void click(int i ){
        TextView out = (TextView)findViewById(R.id.out);
        String str = (String)out.getText();
        String n = String.valueOf(Integer.parseInt(str) + i);
        out.setText(n);
    }

    public void btn1(View view){
        click(1);
    }

    public void btn2(View view){
        click(2);
    }

    public void btn3(View view){
        click(3);
    }

    public void click1(int i ){
        TextView out1 = (TextView)findViewById(R.id.out1);
        String str = (String)out1.getText();
        String n = String.valueOf(Integer.parseInt(str) + i);
        out1.setText(n);

    }
    public void btn1_1(View view){
        click1(1);
    }

    public void btn1_2(View view){
        click1(2);
    }

    public void btn1_3(View view){
        click1(3);
    }

    public void btn5(View view){
        TextView out1 = (TextView)findViewById(R.id.out1);
        TextView out = (TextView)findViewById(R.id.out);
        out.setText("0");
        out1.setText("0");
    }
}
