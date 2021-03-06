package com.developer.mosoti.wishlist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.mosoti.wishlist.models.Item;
import com.developer.mosoti.wishlist.ui.wishesDialog;
import com.squareup.picasso.Picasso;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment {
    @BindView(R.id.imageView2) ImageView mImageView;
    @BindView(R.id.nameView) TextView mInameView;
    @BindView(R.id.priceView) TextView mPriceView;
    @BindView(R.id.availabilityView) TextView mAvailabilityView;
    @BindView(R.id.stockView) TextView mStockView;
    @BindView(R.id.save) Button mSaveButton;
    private Item mItem;

    public static ItemDetailFragment newInstance(Item item) {
        ItemDetailFragment restaurantDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", Parcels.wrap(item));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }



    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem = Parcels.unwrap(getArguments().getParcelable("item"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.bind(this,view);

        Picasso.with(view.getContext()).load(mItem.getImage()).into(mImageView);
        mInameView.setText(mItem.getName());
        mPriceView.setText("Price $: "+String.valueOf(mItem.getPrice()));
        mAvailabilityView.setText("Availability: "+mItem.getAvailability());
        mStockView.setText("Stock: "+mItem.getStock());
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                wishesDialog moodDialogFragment = new wishesDialog ();
                Bundle bundle=new Bundle();
                bundle.putParcelable("item", Parcels.wrap(mItem));
                moodDialogFragment.setArguments(bundle);
                moodDialogFragment.show(fm,mItem.toString());



            }
        });

        return view;

    }



}
