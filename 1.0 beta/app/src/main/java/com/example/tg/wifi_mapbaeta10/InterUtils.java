package com.example.tg.wifi_mapbaeta10;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class InterUtils {
    public boolean WifiPost(String json_string, String lantitude, String longitude){
        String path = "http://120.77.152.169/wifiproject/test.php";
        String json_data = json_string;
        try{
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            String data = "json_data=" + URLEncoder.encode(json_data,"utf-8") + "&lantitude=" + URLEncoder.encode(lantitude,"utf-8") + "&longitude=" + URLEncoder.encode(longitude,"utf-8");
            System.out.println(data);
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",data.length()+"");
            connection.setDoOutput(true);
            connection.getOutputStream().write(data.getBytes());
            //获得结果码
            int respondCode = connection.getResponseCode();
            System.out.println(respondCode);
            if(respondCode==200){
                //请求成功
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
