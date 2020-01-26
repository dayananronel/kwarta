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


}
