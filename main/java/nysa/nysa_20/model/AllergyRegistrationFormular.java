package nysa.nysa_20.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AllergyRegistrationFormular {

    private HashMap<String,Boolean> allergyMap = new HashMap<>();
    private AllergyList allergyList = new AllergyList();

    public AllergyRegistrationFormular() {
        allergyList.getAllergyList()
                   .forEach(s -> allergyMap.put(s,false));
    }

    public HashMap<String, Boolean> getAllergyMap() {
        return allergyMap;
    }

    public void setValue(String key, boolean value){
        allergyMap.replace(key,value);
    }

    public boolean isValuePresent(String key){
        return allergyMap.get(key);
    }

    public String allergyMapToString(){

        return allergyMap.toString();
    }




}
