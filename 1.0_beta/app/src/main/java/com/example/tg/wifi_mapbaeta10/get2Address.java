package com.example.tg.wifi_mapbaeta10;

import android.support.v7.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class get2Address extends AppCompatActivity implements AMapLocationListener {
    //定位参数类
    private AMapLocationClientOption locationClientOption = null;
    public double Latitude;
    public double Longtitude;


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null){
            if (aMapLocation.getErrorCode() == 0) {
                Latitude = aMapLocation.getLatitude();
                Longtitude = aMapLocation.getLongitude();

            }
        }


    }

    public get2Address(AMapLocationClient mlocationClient, int timestep)  {



        //初始化定位

        mlocationClient.setLocationListener(this);
        //定位参数设置
        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClientOption.setInterval(timestep);



        //单次定位
        //locationClientOption.setOnceLocation(true);
        //locationClientOption.setOnceLocationLatest(true);

        mlocationClient.setLocationOption(locationClientOption);
        mlocationClient.startLocation();



    }

    public double getinfo(int flag) {
        if (flag == 1) {
            if (Latitude != 0) {
                return Latitude;
            } else {
                return 0;
            }

        }
        else if (flag == 0) {
            if (Longtitude != 0) {
                return Longtitude;
            } else {
                return 0;
            }
        }
        return 0;
    }

}
