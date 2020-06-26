package com.aditya.jetpack.adapter;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.ItemFilmBinding;
import com.aditya.jetpack.model.ModelFilm;
import com.aditya.jetpack.model.ModelGenre;
import com.aditya.jetpack.model.ModelMovieView;

import java.util.ArrayList;
import java.util.Objects;

public class AdapterPage extends PagedListAdapter<ModelFilm.Result,RecyclerView.ViewHolder> {
    private ModelMovieView modelMovieView;
    private ArrayList<ModelGenre>modelGenres = new ArrayList<>();
    private static final DiffUtil.ItemCallback<ModelFilm.Result> diffCallback = new DiffUtil.ItemCallback<ModelFilm.Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull ModelFilm.Result oldItem, @NonNull ModelFilm.Result newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ModelFilm.Result oldItem, @NonNull ModelFilm.Result newItem) {
            return Objects.equals(oldItem,newItem);
        }
    };

    public void setModelGenres(ArrayList<ModelGenre> modelGenres) {
        this.modelGenres.addAll(modelGenres);
    }

    public ArrayList<ModelGenre> getModelGenres() {
        return modelGenres;
    }

    public  NavController navController;
    public static final String TAG_MODEL = "TAG_MODEL";

    public AdapterPage(NavController navController) {
        super(diffCallback);
        this.navController = navController;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case R.layout.item_film:
                ItemFilmBinding itemFilmBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_film,parent,false);
                return new Holder(itemFilmBinding.getRoot());
            case R.layout.layout_erro_movie:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_erro_movie,parent,false);
                return new NetworkStateHolder(view);
            default:
                throw new IllegalArgumentException("Unknown View Type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case R.layout.item_film:
                ((Holder) holder).bindData(getItem(position));
                break;
            case R.layout.layout_erro_movie:
                ((NetworkStateHolder)holder).bindData(modelMovieView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+ (hasExtraRow() ? 1 : 0);
    }

    public void setModelMovieView(ModelMovieView modelMovieView) {
        if (getCurrentList()!=null){
            if (getCurrentList().size()!=0){
                ModelMovieView modelMovieView1 = this.modelMovieView;
                boolean hadExtraRow = hasExtraRow();
                this.modelMovieView = modelMovieView;
                boolean hasExtraRow = hasExtraRow();
                if (hadExtraRow!=hasExtraRow){
                    if (hadExtraRow){
                        notifyItemRemoved(super.getItemCount());
                    }else {
                        notifyItemInserted(super.getItemCount());
                    }
                }else if (hasExtraRow&&modelMovieView1!=modelMovieView){
                    notifyItemChanged(getItemCount()-1);
                }
            }
        }
    }

    private boolean hasExtraRow(){
        return modelMovieView!=null&& modelMovieView.isStatusLoading();
    }

    @Override
    public int getItemViewType(int position) {
//        Log.d("TAG_LOADING_NEXT", "bindData: "+(hasExtraRow() && position == getItemCount()-1));
        Log.d("TAG_LOADING_NEXT", "bindData: "+(position)+" "+(getItemCount()-1)+" "+(hasExtraRow() && position == getItemCount()-1)+" "+(hasExtraRow())+" "+(position == getItemCount()-1));
        if (hasExtraRow() && position == getItemCount()-1){
            return R.layout.layout_erro_movie;
        }else {
            return R.layout.item_film;
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
        public void bindData(ModelFilm.Result modelFilm){
            ItemFilmBinding itemFilmBinding = DataBindingUtil.bind(itemView);
            if (itemFilmBinding != null) {
                itemFilmBinding.setDataFilm(modelFilm);
            }
            Objects.requireNonNull(itemFilmBinding).linearFilm.setOnClickListener(view -> {
//                    Navigation.createNavigateOnClickListener(R.id.action_movieFragment_to_detailFragment);
//                    Navigation.findNavController(view).navigate(R.id.action_movieFragment_to_detailFragment);
                Bundle bundle = new Bundle();
//                    bundle.putInt(TAG_MODEL,getItem(getAdapterPosition()).getId());
                bundle.putSerializable(TAG_MODEL,getItem(getAdapterPosition()));
                navController.navigate(R.id.action_baseFragment2_to_detailFragment,bundle);
            });
            ArrayList<String>genreMovie = new ArrayList<>();
            for (ModelGenre modelGenre :modelGenres){
                if (modelFilm.getGenre_ids().contains(modelGenre.getId())){
                    genreMovie.add(modelGenre.getName());
                }
            }
            ((TextView) itemView.findViewById(R.id.tv_genre_tv)).setText(TextUtils.join(", ",genreMovie));
        }
    }

    public static class NetworkStateHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        TextView tvError;
        private ImageView imgBannerError;

        public NetworkStateHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_movie);
            tvError = itemView.findViewById(R.id.tv_error);
            imgBannerError = itemView.findViewById(R.id.img_banner_error);
        }
        public void bindData(ModelMovieView modelMovieView){
            if (modelMovieView.isStatusLoading()) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
            if (modelMovieView.getMsgError()!=null){
                progressBar.setVisibility(View.GONE);
                imgBannerError.setVisibility(View.VISIBLE);
                tvError.setText(modelMovieView.getMsgError());
            }
        }
    }
}
