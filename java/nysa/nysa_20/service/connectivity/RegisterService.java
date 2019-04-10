package nysa.nysa_20.service.connectivity;

import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.RegistrationFormular;
import nysa.nysa_20.model.Account;
public class RegisterService {
    private RegisterService(){

    }

    public static int initiateRegisterSequence(RegistrationFormular formular){
        int response = -1;

        response = registrationProcess(formular);

        return response;
    }

    private static int registrationProcess(RegistrationFormular formular){
        int result = 1;

        //TODO if registration is possible ; if another account already exists result = 0; if there has beeen an error result = -1;

        return result;
    }

    public static void finaliseRegisterSequence(){
        Account account = AccountHolder.getAccount();

        //TODO account registration
    }



}
