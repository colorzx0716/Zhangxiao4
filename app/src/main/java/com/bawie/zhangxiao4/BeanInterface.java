package com.bawie.zhangxiao4;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 张肖肖 on 2017/10/31.
 */

public interface BeanInterface {

    //添加条件的get
    @GET("user/getUserInfo")

    Call<Bean> getData(@Query("uid") int uid);

    //post请求---getpost是方法名，不要忘记在MainActivity类里面改
    @FormUrlEncoded
    @POST("user/getUserInfo")
    Call<Bean> getPost(@Field("uid") int uid);

    //post请求---getpost是方法名，不要忘记在MainActivity类里面改
    @FormUrlEncoded
    @POST("user/updateNickName")
    Call<Nike> getNike(@Field("uid") int uid,@Field("nickname") String nickname);


}
