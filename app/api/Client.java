package api;

import api.service.PartyService;
import api.service.TagService;
import models.list.Parties;
import models.list.Tags;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;

public class Client {
    private static final String API_KEY = "4c5119e4941f6685ade902430f064ddc";

    private static Client sInstance;

    static {
        if (sInstance == null) {
            sInstance = new Client();
        }
    }

    private PartyService mPartyService;
    private TagService mTagService;

    public Client() {
        OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    public Request authenticate(Route route, Response response) throws IOException {
                        return response.request()
                                .newBuilder()
                                .header("Authorization", Credentials.basic(API_KEY, ""))
                                .build();
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl("https://tester136.highrisehq.com/")
                .client(client)
                .build();

        mPartyService = retrofit.create(PartyService.class);
        mTagService = retrofit.create(TagService.class);
    }

    public static Call<Parties> getParties(String[] tags) {
        return sInstance.mPartyService.index(tags);
    }

    public static Call<Tags> getTags() {
        return sInstance.mTagService.index();
    }
}
