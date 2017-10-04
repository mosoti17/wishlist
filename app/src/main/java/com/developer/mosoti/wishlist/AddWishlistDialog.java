package com.developer.mosoti.wishlist;


import android.app.DialogFragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddWishlistDialog extends DialogFragment {
    @BindView(R.id.addName) EditText addName;
    @BindView(R.id.addDescription) EditText adddescription;
    @BindView(R.id.addButton) Button addButton;
    @BindView(R.id.cancelButton) Button cancelButton;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_wishlist_dialog, container, false);
        ButterKnife.bind(this,rootView);
        mAuth = FirebaseAuth.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= addName.getText().toString().trim();
                String details =adddescription.getText().toString().trim();

                if (mAuth.getCurrentUser()!=null){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    DatabaseReference restaurantRef = FirebaseDatabase
                            .getInstance()
                            .getReference("Wishlists")
                            .child(uid);
                    DatabaseReference pushRef = restaurantRef.push();



                    wishModel wish= new wishModel(name,details);
                    pushRef.setValue(wish);

                    dismiss();

                }

            }
        });

        return rootView;
    }


}
