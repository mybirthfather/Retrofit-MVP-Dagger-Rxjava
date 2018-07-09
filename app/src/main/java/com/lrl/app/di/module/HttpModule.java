package com.lrl.app.di.module;

import android.app.Application;
import android.text.TextUtils;

import com.lrl.app.http.ApiService;
import com.lrl.app.http.net.rto_rxbuild.RxErrorHandler;
import com.lrl.app.utils.ACache;
import com.lrl.app.utils.Constant;
import com.lrl.app.utils.HttpsUtil;
import com.lrl.app.utils.LogUtil;
import com.lrl.app.utils.StringUtil;
import com.lrl.app.utils.UIUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZHP on 2017/6/24.
 */
@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpsUtil.SSLParams sslParams = HttpsUtil.getSslSocketFactory(null, null, null);
        builder.addInterceptor(new LogInterceptor());
        return builder
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();//这个地方添加公共的请求头
//                                .newBuilder()
//                                .addHeader("AppType",Constant.app_type)
//                                .addHeader("channel",ChannelIdUtil.getChannelName())
//                                .addHeader("channelID", ChannelIdUtil.getProxyCode())
//                                .addHeader("DeviceToken", Constant.device_token)
//                                .addHeader("UserAuth", getUserAuth())
//                                .addHeader("pkg_name", App.getApplication().getPackageName())
//                                .addHeader("Uid",getUid() )
//                                .addHeader("PHPSESSID", getSessionId())
//                                .addHeader("APPVERSION", String.valueOf(AppVersionUtil.getVersionCode(UIUtil.getContext())))
//                                .addHeader("TESTPLAYER", ACache.get(UIUtil.getContext()).getAsString(Constant.FREE_BETTING));    //是否是试玩用户 1是 0否
                        Request request = builder.build();
                        return chain.proceed(request);
                    }
                })
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .cookieJar(new CookieJar() {

                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put("PHPSESSID", cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {//cookieJAr用法
                        //登录、注册后返回的sessionId
                        List<Cookie> cookies = cookieStore.get("PHPSESSID");

                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();


    }


//    private String getUserAuth() {
//
////        final String uid = ACache.get(UIUtil.getContext()).getAsString(Constant.uid);
////        final String token = ACache.get(UIUtil.getContext()).getAsString(Constant.token);
//        String userAuth = null;
//        try {
////            userAuth = MD5.md5(Constant.apiKey+uid+token);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        final String finalUserAuth = userAuth;
//        return StringUtil.isNullString(uid)?"":finalUserAuth;
//    }
//
//    private String getUid() {
//
//        String uid = ACache.get(UIUtil.getContext()).getAsString(Constant.uid);
//        return StringUtil.isNullString(uid)? "":uid;
//    }
//
//    private String getSessionId() {
//
//        String session_id = ACache.get(UIUtil.getContext()).getAsString(Constant.session_id);
//        return StringUtil.isNullString(session_id)? "":session_id;
//    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){

        return  retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);

        return builder.build();

    }

    @Provides
    @Singleton
    public RxErrorHandler provideErrorHandlder(Application application){

        return  new RxErrorHandler(application);
    }


    public class LogInterceptor implements Interceptor {

        public String TAG = "okhttp";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(chain.request());
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtil.i(TAG,"\n");
            LogUtil.i(TAG,"----------Start----------------");
            LogUtil.i(TAG, "| Request:"+request.toString());
            LogUtil.i(TAG, "| Response:" + content);
            LogUtil.i(TAG,"----------End------------------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

}
