package com.example.callapi.api;




import com.example.callapi.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountService {
    String ACCOUNT = "account";

    @GET(ACCOUNT)
    Call<User[]> getAccount();


//    @GET(Account +"/{id}")
//    Call<User> getTraineesbyID(@Path("id") long id);

    @POST(ACCOUNT)
    Call<User> createAccount(@Body User user);
//
//    @PUT(Account+"/{id}")
//    Call<User> updateTrainees(@Path("id") long id, @Body User user);
//
//    @DELETE(Account +"/{id}")
//    Call<User> deleteTrainees(@Path("id") long id);
}
