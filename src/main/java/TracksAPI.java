import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface TracksAPI {
    @GET("tracks")
    Call<List<Track>> loadTracks();

    @GET("tracks/{id}")
    Call<Track> getTrack(@Path("id") String id);

    @POST("tracks")
    Call<Track> addTrack(@Body Track track);

}
