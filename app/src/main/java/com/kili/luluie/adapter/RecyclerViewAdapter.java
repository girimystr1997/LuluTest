package com.kili.luluie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.kili.luluie.R;
import com.kili.luluie.databinding.RecycContentBinding;
import com.kili.luluie.model.AlbumDatum;
import com.kili.luluie.viewmodel.MainActivityViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {



    List<AlbumDatum> albumDatumList;
    Context context;

    public RecyclerViewAdapter(List<AlbumDatum> albumData, Context context) {
        this.albumDatumList =albumData;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecycContentBinding recycContentBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recyc_content,parent,false);
        return new MyViewHolder(recycContentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RecycContentBinding recycContentBinding = holder.recycContentBinding;
        recycContentBinding.setAlbumdata(new MainActivityViewModel(albumDatumList.get(position)));

    }

    @Override
    public int getItemCount() {
        return albumDatumList.size();
    }

     public static class MyViewHolder extends RecyclerView.ViewHolder {

        RecycContentBinding recycContentBinding;

        public MyViewHolder(RecycContentBinding recycContentBinding) {
            super(recycContentBinding.getRoot());
            this.recycContentBinding = recycContentBinding;
        }
    }


}
