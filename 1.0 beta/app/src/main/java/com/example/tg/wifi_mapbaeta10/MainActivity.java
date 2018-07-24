package com.example.tg.wifi_mapbaeta10;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocationClient;

import org.json.JSONArray;
import org.json.JSONObject;
import com.amap.api.maps2d.MapView;

public class MainActivity extends AppCompatActivity {

    Information info = new Information();
    JSONArray json = new JSONArray();
    String json_string;
    String longitude = "longitude";
    String lantitude = "lantitude";

    //定位服务类
    public AMapLocationClient mlocationClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlocationClient = new AMapLocationClient(getApplicationContext());
        final get2Address location = new get2Address(mlocationClient,1000);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    longitude = String.valueOf(location.getinfo(0));
                    lantitude = String.valueOf(location.getinfo(1));
                    json = info.GetWifiinfomation(MainActivity.this);
                    json_string = json.toString();
                    json = null;
                    InterUtils utils = new InterUtils();
                    utils.WifiPost(json_string,longitude,lantitude);
                    System.out.println(lantitude);
                    System.out.println(longitude);
                    System.out.println();
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                 }
            }
        };
        Thread t = new Thread(runnable);
        t.start();


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,gaoDeMap.class);
                startActivity(intent);
            }
        });
/*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                json = info.GetWifiinfomation(MainActivity.this);
                json_string = json.toString();
                String longitude = "";
                String lantitude = "";
                System.out.println(json_string);
                InterUtils utils = new InterUtils();
                utils.WifiPost(json_string,longitude,lantitude);
            }
        };

        new Thread(runnable).start();
        */
    }


}
