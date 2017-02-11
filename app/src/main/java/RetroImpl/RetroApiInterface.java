package RetroImpl;

import DataModel.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lakshmisagar on 1/28/2017.
 */

public interface RetroApiInterface {
    @GET("Search")
    Call<ProductResponse> getSearch(@Query("term") String _term, @Query("key") String _apiKey);
}
