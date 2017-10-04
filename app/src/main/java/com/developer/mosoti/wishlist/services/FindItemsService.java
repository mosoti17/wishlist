package com.developer.mosoti.wishlist.services;


import android.util.Log;

import com.developer.mosoti.wishlist.Constants;
import com.developer.mosoti.wishlist.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mosoti on 10/2/17.
 */

public class FindItemsService {
    public static void findItems(String item, Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY, item);
        urlBuilder.addQueryParameter(Constants.API_KEY_BASE,Constants.API_KEY);
        urlBuilder.addQueryParameter(Constants.API_FORMAT,"json");
        String url = urlBuilder.build().toString();
        Log.v("url",url);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()

                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
    public static ArrayList<Item> processResults(Response response) {
        ArrayList<Item> items = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject itemsJSON= (JSONObject) new JSONObject(jsonData);
                JSONArray listJSON = itemsJSON.getJSONArray("items");

                for (int i = 0; i < listJSON.length(); i++) {
                    JSONObject itemJSON = listJSON.getJSONObject(i);
                    Integer itemid=itemJSON.getInt("itemId");
                    double price =itemJSON.getDouble("salePrice");
                    String name=itemJSON.getString("name");
                    String image=itemJSON.getString("mediumImage");
                    String stock=itemJSON.getString("stock");
                    String url=itemJSON.getString("productUrl");
                    String availability;
                    if (itemJSON.has("offerType")){
                        availability=itemJSON.getString("offerType");
                    }else {
                        availability="not available";
                    }


                    ArrayList<String> images= new ArrayList<>();

                    if (itemJSON.has("imageEntities")){
                        JSONArray imageJSON= itemJSON.getJSONArray("imageEntities");
                        for (int j=0;j<imageJSON.length();j++){
                            JSONObject tumb=imageJSON.getJSONObject(j);
                            images.add(tumb.getString("thumbnailImage"));

                        }

                    }

                    String description;
                    if (itemJSON.has("sshortDescription")){
                        description=itemJSON.getString("shortDescription");
                    }else{
                         description="not available";
                    }

                    Item item= new Item(itemid,price,name,image,stock,availability,url,images,description);

                    items.add(item);
                }



            }


        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return items;
    }
}
