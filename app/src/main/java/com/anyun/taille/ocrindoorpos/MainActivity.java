package com.anyun.taille.ocrindoorpos;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.*;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.Word;
import com.baidu.ocr.ui.camera.CameraActivity;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.ui.camera.CameraActivity;

import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.anyun.taille.ocrindoorpos.R.drawable.location;


public class MainActivity extends Activity  {

    MapView mMapView = null;
    AMap aMap;
    Button bt1;
    private int sid1;
    private String name;

    private static final int REQUEST_CODE_GENERAL = 105;
    private static final int REQUEST_CODE_GENERAL_BASIC = 106;
    private static final int REQUEST_CODE_ACCURATE_BASIC = 107;
    private static final int REQUEST_CODE_ACCURATE = 108;
    private static final int REQUEST_CODE_GENERAL_ENHANCED = 109;
    private static final int REQUEST_CODE_GENERAL_WEBIMAGE = 110;
    private static final int REQUEST_CODE_BANKCARD = 111;
    private static final int REQUEST_CODE_VEHICLE_LICENSE = 120;
    private static final int REQUEST_CODE_DRIVING_LICENSE = 121;
    private static final int REQUEST_CODE_LICENSE_PLATE = 122;
    private static final int REQUEST_CODE_BUSINESS_LICENSE = 123;
    private static final int REQUEST_CODE_RECEIPT = 124;
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;

    private Marker marker;
    private MarkerOptions markerOption;
    private MarkerOptions markerOption1;
    private Marker marker1;
    private MarkerOptions markerOption2;
    private Marker marker2;
    private MarkerOptions markerOption3;
    private Marker marker3;
    private MarkerOptions markerOption4;
    private Marker marker4;
    private MarkerOptions markerOption5;
    private Marker marker5;
    private MarkerOptions markerOption6;
    private Marker marker6;
    private MarkerOptions markerOption7;
    private Marker marker7;
    private MarkerOptions markerOption8;
    private Marker marker8;
    private MarkerOptions markerOption9;
    private Marker marker9;
    private MarkerOptions markerOption10;
    private Marker marker10;
    private MarkerOptions markerOption11;
    private Marker marker11;
    private MarkerOptions markerOption12;
    private Marker marker12;
    private MarkerOptions markerOption13;
    private Marker marker13;
    private MarkerOptions markerOption14;
    private Marker marker14;
    private MarkerOptions markerOption15;
    private Marker marker15;
    private MarkerOptions markerOption16;
    private Marker marker16;
    private MarkerOptions markerOption17;
    private Marker marker17;
    private MarkerOptions markerOption18;
    private Marker marker18;


    private boolean hasGotToken = false;

    private AlertDialog.Builder alertDialog;


    private static final int REQUEST_CODE_CAMERA = 1000;
    private static final int REQUEST_CODE_PICK_IMAGE = 2000;

    private String path = new String();
    private TextView txtResult;//返回的数据
    private List<String> resultList = new ArrayList<>();

    //声明一个类存放百度服务器传回后通过GSON解析的json数据
    public class JsonBean{
        public String log_id;
        public String direction;
        public String words_result_num;
        public List<WORDS_RESULT> words_result;

        public String getLog_id(){
            return log_id;
        }
        public void setLog_id(String log_id){
            this.log_id=log_id;
        }
        public String getDirection(){
            return direction;
        }
        public void setDirection(String direction){
            this.direction=direction;
        }
        public String getWords_result_num(){
            return words_result_num;
        }
        public void setWords_result_num(String words_result_num){
            this.words_result_num=words_result_num;
        }
        public List<WORDS_RESULT> getWords_result(){
            return words_result;
        }
        public void setWords_result(List<WORDS_RESULT> words_result){
            this.words_result=words_result;
        }
    }
    //返回json中存在识别结果嵌套数组，放在另一个类里
    public  class WORDS_RESULT{
        public String words;
        public String getWords(){
            return words;
        }
        public void setWords(String words){
            this.words=words;
        }
    }


//    qoSsGuvUSAORoTTvTApGk3nl   oppPFGIKRGBSOeKh9SQdYV4Gwoq6Zpq1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertDialog = new AlertDialog.Builder(this);

        final Button bt1 = (Button)findViewById(R.id.button2);
//        Button bt2 = (Button)findViewById(R.id.button3);
        Button bt2 = (Button)findViewById(R.id.button3);
        Button bt3 = (Button)findViewById(R.id.button4);

        bt1.setText("3D地图");
        sid1 = 1;
        bt1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if (sid1==1){
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                    sid1 = 0;
                    bt1.setText("卫星地图");
                }else{
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                    sid1 = 1;
                    bt1.setText("3D地图");
                }
            }
        });

        bt2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
//                screenshot();


                    Uri imageUri = Uri.fromFile(new File("/storage/emulated/0/1/sc.png"));
                    Intent shareIntent = new Intent();
                    ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");//包名和界面的路径，这个是分享给微信好友的
//ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");//包名和界面的路径，这个是分享给微信朋友圈
                    shareIntent.setComponent(comp);
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setType("image/*");
                    startActivity(Intent.createChooser(shareIntent, "分享图片"));
                }

        });

        bt3.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if (!checkTokenStatus()) {
                    return;
                }
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent, REQUEST_CODE_GENERAL_BASIC);
            }
        });



//        LatLng ct = new LatLng(31.86567653,117.29540348);
        LatLng ct = new LatLng(31.81721141,117.23288119);
        AMapOptions mapOptions = new AMapOptions();
        mapOptions.camera(new CameraPosition(ct, 10f, 0, 0));

        mMapView = new MapView(this, mapOptions);

        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        aMap = mMapView.getMap();

        aMap.setTrafficEnabled(true);// 显示实时交通状况
        aMap.showIndoorMap(true);

//        LatLng latLng = new LatLng(31.86752622,117.30075717);
        LatLng latLng = new LatLng(31.81721141,117.23288119);

        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
//        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 普通地图模式
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,19));


//        LatLng latLng = new LatLng(39.906901,116.397972);
//        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("您的位置").snippet("周生生珠宝店"));
//        marker.setVisible(false);
//        marker.showInfoWindow();


//        //设置显示定位按钮 并且可以点击
//        UiSettings settings = aMap.getUiSettings();
//        //设置定位监听
//        aMap.setLocationSource(this);
//        // 是否显示定位按钮
//        settings.setMyLocationButtonEnabled(true);
//        // 是否可触发定位并显示定位层
//        aMap.setMyLocationEnabled(true);



//        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.position(SyncStateContract.Constants.HEFEI);
//        markerOption.title("煲煲").snippet("合肥市：117.30075181, 31.86786335");
//
//        markerOption.draggable(true);//设置Marker可拖动
//        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                .decodeResource(getResources(),R.drawable.location_marker)));
//        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
//        markerOption.setFlat(true);//设置marker平贴地图效果


        initAccessToken();

    }

    public static Bitmap loadBitmapFromView(View view) {

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        int w1 = view.getLeft();
        int h1 = view.getTop();

        //int w2 = view.getWidth()+view.getLeft();
        //int h2 = view.getHeight()+view.getTop();
        //相当于上面的
        int w2 = view.getRight();
        int h2 = view.getBottom();

        //w2-w1, h2-h1这个两个参数要注意一下，要不图片的背景色和图片的大小不一
        Bitmap bmp = Bitmap.createBitmap(w2-w1, h2-h1,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        view.layout(w1, h1, w2, h2);
        view.draw(c);

        return bmp;
    }

    public void saveBitmap(Context context, Bitmap bitmap) {

        String sdCardDir = Environment.getExternalStorageDirectory() + "/DCIM/";
        File appDir = new File(sdCardDir, "cache");//文件夹名称
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //文件名，这里你是如果要保存到本地，每次保存的图片的名称都得不一样
        //文件名加一个当前时间就好：System.currentTimeMillis()
        String fileName =  "pos.jpg";
        File mFile = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(mFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Tag-----", appDir+fileName);//这里做一个简单的打印
    }

    private void screenshot()
    {
        // 获取屏幕
        View dView = getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bmp = dView.getDrawingCache();
        if (bmp != null)
        {
            try {
                // 获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                // 图片文件路径
                String filePath = sdCardPath + File.separator + "screenshot.png";

                File file = new File(filePath);
                FileOutputStream os = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
            }
        }
    }

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            Toast.makeText(getApplicationContext(), "token还未成功获取", Toast.LENGTH_LONG).show();
        }
        return hasGotToken;
    }

    private void initAccessToken() {
        OCR.getInstance().initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("licence方式获取token失败", error.getMessage());
            }
        }, getApplicationContext());
    }


    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }




    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext(), "oksvdnQVaCqKKPiQxzecXCuV", "ZyAIz2diDGVPRMwA0b84V5tstv9O8UpI");
    }

    private void alertText(final String title, final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }

    private void infoPopText(final String result) {
        //利用GSON解析传回的json字符串，利用迭代累加到message中
        Gson gson=new Gson();
        String message="";
        JsonBean jsonBean=gson.fromJson(result,JsonBean.class);
        for(Iterator i = jsonBean.getWords_result().iterator(); i.hasNext();){
            WORDS_RESULT wordsResult=(WORDS_RESULT)i.next();
            message+=wordsResult.getWords();
        }
        alertText("识别结果", message);
//        if(message.contains("周生生")){
//            LatLng latLng = new LatLng(32.2047408251,119.4487934716);
//            final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("您的位置").snippet("周生生珠宝店"));
////            marker.setVisible(true);
////            marker.showInfoWindow();
//        }
        if(message.contains("西贝")){
//                                MarkerOptions locationOption = new MarkerOptions().icon(BitmapDescriptorFactory.
//                                        defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                                        .position(new LatLng(32.2047408251, 119.4487934716)) //latitude -38 is the center of picture
//                                        .title("您的位置")
//                                        .snippet("周生生")
//                                        .draggable(false)
//                                        .visible(true);
//                                location = aMap.addMarker(locationOption);
//                                location.showInfoWindow();
//
            showlab1();
        }
        if(message.contains("风")){
//                                MarkerOptions locationOption = new MarkerOptions().icon(BitmapDescriptorFactory.
//                                        defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                                        .position(new LatLng(32.2047408251, 119.4487934716)) //latitude -38 is the center of picture
//                                        .title("您的位置")
//                                        .snippet("周生生")
//                                        .draggable(false)
//                                        .visible(true);
//                                location = aMap.addMarker(locationOption);
//                                location.showInfoWindow();
//
            showlab2();
        }

        if(message.contains("煲")){
//                                MarkerOptions locationOption = new MarkerOptions().icon(BitmapDescriptorFactory.
//                                        defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                                        .position(new LatLng(32.2047408251, 119.4487934716)) //latitude -38 is the center of picture
//                                        .title("您的位置")
//                                        .snippet("周生生")
//                                        .draggable(false)
//                                        .visible(true);
//                                location = aMap.addMarker(locationOption);
//                                location.showInfoWindow();
//
            showlab3();
        }

        if(message.contains("主人")){
//                                MarkerOptions locationOption = new MarkerOptions().icon(BitmapDescriptorFactory.
//                                        defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                                        .position(new LatLng(32.2047408251, 119.4487934716)) //latitude -38 is the center of picture
//                                        .title("您的位置")
//                                        .snippet("周生生")
//                                        .draggable(false)
//                                        .visible(true);
//                                location = aMap.addMarker(locationOption);
//                                location.showInfoWindow();
//
            showlab4();
        }

        if(message.contains("大姐姐")){
//                                MarkerOptions locationOption = new MarkerOptions().icon(BitmapDescriptorFactory.
//                                        defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                                        .position(new LatLng(32.2047408251, 119.4487934716)) //latitude -38 is the center of picture
//                                        .title("您的位置")
//                                        .snippet("周生生")
//                                        .draggable(false)
//                                        .visible(true);
//                                location = aMap.addMarker(locationOption);
//                                location.showInfoWindow();
//
            showlab5();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initAccessToken();
        } else {
            Toast.makeText(getApplicationContext(), "需要android.permission.READ_PHONE_STATE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 识别成功回调，通用文字识别（含位置信息）
        if (requestCode == REQUEST_CODE_GENERAL && resultCode == Activity.RESULT_OK) {
            RecognizeService.recGeneral(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            try
                            {
                            JSONObject demoJson = new JSONObject(result);
                                name = demoJson.optString("words_result");
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Gson gson=new Gson();
                            String message="";
                            JsonBean jsonBean=gson.fromJson(name,JsonBean.class);
                            for(Iterator i = jsonBean.getWords_result().iterator(); i.hasNext();){
                                WORDS_RESULT wordsResult=(WORDS_RESULT)i.next();
                                message+=wordsResult.getWords();}
                            infoPopText(name);
                        }
                    });
        }

        // 识别成功回调，通用文字识别（含位置信息高精度版）
        if (requestCode == REQUEST_CODE_ACCURATE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recAccurate(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，通用文字识别
        if (requestCode == REQUEST_CODE_GENERAL_BASIC && resultCode == Activity.RESULT_OK) {
            RecognizeService.recGeneralBasic(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，通用文字识别（高精度版）
        if (requestCode == REQUEST_CODE_ACCURATE_BASIC && resultCode == Activity.RESULT_OK) {
            RecognizeService.recAccurateBasic(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，通用文字识别（含生僻字版）
        if (requestCode == REQUEST_CODE_GENERAL_ENHANCED && resultCode == Activity.RESULT_OK) {
            RecognizeService.recGeneralEnhanced(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，网络图片文字识别
        if (requestCode == REQUEST_CODE_GENERAL_WEBIMAGE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recWebimage(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD && resultCode == Activity.RESULT_OK) {
            RecognizeService.recBankCard(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，行驶证识别
        if (requestCode == REQUEST_CODE_VEHICLE_LICENSE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recVehicleLicense(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，驾驶证识别
        if (requestCode == REQUEST_CODE_DRIVING_LICENSE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recDrivingLicense(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，车牌识别
        if (requestCode == REQUEST_CODE_LICENSE_PLATE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recLicensePlate(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，营业执照识别
        if (requestCode == REQUEST_CODE_BUSINESS_LICENSE && resultCode == Activity.RESULT_OK) {
            RecognizeService.recBusinessLicense(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

        // 识别成功回调，通用票据识别
        if (requestCode == REQUEST_CODE_RECEIPT && resultCode == Activity.RESULT_OK) {
            RecognizeService.recReceipt(FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath(),
                    new RecognizeService.ServiceListener() {
                        @Override
                        public void onResult(String result) {
                            infoPopText(result);
                        }
                    });
        }

    }


    private void showlab1() {
//        alertText("run","");

        LatLng latLng = new LatLng(31.81721141,117.23288119);
        LatLng latLng2 = new LatLng(31.817257,117.23340154);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("您的位置").snippet("西贝莜面村").visible(true));
//                                marker.setVisible(true);
        marker.showInfoWindow();
//        alertText("end","");
    }

    private void showlab2() {
//        alertText("run","");

        LatLng latLng2 = new LatLng(31.817257,117.23340154);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng2).title("您的位置").snippet("避风塘餐厅").visible(true));
//                                marker.setVisible(true);
        marker.showInfoWindow();
//        alertText("end","");
    }

    private void showlab3() {
//        alertText("run","");

        LatLng latLng2 = new LatLng(31.86753533,117.3007679);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng2).title("煲煲的位置").snippet("在家等主人").visible(true));
//                                marker.setVisible(true);
        marker.showInfoWindow();
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2,19));
//        alertText("end","");
    }

    private void showlab4() {
//        alertText("run","");

        LatLng latLng2 = new LatLng(32.19671451,119.50987816);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng2).title("主人的位置").snippet("在实验室上班").visible(true));
//                                marker.setVisible(true);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2,19));
        marker.showInfoWindow();
//        alertText("end","");
    }

    private void showlab5() {
//        alertText("run","");

        LatLng latLng2 = new LatLng(31.2785096,121.50019526);
        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng2).title("大姐姐的位置").snippet("在寝室赖床").visible(true));
//                                marker.setVisible(true);
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2,19));
        marker.showInfoWindow();
//        alertText("end","");
    }

    public void onBackPressed(){
        marker.hideInfoWindow();
        marker.setVisible(false);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        OCR.getInstance().release();
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

    private void addMarkersToMap() {
        //112
//        markerOption1 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room112))
//                .position(new LatLng(32.196800, 119.510266)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker1 = aMap.addMarker(markerOption1);
//        //110
//        markerOption2 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room110))
//                .position(new LatLng(32.196822, 119.510136)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker2 = aMap.addMarker(markerOption2);
//        //108
//        markerOption3 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room108))
//                .position(new LatLng(32.196871, 119.509710)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker3 = aMap.addMarker(markerOption3);
//        //106
//        markerOption4 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room106))
//                .position(new LatLng(32.196897, 119.509535)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker4 = aMap.addMarker(markerOption4);
//        //104
//        markerOption5 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room104))
//                .position(new LatLng(32.196915, 119.509380)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker5 = aMap.addMarker(markerOption5);
//        //102
//        markerOption6 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room102))
//                .position(new LatLng(32.196944, 119.509221)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker6 = aMap.addMarker(markerOption6);
//        //toilet
//        markerOption7 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.toilet))
//                .position(new LatLng(32.196762, 119.509202)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker7 = aMap.addMarker(markerOption7);
//        //elevator
//        markerOption8 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.elevator))
//                .position(new LatLng(32.196504, 119.509934)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker8 = aMap.addMarker(markerOption8);
//        //117
//        markerOption9 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room117))
//                .position(new LatLng(32.196642, 119.510285)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker9 = aMap.addMarker(markerOption9);
//        //115
//        markerOption10 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room115))
//                .position(new LatLng(32.196487, 119.510255)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker10 = aMap.addMarker(markerOption10);
//        //113
//        markerOption11 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room113))
//                .position(new LatLng(32.196257, 119.510185)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker11 = aMap.addMarker(markerOption11);
//        //111
//        markerOption12 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room111))
//                .position(new LatLng(32.196292, 119.510000)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker12 = aMap.addMarker(markerOption12);
//        //109
//        markerOption13 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room109))
//                .position(new LatLng(32.196302, 119.509865)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker13 = aMap.addMarker(markerOption13);
//        //107
//        markerOption14 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room107))
//                .position(new LatLng(32.196342, 119.509600)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker14 = aMap.addMarker(markerOption14);
//        //105
//        markerOption15 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room105))
//                .position(new LatLng(32.196357, 119.509460)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker15 = aMap.addMarker(markerOption15);
//        //103
//        markerOption16 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room103))
//                .position(new LatLng(32.196392, 119.509260)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker16 = aMap.addMarker(markerOption16);
//        //101
//        markerOption17 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.room101))
//                .position(new LatLng(32.196397, 119.509140)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker17 = aMap.addMarker(markerOption17);
//        //学院牌子
//        markerOption18 = new MarkerOptions().icon(BitmapDescriptorFactory.
//                fromResource(R.drawable.board))
//                .position(new LatLng(32.196417, 119.509730)) //latitude -38 is the center of picture
//                .draggable(false)
//                .visible(true);
//        marker18 = aMap.addMarker(markerOption18);
    }

}
