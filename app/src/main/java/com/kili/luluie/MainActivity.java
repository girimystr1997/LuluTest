package com.kili.luluie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.kili.luluie.adapter.RecyclerViewAdapter;
import com.kili.luluie.api.Helper;
import com.kili.luluie.api.HttpsRequest;
import com.kili.luluie.databinding.ActivityMainBinding;
import com.kili.luluie.model.AlbumDatum;
import com.kili.luluie.variables.Variable;
import com.kili.luluie.viewmodel.MainActivityViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    RecyclerViewAdapter recyclerViewAdapter;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainActivityViewModel = new MainActivityViewModel(getApplication());
        mainActivityViewModel.getListLiveData().observe(this, albumData -> {
            activityMainBinding.itemProgressBar.setVisibility(View.VISIBLE);
            loadFromApi(albumData);
        });


    }

    private void loadFromLocal(List<AlbumDatum> albumData) {

        recyclerViewAdapter = new RecyclerViewAdapter(albumData,getApplicationContext());
        activityMainBinding.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
        activityMainBinding.itemProgressBar.setVisibility(View.GONE);
    }

    private void loadFromApi(List<AlbumDatum> albumData) {
        new HttpsRequest(Variable.URl,MainActivity.this).stringGet(Request.Method.GET, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONArray response) {
                if (flag){
                    if (response.length() != albumData.size()){
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                mainActivityViewModel.insert(new Gson().fromJson(response.get(i).toString(),AlbumDatum.class));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        loadFromLocal(albumData);
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}