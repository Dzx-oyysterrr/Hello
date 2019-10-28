package com.example.hello;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Exchange extends AppCompatActivity implements Runnable {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);

        dollar_key = sharedPreferences.getFloat("dollar_rate1", 0.0f);
        eu_key = sharedPreferences.getFloat("eu_rate1", 0.0f);
        won_key = sharedPreferences.getFloat("won_rate1", 0.0f);

        String[] list_data = {"one","two","three","four"};
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list_data);


        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==5){

                    String so=(String)msg.obj;
                    String[] str= so.split(",");

                    for (int i=0;i<=str.length-1;i++)
                    {
                        Log.i(TAG, "rate  "+str[i] );
                    }
                }

                super.handleMessage(msg);
            }
        };
        //开启子进程
        Thread th=new Thread(this);
        th.start();

    }

    private String inputStream25tring(InputStream in) throws IOException {

        String url = "http:www.usd-cny.com/bankofchina.htm";
        Document doc = Jsoup.connect(url).get();
        Elements tables = doc.getElementsByTag("table");

        Element table1 = tables.get(0);
        Elements tds = table1.getElementsByTag("tag");
        for (int i = 0; i <= tds.size(); i += 6) {
            Element td1 = tds.get(i);
            Element td2 = tds.get(i + 3);
            String str1 = td1.text();
            String val = td2.text();
            float v = 100f / Float.parseFloat(val);
        }
        return url;
    }

    private static final String TAG = "MainActivity";
    float dollar_key = 0.1404f;
    float eu_key = 0.1278f;
    float won_key = 167.8756f;

    TextView show;

    public void click(View v) {
        EditText input = findViewById(R.id.txt);
        String str = input.getText().toString();
        float s = Float.parseFloat(str);
        double e = s * eu_key;
        TextView show = findViewById(R.id.show_tmp);
        show.setText(String.format("%.2f", e));
    }

    public void doller(View v) {
        EditText input = findViewById(R.id.txt);
        String str = input.getText().toString();
        float s = Float.parseFloat(str);
        double d = s * dollar_key;
        TextView show = findViewById(R.id.show_tmp);
        show.setText(String.format("%.2f", d));
    }

    public void won(View v) {
        EditText input = findViewById(R.id.txt);
        String str = input.getText().toString();
        float s = Float.parseFloat(str);
        double w = s * won_key;
        TextView show = findViewById(R.id.show_tmp);
        show.setText(String.format("%.2f", w));
    }

    public void sendinf(View v) {
        Intent intent = new Intent(this, NewA.class);

        intent.putExtra("dollar_rate_key", dollar_key);
        intent.putExtra("eu_rate_key", eu_key);
        intent.putExtra("won_rate_key", won_key);

        //startActivity(intent);
        startActivityForResult(intent, 1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_set) {
            Intent intent = new Intent(this, NewA.class);
            intent.putExtra("dollar_rate_key", dollar_key);
            intent.putExtra("eu_rate_key", eu_key);
            intent.putExtra("won_rate_key", won_key);

            //startActivity(intent);
            startActivityForResult(intent, 1);

        } else if (item.getItemId() == R.id.list_open) {
            Intent list = new Intent(this, List.class);
            startActivity(list);

        }else if (item.getItemId() == R.id.list2){
            Intent list = new Intent(this, List2Activity.class);
            startActivity(list);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            dollar_key = bundle.getFloat("key_dollar", 0.01f);
            eu_key = bundle.getFloat("key_eu", 0.01f);
            won_key = bundle.getFloat("key_won", 0.01f);

            SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("dollar_rate1", dollar_key);
            editor.putFloat("eu_rate1", eu_key);
            editor.putFloat("won_rate1", won_key);
            editor.apply();


        }
        super.onActivityResult(requestCode, resultCode, data);

    }



    public void run() {
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message msg = handler.obtainMessage(5);

        msg.obj = "hello";
        handler.sendMessage(msg);

        URL url = null;
        try {
            url = new URL("www.usd-cny.com/bankofchina.htm");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            //String html = inputStream25tring(in);
            String html = inputStream2String(in);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String inputStream2String(InputStream inpurstream) throws IOException {
        final int bufferSize = 1024;
        final  char[] buffer = new char[bufferSize];
        final  StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inpurstream,"gb2312");
        for(;; ){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0, rsz);

        }
        return out.toString();
    }
}
