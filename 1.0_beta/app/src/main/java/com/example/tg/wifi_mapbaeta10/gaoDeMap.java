package com.example.tg.wifi_mapbaeta10;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

import java.io.BufferedReader;

public class gaoDeMap extends Activity {

    MapView mMapView = null;
    int width,height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gao_de_map);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        final AMap aMap = mMapView.getMap();

        //定位小蓝点
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.interval(2000);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);


        //标点测试
        LatLng latLng = new LatLng(35.173055,109.940383);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("title").snippet("text"));
        LatLng latLng1 = new LatLng(35.173155,109.940483);
        final Marker marker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("title1").snippet("text1"));


        //screenInfo screen = null;
        //screen = (screenInfo) new screenInfo();


        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels; // screen_width
        height = dm.heightPixels; //screen_height
        // dm.density;//获取像素密度

        Button button = (Button)findViewById(R.id.find);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Marker markerl = null;
                Marker markerr = null;
                LatLng mkr = adjustCamerar(aMap,height,width);
                LatLng mkl = adjustCameral(aMap);
                markerr = aMap.addMarker((new MarkerOptions().position(mkr).title("edger").snippet(String.valueOf(mkr.latitude)+"\n"+String.valueOf(mkr.longitude))));
                markerl = aMap.addMarker((new MarkerOptions().position(mkl).title("edgel").snippet(String.valueOf(mkl.latitude)+"\n"+String.valueOf(mkl.longitude))));
            }
        });







    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    protected LatLng adjustCamerar(AMap aMap,int height,int width){
        Projection projection = aMap.getProjection();
        Point right = new Point(width,height);
        LatLng rightlatlng = projection.fromScreenLocation(right);
        LatLngBounds bounds = LatLngBounds.builder().include(rightlatlng).build();
        aMap.getMapScreenMarkers();
        return rightlatlng;

    }

    protected LatLng adjustCameral(AMap aMap){
        Projection projection = aMap.getProjection();
        Point left = new Point(0,0);
        LatLng leftlatlng = projection.fromScreenLocation(left);
        LatLngBounds bounds = LatLngBounds.builder().include(leftlatlng).build();
        aMap.getMapScreenMarkers();
        return leftlatlng;

    }








}

