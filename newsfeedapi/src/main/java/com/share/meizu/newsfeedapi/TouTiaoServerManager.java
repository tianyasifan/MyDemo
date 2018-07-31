package com.share.meizu.newsfeedapi;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.apaches.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by tongxiaotao on 18-3-1.
 */

public class TouTiaoServerManager {
    static final String BASE_URL = "https://open.isnssdk.com/";
    static final String TOKEN_URL = BASE_URL + "token/register/device/v1/";
    static final String FEED_URL = BASE_URL + "data/stream/topbuzz/v1/";
    static final String PARTNER = "i18n_meizu_llq";
    static final String SECURE_KEY = "aa36975e590ec3d8656cbd1c12b483b3";
    Map<String, String> commParam = new HashMap<>();
    String mToken = null;
    Retrofit mRetrofit;
    TouTiaoServerApi mApi;
    static TouTiaoServerManager mInstance;
    static final String TAG = "TouTiaoServerManager";

    public static TouTiaoServerManager getInstance(){
        if (mInstance == null) {
            synchronized (TouTiaoServerManager.class) {
                mInstance = new TouTiaoServerManager();
            }
        }
        return mInstance;
    }

    private TouTiaoServerManager(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(TouTiaoServerManager.BASE_URL)
                .addConverterFactory(MyGsonConverterFactory.create())
                .client(TouTiaoServerManager.getUnsafeOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApi = mRetrofit.create(TouTiaoServerApi.class);
    }

    public TouTiaoServerApi getServerApi(){
        return mApi;
    }

    public Map getTokenParam() {
        TelephonyManager tm = (TelephonyManager) App.sAppContext.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = "";
        if (ActivityCompat.checkSelfPermission(App.sAppContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            deviceId = tm.getDeviceId();
        }
        String androidId = Settings.Secure.getString(App.sAppContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        Map<String, String> param = new HashMap<>();
        param.putAll(getCommParamWithNoToken());
        param.put("udid", deviceId);
        param.put("clientudid", androidId);
        param.put("os", "android");
        param.put("os_version", android.os.Build.VERSION.RELEASE);
        param.put("device_model", Build.MODEL);
        param.put("language", Locale.getDefault().getLanguage());
        param.put("region", Locale.getDefault().getCountry());
        param.put("resolution", getResolution());
        param.put("display_density", getDpi());
        Log.i("Api", "map:" + param);
        return param;
    }

    public Map getFeedParam(String channel , long timestamp, boolean isRefresh){
        Map<String, String> param = new HashMap<>();
        String maxTime = "";
        String mixTime = "";
        if(isRefresh){ //下拉刷新或是第一次加载
            if(timestamp == 0) { //表示第一次加载，无历史时间戳，传递当前时间戳-10
                timestamp = System.currentTimeMillis() / 1000 - 10;
            }
            mixTime = String.valueOf(timestamp);
        }else {
            maxTime = String.valueOf(timestamp);
        }
        param.putAll(getCommParam());
        param.put("os", "android");
        param.put("category", "__all__");
        param.put("language", Locale.getDefault().getLanguage());
        param.put("region", Locale.getDefault().getCountry().toLowerCase());
        param.put("max_behot_time", maxTime);
        param.put("min_behot_time", mixTime);
        param.put("content_space", "nr");
        return param;
    }

    /**
     * 获取手机的分辨率，如800x400
     * @return
     */
    public String getResolution(){
        String resolution = "";
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) App.sAppContext.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        resolution = dm.widthPixels+"x"+dm.heightPixels;
        return resolution;
    }

    public String getDpi(){
        String dpi = "";
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) App.sAppContext.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        dpi = String.valueOf(dm.densityDpi);
        return dpi;
    }

    public Map getCommParam(){
        commParam.putAll(getCommParamWithNoToken());
        commParam.put("token", getTokenFromPreferences());
        return commParam;
    }

    public Map getCommParamWithNoToken(){
        String timestamp = String.valueOf(System.currentTimeMillis()/1000);
        String nonce = RandomString.getRandomString(10);
        commParam.put("partner", PARTNER);
        commParam.put("timestamp", timestamp);
        commParam.put("nonce", nonce);
        commParam.put("signature", getSignature(timestamp, nonce));
        commParam.put("token", "");
        return commParam;
    }

    public String getSignature(String timestamp,String nonce){
        String signature = "";
        ArrayList<String> list=new ArrayList<String>();
        list.add(nonce);
        list.add(timestamp);
        list.add(SECURE_KEY);

        Collections.sort(list);

        signature = DigestUtils.shaHex(list.get(0)+list.get(1)+list.get(2));
        return signature;
    }

    public String getTokenFromPreferences(){
        SharedPreferences sp = App.sAppContext.getSharedPreferences("appShare", MODE_PRIVATE);
        String token = sp.getString("token", "");
        return token;
    }

    public String getToken(){
        if(mToken == null || "".equals(mToken)){
            SharedPreferences sp = App.sAppContext.getSharedPreferences("appShare", MODE_PRIVATE);
            String token = sp.getString("token", null);
            if(token == null || "".equals(token)){
                mToken = getTokenFromServer();
                sp.edit().putString("token", mToken).commit();
            }else {
                mToken = token;
            }
        }
        return mToken;
    }

    /*public void getTokenFromServer2(){
        mApi.getToken(TouTiaoServerManager.getInstance().getTokenParam())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<TokenBean>() {
                    @Override
                    public void accept(TokenBean bean) throws Exception {
                        mToken = bean.data.token;
                        SharedPreferences sp = App.sAppContext.getSharedPreferences("appShare", MODE_PRIVATE);
                        sp.edit().putString("token", mToken).commit();
                    }
                });

    }*/

    /*public void getFeedsFromServer(){
        final Observable<FeedBean> feed = mApi.getFeed(TouTiaoServerManager.getInstance().getFeedParam());

        feed.subscribeOn(Schedulers.io())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<FeedBean>>() {
                    @Override
                    public ObservableSource apply(Throwable throwable) throws Exception {
                        Log.e(TAG, "onErrorResumeNext.apply:" + throwable);
                        if(throwable instanceof DataResultException){
                            if(1004 == ((DataResultException)throwable).code){
                                Log.e(TAG, "token失效");
                                return mApi.getToken(TouTiaoServerManager.getInstance().getTokenParam())
                                        .flatMap(new Function<TokenBean, ObservableSource<FeedBean>>(){

                                            @Override
                                            public ObservableSource<FeedBean> apply(TokenBean tokenBean) throws Exception {
                                                mToken = tokenBean.data.token;
                                                SharedPreferences sp = App.sAppContext.getSharedPreferences("appShare", MODE_PRIVATE);
                                                sp.edit().putString("token", mToken).commit();
                                                return feed;
                                            }
                                        });
                            }
                        }
                        return Observable.error(throwable);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Object value) {
                        Log.i(TAG, "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete");
                    }
                });
    }*/

    public String getTokenFromServer(){
        final String[] token = {""};
        TokenApi tokenApi = mRetrofit.create(TokenApi.class);
        Call<TokenBean> call = tokenApi.getToken(TouTiaoServerManager.getInstance().getTokenParam());
        try {
            Response<TokenBean> response = call.execute();
            token[0] = response.body().data.token;
        }catch (Exception e){

        }
        return token[0];
    }

    public void getFeedFromServer(final IFeedView vew){
        FeedApi feedApi = mRetrofit.create(FeedApi.class);
        Call<FeedBean> call = feedApi.getFeed(TouTiaoServerManager.getInstance().getFeedParam());
        call.enqueue(new Callback<FeedBean>() {
            @Override
            public void onResponse(Call<FeedBean> call, Response<FeedBean> response) {
                if(vew != null){
                    vew.onLoadFeed(response.body().data);
                }
            }

            @Override
            public void onFailure(Call<FeedBean> call, Throwable t) {
                Log.e("Test", "fail: " + t);
            }
        });
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
           /* final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
*/
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if(BuildConfig.DEBUG) {
                //声明日志类
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                //设定日志级别
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                //添加拦截器
                builder.addInterceptor(httpLoggingInterceptor);
            }

            /*builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });*/

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
