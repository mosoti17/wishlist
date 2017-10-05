package com.developer.mosoti.wishlist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.developer.mosoti.wishlist.adapters.ItemListAdapter;
import com.developer.mosoti.wishlist.models.Item;
import com.developer.mosoti.wishlist.services.FindItemsService;
import com.developer.mosoti.wishlist.services.TrendingService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatelistFragment extends Fragment  {
    private ArrayList<Item> mItems;
   // @BindView(R.id.recyclerview) RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView;
    public ItemListAdapter mAdapter;


    public CreatelistFragment() {
        // Required empty public constructor
    }
//    @Override
//    public void onCreate(Bundle saveBundle,View view) {
//        super.onCreate(saveBundle);
//      ButterKnife.bind(view);
//
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_createlist, container, false);
         mRecyclerView=(RecyclerView) view.findViewById( R.id.recyclerview);
         findItems();

        setHasOptionsMenu(true);
        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.searchitems,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);


        final SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {


                getItems(query);



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

       // return true;
    }

    public void getItems(String item){
        final FindItemsService storeService= new FindItemsService();
        storeService.findItems(item, new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //String jsonData = response.body().string();
                // Log.v("data", jsonData);
                mItems = FindItemsService.processResults(response);
            if (getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new ItemListAdapter(getContext(), mItems);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(false);
//
                    }
                });
            }

            }

        });
    }

    public void findItems(){
        final FindItemsService storeService= new FindItemsService();
        TrendingService.findVOD( new okhttp3.Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //String jsonData = response.body().string();
                // Log.v("data", jsonData);
                mItems = FindItemsService.processResults(response);
                if (getActivity()!=null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mAdapter = new ItemListAdapter(getContext(), mItems);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(false);
//
                        }
                    });
                }

            }

        });
    }

}
