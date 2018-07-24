package com.example.tg.wifi_mapbaeta10;
import android.content.Context;
import   java.text.SimpleDateFormat;
import android.net.wifi.WifiManager;
import android.net.wifi.ScanResult;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Information extends AppCompatActivity{
    /*获得的数据*/
    public String  ssid,bssid,capability;
    public int level;
    int frequency;
    double distance;
    public String timestamp;
    int channel;
    /*每个数据作为json*/
    JSONObject wifi_json = new JSONObject();
    SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
    /*得到扫描信息的方法*/
    public JSONArray GetWifiinfomation(Context context){
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> scanResults = wifiManager.getScanResults();
        System.out.println(scanResults);
        JSONArray json = new JSONArray();
        for(ScanResult scanResult : scanResults){
            Date curDate =  new Date(System.currentTimeMillis());
            distance = Math.pow(10,((Math.abs(scanResult.level)-45)/(10*2.25))); //计算距离
            ssid = scanResult.SSID;
            bssid = scanResult.BSSID;
            level = scanResult.level;
            timestamp = formatter.format(curDate);
            capability = scanResult.capabilities;
            frequency = scanResult.frequency;
            if(frequency<2472&&frequency>2412){
                channel = (frequency-2407)/5;
            }else if(frequency==2484){
                channel = 14;
            }
            switch (frequency) {
                case 5745:
                    channel = 149;
                case 5765:
                    channel = 153;
                case 5785:
                    channel = 157;
                case 5805:
                    channel = 161;
                case 5825:
                    channel = 165;
            }
            wifi_json = new JSONObject();
            try{
                wifi_json.put("ssid",ssid);
                wifi_json.put("channel",channel);
                wifi_json.put("bssid",bssid);
                wifi_json.put("capability",capability);
                wifi_json.put("level",level);
                wifi_json.put("distance",distance);
                wifi_json.put("timestamp",timestamp);
                json.put(wifi_json);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(json.toString());
        return json;
    }

}
