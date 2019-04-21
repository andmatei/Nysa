package nysa.nysa_20.model;

public class RegistrationFormular {



    private RegistrationFormular(){}
    private String lastName;
    private String firstName;
    private String username;
    private String email;
    private String password;
    private boolean isAnyEmpty;


    public static class Builder{

        private String lastName;
        private String firstName;
        private String username;
        private String email;
        private String password;

        public Builder setFirstName(String firstName){
            this.firstName = firstName;
            return  this;
        }

        public Builder setLastName(String lastName){
            this.lastName = lastName;
            return  this;
        }

        public Builder setUsername(String username){
            this.username = username;
            return this;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public RegistrationFormular build(){
            RegistrationFormular formular = new RegistrationFormular();

            formular.firstName = this.firstName;
            formular.lastName = this.lastName;
            formular.username = this.username;
            formular.email = this.email;
            formular.password = this.password;
            formular.isAnyEmpty = checkIsAnyEmpty();


            return formular;

        }

        private  boolean checkIsAnyEmpty(){
            boolean result = false;
            if(firstName.equals("")) result = true;
            else
                if(lastName.equals("")) result = true;
                else
                    if(username.equals("")) result  = true;
                    else
                        if(email.equals("")) result = true;
                        else
                            if(password.equals("")) result = true;


            return result;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
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

    @Override
    public String toString() {
        return "RegistrationFormular{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAnyEmpty=" + isAnyEmpty +
                '}';
    }
}
