package com.example.callapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.callapi.api.AccountService;
import com.example.callapi.api.TraineeRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity  {
private EditText etUsername;
private EditText etPassword;
private EditText etConfirmPassword;
private TextView tvAlreadyAccount;
private Button btnSignUp;
    AccountService account;
private static final String REQUIRE = "require";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.editUserName);
        etPassword = findViewById(R.id.editPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount);
        btnSignUp = findViewById(R.id.buttonRegister);
        account = TraineeRepository.getAccountService();
       tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
               startActivity(intent);
           }
       });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUsername =etUsername.getText().toString();
                Log.d("username", "onClick: "+ getUsername);
                String getPassword =etPassword.getText().toString();
                Log.d("pass", "onClick: "+ getPassword);
                String getPasswordConfirm =etConfirmPassword.getText().toString();
                Log.d("passCF", "onClick: "+ getPasswordConfirm);

                if (getPassword.equals(getPasswordConfirm)) {
                    // Tiếp tục tạo tài khoản
                    User newUser = new User(getUsername, getPassword);
                    createAccount(newUser);
                }else {
                    Toast.makeText(SignUpActivity.this,"Mk ko trùng "
                            ,Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    private void createAccount(User user){
        try{
            Call<User> call = account.createAccount(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.body()!=null){
                        Toast.makeText(SignUpActivity.this,"Create Successfully"
                                ,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this,"ERRRORR API"
                            ,Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }
}