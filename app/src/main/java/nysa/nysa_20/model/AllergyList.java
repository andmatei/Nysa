package nysa.nysa_20.model;

import java.util.ArrayList;
import java.util.List;

public class AllergyList {

   private List<String> allergyList = new ArrayList<>();
   private int items;

    public AllergyList() {
        initList();

    }

    public List<String> getAllergyList() {
        return allergyList;
    }

    public int getItems() {
        return items;
    }

    private void initList(){

        allergyList.add("lactoseAllergy");
        allergyList.add("eggAllergy");
        allergyList.add("glutenAllergy");
        allergyList.add("sesameAllergy");
        allergyList.add("crustaceansAllergy");
        allergyList.add("peanutsAllergy");
        allergyList.add("soyAllergy");
        allergyList.add("nutsAllergy");
        allergyList.add("fishAllergy");
        allergyList.add("dogAllergy");
        allergyList.add("catAllergy");
        allergyList.add("penicillinAllergy");
        allergyList.add("antibioticsAllergy");
        allergyList.add("anticonvulsantsAllergy");
        allergyList.add("aspirinAllergy");
        allergyList.add("insectAllergy");
        allergyList.add("pollenAllergy");
        allergyList.add("dustAllergy");
        allergyList.add("moldAllergy");

        items = 19;
    }



}
