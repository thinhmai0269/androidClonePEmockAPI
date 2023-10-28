package com.example.callapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callapi.api.TraineeRepository;
import com.example.callapi.api.TraineeService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSinhVienActivity extends AppCompatActivity {
    ListView lvStudent;
    ArrayList<Trainee> traineeArrayList;
    SinhVienAdapter svAdapter;
    TraineeService traineeService;
    Button addSV;
    TextView name,sex,phone, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_sinh_vien);
        ax();
        //ADD
        addSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSinhVienActivity.this, ThemSinhVienActivity.class);
                startActivity(intent);
            }
        });
        //UD
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewSinhVienActivity.this, UpdateDeleteSinhVien.class);
                long idSv = traineeArrayList.get(position).getId();
                intent.putExtra("id", idSv);
                startActivity(intent);
                return true;
            }
        });
        traineeService = TraineeRepository.getTraineeService();
        //View
        try{
    Call<Trainee[]> call = traineeService.getAllTrainees();
    call.enqueue(new Callback<Trainee[]>() {
        @Override
        public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
            Trainee[] trainees = response.body();
            if (trainees != null) {
                traineeArrayList = new ArrayList<>();
                for (Trainee trainee : trainees) {
                    traineeArrayList.add(trainee);
                }
            }


            svAdapter = new SinhVienAdapter(ViewSinhVienActivity.this,
                    R.layout.detail_danh_sach_sinh_vien,
                    traineeArrayList);
            lvStudent.setAdapter(svAdapter);
        }

        @Override
        public void onFailure(Call<Trainee[]> call, Throwable t) {

        }
    });
        }catch (Exception e){
            Log.d("err", "onCreate: "+e);
        }



    }
private void ax(){
        name = (TextView)  findViewById(R.id.NameSV);
        sex = (TextView)  findViewById(R.id.SexSV);
        phone = (TextView)  findViewById(R.id.PhoneSV);
        email = (TextView)  findViewById(R.id.EmailSV);
    lvStudent =(ListView)   findViewById(R.id.listStudent);
    addSV=(Button) findViewById(R.id.AddButton);
}
}