package com.example.callapi.api;

import com.example.callapi.Trainee;

public class TraineeRepository {
    public static TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }

    public static AccountService getAccountService(){
        return APIClient.getClient().create(AccountService.class);
    }
}
