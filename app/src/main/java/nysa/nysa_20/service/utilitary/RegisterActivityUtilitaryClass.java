package nysa.nysa_20.service.utilitary;

import android.graphics.drawable.Drawable;

import org.jetbrains.annotations.NotNull;

import nysa.nysa_20.R;

public class RegisterActivityUtilitaryClass {

    public static int getCorespondentImageViewId(@NotNull String s){
        switch (s){

            case "lactoseAllergy":
                return R.id.lactoseImageView;
            case "eggAllergy":
                return R.id.eggImageView;
            case "glutenAllergy":
                return R.id.glutenImageView;
            case "sesameAllergy":
                return R.id.sesameImageView;
            case "crustaceansAllergy":
                return R.id.crustaceansImageView;
            case "peanutsAllergy":
                return  R.id.peanutsImageView;
            case "soyAllergy":
                return R.id.soyImageView;
            case "nutsAllergy":
                return R.id.nutsImageView;
            case "fishAllergy":
                return R.id.fishImageView;
            case "dogAllergy":
                return R.id.dogImageView;
            case "catAllergy":
                return R.id.catImageView;
            case "penicillinAllergy":
                return R.id.penicillinImageView;
            case "antibioticsAllergy":
                return R.id.antibioticsImageView;
            case "anticonvulsantsAllergy":
                return R.id.anticonvulsantsImageView;
            case "aspirinAllergy":
                return R.id.aspirinImageView;
            case "insectAllergy":
                return R.id.insectAllergyImageView;
            case "pollenAllergy":
                return R.id.pollenAllergyImageView;
            case "dustAllergy":
                return R.id.dustAllergyImageView;
            case "moldAllergy":
                return R.id.moldAllergyImageView;
            default: return 0;


        }



    }

    public static int getOppositeImageId(String s ,boolean b){
            int family = getFamily(s);
            if(family >0){
                if(b){
                    return R.drawable.checked;
                }
                else{
                    return R.drawable.unchecked;
                }
            }
            else{

                    switch(s){
                        case "insectAllergy":
                            if(b)
                                return R.drawable.insect;
                            else
                                return R.drawable.noinsect;

                        case "pollenAllergy":
                            if(b)
                                return R.drawable.pollen;
                            else
                                return R.drawable.nopollen;

                        case "dustAllergy":
                            if(b)
                                return R.drawable.dust;
                            else
                                return R.drawable.nodust;

                        case "moldAllergy":
                            if(b)
                                return R.drawable.mold;
                            else
                                return R.drawable.nomold;

                    }

            }
        return -1;
    }

    public static int getFamily(String string) {  //0 - noFamily   1 - Food   2 - Pet   3 - Drug
        switch (string){
            case "lactoseAllergy":
            case "eggAllergy":
            case "glutenAllergy":
            case "sesameAllergy":
            case "crustaceansAllergy":
            case "peanutsAllergy":
            case "soyAllergy":
            case "nutsAllergy":
            case "fishAllergy":
                return 1;
            case "dogAllergy":
            case "catAllergy":
                return 2;
            case "penicillinAllergy":
            case "antibioticsAllergy":
            case "anticonvulsantsAllergy":
            case "aspirinAllergy":
                return 3;
            case "insectAllergy":
            case "pollenAllergy":
            case "dustAllergy":
            case "moldAllergy":
                return 0;
        }
        return -1;
    }



}
