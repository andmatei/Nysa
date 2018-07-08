package com.nysa;


import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.Objects;


public class EyesFragment extends Fragment {

    public SmileRating smileRating;
    private DatabaseReference mDatabase;
    public int eyeStatus;
    public EyesFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_eyes, container, false);
         smileRating = (SmileRating) view.findViewById(R.id.smile_rating_eyes);
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = current_user.getUid();
         mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("tracker");
         mDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 if(dataSnapshot != null) {
                     String eyeS = dataSnapshot.child("ES").getValue().toString();
                     if(Integer.parseInt(eyeS)!=-1)
                         smileRating.setSelectedSmile(Integer.parseInt(eyeS));
                 }
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });









        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected( int smiley, boolean reselected) {
                mDatabase.child("ES").setValue(smiley);


                switch (smiley) {



                    case SmileRating.BAD:
                        mDatabase.child("ES").setValue(smiley);


                        break;
                    case SmileRating.GOOD:
                        mDatabase.child("ES").setValue(smiley);


                        break;
                    case SmileRating.GREAT:
                        mDatabase.child("ES").setValue(smiley);


                        break;
                    case SmileRating.OKAY:
                        mDatabase.child("ES").setValue(smiley);


                        break;
                    case SmileRating.TERRIBLE:
                        mDatabase.child("ES").setValue(smiley);


                        break;
                }
            }
        });




    return  view;

    }

}
