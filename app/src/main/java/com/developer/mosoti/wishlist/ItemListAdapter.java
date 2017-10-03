package com.developer.mosoti.wishlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mosoti on 10/3/17.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
    private Context mContext;
    private ArrayList<Item> mItems=new ArrayList<>();
    public ItemListAdapter(Context context,ArrayList<Item> items){
        mContext=context;
        mItems=items;

    }
    @Override
    public ItemListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemListAdapter.ItemViewHolder holder, int position) {
        holder.bindItem(mItems.get(position));
    }

    @Override
    public int getItemCount() {
       // Log.v("items",mItems.get(0).getName());
        return mItems.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView mNameView;
        private ImageView imageView;
        private TextView mPriceView;
        private Context mContext;

        public ItemViewHolder(View itemView){
            super(itemView);
            //ButterKnife.bind(this,itemView);
            mNameView =(TextView) itemView.findViewById(R.id.textView2);
            imageView=(ImageView) itemView.findViewById(R.id.imageView2);
            mPriceView=(TextView) itemView.findViewById(R.id.textView3);


            mContext=itemView.getContext();

        }
        public void bindItem(Item item){


                Picasso.with(mContext).load(item.getImage()).into(imageView);


            mNameView.setText(item.getName());
            mPriceView.setText(String.valueOf(item.getPrice()));
        }


    }


}