package org.grakovne.mds.publisher.network.api.vkontakte;

import com.google.gson.GsonBuilder;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.grakovne.mds.publisher.configuraton.Configuration;
import org.grakovne.mds.publisher.network.api.vkontakte.dto.UploadAudioResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

import static org.grakovne.mds.publisher.network.api.ApiNames.VK_API_BASE_URL;

public interface VkontakteRepository {

    @POST
    @Multipart
    Call<UploadAudioResult> uploadAudio(@Url String url, @Part MultipartBody.Part file);

    @POST("al_audio.php")
    @FormUrlEncoded
    Call<ResponseBody> attachAudio(
        @Field("act") String act,
        @Field("aid") String aid,
        @Field("al") String al,
        @Field("audio") String audio,
        @Field("gid") String gid,
        @Field("hash") String hash,
        @Field("mid") String mid,
        @Field("server") String server,
        @Field("upldr") String upldr
    );

    @POST("al_wall.php")
    @FormUrlEncoded
    Call<ResponseBody> postAudio(
        @Field("al") String al,
        @Field("act") String act,
        @Field("to_id") String toId,
        @Field("type") String type,
        @Field("hash") String hash,
        @Field("attach1_type") String audioType,
        @Field("attach1") String audioId,
        @Field("Message") String message
    );

    class ApiFactory {
        public static VkontakteRepository create() {

            Configuration configuration = new Configuration();

            OkHttpClient httpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
                Request.Builder builder = chain.request().newBuilder();
                builder.header("Cookie", "remixsid=" + configuration.getRemixId() + ";");
                return chain.proceed(builder.build());
            }).build();

            Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .baseUrl(VK_API_BASE_URL)
                .client(httpClient)
                .build();

            return retrofit.create(VkontakteRepository.class);
        }
    }

}
