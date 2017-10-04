package com.developer.mosoti.wishlist;


import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WishlistsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlists);

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


}
