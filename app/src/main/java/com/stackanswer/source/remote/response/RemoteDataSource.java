package com.stackanswer.source.remote.response;

import android.util.Log;

import com.stackanswer.source.datasource.JsonHelper;
import com.stackanswer.source.repository.MovieRepository;
import com.stackanswer.model.Movie;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private final JsonHelper jsonHelper;

    public RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public void getAllCourses(String language, String page, final MovieRepository.Callback arrayCallback) {
        jsonHelper.loadMovie(language, page, movieList2 -> {
            arrayCallback.sukses(movieList2);
            Log.d(TAG, "loadMovie: 2 "+movieList2.size());
        });
    }

    private static final String TAG = "MainViewModel";

    public interface Callback{
        void sukses(List<Movie> movieList);
    }






//    public List<Movie> getAllCourses(String language, String page, final MovieRepository.Callback arrayCallback) {
////        List<Movie> movieList = jsonHelper.loadMovie(language, page);
////        return  movieList;
////        bookmark(new BookmarkCallback{
////            public void onSuccess(boolean value){
////            //You can make here all you want with "Value"
////             }
////         public void onError(){
////
////            }       });
//
//        final List<Movie>[] movieList = new List[]{new ArrayList<>()};
//        jsonHelper.loadMovie(language, page, new Callback() {
//            @Override
//            public void sukses(List<Movie> movieList2) {
//                movieList[0] =movieList2;
//                arrayCallback.sukses(movieList2);
//                Log.d(TAG, "loadMovie: 2 "+movieList2.size());
//            }
//        });
//
//
//        Log.d(TAG, "loadMovie: 3 "+movieList[0].size());
//        return movieList[0];
////        return jsonHelper.loadMovie(language, page);
//    }

//    public void setCallback(Callback callback) {
//        this.callback = callback;
//    }



//    public List<CourseResponse> getAllCourses() {
//        return jsonHelper.loadCourses();
//    }
//
//    public List<ModuleResponse> getModules(String courseId) {
//        return jsonHelper.loadModule(courseId);
//    }
//
//    public ContentResponse getContent(String moduleId) {
//        return jsonHelper.loadContent(moduleId);
//    }

}
