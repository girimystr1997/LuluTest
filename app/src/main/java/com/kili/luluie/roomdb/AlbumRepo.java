package com.kili.luluie.roomdb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.kili.luluie.model.AlbumDatum;

import java.util.List;

public class AlbumRepo {

    private Albumdao albumdao;
    private LiveData<List<AlbumDatum>> allAlbumData;

    public AlbumRepo(Application application) {
        AlbumDatabase db = AlbumDatabase.getDatabase(application);
        albumdao = db.albumdao();
        allAlbumData = albumdao.getAllAlbumData();
    }

    public LiveData<List<AlbumDatum>> getAllAlbumData() {
        return allAlbumData;
    }

    public void insert (AlbumDatum albumDatum) {
        new insertAsyncTask(albumdao).execute(albumDatum);
    }

    private static class insertAsyncTask extends AsyncTask<AlbumDatum, Void, Void> {

        private Albumdao mAsyncTaskDao;

        insertAsyncTask(Albumdao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(AlbumDatum... albumData) {
            for (AlbumDatum datum : albumData) {
                AlbumDatum albumDatum = new AlbumDatum(datum.getAlbumId(),datum.getId(),datum.getTitle(),datum.getUrl(),datum.getThumbnailUrl());
                mAsyncTaskDao.insert(albumDatum);
            }
            return null;
        }
    }
}
