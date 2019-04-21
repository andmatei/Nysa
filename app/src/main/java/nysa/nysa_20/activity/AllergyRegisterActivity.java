package nysa.nysa_20.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.AllergyRegistrationFormular;
import nysa.nysa_20.service.connectivity.RegisterService;
import nysa.nysa_20.service.utilitary.ActivityShiftService;
import nysa.nysa_20.service.utilitary.RegisterActivityUtilitaryClass;

public class AllergyRegisterActivity extends AppCompatActivity {

        TextView lactoseTextView;
        TextView eggTextView;
        TextView glutenTextView;
        TextView sesameTextView;
        TextView crustaceansTextView;
        TextView peanutsTextView;
        TextView soyTextView;
        TextView nutsTextView;
        TextView fishTextView;
        TextView dogTextView;
        TextView catTextView;
        TextView penicillinTextView;
        TextView antibioticsTextView;
        TextView anticonvulsantsTextView;
        TextView aspirinTextView;
        TextView insectAllergyTextView;
        TextView pollenAllergyTextView;
        TextView dustAllergyTextView;
        TextView moldAllergyTextView;
        Button toLibraryButton;
        Button finishButton;
        int foodSelectedCount;
        int petSelectedCount;
        int drugSelectedCount;
        int overallSelectedCount;
        ImageView imageView;
        int oppositeImageViewID;
        AllergyRegistrationFormular formular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_allergies);


        initComponents();


    }

    void initComponents(){

        assignComponentReferences();
        assignComponentFunctionalities();
        foodSelectedCount = 0;
        petSelectedCount = 0;
        drugSelectedCount = 0;
        overallSelectedCount = 0;
        formular  = new AllergyRegistrationFormular();
    }

    void assignComponentReferences(){

        lactoseTextView = findViewById(R.id.lactoseTextView);
        eggTextView = findViewById(R.id.eggTextView);
        glutenTextView = findViewById(R.id.glutenTextView);
        sesameTextView = findViewById(R.id.sesameTextView);
        crustaceansTextView = findViewById(R.id.crustaceansTextView);
        peanutsTextView = findViewById(R.id.peanutsTextView);
        soyTextView = findViewById(R.id.soyTextView);
        nutsTextView = findViewById(R.id.nutsTextView);
        fishTextView = findViewById(R.id.fishTextView);
        dogTextView = findViewById(R.id.dogTextView);
        catTextView = findViewById(R.id.catTextView);
        penicillinTextView = findViewById(R.id.penicillinTextView);
        antibioticsTextView = findViewById(R.id.antibioticsTextView);
        anticonvulsantsTextView = findViewById(R.id.anticonvulsantsTextView);
        aspirinTextView = findViewById(R.id.aspirinTextView);
        insectAllergyTextView = findViewById(R.id.insectAllergyTextView);
        pollenAllergyTextView = findViewById(R.id.pollenAllergyTextView);
        dustAllergyTextView = findViewById(R.id.dustAllergyTextView);
        moldAllergyTextView = findViewById(R.id.moldAllergyTextView);
        toLibraryButton = findViewById(R.id.respiratorySymptomsButton);
        finishButton = findViewById(R.id.finishButton);
    }

    void assignComponentFunctionalities(){
          lactoseTextView.setOnClickListener(ev -> textViewOnClick("lactoseAllergy"));
          eggTextView.setOnClickListener(ev -> textViewOnClick("eggAllergy"));
          glutenTextView.setOnClickListener(ev -> textViewOnClick("glutenAllergy"));
          sesameTextView.setOnClickListener(ev -> textViewOnClick("sesameAllergy"));
          crustaceansTextView.setOnClickListener(ev -> textViewOnClick("crustaceansAllergy"));
          peanutsTextView.setOnClickListener(ev -> textViewOnClick("peanutsAllergy"));
          soyTextView.setOnClickListener(ev -> textViewOnClick("soyAllergy"));
          nutsTextView.setOnClickListener(ev -> textViewOnClick("nutsAllergy"));
          fishTextView.setOnClickListener(ev -> textViewOnClick("fishAllergy"));
          dogTextView.setOnClickListener(ev -> textViewOnClick("dogAllergy"));
          catTextView.setOnClickListener(ev -> textViewOnClick("catAllergy"));
          penicillinTextView.setOnClickListener(ev -> textViewOnClick("penicillinAllergy"));
          antibioticsTextView.setOnClickListener(ev -> textViewOnClick("antibioticsAllergy"));
          anticonvulsantsTextView.setOnClickListener(ev -> textViewOnClick("anticonvulsantsAllergy"));
          aspirinTextView.setOnClickListener(ev -> textViewOnClick("aspirinAllergy"));
          insectAllergyTextView.setOnClickListener(ev -> textViewOnClick("insectAllergy"));
          pollenAllergyTextView.setOnClickListener(ev -> textViewOnClick("pollenAllergy"));
          dustAllergyTextView.setOnClickListener(ev -> textViewOnClick("dustAllergy"));
          moldAllergyTextView.setOnClickListener(ev -> textViewOnClick("moldAllergy"));
          finishButton.setOnClickListener(ev -> finishButtonClicked());
    }

    void textViewOnClick(String string){

        int imageId = RegisterActivityUtilitaryClass.getCorespondentImageViewId(string);
        imageView = findViewById(imageId);



        boolean currentImageStatus = formular.isValuePresent(string);
        currentImageStatus = !currentImageStatus;


        formular.setValue(string,currentImageStatus);


        oppositeImageViewID = RegisterActivityUtilitaryClass.getOppositeImageId(string,currentImageStatus);
        imageView.setBackgroundResource(oppositeImageViewID);

        int idFamily = RegisterActivityUtilitaryClass.getFamily(string);
        updateCounters(idFamily,currentImageStatus);



    }

    private void updateCounters(int family,boolean b) {
        System.out.println("Aici2");
        if(family == 1){
            if(b)
                foodSelectedCount++;
            else
                foodSelectedCount--;
        }
        else
            if(family == 2){
                if(b)
                    petSelectedCount++;
                else
                    petSelectedCount--;
           }
           else
               if(family == 3) {
                   if (b)
                       drugSelectedCount++;
                   else
                       drugSelectedCount--;
               }

        System.out.println("Aici3");
        if(b) overallSelectedCount++;
        else
            overallSelectedCount--;

        System.out.println(family);
        updateFinishText();
        updateFamilyIcon(family);

        System.out.println();
        System.out.println(foodSelectedCount);
        System.out.println(petSelectedCount);
        System.out.println(drugSelectedCount);
        System.out.println(overallSelectedCount);
        System.out.println();

    }

    private void updateFamilyIcon(int family) {
       if(family == 1){
           ImageView icon = findViewById(R.id.foodAllergyImageView);
           if(foodSelectedCount==0){
               icon.setBackgroundResource(R.drawable.nofood);
           }
           else{
               icon.setBackgroundResource(R.drawable.food);
           }
       }
       else
           if(family == 2){
               ImageView icon = findViewById(R.id.petAllergyImageView);
               if(petSelectedCount==0){
                   icon.setBackgroundResource(R.drawable.nopet);
               }
               else{
                   icon.setBackgroundResource(R.drawable.pet);
               }
           }
           else
               if(family == 3){
                   ImageView icon = findViewById(R.id.drugAllergyImageView);
                   if(drugSelectedCount==0){
                       icon.setBackgroundResource(R.drawable.nodrugs);
                   }
                   else{
                       icon.setBackgroundResource(R.drawable.drugs);
                   }
               }



    }

    private void updateFinishText() {
        String s;
        if(overallSelectedCount==0){
            s = "No allergies to declare";
        }
        else
            if(overallSelectedCount < 19){
                s = "Finish declaration";
            }
            else{
                s = "How are you still alive?";
            }
       finishButton.setText(s);
    }


    void finishButtonClicked(){
        Account account = AccountHolder.getAccount();
        account.setAllergyMap(formular.getAllergyMap());
        RegisterService.finaliseRegisterSequence();
        ActivityShiftService.toMainActivity(this);
    }


}
