package nysa.nysa_20.service.connectivity;

import android.content.Context;

import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIMethodsService implements Callable {
    private static RetrofitService retrofitService;
    private static Retrofit retrofit;
    private Context context;

    public APIMethodsService(Context context) {
        this.context = context;
    }

    private static void initializeRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofitService = retrofit.create(RetrofitService.class);
        }
    }



    @Override
    public Object call() throws Exception {
        return null;
    }
}
