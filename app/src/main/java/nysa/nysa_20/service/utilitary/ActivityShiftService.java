package nysa.nysa_20.service.utilitary;

import android.content.Context;
import android.content.Intent;

import nysa.nysa_20.activity.AccountSettingsActivity;
import nysa.nysa_20.activity.AllergyRegisterActivity;
import nysa.nysa_20.activity.ChatBotActivity;
import nysa.nysa_20.activity.HomeActivity;
import nysa.nysa_20.activity.LibraryActivity;
import nysa.nysa_20.activity.LoginActivity;
import nysa.nysa_20.activity.MainActivity;
import nysa.nysa_20.activity.RegisterActivity;
import nysa.nysa_20.activity.SymptomEntryActivity;
import nysa.nysa_20.activity.SymptomTrackActivity;

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

    public static void toAllergyRegisterActivity(Context context){
        Intent intent = new Intent(context, AllergyRegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void toMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void toAccountSettingsActivity(Context context){
        Intent intent = new Intent(context, AccountSettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void toLibraryActivity(Context context){
        Intent intent = new Intent(context, LibraryActivity.class);
        context.startActivity(intent);
    }

    public static void toSymptomTrackActivity(Context context){
        Intent intent = new Intent(context, SymptomTrackActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void toHomeActivity(Context context){
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void toSymptomEntryActivity(Context context){
        Intent intent = new Intent(context, SymptomEntryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void toChatBotActivity(Context context){
        Intent intent = new Intent(context, ChatBotActivity.class);
        context.startActivity(intent);
    }
}
