package com.doctorn.utils;

import com.doctorn.models.UserModel;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {

    @POST("api/login")
    Call<UserModel> loginUser (@QueryMap Map<String,Object> map);

    @POST("api/register")
    Call<UserModel> registerUser(@QueryMap Map<String,Object> map);


    @POST("api/changedoctorpassword")
    Call<UserModel> changePassword(@Query("user_id") int id,
                                   @Query("current_password") String old,
                                    @Query ("password")String newp,
                                   @Query("password_confirmation")String confirm,
                                   @Query("api_token")String token);


    @POST("/api/updateprofile")
    Call<UserModel> updateProfile( @Query("user_age") int  age,
                                   @Query("user_type") String type,
                                   @Query("email") String email,
                                   @Query("name") String name,
                                   @Query("api_token") String token,
                                   @Query("user_gender") String gender,
                                   @Query("user_id") int id,
                                  @Query("user_phone") String phone);


    @Multipart
    @POST("api/sendsuggestions")
    Call<UserModel> sendSuggestionAndComplaints(@QueryMap Map<String,Object> map, @Part MultipartBody.Part part);


    @GET("api/doctortermsandcondtion")
    Call<UserModel> getTermsAndConditions();

    @GET("api/doctorprivacy")
    Call<UserModel> getPrivacyAndPolicy();

}
