package com.developer.mosoti.wishlist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.developer.mosoti.wishlist.R;
import com.developer.mosoti.wishlist.models.wishModel;

/**
 * Created by mosoti on 10/4/17.
 */

public class FirebaseViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;
    public FirebaseViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();


    }
    public void bindWish(wishModel wish) {

        TextView nameTextView = (TextView) mView.findViewById(R.id.textName);
        TextView descTextView = (TextView) mView.findViewById(R.id.texDesc);

        Log.v("name",String.valueOf(wish.getName()));

        nameTextView.setText(wish.getName());
        descTextView.setText(wish.getDescription());

    }

}
