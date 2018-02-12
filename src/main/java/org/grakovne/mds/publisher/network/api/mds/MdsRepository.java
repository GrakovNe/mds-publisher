package org.grakovne.mds.publisher.network.api.mds;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.grakovne.mds.publisher.entity.mds.Story;
import org.grakovne.mds.publisher.network.api.mds.dto.ApiResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import static org.grakovne.mds.publisher.network.api.ApiNames.MDS_API_BASE_URL;

public interface MdsRepository {

    @GET("story/{id}")
    Call<ApiResponse<Story>> getStory(@Path("id") Integer id);

    @GET("story/random")
    Call<ApiResponse<Story>> getRandomStory();

    @GET
    @Streaming
    Call<ResponseBody> getStoryAudio(@Url String url);

    class ApiFactory {
        public static MdsRepository create() {
            Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MDS_API_BASE_URL)
                .client(new OkHttpClient())
                .build();

            return retrofit.create(MdsRepository.class);
        }
    }
}
