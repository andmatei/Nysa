package nysa.nysa_20.service.utilitary;

import android.content.Context;
import android.content.Intent;

import nysa.nysa_20.activity.LoginActivity;
import nysa.nysa_20.activity.MainActivity;
import nysa.nysa_20.activity.RegisterActivity;

public class ActivityShiftService {

    private ActivityShiftService(){
    }

    public static void toLoginActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void toRegisterActivity(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    public static void toMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }






}
