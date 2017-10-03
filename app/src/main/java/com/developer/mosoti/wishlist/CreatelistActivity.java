package com.developer.mosoti.wishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

public class CreatelistActivity extends AppCompatActivity {
    //private ArrayList<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlist);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.searchitems, menu);
//
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//
//
//        SearchView searchView = (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//
//               getItems(query);
//
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//
//        });
//
//        return true;
//    }
//
//    public void getItems(String item){
//        final FindItemsService storeService= new FindItemsService();
//        storeService.findItems(item, new okhttp3.Callback(){
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException{
//                                //String jsonData = response.body().string();
//                               // Log.v("data", jsonData);
//                mItems=FindItemsService.processResults(response);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (Item item:mItems){
//                            Log.v("name",item.getName());
//                            Log.v("description",item.getDescription());
//                            for (String image:item.getImages()){
//                                Log.v("images",image);
//                            }
//
//
//                        }
//                    }
//                });
//
//            }
//
//        });
//    }
}
