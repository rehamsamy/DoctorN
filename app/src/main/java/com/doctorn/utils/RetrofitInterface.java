package com.doctorn.utils;

import com.doctorn.models.ReviewModel;
import com.doctorn.models.SpecialtiesModel;
import com.doctorn.models.User;
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

    @POST("/api/changepassword")
    Call<UserModel> changePassword(@Query("user_id") int id,
                                   @Query("current_password") String old,
                                   @Query ("password")String newp,
                                   @Query("password_confirmation")String confirm,
                                   @Query("api_token")String token);


    @POST("/api/changedoctorpassword")
    Call<UserModel> changeDoctorPassword(@Query("user_id") int id,
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



    @POST("/api/updatedoctorprofile")
    Call<UserModel> updateDoctorProfile( @Query("user_age") int  age,
                                   @Query("user_type") String type,
                                   @Query("email") String email,
                                   @Query("name") String name,
                                   @Query("api_token") String token,
                                   @Query("user_gender") String gender,
                                   @Query("user_id") int id,
                                   @Query("user_phone") String phone);



    @POST("/api/logout")
    Call<UserModel> logout();

    @POST("/api/addpaymentcard")
    Call<UserModel> addPaymentCard(@QueryMap Map<String,Object> map);

    @POST("/api/adddoctorinformation")
    Call<UserModel> addDoctorInfo(@QueryMap Map<String,Object> map);


//    @POST("/api/forgetpassword/sendcode")
//


    @Multipart
    @POST("api/sendsuggestions")
    Call<UserModel> sendSuggestionAndComplaints(@QueryMap Map<String,Object> map, @Part MultipartBody.Part part);


    @GET("api/doctortermsandcondtion")
    Call<UserModel> getTermsAndConditions();

    @GET("api/doctorprivacy")
    Call<UserModel> getPrivacyAndPolicy();


    @GET("api/getdoctorspecialties")
    Call<SpecialtiesModel> getSpecialities(@QueryMap Map<String,Object> map);

    @GET("api/getreviewbyuserid")
    Call<ReviewModel> getReviewByUserId(@QueryMap Map<String,Object>map);

    @GET("api/getreviewbydoctorid")
    Call<ReviewModel> getReviewByDoctorId(@QueryMap Map<String,Object> map);


    @GET("api/generalnotifications")
    Call<UserModel> getGeneralNotification(@Query("api_token") String api_key);

    @GET("api/privatenotifications")
    Call<UserModel> getPrivateNotification(@Query("api_token") String api_key);

    @GET("api/doctors")
    Call<UserModel> getDoctorsList(@Query("api_token") String api_key);

    @GET("api/d-search")
    Call<UserModel> doctorSearch(@Query("api_token")String api_key,@Query("name")String name,@Query("specialization")String spec);

}