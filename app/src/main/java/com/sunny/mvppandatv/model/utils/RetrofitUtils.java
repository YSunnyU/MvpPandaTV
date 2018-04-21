package com.sunny.mvppandatv.model.utils;

import android.Manifest;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.sunny.mvppandatv.app.App;
import com.sunny.mvppandatv.model.url.Path;
import com.sunny.mvppandatv.service.CCTVService;
import com.sunny.mvppandatv.service.HuDongService;
import com.sunny.mvppandatv.service.LiveChinaService;
import com.sunny.mvppandatv.service.PandaEyeService;
import com.sunny.mvppandatv.service.PandaLiveService;
import com.sunny.mvppandatv.service.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 张玗 on 2018/4/12.
 */

public class RetrofitUtils {

    //指定缓存目录为SD卡下的cache目录
    File cacheFile = new File(Environment.getExternalStorageDirectory().getPath() + "/cache");
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response proceed = chain.proceed(request);
            return proceed.newBuilder()
                    .header("Cache-Control", "max-age=" + 60 * 60 * 24)
                    .removeHeader("Pragma")
                    .build();
        }
    };


    OkHttpClient client = new OkHttpClient
            .Builder()
//          .addInterceptor(addQueryParameterInterceptor())  //参数添加
//          .addInterceptor(addHeaderInterceptor()) // token过滤
//          .addInterceptor(new LogInterceptor().setLevel(LogInterceptor.Level.BODY))
            .cache(cache)  //添加缓存
            .connectTimeout(60l, TimeUnit.SECONDS)
            .readTimeout(60l, TimeUnit.SECONDS)
            .writeTimeout(60l, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .build();

    public static void deleteFile(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {

                        deleteFile(files[i].getAbsolutePath());
                    }
                }

                if (!file.isDirectory()) {// 如果是文件，删除
                    file.delete();
                } else {// 目录
                    if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                        file.delete();
                    }
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }


    public static RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;

    }

    public RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Path.ABOVE_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(App.context, mPermissionList, 123);
        }


    }

    public Service getHomeService() {
        return retrofit.create(Service.class);
    }

    public PandaEyeService getPandaEyeService() {
        return retrofit.create(PandaEyeService.class);
    }

    public CCTVService getCCTVService() {
        return retrofit.create(CCTVService.class);
    }

    public LiveChinaService getLiveChinaService() {
        return retrofit.create(LiveChinaService.class);
    }

    public PandaLiveService getPandaLiveService() {
        return retrofit.create(PandaLiveService.class);
    }

    public HuDongService getHuDongService() {
        return retrofit.create(HuDongService.class);
    }
   /*public  void doGet(String path,final MyNetworkCallback myNetworkCallback){

       Service addressService = retrofit.create(Service.class);
       addressService.getHomeModelMess()
               .subscribeOn(Schedulers.newThread())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<HomeNewsMess>() {
                   @Override
                   public void accept(final HomeNewsMess homeNewsMess) throws Exception {

                       App.context.runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
//                               T parse = parse(string, myNetworkCallback);
                               myNetworkCallback.showRightMess(homeNewsMess);
                           }
                       });
                   }
               });
   }*/
    /*public <T> T parse(String json,MyNetworkCallback<T> myNetworkCallback){
        //得到callback这个类上实现的所有接口包括泛型
        Type[] types = myNetworkCallback.getClass().getGenericInterfaces();
        //interfaces[0] 取出数组中的第一个 getActualTypeArguments 获取真实的类型
        Type[] typeArguments = ((ParameterizedType) types[0]).getActualTypeArguments();
        Gson gson = new Gson();
        T t = gson.fromJson(json, typeArguments[0]);
        return t;
    }*/
}
