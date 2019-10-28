package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void btn4(View view){
        TextView out = (TextView)findViewById(R.id.out);
        out.setText("0");
    }


}
