package com.aditya.jetpack.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.FragmentDetailTvBinding;
import com.aditya.jetpack.model.ModelTv;
import com.aditya.jetpack.viewmodel.FavoriteViewModel;
import com.aditya.jetpack.viewmodel.ViewModelDetailMovie;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

public class DetailTvFragment extends Fragment {

    ViewModelDetailMovie viewModelDetailMovie;
    ModelTv.Result result;
    FragmentDetailTvBinding detailTvBinding;
    private FavoriteViewModel favoriteViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detailTvBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_tv,container,false);
        result = (ModelTv.Result) (getArguments() != null ? getArguments().getSerializable("TAG_MODEL_1") : null);
//        detailTvBinding.setData(result);
        return detailTvBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModelDetailMovie = ViewModelProviders.of(getActivity()).get(ViewModelDetailMovie.class);
        favoriteViewModel = ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class);

        if (result.getId()>0){
            viewModelDetailMovie.getDetailTv(result.getId());
        }

        viewModelDetailMovie.getModelDetailTvStateLiveData().observe(getViewLifecycleOwner(),modelDetailTvState -> {
            detailTvBinding.setData(modelDetailTvState.getModelDetailTv());
        });

        viewModelDetailMovie.getModelViewTrailerStateLiveData().observe(getViewLifecycleOwner(),modelViewTrailerState -> {
            getLifecycle().addObserver(detailTvBinding.youTubePlayerView);
            detailTvBinding.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    if (modelViewTrailerState.getModelResponseVideo()!=null){
                        youTubePlayer.loadVideo(modelViewTrailerState.getModelResponseVideo().getKey(),0);
                        youTubePlayer.pause();
                    }
                }
            });
        });

        favoriteViewModel.getTvResults(result.getId()).observe(getViewLifecycleOwner(),result1 -> {
            if (result1!=null){
                detailTvBinding.imgLove.setImageResource(R.drawable.ic_baseline_favorite_24);
                detailTvBinding.imgLove.setOnClickListener(view -> favoriteViewModel.deleteFavoriteTv(result1));
            }else {
                detailTvBinding.imgLove.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                detailTvBinding.imgLove.setOnClickListener(view -> favoriteViewModel.insertFavoriteTvs(this.result));
            }
        });
    }
}
