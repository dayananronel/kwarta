package com.kwarta.ph.utilities;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<GenericResponse> login(@Field("username") String from,
                               @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<GenericResponse> register(@Field("usertypeid") String usertypeid,
                                   @Field("emailaddress") String emailaddress,
                                   @Field("mobilenumber") String mobilenumber,
                                   @Field("password") String password,
                                   @Field("ipaddress")String ipaddress
    );
}
