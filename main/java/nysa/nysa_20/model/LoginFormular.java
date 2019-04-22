package nysa.nysa_20.model;


public class LoginFormular {
    private LoginFormular(){}

    private String email;
    private String password;
    private boolean isAnyEmpty;

    public static class Builder{
        private String email;
        private String password;

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public LoginFormular build(){
            LoginFormular formular = new LoginFormular();

            formular.email = email;
            formular.password = password;
            formular.isAnyEmpty = isAnyEmpty();

            return formular;

        }

        private boolean isAnyEmpty() {
            boolean result = false;

            if(email.equals("")) result = true;
            else
                if(password.equals("")) result = true;

            return result;
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAnyEmpty() {
        return isAnyEmpty;
    }
}
