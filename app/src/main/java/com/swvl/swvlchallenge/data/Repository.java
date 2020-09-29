package com.swvl.swvlchallenge.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.swvl.swvlchallenge.data.model.Movie;
import com.swvl.swvlchallenge.data.model.MoviesJsonFile;
import com.swvl.swvlchallenge.utils.Utils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

/**
 * Created by Karim Abdell Salam on 28,September,2020
 */
public class Repository implements DataHelper {
    private Gson gson;
    Context mContext;

    @Inject
    public Repository(Retrofit retrofit, Context mContext, Gson gson) {
        this.mContext = mContext;
        this.gson = gson;
    }

    @Override
    public Observable<List<Movie>> loadAllMovies() {
        return Observable.fromCallable(new Callable<List<Movie>>() {
            @Override
            public List<Movie> call() throws Exception {
                Type type = new TypeToken<MoviesJsonFile>() {
                }.getType();
                MoviesJsonFile moviesJsonFile = gson.fromJson(Utils.Data.loadJSONFromAsset(mContext, Utils.Const.SEED_MOVIE_JSON), type);
                return moviesJsonFile.getMovies();
            }
        });
    }
}
