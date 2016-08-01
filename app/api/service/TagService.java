package api.service;

import models.list.Tags;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TagService {
    @GET("/tags.xml")
    Call<Tags> index();
}
