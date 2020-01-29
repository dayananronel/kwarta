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
    @POST("showauction")
    Call<GenericResponse> showauction(@Field("status") String status
    );

    @FormUrlEncoded
    @POST("biditem")
    Call<GenericResponse> biditem(@Field("id") String id,
                                  @Field("item_id") String item_id,
                                  @Field("amount_bid") String amount_bid
    );
    @FormUrlEncoded
    @POST("register")
    Call<GenericResponse> register(@Field("fname") String fname,
                                   @Field("lname") String lname,
                                   @Field("email") String email,
                                   @Field("username") String username,
                                   @Field("password") String password,
                                   @Field("user_type") String user_type

    );

    @FormUrlEncoded
    @POST("addauction")
    Call<GenericResponse> addauction(@Field("name") String name,
                                     @Field("image") String image,
                                     @Field("description") String description,
                                     @Field("min_bid") String min_bid,
                                     @Field("duration") String duration,
                                     @Field("auctioner_id") String auctioner_id
    );


}
