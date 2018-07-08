package com.nysa;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class RegisterAllergies extends AppCompatActivity {

    private TextView title;
    private TextView foodTitle;
    private TextView petTitle;
    private TextView drugTitle;
    private TextView insectTitle;
    private TextView polenTitle;
    private TextView dustTitle;
    private TextView moldTitle;


    private TextView  lactoseTxt;
    private TextView  eggTxt;
    private TextView  glutenTxt;
    private TextView  sesameTxt;
    private TextView  crustaceansTxt;
    private TextView  peanutsTxt;
    private TextView  soyTxt;
    private TextView  nutsTxt;
    private TextView  fishTxt;

    private TextView  dogTxt;
    private TextView catTxt;


    private TextView penicillinTxt;
    private TextView antibioticsTxt1;
    private TextView antibioticsTxt2;
    private TextView anticonvulsantsTxt;
    private TextView aspirinTxt1;
    private TextView aspirinTxt2;
    private TextView aspirinTxt3;


    private ImageButton iconFood;
    private Button titleFood;
    private Button lactoseBtn;
    private Button eggBtn;
    private Button glutenBtn;
    private Button sesameBtn;
    private Button crustaceansBtn;
    private Button peanutsBtn;
    private Button soyBtn;
    private Button nutsBtn;
    private Button fishBtn;

    private ImageButton iconPet;
    private Button titlePet;
    private Button dogBtn;
    private Button catBtn;

    private ImageButton iconDrug;
    private Button titleDrug;
    private Button penicillinBtn;
    private Button antibiotcsBtn;
    private Button anticonvulsantsBtn;
    private Button aspirinBtn;

    private ImageButton iconInsect;
    private Button titleInsect;

    private ImageButton iconPolen;
    private Button titlePolen;

    private ImageButton iconDust;
    private Button titleDust;

    private ImageButton iconMold;
    private Button titleMold;

    private Button nextBtn;
    private Button libraryBtn;


    private ImageView lactoseCheck;
    private ImageView eggCheck;
    private ImageView glutenCheck;
    private ImageView sesameCheck;
    private ImageView crustaceansCheck;
    private ImageView peanutsCheck;
    private ImageView soyCheck;
    private ImageView nutsCheck;
    private ImageView fishCheck;


    private ImageView dogCheck;
    private ImageView catCheck;


    private ImageView penicillinCheck;
    private ImageView antibioticsCheck;
    private ImageView anticonvulsantsCheck;
    private ImageView aspirinCheck;

    private DatabaseReference nDatabase;
    private FirebaseUser c_user;


private boolean [] result = new boolean[25];
private int [] groupChecked = new int[9];
private int elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_allergies);


        libraryBtn = (Button) findViewById(R.id.libraryBtn);
        libraryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLibrary = new Intent(RegisterAllergies.this, Library.class);
                toLibrary.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(toLibrary);
            }
        });

        Typeface fontSegoeSemiBold = Typeface.createFromAsset(getAssets(), "Fonts/seguisb.ttf");
        Typeface fontSegoeStd = Typeface.createFromAsset(getAssets(), "Fonts/Segoe-UI.ttf");
        Typeface fontSegoeSemiLight = Typeface.createFromAsset(getAssets(), "Fonts/segoe-ui-semilight-610.ttf");

        title = (TextView) findViewById(R.id.Title);
        title.setTypeface(fontSegoeStd);

        foodTitle = (TextView) findViewById(R.id.FoodAllergytxt);
        petTitle = (TextView) findViewById(R.id.PetAllergytxt);
        drugTitle = (TextView) findViewById(R.id.DrugAllergytxt);
        insectTitle = (TextView) findViewById(R.id.InsectAllergytxt);
        polenTitle = (TextView) findViewById(R.id.PolenAllergytxt);
        dustTitle = (TextView) findViewById(R.id.DustAllergytxt);
        moldTitle = (TextView) findViewById(R.id.MoldAllergytxt);


        foodTitle.setTypeface(fontSegoeSemiLight);
        petTitle.setTypeface(fontSegoeSemiLight);
        drugTitle.setTypeface(fontSegoeSemiLight);
        insectTitle.setTypeface(fontSegoeSemiLight);
        polenTitle.setTypeface(fontSegoeSemiLight);
        dustTitle.setTypeface(fontSegoeSemiLight);
        moldTitle.setTypeface(fontSegoeSemiLight);



        lactoseTxt = (TextView) findViewById(R.id.LactoseTxt);
        eggTxt = (TextView) findViewById(R.id.EggTxt);
        glutenTxt = (TextView) findViewById(R.id.GlutenTxt);
        sesameTxt = (TextView) findViewById(R.id.SesameTxt);
        crustaceansTxt= (TextView) findViewById(R.id.CrustaceansTxt);
        peanutsTxt = (TextView) findViewById(R.id.PeanutsTxt);
        soyTxt   = (TextView) findViewById(R.id.SoyTxt);
        nutsTxt   = (TextView) findViewById(R.id.NutsTxt);
        fishTxt    = (TextView) findViewById(R.id.FishTxt);

        lactoseTxt.setTypeface(fontSegoeSemiBold);
        eggTxt.setTypeface(fontSegoeSemiBold);
        glutenTxt.setTypeface(fontSegoeSemiBold);
        sesameTxt.setTypeface(fontSegoeSemiBold);
        crustaceansTxt.setTypeface(fontSegoeSemiBold);
        peanutsTxt.setTypeface(fontSegoeSemiBold);
        soyTxt.setTypeface(fontSegoeSemiBold);
        nutsTxt.setTypeface(fontSegoeSemiBold);
        fishTxt.setTypeface(fontSegoeSemiBold);


        dogTxt = (TextView) findViewById(R.id.DogTxt);
        catTxt = (TextView) findViewById(R.id.CatTxt);


        dogTxt.setTypeface(fontSegoeSemiBold);
        catTxt.setTypeface(fontSegoeSemiBold);


        penicillinTxt = (TextView) findViewById(R.id.PenicillinTxt);
        antibioticsTxt1 = (TextView) findViewById(R.id.AntibioticsTxt1);
        antibioticsTxt2 = (TextView) findViewById(R.id.AntibioticsTxt2);
        anticonvulsantsTxt = (TextView) findViewById(R.id.AnticonvulsantsTxt);
        aspirinTxt1 = (TextView) findViewById(R.id.AspirinTxt1);
        aspirinTxt2 = (TextView) findViewById(R.id.AspirinTxt2);
        aspirinTxt3 = (TextView) findViewById(R.id.AspirinTxt3);


        penicillinTxt.setTypeface(fontSegoeSemiBold);
        antibioticsTxt1.setTypeface(fontSegoeSemiBold);
        antibioticsTxt2.setTypeface(fontSegoeSemiBold);
        anticonvulsantsTxt.setTypeface(fontSegoeSemiBold);
        aspirinTxt1.setTypeface(fontSegoeSemiBold);
        aspirinTxt2.setTypeface(fontSegoeSemiBold);
        aspirinTxt3.setTypeface(fontSegoeSemiBold);

        iconFood = (ImageButton) findViewById(R.id.FoodIcon);
        iconDrug = (ImageButton) findViewById(R.id.DrugIcon);
        iconDust = (ImageButton) findViewById(R.id.DustIcon);
        iconInsect = (ImageButton) findViewById(R.id.InsectIcon);
        iconMold = (ImageButton) findViewById(R.id.MoldIcon);
        iconPet = (ImageButton) findViewById(R.id.PetIcon);
        iconPolen = (ImageButton) findViewById(R.id.PolenIcon);

        titleFood = (Button) findViewById(R.id.FoodTitleBtn);
        titlePet = (Button) findViewById(R.id.PetTitleBtn);
        titleDrug = (Button) findViewById(R.id.DrugTitleBtn);
        titleDust = (Button) findViewById(R.id.DustTitleBtn);
        titleInsect = (Button) findViewById(R.id.InsectTitleBtn);
        titleMold = (Button) findViewById(R.id.MoldTitleBtn);
        titlePolen = (Button) findViewById(R.id.PolenTitleBtn);


        lactoseBtn = (Button) findViewById(R.id.LactoseBtn);
        eggBtn = (Button) findViewById(R.id.EggBtn) ;
        glutenBtn = (Button) findViewById(R.id.GlutenBtn);
        sesameBtn =  (Button) findViewById(R.id.SesameBtn);
        crustaceansBtn = (Button) findViewById(R.id.CrustaceansBtn);
        peanutsBtn = (Button) findViewById(R.id.PeanutsBtn);
        soyBtn = (Button) findViewById(R.id.SoyBtn);
        nutsBtn = (Button) findViewById(R.id.NutsBtn);
        fishBtn = (Button) findViewById(R.id.FishBtn);

        dogBtn = (Button) findViewById(R.id.DogBtn);
        catBtn = (Button) findViewById(R.id.CatBtn);

        penicillinBtn = (Button) findViewById(R.id.PenicillinBtn);
        antibiotcsBtn = (Button) findViewById(R.id.AntibioticsBtn);
        anticonvulsantsBtn = (Button) findViewById(R.id.AnticonvulsantsBtn);
        aspirinBtn = (Button) findViewById(R.id.AsprinBtn);

        nextBtn = (Button) findViewById(R.id.NextBtn);


        lactoseCheck = (ImageView) findViewById(R.id.LactoseCheck);
        glutenCheck = (ImageView) findViewById(R.id.GlutenCheck);
        eggCheck = (ImageView) findViewById(R.id.EggCheck);
        sesameCheck = (ImageView) findViewById(R.id.SesameCheck);
        crustaceansCheck = (ImageView) findViewById(R.id.CrustaceansCheck);
        peanutsCheck = (ImageView) findViewById(R.id.PeanutsCheck);
        soyCheck = (ImageView) findViewById(R.id.SoyCheck);
        nutsCheck = (ImageView) findViewById(R.id.NutsCheck);
        fishCheck = (ImageView) findViewById(R.id.FishCheck);

        dogCheck = (ImageView) findViewById(R.id.DogCheck);
        catCheck = (ImageView) findViewById(R.id.CatCheck);

        aspirinCheck = (ImageView) findViewById(R.id.AsprinCheck);
        anticonvulsantsCheck = (ImageView)findViewById(R.id.AnticonvulsantsCheck);
        antibioticsCheck = (ImageView) findViewById(R.id.AntibioticsCheck);
        penicillinCheck = (ImageView) findViewById(R.id.PenicillinCheck);

        getData();


            lactoseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkingChild(1,1,lactoseCheck);
                    checkingFood();
                    checkElements(elements);
                }
            });

          eggBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(2,1,eggCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });
          glutenBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(3,1,glutenCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });
          sesameBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(4,1,sesameCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });

          crustaceansBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(5,1,crustaceansCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });

          peanutsBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(6,1,peanutsCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });

          soyBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(7,1,soyCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });

          nutsBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(8,1,nutsCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });

          fishBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(9,1,fishCheck);
                  checkingFood();
                  checkElements(elements);
              }
          });

          dogBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(10,2,dogCheck);
                  checkingPet();
                  checkElements(elements);
              }
          });
          catBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(11,2,catCheck);
                  checkingPet();
                  checkElements(elements);
              }
          });

          penicillinBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(12,3,penicillinCheck);
                  checkingDrugs();
                  checkElements(elements);
              }
          });

          antibiotcsBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(13,3,antibioticsCheck);
                  checkingDrugs();
                  checkElements(elements);

              }
          });

          anticonvulsantsBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(14,3,anticonvulsantsCheck);
                  checkingDrugs();
                  checkElements(elements);
              }
          });

          aspirinBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  checkingChild(15,3,aspirinCheck);
                  checkingDrugs();
                  checkElements(elements);
              }
          });

          iconFood.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  iconFood.setImageDrawable(getDrawable(R.drawable.food));
                  if(!result[1]){

                      checkingChild(1,1,lactoseCheck);
                  }
                  if(!result[2]){

                      checkingChild(2,1,eggCheck);
                  }
                  if(!result[3]){

                      checkingChild(3,1,glutenCheck);
                  }
                  if(!result[4]){

                      checkingChild(4,1,sesameCheck);
                  }
                  if(!result[5]){

                      checkingChild(5,1,crustaceansCheck);
                  }
                  if(!result[6]){

                      checkingChild(6,1,peanutsCheck);
                  }
                  if(!result[7]){

                      checkingChild(7,1,soyCheck);
                  }
                  if(!result[8]){

                      checkingChild(8,1,nutsCheck);
                  }
                  if(!result[9]){

                      checkingChild(9,1,fishCheck);
                  }

                    checkElements(elements);

              }
          });

          iconPet.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  iconPet.setImageDrawable(getDrawable(R.drawable.pet));
                  if(!result[10]){
                      checkingChild(10,2,dogCheck);

                  }
                  if(!result[11]){
                      checkingChild(11,2,catCheck);

                  }

                  checkElements(elements);
              }
          });

          iconDrug.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  iconDrug.setImageDrawable(getDrawable(R.drawable.drugs));

                  if(!result[12]){
                      checkingChild(12,3,penicillinCheck);
                  }
                  if(!result[13]){
                      checkingChild(13,3,antibioticsCheck);

                  }
                  if(! result[14]){
                      checkingChild(14,3,anticonvulsantsCheck);

                  }
                  if(!result[15]){
                      checkingChild(15,3,aspirinCheck);

                  }
                  checkElements(elements);
              }
          });

          iconPolen.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  if(groupChecked[4]==0)
                  {
                      groupChecked[4]=1;
                      elements++;
                      iconPolen.setImageDrawable(getDrawable(R.drawable.polen));
                      checkElements(elements);
                      result[17] = true;
                  }
                  else
                  {

                      groupChecked[4]=0;
                      elements--;
                      iconPolen.setImageDrawable(getDrawable(R.drawable.nopolen));
                      checkElements(elements);
                      result[17] =false;
                  }

              }
          });

          titlePolen.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  if(groupChecked[4]==0)
                  {
                      groupChecked[4]=1;
                      elements++;
                      iconPolen.setImageDrawable(getDrawable(R.drawable.polen));
                      checkElements(elements);
                      result[17] = true;
                  }
                  else
                  {

                      groupChecked[4]=0;
                      elements--;
                      iconPolen.setImageDrawable(getDrawable(R.drawable.nopolen));
                      checkElements(elements);
                      result[17] = false;
                  }

              }
          });


        iconInsect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(groupChecked[5]==0)
                {
                    groupChecked[5]=1;
                    elements++;
                    iconInsect.setImageDrawable(getDrawable(R.drawable.ganganie));
                    checkElements(elements);
                    result[16] = true;
                }
                else
                {

                    groupChecked[5]=0;
                    elements--;
                    iconInsect.setImageDrawable(getDrawable(R.drawable.noganganie));
                    checkElements(elements);
                    result[16] = false;
                }

            }
        });



        titleInsect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(groupChecked[5]==0)
                {
                    groupChecked[5]=1;
                    elements++;
                    iconInsect.setImageDrawable(getDrawable(R.drawable.ganganie));
                    checkElements(elements);
                    result[16] = true;
                }
                else
                {

                    groupChecked[5]=0;
                    elements--;
                    iconInsect.setImageDrawable(getDrawable(R.drawable.noganganie));
                    checkElements(elements);
                    result[16] = false;
                }

            }
        });


        iconDust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(groupChecked[6]==0)
                {
                    groupChecked[6]=1;
                    elements++;
                    iconDust.setImageDrawable(getDrawable(R.drawable.dust));
                    checkElements(elements);
                    result[18] =true;
                }
                else
                {

                    groupChecked[6]=0;
                    elements--;
                    iconDust.setImageDrawable(getDrawable(R.drawable.nodust));
                    checkElements(elements);
                    result[18] =false;
                }

            }
        });



        titleDust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(groupChecked[6]==0)
                {
                    groupChecked[6]=1;
                    elements++;
                    iconDust.setImageDrawable(getDrawable(R.drawable.dust));
                    checkElements(elements);
                    result[18] = true;
                }
                else
                {

                    groupChecked[6]=0;
                    elements--;
                    iconDust.setImageDrawable(getDrawable(R.drawable.nodust));
                    checkElements(elements);
                    result[18] = false;
                }

            }
        });


        iconMold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(groupChecked[7]==0)
                {
                    groupChecked[7]=1;
                    elements++;
                    iconMold.setImageDrawable(getDrawable(R.drawable.mold));
                    checkElements(elements);
                    result[19] = true;
                }
                else
                {

                    groupChecked[7]=0;
                    elements--;
                    iconMold.setImageDrawable(getDrawable(R.drawable.nomold));
                    checkElements(elements);
                    result[19] = false;
                }

            }
        });



        titleMold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(groupChecked[7]==0)
                {
                    groupChecked[7]=1;
                    elements++;
                    iconMold.setImageDrawable(getDrawable(R.drawable.mold));
                    checkElements(elements);
                    result[19] = true;
                }
                else
                {

                    groupChecked[7]=0;
                    elements--;
                    iconMold.setImageDrawable(getDrawable(R.drawable.nomold));
                    checkElements(elements);
                    result[19] = false;
                }

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = current_user.getUid();
                nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("allergies");
                HashMap<String,String> userMap = new HashMap<>();
                String Allergy;
                String [] allergies = new String[20];
                allergies[1] = "Lactose";
                allergies[2] = "Egg";
                allergies[3] = "Gluten";
                allergies[4] = "Sesame";
                allergies[5] = "Crustaceans";
                allergies[6] = "Peanuts";
                allergies[7] = "Soy";
                allergies[8] = "Nuts";
                allergies[9] = "Fish";
                allergies[10] = "Dog";
                allergies[11] = "Cat";
                allergies[12] = "Penicilin";
                allergies[13] = "Antibiotics";
                allergies[14] = "Anticonvulsants";
                allergies[15] = "Aspirin";
                allergies[16] = "Insect";
                allergies[17] = "Pollen";
                allergies[18] = "Dust";
                allergies[19] = "Mold";
                 Boolean T = true;
                 Boolean F = false;
                for(int i=1;i<=19;i++)
                {
                    if(result[i]){
                        userMap.put(allergies[i],T.toString());
                    }
                    else {

                        userMap.put(allergies[i],F.toString());
                    }



                }

                nDatabase.setValue(userMap);

                Intent toMain = new Intent(RegisterAllergies.this,MainActivity.class);
                toMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(toMain);





            }
        });






    }






    public void checkElements(int elements){
        if(elements==19){

            nextBtn.setText("How are you still alive?");
        }
        else
        if(elements==0)
        {
            nextBtn.setText("No allergies to declare");

        }
        else
        {

            nextBtn.setText("Finish declaration");
        }

    }

    public void checkingFood()  {
        if(groupChecked[1]==0){
            iconFood.setImageDrawable(getDrawable(R.drawable.nofood));
        }
        else
        {
            iconFood.setImageDrawable(getDrawable(R.drawable.food));

        }
    }


    public void checkingPet()  {
        if(groupChecked[2]==0){
            iconPet.setImageDrawable(getDrawable(R.drawable.nopet));
        }
        else
        {
            iconPet.setImageDrawable(getDrawable(R.drawable.pet));

        }
    }


    public void checkingDrugs()  {
        if(groupChecked[3]==0){
            iconDrug.setImageDrawable(getDrawable(R.drawable.nodrugs));
        }
        else
        {
            iconDrug.setImageDrawable(getDrawable(R.drawable.drugs));

        }
    }

    public void checkingChild(int childid, int parentid, ImageView checks){
        if(!result[childid]){

            checks.setImageDrawable(getDrawable(R.drawable.checked));
            result[childid] = true;
            groupChecked[parentid]++;
            elements++;
        }
        else
        {
            checks.setImageDrawable(getDrawable(R.drawable.unchecked));
            result[childid] = false;
            groupChecked[parentid]--;
            elements--;
        }

    }

    public void getData(){

        c_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = c_user.getUid();
        nDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("allergies");
        nDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String getLactose = dataSnapshot.child("Lactose").getValue().toString();
                String getEgg = dataSnapshot.child("Egg").getValue().toString();
                String getGluten = dataSnapshot.child("Gluten").getValue().toString();
                String getSesame = dataSnapshot.child("Sesame").getValue().toString();
                String getCrustaceans = dataSnapshot.child("Crustaceans").getValue().toString();
                String getPeanuts = dataSnapshot.child("Lactose").getValue().toString();
                String getSoy = dataSnapshot.child("Soy").getValue().toString();
                String getNuts = dataSnapshot.child("Nuts").getValue().toString();
                String getFish = dataSnapshot.child("Fish").getValue().toString();
                String getDog = dataSnapshot.child("Dog").getValue().toString();
                String getCat = dataSnapshot.child("Cat").getValue().toString();
                String getPenicilin = dataSnapshot.child("Penicilin").getValue().toString();
                String getAntibiotics = dataSnapshot.child("Antibiotics").getValue().toString();
                String getAnticonvulsants = dataSnapshot.child("Anticonvulsants").getValue().toString();
                String getAspirin = dataSnapshot.child("Aspirin").getValue().toString();
                String getInsect = dataSnapshot.child("Insect").getValue().toString();
                String getPollen = dataSnapshot.child("Pollen").getValue().toString();
                String getDust = dataSnapshot.child("Dust").getValue().toString();
                String getMold = dataSnapshot.child("Mold").getValue().toString();



                processData(getLactose,1,1,lactoseCheck,1);
                processData(getEgg,2,1,eggCheck,2);
                processData(getGluten,3,1,glutenCheck,3);
                processData(getSesame,4,1,sesameCheck,4);
                processData(getCrustaceans,5,1,crustaceansCheck,5);
                processData(getPeanuts,6,1,peanutsCheck,6);
                processData(getSoy,7,1,soyCheck,7);
                processData(getNuts,8,1,nutsCheck,8);
                processData(getFish,9,1,fishCheck,9);
                processData(getDog,1,2,dogCheck,10);
                processData(getCat,2,2,catCheck,11);
                processData(getPenicilin,1,3,penicillinCheck,12);
                processData(getAntibiotics,2,3,antibioticsCheck,13);
                processData(getAnticonvulsants,3,3,anticonvulsantsCheck,14);
                processData(getAspirin,4,3,aspirinCheck,15);
                checkingFood();
                checkingPet();
                checkingDrugs();
                if(getInsect.equals("true")){
                    elements++;
                    groupChecked[4]=1;
                    result[16] = true;
                    iconInsect.setImageDrawable(getDrawable(R.drawable.ganganie));
                }

                if(getPollen.equals("true")){
                    elements++;
                    groupChecked[5]=1;
                    result[17] = true;
                    iconPolen.setImageDrawable(getDrawable(R.drawable.polen));
                }

                if(getDust.equals("true")){
                    elements++;
                    groupChecked[6]=1;
                    result[18] = true;
                    iconDust.setImageDrawable(getDrawable(R.drawable.dust));
                }

                if(getMold.equals("true")){
                    elements++;
                    groupChecked[7]=1;
                    result[19] = true;
                    iconMold.setImageDrawable(getDrawable(R.drawable.mold));
                }

                checkElements(elements);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void processData(String status, int posChild, int posParent, ImageView check , int resultpos){
        if(status.equals("true")){
            check.setImageDrawable(getDrawable(R.drawable.checked));
            result[resultpos] = true;
            groupChecked[posParent]++;
            elements++;
        }
    }

}

