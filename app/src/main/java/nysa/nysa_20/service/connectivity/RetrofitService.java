package nysa.nysa_20.service.connectivity;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitService {
    public static String BASE_URL = "https://nysa-backend.herokuapp.com/";

    @POST("auth/register")
    @FormUrlEncoded
    Call<ResponseBody> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName
    );

    @POST("auth/login")
    @FormUrlEncoded
    Call<ResponseBody> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("auth/refreshToken")
    @FormUrlEncoded
    Call<ResponseBody> refreshToken(
            @Header("Authorization") String refreshToken,
            @Field("id") String id
    );
}
