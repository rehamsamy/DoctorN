package com.doctorn.utils;

import com.doctorn.models.AddReservationModel;
import com.doctorn.models.AllArticleModel;
import com.doctorn.models.AllDoctorsModel;
import com.doctorn.models.Articles;
import com.doctorn.models.AvailableSessionModel;
import com.doctorn.models.DoWithdrawRequestModel;
import com.doctorn.models.FavoriteDoctorsModel;
import com.doctorn.models.LanguageModel;
import com.doctorn.models.MyFinancailModel;
import com.doctorn.models.NotificationModel;
import com.doctorn.models.RegisterDoctorModel;
import com.doctorn.models.ReservationsModel;
import com.doctorn.models.ReviewModel;
import com.doctorn.models.SpecialtiesModel;
import com.doctorn.models.UserModel;
import com.doctorn.models.WalletModel;
import com.doctorn.models.WithdrawModel;
import com.doctorn.models.WorkDaysModel;
import com.doctorn.notify.MyResponse;
import com.doctorn.notify.Sender;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {

    @POST("api/login")
    Call<UserModel> loginUser(@QueryMap Map<String, Object> map);

    @POST("api/register")
    Call<UserModel> registerUser(@QueryMap Map<String, Object> map);

    @Multipart
    @POST("api/register")
    Call<UserModel> registerDoctor(@QueryMap Map<String, Object> map,@Part MultipartBody.Part part);

    @POST("/api/changepassword")
    Call<UserModel> changePassword(@Query("user_id") int id,
                                   @Query("current_password") String old,
                                   @Query("password") String newp,
                                   @Query("password_confirmation") String confirm,
                                   @Query("api_token") String token);


    @POST("/api/changedoctorpassword")
    Call<UserModel> changeDoctorPassword(@Query("user_id") int id,
                                         @Query("current_password") String old,
                                         @Query("password") String newp,
                                         @Query("password_confirmation") String confirm,
                                         @Query("api_token") String token);


    @POST("/api/updateprofile")
    Call<UserModel> updateUserProfile(@Query("user_age") int age,
                                      @Query("user_type") String type,
                                      @Query("email") String email,
                                      @Query("name") String name,
                                      @Query("api_token") String token,
                                      @Query("user_gender") String gender,
                                      @Query("lang") String lang,
                                      @Query("user_phone") String phone);


    @POST("/api/updatedoctorprofile")
    Call<UserModel> updateDoctorProfile(@Query("user_age") int age,
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
    Call<UserModel> addPaymentCard(@QueryMap Map<String, Object> map);

    @POST("/api/adddoctorinformation")
    Call<UserModel> addDoctorInfo(@QueryMap Map<String, Object> map);


//    @POST("/api/forgetpassword/sendcode")
//


    @Multipart
    @POST("api/sendsuggestions")
    Call<UserModel> sendSuggestionAndComplaints(@QueryMap Map<String, Object> map, @Part MultipartBody.Part part);


    @GET("api/doctortermsandcondtion")
    Call<UserModel> getTermsAndConditions();

    @GET("api/doctorprivacy")
    Call<UserModel> getPrivacyAndPolicy();


    @GET("/api/specializations")
    Call<SpecialtiesModel> getSpecialities(@QueryMap Map<String, Object> map);

    @GET("api/getreviewbyuserid")
    Call<ReviewModel> getReviewByUserId(@QueryMap Map<String, Object> map);

    @GET("api/getreviewbydoctorid")
    Call<ReviewModel> getReviewByDoctorId(@QueryMap Map<String, Object> map);


    @GET("api/notifications")
    Call<NotificationModel> getNotifications(@Query("api_token") String api_key,
                                             @Query("get_unreaded_only") String readed_state,
                                             @Query("page") int page,
                                             @Query("limit") int limit);


    @GET("api/doctors")
    Call<AllDoctorsModel> getDoctorsList(@Header("Accept") String accept,
                                         @Query("api_token") String api_key,
                                         @Query("page") int page,
                                         @Query("limit") int limit,
                                         @Query("lang") String lang);

    @GET("api/d-search")
    Call<AllDoctorsModel> doctorSearch(@Header("Accept") String accept,
                                       @Query("api_token") String api_key,
                                       @Query("name") String name,
                                       @Query("specialization_id") int spec,
                                       @Query("page") int page,
                                       @Query("limit") int limit);


    @GET("api/getanyDoctorInfo")
    Call<AllDoctorsModel> getDoctorInfoDetails(@Query("api_token") String api_key,
                                               @Query("doctor_id") int doctor_id,
                                               @Query("lang") String lang);


    ////////   get fvorite doctors in user fragment //////////////

    @GET("api/getMyFavoriteDoctors")
    Call<FavoriteDoctorsModel> getMyFavoriteDoctors(@Query("api_token") String api_key,
                                                    @Query("page") int page,
                                                    @Query("limit") int limit,
                                                    @Query("lang") String lang);

    ///-------------  get favorite article in user fragment-----------------------

    @GET("api/getMyFavoriteArticles")
    Call<FavoriteDoctorsModel> getMyFavoriteArticle(@Query("api_token") String api_key,
                                                    @Query("page") int page,
                                                    @Query("limit") int limit,
                                                    @Query("lang") String lang);


    //------------------- get all articles in page activity--------------------
    @GET("api/articles/get-all")
    Call<AllArticleModel> getAllArticle(@Query("api_token") String api_key,
                                        @Query("page") int page,
                                        @Query("limit") int limit);

//---------------------- get Article details by id --------------------------

    @GET("api/articles/get-one")
    Call<Articles> getArticleDetails(@Query("api_token") String api_key,
                                     @Query("article_id") int id);


    @GET("api/getdoctorinfo")
    Call<AllDoctorsModel> getDoctorInfoDetailsInDoctors(@Query("api_token") String token);


    // ----------------- add doctor to favorite list-----------------------------
    @POST("api/favorites")
    Call<FavoriteDoctorsModel> addDoctorToFavorite(@QueryMap Map<String, Object> map);


    //----------------   add review for doctor ------------------------------------
    @POST("api/addreview")
    Call<ReviewModel> addReviewForDoctor(@Query("api_token") String api_key,
                                         @Query("reservation_id") int reservation_id,
                                         @Query("user_review") String review,
                                         @Query("user_rate") int rate);


    // https://fcm.googleapis.com/fcm/send


    @Headers(
            {"Content-Type:application/json",
                    "Authorization:Key=AAAAgtHR2uE:APA91bEESOS34cewBE1wYUAwkZ0kI2rkzApYJZWV1Wyw1RY6LzBe7Hobf9nbWI__6NGe30PBD2jmFRoto_a3kvV8vXBOQFhk1_O-yp_u4ZywwdCGy0ceeHzRl2Om-5jja6BsYRT-eWNX"
            })
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);


    //------------------------------get workdays by doctor id ----------------------------------
    @GET("api/weakdays")
    Call<WorkDaysModel> getWorksDayByDoctorId();


    //----------------------  get available sessions by doctor id -------------------------------
    @POST("api/getAvailableSessions")
    Call<AvailableSessionModel> getAvailableSession(@Query("api_token") String api_key,
                                                    @Query("doctor_id") int doctorId,
                                                    @Query("lang") String lang);


    ///-------------------------  get Doctor reservations  -----------------------------
    @GET("/api/reservations")
    Call<ReservationsModel> getReservations(@Query("api_token") String api_key,
                                            @Query("page") int page,
                                            @Query("limit") int limit,
                                            @Query("lang") String lang);


    //---------------------------  get withdraw for doctor ----------------------------

    @GET("/api/withdraw/show")
    Call<WithdrawModel> getWithdraw(@Query("api_token") String api_key,
                                    @Query("page") int page,
                                    @Query("limit") int limit,
                                    @Query("lang") String lang);


    //-------------------------------  do withdraw request  --------------------------------
    @POST("/api/withdraw/add")
    Call<DoWithdrawRequestModel> doWithdrawRequest(@Header("Accept") String accept,
                                                   @Query("amount") String amount,
                                                   @Query("api_token") String token,
                                                   @Query("lang") String lang);


    //----------------------  get my wallet balance -------------------------------------------
    @GET("/api/getmywalletbalance")
    Call<WalletModel> getMyWalletBalance(@Query("api_token") String token,
                                         @Query("lang") String lang);


    //-------------------------- add new reservation --------------------------------------------
    @POST("/api/reservation")
    Call<AddReservationModel> addReservation(@QueryMap Map<String, Object> map);

    //---------------------------- final step of register doctor ----------------------------

    @Multipart
    @POST("/api/addeditdoctorinformation")
    Call<RegisterDoctorModel> addOrEditDoctorInfo(@QueryMap Map<String, Object> map, @Part MultipartBody.Part part);

    //---------------------------- get language model------------------------------------------

    @GET("/api/languages")
    Call<LanguageModel> getlangauge();


    //------------------------ get my financail ------------------------------------------------
    @POST("/api/getMyFinancialTrans")
    Call<MyFinancailModel> getMyFinancail(@Query("api_token") String api_key,
                                          @Query("page") int page,
                                          @Query("limit") int limit,
                                          @Query("lang") String lang);


    //------------------------------  get bank transactions  ---------------------------------------
    @POST("api/getMyBankTransfereTrans")
    Call<MyFinancailModel> getBankTransaction(@Body Map<String, Object> map);


    // ----------------- add doctor to favorite list-----------------------------
    @POST("api/favorites")
    Call<FavoriteDoctorsModel> addArticleToFavorite(@Query("api_token") String api_key,
                                                    @Query("favorite_type") String type,
                                                    @Query("article_id") int articleId,
                                                    @Query("lang") String lang);


}