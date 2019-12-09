package com.dineshredditsample.retrofit;




import com.dineshredditsample.models.ModelRedditPopular;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface API {



    @GET("popular.json")
    Call<ModelRedditPopular> getPopular(@Query("limit") String limit);

}

