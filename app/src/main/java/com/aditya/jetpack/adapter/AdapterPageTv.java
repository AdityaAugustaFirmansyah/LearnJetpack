package com.aditya.jetpack.adapter;

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
import androidx.navigation.Navigation;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.jetpack.BaseFragmentDirections;
import com.aditya.jetpack.datasource.ModelTv;
import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.ItemTvBinding;
import com.aditya.jetpack.model.ModelGenre;
import com.aditya.jetpack.model.ModelTvView;

import java.util.ArrayList;
import java.util.Objects;

public class AdapterPageTv extends PagedListAdapter<ModelTv.Result,RecyclerView.ViewHolder> {

    private ModelTvView modelTvView;
    private ArrayList<ModelGenre>modelGenres = new ArrayList<>();

    public void setModelGenres(ArrayList<ModelGenre> modelGenres) {
        this.modelGenres.addAll(modelGenres);
    }

    private static final DiffUtil.ItemCallback<ModelTv.Result> diffCallback = new DiffUtil.ItemCallback<ModelTv.Result>() {
        @Override
        public boolean areItemsTheSame(@NonNull ModelTv.Result oldItem, @NonNull ModelTv.Result newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ModelTv.Result oldItem, @NonNull ModelTv.Result newItem) {
            return true;
        }
    };

    public AdapterPageTv() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case R.layout.item_tv:
                ItemTvBinding itemTvBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_tv,parent,false);
                return new Holder(itemTvBinding.getRoot());
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
            case R.layout.layout_erro_movie:
                ((NetworkStateHolder)holder).bindData(modelTvView);
                break;
            case R.layout.item_tv:
                ((Holder) holder).bindDataTv(getItem(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow()&&position==getItemCount()-1){
            return R.layout.layout_erro_movie;
        }else {
            return R.layout.item_tv;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+(hasExtraRow()?1:0);
    }

    public void setModelTvView(ModelTvView modelTvView) {
        if (getCurrentList()!=null&&getCurrentList().size()>0){
            ModelTvView modelTvView1 = this.modelTvView;
            boolean hadExtraRow = hasExtraRow();
            this.modelTvView = modelTvView;
            boolean hasExtraRow = hasExtraRow();

            if (hadExtraRow!=hasExtraRow){
                if (hadExtraRow){
                    notifyItemRemoved(super.getItemCount());
                }else {
                    notifyItemInserted(super.getItemCount());
                }
            }else if (hasExtraRow&&modelTvView1!=modelTvView){
                notifyItemChanged(getItemCount()-1);
            }
        }
    }

    private boolean hasExtraRow(){
        return modelTvView!=null&&modelTvView.isLoading();
    }

    private  class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
        void bindDataTv(final ModelTv.Result result){
            ItemTvBinding itemTvBinding = DataBindingUtil.bind(itemView);
            Objects.requireNonNull(itemTvBinding).setData(result);
            itemTvBinding.linearTv.setOnClickListener(view -> {
                BaseFragmentDirections.ActionBaseFragment2ToDetailTvFragment actionBaseFragment2ToDetailTvFragment =
                        BaseFragmentDirections.actionBaseFragment2ToDetailTvFragment(result);
                Navigation.findNavController(view).navigate(actionBaseFragment2ToDetailTvFragment);
            });
            ArrayList<String>genreTv = new ArrayList<>();
            for (ModelGenre modelGenre :modelGenres){
                if ( result.getGenre_ids().contains(modelGenre.getId())){
                    genreTv.add(modelGenre.getName());
                }
            }
            ((TextView)itemView.findViewById(R.id.tv_genre_tv)).setText(TextUtils.join(", ",genreTv));
        }
    }

    private static class NetworkStateHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView tvError;
        ImageView imageView;
        public NetworkStateHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_movie);
            tvError = itemView.findViewById(R.id.tv_error);
            imageView = itemView.findViewById(R.id.img_banner_error);
        }

        public void bindData(ModelTvView modelTvView){
            progressBar.setVisibility(modelTvView.isLoading()?View.VISIBLE:View.GONE);
            if (modelTvView.getMsgError()!=null){
                imageView.setVisibility(View.VISIBLE);
                tvError.setText(modelTvView.getMsgError());
            }else {
                imageView.setVisibility(View.GONE);
                tvError.setText("");
            }
            Log.d("TAG_ERROR", "bindData: "+modelTvView.getMsgError());
        }
    }
}
