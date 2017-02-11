package RetroImpl;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static GlobalConstants.Constants.BASE_URL;

/**
 * Created by Lakshmisagar on 1/28/2017.
 */

public class RetroClientAPI {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
