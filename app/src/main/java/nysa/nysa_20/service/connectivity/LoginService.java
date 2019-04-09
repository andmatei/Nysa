package nysa.nysa_20.service.connectivity;

import nysa.nysa_20.model.LoginFormular;

public class LoginService {

    private LoginService(){}

    public static int initiateLoginSequence(LoginFormular formular) {
        int response;

        response = loginProcess(formular);

        return response;
    }

    public static  int loginProcess(LoginFormular formular){
        int result = 0;

        //TODO if registration is successful result = 1 ; if there has been an error result = 0;

        return result;
    }

}
