package com.example.callapi.api;

import com.example.callapi.Trainee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TraineeService {
    String TRAINEES = "sinhvien";

    @GET(TRAINEES)
    Call<Trainee[]> getAllTrainees();

    @GET(TRAINEES +"/{id}")
    Call<Trainee> getTraineesbyID(@Path("id") long id);

    @POST(TRAINEES)
    Call<Trainee> createTrainees(@Body Trainee trainee);

    @PUT(TRAINEES+"/{id}")
    Call<Trainee> updateTrainees(@Path("id") long id, @Body Trainee trainee);

    @DELETE(TRAINEES +"/{id}")
    Call<Trainee> deleteTrainees(@Path("id") long id);
}
