package com.kili.luluie.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.kili.luluie.BR;
import com.kili.luluie.model.AlbumDatum;
import com.kili.luluie.roomdb.AlbumRepo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivityViewModel extends BaseObservable {



    AlbumRepo albumRepo;
    LiveData<List<AlbumDatum>> listLiveData;
    Application application;
    AlbumDatum albumDatum;


    public MainActivityViewModel(Application application) {
        albumRepo = new AlbumRepo(application);
        listLiveData = albumRepo.getAllAlbumData();
        this.application  = application;
    }

    public MainActivityViewModel(AlbumDatum albumDatum) {
        this.albumDatum = albumDatum;
    }

    public LiveData<List<AlbumDatum>> getListLiveData() { return listLiveData; }

    public void insert(AlbumDatum albumDatum) { albumRepo.insert(albumDatum); }




    @Bindable
    public String getTitle() {
        return albumDatum.getTitle();
    }

    public void setTitle(String title) {
        albumDatum.setTitle(title);
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getUrl(){
        return albumDatum.getUrl();
    }

    public void setUrl(String url){
        albumDatum.setUrl(url);
        notifyPropertyChanged(BR.url);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.get().load(url).into(view);
    }


}
