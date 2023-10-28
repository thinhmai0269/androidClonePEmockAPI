package com.example.callapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.callapi.api.TraineeRepository;
import com.example.callapi.api.TraineeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemSinhVienActivity extends AppCompatActivity {
    TraineeService traineeService;
    EditText etname, etemail, etphone, etsex;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_sinh_vien);

        etname = findViewById(R.id.inputNameSV);
        etemail = findViewById(R.id.inputEmailSV);
        etphone = findViewById(R.id.inputPhoneSV);
        etsex = findViewById(R.id.inputSex);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        traineeService = TraineeRepository.getTraineeService();

    }
    private void save(){
        String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String phone = etphone.getText().toString();
        String sex = etsex.getText().toString();

        Trainee trainee = new Trainee(name,email,phone,sex);
        try{
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body()!=null){
                        Toast.makeText(ThemSinhVienActivity.this,"Save oke"
                                ,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ThemSinhVienActivity.this, ViewSinhVienActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {

                        Toast.makeText(ThemSinhVienActivity.this,"Save ko oke"
                                ,Toast.LENGTH_LONG).show();

                }
            });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }
}