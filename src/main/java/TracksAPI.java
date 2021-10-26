import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import javax.swing.plaf.nimbus.State;

public interface TracksAPI {
    @GET("tracks")
    Call<List<Track>> loadTracks();

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id") String id);

    @DELETE("tracks/{id}")
    Call<ResponseBody> deleteTrack(@Path("id") String id);

    @POST("tracks")
    Call<Track> addTrack(@Body Track track);



}
