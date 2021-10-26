import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TracksAPI {
    @GET("tracks")
    Call<List<Track>> loadTracks();

    @POST("tracks")
    Call<Track> addTrack(@Body Track track);

}
