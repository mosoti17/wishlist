package com.developer.mosoti.wishlist.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.developer.mosoti.wishlist.R;
import com.developer.mosoti.wishlist.models.Item;
import com.developer.mosoti.wishlist.models.wishModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;



public class wishesDialog extends DialogFragment{
    private ListView mListView;
    private DatabaseReference mRestaurantReference;
    private FirebaseAuth mAuth;
    List<String> names = new ArrayList<String>();
    List<String> id = new ArrayList<String>();
    private Item mItem;


    public wishesDialog() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mItem=Parcels.unwrap(getArguments().getParcelable("item"));
        String uid = user.getUid();
        mRestaurantReference = FirebaseDatabase.getInstance().getReference("Wishlists").child(uid);
        mRestaurantReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){

                    names.add(data.getValue(wishModel.class).getname());
                    id.add(data.getValue(wishModel.class).getPushId());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_wishes_dialog, container, false);
        mListView = (ListView)view.findViewById(R.id.listView);



        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, names);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (mAuth.getCurrentUser()!=null){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = user.getUid();
                    DatabaseReference restaurantRef = FirebaseDatabase
                            .getInstance()
                            .getReference("Wishlists")
                            .child(uid)
                            .child(id.get(i))
                            .child("items");
                    Log.v("id",id.get(i));
                restaurantRef.push().setValue(mItem);

                    dismiss();
                }
            }
        });

        return view;
    }


}
