package com.aditya.jetpack.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.FragmentDetailBinding;
import com.aditya.jetpack.model.ModelFilm;
import com.aditya.jetpack.viewmodel.FavoriteViewModel;
import com.aditya.jetpack.viewmodel.ViewModelDetailMovie;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private int movieId = 0;
    private FragmentDetailBinding fragmentDetailBinding;
    private FavoriteViewModel favoriteViewModel;
    private ModelFilm.Result modelFilm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDetailBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_detail,container,false);


        if (getArguments()!=null&& getArguments().getSerializable("TAG_MODEL") !=null) {
            modelFilm = (ModelFilm.Result) getArguments().getSerializable("TAG_MODEL");
            movieId = modelFilm.getId();
        }

        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelDetailMovie viewModelDetailMovie = ViewModelProviders.of(getActivity()).get(ViewModelDetailMovie.class);
        favoriteViewModel =ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class);
        if (movieId>0){
            viewModelDetailMovie.getTrailer(movieId);
            viewModelDetailMovie.getDetailMovie(movieId);
        }
        viewModelDetailMovie.getModeDetailMovieStateMutableLiveData().observe(getViewLifecycleOwner(), modeDetailMovieState -> fragmentDetailBinding.setData(modeDetailMovieState.getModelDetailMovie()));
        viewModelDetailMovie.getModelViewTrailerStateLiveData().observe(getViewLifecycleOwner(), modelViewTrailerState -> {
            getLifecycle().addObserver(fragmentDetailBinding.youTubePlayerView);
            fragmentDetailBinding.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    if (modelViewTrailerState.getModelResponseVideo()!=null&&modelViewTrailerState.getModelResponseVideo().getKey()!=null){
                        youTubePlayer.loadVideo(modelViewTrailerState.getModelResponseVideo().getKey(),0);
                        youTubePlayer.pause();
                    }
                }
            });
        });

        favoriteViewModel.getResultLiveData(movieId).observe(getViewLifecycleOwner(),result -> {
            Log.d("TAG_FAVORITE_MOVIE", "onActivityCreated: "+result);
            if (result!=null){
                fragmentDetailBinding.imgLove.setImageResource(R.drawable.ic_baseline_favorite_24);
                fragmentDetailBinding.tvLove.setText("Loved");
            }else {
                fragmentDetailBinding.imgLove.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                fragmentDetailBinding.tvLove.setText("Love");
            }
            fragmentDetailBinding.imgLove.setOnClickListener(view -> {
                if (result!=null){
                    favoriteViewModel.deleteFavoriteMovie(result);
                }else {
                    favoriteViewModel.insertFavoriteMovie(this.modelFilm);
                }
            });
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
