package com.developer.mosoti.wishlist.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.developer.mosoti.wishlist.ItemDetailFragment;
import com.developer.mosoti.wishlist.models.Item;

import java.util.ArrayList;

/**
 * Created by mosoti on 10/3/17.
 */

public class ItemPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Item> mItems;

    public ItemPageAdapter(FragmentManager fm, ArrayList<Item> items) {
        super(fm);
        mItems = items;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemDetailFragment.newInstance(mItems.get(position));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).getName();
    }
}
