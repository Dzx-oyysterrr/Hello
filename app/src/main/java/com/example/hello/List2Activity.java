package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class List2Activity extends ListActivity {

    private  static String TAG="main";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        final ListView listView=(ListView)findViewById(R.id.listview1);


        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what ==6 ) {
                    ArrayList<HashMap<String, String>> listItems = (ArrayList<HashMap<String, String>>) msg.obj;

                    Myada myAdapter = new Myada(List2Activity.this,
                            R.layout.activity_list2,
                            listItems);
                    Log.i(TAG, "listView");
                    listView.setAdapter(myAdapter);
                }

                super.handleMessage(msg);
            }
        };
        //开启子进程
        Thread th=new Thread((Runnable) this);
        th.start();
    }

    public void run() {


        Log.i(TAG, "run=  .......... " );
        for(int i=1;i<2;i++)
        {

            Log.i(TAG, "run i=  "+i );
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }





        String url="http://www.usd-cny.com/bankofchina.htm";
        try {
            Document doc= Jsoup.connect(url).get();
            Elements tables=doc.getElementsByTag("table");
            Element table6=tables.get(0);
            Elements tds=table6.getElementsByTag("td");

            System.out.println(tds.size());
            //List<String> list=new ArrayList<String>();
            ArrayList<HashMap<String, String>> listItems = new ArrayList<HashMap<String, String>>();


            for(int i=0;i<=tds.size()-6;i+=6)
            {
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String str1=td1.text();
                String val=td2.text();
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("country",str1);
                map.put("ratio",val);
                listItems.add(map);

                //list.add(str1+"----->"+val);

                Log.i(TAG, str1);
                Log.i(TAG, val);

            }


            Message msg=handler.obtainMessage(6);//获取对象 返回主线程
            //msg.what=6;
            //msg.obj=list;
            msg.obj=listItems;
            handler.sendMessage(msg);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }






    }





}
