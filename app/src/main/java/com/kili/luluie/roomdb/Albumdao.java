package com.kili.luluie.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kili.luluie.model.AlbumDatum;

import java.util.List;

@Dao
public interface Albumdao {

    @Insert
    void insert(AlbumDatum albumDatum);

    @Query("SELECT * FROM album_table ")
    LiveData<List<AlbumDatum>> getAllAlbumData();

}
