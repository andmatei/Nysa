package nysa.nysa_20.service.connectivity;


import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import nysa.nysa_20.model.APIResponse;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.RegistrationFormular;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.service.localPersistance.MainLocalPersistenceService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterService implements Callable {

    private static RetrofitService retrofitService;
    private static Retrofit retrofit;
    private static RegistrationFormular formular;

    private static final String TAG = "RegisterService";


    public RegisterService(RegistrationFormular formular){
        this.formular = formular;
    }

    public static int initiateRegisterSequence(RegistrationFormular formular){
        int response = -1;

        response = registrationProcess(formular);

        return response;
    }

    private static int registrationProcess(RegistrationFormular formular){
        return 0;
    }

    public static void finaliseRegisterSequence(){
        Account account = AccountHolder.getAccount();
        MainLocalPersistenceService.persistCurrentAccount();



        //TODO account registration
    }


    private static void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    @Override
    public Object call() throws Exception {

        finaliseRegisterSequence();
        //TODO if registration is possible ; if another account already exists result = 0; if there has beeen an error result = -1;

        initializeRetrofit();
        Call<APIResponse> call = retrofitService.register(
                formular.getUsername(),
                formular.getEmail(),
                formular.getPassword(),
                formular.getFirstName(),
                formular.getLastName()
        );

        APIResponse apiResponse = call.execute().body();
        Log.d(TAG, "call: " + apiResponse.getData());
        return apiResponse;
    }
}
