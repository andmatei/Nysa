package nysa.nysa_20.service.connectivity;

import nysa.nysa_20.model.APIResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {
    public static String BASE_URL = "https://nysa-backend.herokuapp.com/";

    @POST("auth/register")
    @FormUrlEncoded
    Call<APIResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName
    );
}
