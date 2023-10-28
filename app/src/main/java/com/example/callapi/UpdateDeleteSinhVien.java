package com.example.callapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callapi.api.TraineeRepository;
import com.example.callapi.api.TraineeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeleteSinhVien extends AppCompatActivity {
    TraineeService traineeService;
    Button Update, Delete;
    EditText name, email, phone, gender;
    long idSv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_sinh_vien);
        //AX
        name = (EditText)findViewById(R.id.nameDetail);
        email = (EditText)findViewById(R.id.emailDetail);
        phone = (EditText)findViewById(R.id.phoneDetail);
        gender = (EditText)findViewById(R.id.sexDetail);
        Update =(Button)findViewById(R.id.Updatebtn);
        Delete =(Button)findViewById(R.id.deleteBtn);
        //
        Intent intent = getIntent();
         idSv = intent.getLongExtra("id", -1);
        Log.d("test id", "onCreate: "+idSv);
        traineeService = TraineeRepository.getTraineeService();
        Trainee trainee = new Trainee();
        try{
            Call<Trainee> call = traineeService.getTraineesbyID(idSv);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null){
                        Log.d("Res", "onResponse: "+response.body());
                        name.setText(response.body().getName());
                        email.setText(response.body().getEmail());
                        phone.setText(response.body().getPhone());
                        gender.setText(response.body().getGender());
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Log.d("err", "onCreate: "+e);
        }


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateThongTin();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteSV();
            }
        });
        traineeService = TraineeRepository.getTraineeService();

    }
    private void UpdateThongTin(){
        String nameedit = name.getText().toString();
        String emailedit = email.getText().toString();
        String phoneedit = phone.getText().toString();
        String sexedit = gender.getText().toString();

     Trainee trainee = new Trainee(nameedit,emailedit,phoneedit,sexedit);
     Call<Trainee> call = traineeService.updateTrainees(idSv, trainee);
     call.enqueue(new Callback<Trainee>() {
         @Override
         public void onResponse(Call<Trainee> call, Response<Trainee> response) {
             if(response.body()!=null){
                 Toast.makeText(UpdateDeleteSinhVien.this,"Update oke"
                         ,Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(UpdateDeleteSinhVien.this, ViewSinhVienActivity.class);
                 startActivity(intent);
             }
         }

         @Override
         public void onFailure(Call<Trainee> call, Throwable t) {

         }
     });
    }
    private void DeleteSV(){

        Call<Trainee> call = traineeService.deleteTrainees(idSv);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                if(response.body()!=null){
                    Toast.makeText(UpdateDeleteSinhVien.this,"Delete oke"
                            ,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UpdateDeleteSinhVien.this, ViewSinhVienActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {

            }
        });
    }
}