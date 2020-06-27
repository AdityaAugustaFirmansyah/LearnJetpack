package com.aditya.jetpack.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.aditya.jetpack.R;
import com.aditya.jetpack.databinding.FragmentDetailBinding;
import com.aditya.jetpack.model.ModelFilm;
import com.aditya.jetpack.viewmodel.ViewModelDetailMovie;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private ViewModelDetailMovie viewModelDetailMovie;
    private int movieId = 0;
    private FragmentDetailBinding fragmentDetailBinding;
    private YouTubePlayerFragment youTubePlayerSupportFragment;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
        fragmentDetailBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_detail,container,false);


        if (getArguments()!=null&& getArguments().getSerializable("TAG_MODEL") !=null) {
            ModelFilm.Result modelFilm = (ModelFilm.Result) getArguments().getSerializable("TAG_MODEL");
            movieId = modelFilm.getId();
        }

        return fragmentDetailBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModelDetailMovie = ViewModelProviders.of(getActivity()).get(ViewModelDetailMovie.class);
        if (movieId>0){
            viewModelDetailMovie.getTrailer(movieId);
            viewModelDetailMovie.getDetailMovie(movieId);
        }
        viewModelDetailMovie.getModeDetailMovieStateMutableLiveData().observe(getViewLifecycleOwner(), modeDetailMovieState -> {
            fragmentDetailBinding.setData(modeDetailMovieState.getModelDetailMovie());
        });
        viewModelDetailMovie.getModelViewTrailerStateLiveData().observe(getViewLifecycleOwner(),modelViewTrailerState -> {
            getLifecycle().addObserver(fragmentDetailBinding.youTubePlayerView);
            fragmentDetailBinding.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    if (modelViewTrailerState.getModelResponseVideo()!=null&&modelViewTrailerState.getModelResponseVideo().getKey()!=null){
                        youTubePlayer.loadVideo(modelViewTrailerState.getModelResponseVideo().getKey(),0);
                        youTubePlayer.pause();
                    }
                }
            });
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}