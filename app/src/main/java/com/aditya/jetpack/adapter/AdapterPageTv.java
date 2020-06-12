package com.aditya.jetpack.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class AdapterPageTv extends PagedListAdapter<ModelTv.Result,AdapterPageTv.Holder> {

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
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTvBinding itemTvBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_tv,parent,false);
        return new Holder(itemTvBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bindDataTv(getItem(position));
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
        void bindDataTv(final ModelTv.Result result){
            ItemTvBinding itemTvBinding = DataBindingUtil.bind(itemView);
            itemTvBinding.setData(result);
            itemTvBinding.linearTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BaseFragmentDirections.ActionBaseFragment2ToDetailTvFragment actionBaseFragment2ToDetailTvFragment =
                            BaseFragmentDirections.actionBaseFragment2ToDetailTvFragment(result);
                    Navigation.findNavController(view).navigate(actionBaseFragment2ToDetailTvFragment);
                }
            });
        }
    }
}
