package com.developer.mosoti.wishlist;


import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.developer.mosoti.wishlist.adapters.FirebaseViewHolder;
import com.developer.mosoti.wishlist.models.wishModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WishlistsActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview1) RecyclerView mRecyclerView;
    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    ArrayList<wishModel> wish= new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlists);
        ButterKnife.bind(this);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mRestaurantReference = FirebaseDatabase.getInstance().getReference("Wishlists").child(uid);


        setUpFirebaseAdapter();
        Floating();


    }

    public void Floating(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                AddWishlistDialog moodDialogFragment = new AddWishlistDialog ();
                moodDialogFragment.show(fm,"dialog");
            }
        });
    }
    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRestaurantReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){

                    wish.add(data.getValue(wishModel.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRestaurantReference = FirebaseDatabase.getInstance().getReference("Wishlists").child(uid);

        mFirebaseAdapter = new FirebaseRecyclerAdapter<wishModel, FirebaseViewHolder>
                (wishModel.class, R.layout.wish, FirebaseViewHolder.class,
                        mRestaurantReference) {

            @Override
            protected void populateViewHolder(FirebaseViewHolder viewHolder,
                                              wishModel model, int position) {
                viewHolder.bindWish(model);
            }
        };



        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }


}
