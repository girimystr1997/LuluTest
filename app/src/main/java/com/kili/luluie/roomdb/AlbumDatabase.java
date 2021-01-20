package com.kili.luluie.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kili.luluie.model.AlbumDatum;
import com.kili.luluie.variables.Variable;

@Database(entities = {AlbumDatum.class},version = 1)

public abstract class AlbumDatabase extends RoomDatabase {

    public static AlbumDatabase instance;
    public abstract Albumdao albumdao();

    public static AlbumDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AlbumDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AlbumDatabase.class, Variable.TableNAme)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
