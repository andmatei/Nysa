package com.nysa;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.SmileRating;



/**
 * A simple {@link Fragment} subclass.
 */
public class RespiratoryFragment extends Fragment {

    public SmileRating smileRatingR1;
    public SmileRating smileRatingR2;
    private DatabaseReference mDatabase;
    public int r1Status;
    public int r2Status;
    public RespiratoryFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_respiratory, container, false);
        smileRatingR1 = (SmileRating) view.findViewById(R.id.smile_rating_r1);
        smileRatingR2 = (SmileRating) view.findViewById(R.id.smile_rating_r2);
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current_user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tracker");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String R1S = dataSnapshot.child("RS1").getValue().toString();
                String R2S = dataSnapshot.child("RS2").getValue().toString();

                if(Integer.parseInt(R1S)!=-1)
                smileRatingR1.setSelectedSmile(Integer.parseInt(R1S));
                if(Integer.parseInt(R2S)!=-1)
                smileRatingR2.setSelectedSmile(Integer.parseInt(R2S));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        smileRatingR1.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected( int smiley, boolean reselected) {
                mDatabase.child("RS1").setValue(smiley);


                switch (smiley) {



                    case SmileRating.BAD:
                        mDatabase.child("RS1").setValue(smiley);


                        break;
                    case SmileRating.GOOD:
                        mDatabase.child("RS1").setValue(smiley);


                        break;
                    case SmileRating.GREAT:
                        mDatabase.child("RS1").setValue(smiley);


                        break;
                    case SmileRating.OKAY:
                        mDatabase.child("RS1").setValue(smiley);


                        break;
                    case SmileRating.TERRIBLE:
                        mDatabase.child("RS1").setValue(smiley);


                        break;
                }
            }
        });

        smileRatingR2.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected( int smiley, boolean reselected) {
                mDatabase.child("RS2").setValue(smiley);


                switch (smiley) {



                    case SmileRating.BAD:
                        mDatabase.child("RS2").setValue(smiley);


                        break;
                    case SmileRating.GOOD:
                        mDatabase.child("RS2").setValue(smiley);


                        break;
                    case SmileRating.GREAT:
                        mDatabase.child("RS2").setValue(smiley);


                        break;
                    case SmileRating.OKAY:
                        mDatabase.child("RS2").setValue(smiley);


                        break;
                    case SmileRating.TERRIBLE:
                        mDatabase.child("RS2").setValue(smiley);


                        break;
                }
            }
        });





        return  view;

    }

}
