package api.service;

import models.list.Parties;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PartyService {
    @GET("/parties.xml")
    Call<Parties> index(@Query("tag[]") String... tags);
}
