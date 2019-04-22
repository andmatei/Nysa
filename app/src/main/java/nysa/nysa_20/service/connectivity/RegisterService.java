package nysa.nysa_20.service.connectivity;


import org.json.JSONObject;

import java.util.concurrent.Callable;

import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.RegistrationFormular;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.service.localPersistance.MainLocalPersistenceService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterService implements Callable {

    private static RetrofitService retrofitService;
    private static Retrofit retrofit;
    private static RegistrationFormular formular;


    public RegisterService(RegistrationFormular formular){
        this.formular = formular;
    }

    public static void finaliseRegisterSequence(){
        Account account = AccountHolder.getAccount();
        MainLocalPersistenceService.persistCurrentAccount();
        //TODO account registration
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

        finaliseRegisterSequence();
        //TODO if registration is possible ; if another account already exists result = 0; if there has beeen an error result = -1;

        initializeRetrofit();
        Call<ResponseBody> call = retrofitService.register(
                formular.getUsername(),
                formular.getEmail(),
                formular.getPassword(),
                formular.getFirstName(),
                formular.getLastName()
        );

        String jsonResponse = call.execute().body().string();
        JSONObject apiResponse = new JSONObject(jsonResponse);
        return apiResponse;
    }
}
