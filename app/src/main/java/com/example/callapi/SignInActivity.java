package com.example.callapi;

import android.accounts.Account;
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
import com.example.callapi.api.TraineeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity  {
private EditText etUserName;
private EditText etPassword;
private TextView tvNotAccountYet;
private Button btnSignIn;
    AccountService account;
private static final String REQUIRE = "require";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etUserName = findViewById(R.id.editTextUserName);
        etPassword = findViewById(R.id.editTextPassword);
        tvNotAccountYet = findViewById(R.id.tvNotAcYet);
        btnSignIn = findViewById(R.id.buttonSignIn);

      tvNotAccountYet.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
               startActivity(intent);

          }
      });
       btnSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String getUsername =etUserName.getText().toString();
               Log.d("username", "onClick: "+ getUsername);
               String getPassword =etPassword.getText().toString();
               Log.d("pass", "onClick: "+ getPassword);
               btnSignIn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       SignIn();
                   }
               });
           }
       });
        account = TraineeRepository.getAccountService();

    }

    private void SignIn(){
        String name = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        try{
            Call<User[]> call = account.getAccount();
            call.enqueue(new Callback<User[]>() {
                @Override
                public void onResponse(Call<User[]> call, Response<User[]> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        User[] checkUser = response.body();

                        boolean isUserFound = false;

                        for (int i = 0; i < checkUser.length; i++) {
                            User user = checkUser[i];
                            if (user.getUsername().equals(name) && user.getPassword().equals(password)) {
                                isUserFound = true;
                                break;
                            }
                        }
                        if (isUserFound) {
                            Intent intent = new Intent(SignInActivity.this, ViewSinhVienActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignInActivity.this, "Tìm thấy cặp username và password trùng.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SignInActivity.this, "Tk hoặc mk sai", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SignInActivity.this, "Không thể tải dữ liệu từ máy chủ.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<User[]> call, Throwable t) {

                    Toast.makeText( SignInActivity.this,"Save ko oke"
                            ,Toast.LENGTH_LONG).show();

                }
            });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

//    private void signIn() {
//        if (!checkInput()) {
//            return;
//        }
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//    private void signUpForm() {
//        Intent intent = new Intent(this, SignUpActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.buttonSignIn) {
//            signIn();
//        } else if (view.getId() == R.id.tvNotAcYet) {
//            signUpForm();
//        }
//    }


}