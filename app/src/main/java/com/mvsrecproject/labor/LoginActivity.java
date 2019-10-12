package com.mvsrecproject.labor;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    EditText etPhone,etOTP;
    Button btnLogin,btnGetOTP;
    ProgressBar OTPprogressBar;
    FirebaseAuth mAuth;
    private String OTPCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etPhone = findViewById(R.id.etPhone);
        etOTP = findViewById(R.id.etOTP);
        btnGetOTP = findViewById(R.id.btngetotp);
        btnLogin = findViewById(R.id.btnLogin);
        OTPprogressBar = findViewById(R.id.OTPprogressBar);
        mAuth = FirebaseAuth.getInstance();


        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenum = etPhone.getText().toString().trim();
                if(phonenum.length()!=10){
                    etPhone.setError("Enter Valid phone number");
                    etPhone.requestFocus();
                    return;
                }
                Toast.makeText(LoginActivity.this, "OTP sent", Toast.LENGTH_SHORT).show();
                OTPprogressBar.setVisibility(View.VISIBLE);
                sendOTP(phonenum);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            String phonenum = etPhone.getText().toString().trim();
            @Override
            public void onClick(View v) {
                if(etOTP.length()!=6){
                    etOTP.setError("Enter OTP");
                    etOTP.requestFocus();
                    return;
                }
                String otp = etOTP.getText().toString().trim();
                verifyOTP(otp);
            }
        });
        nextActivity();
    }

    //Method that will send OTP code.
    private void sendOTP(String phno){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phno,
                30,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
                );
    }

    private void verifyOTP(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTPCode,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,SelectorActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("PhoneNum",etPhone.getText().toString().trim());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            OTPCode = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String otp = phoneAuthCredential.getSmsCode();
            if(otp!=null){
                verifyOTP(otp);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    public void startNextActivity(int x){
        Intent intent;
        if(x==0){
            intent = new Intent(LoginActivity.this, LaborSkillSelector.class);
            startActivity(intent);
        }
        else{
            intent = new Intent(LoginActivity.this,AvailabilityActivity.class);
            startActivity(intent);
        }
    }

    public void nextActivity(){
        DatabaseReference contractorRootNode = FirebaseDatabase.getInstance().getReference().child("Contractors");
        DatabaseReference laborsRootNode = FirebaseDatabase.getInstance().getReference().child("Labors");


        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            final String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            contractorRootNode.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(currentUser)){
                        startNextActivity(0);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(LoginActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            laborsRootNode.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   if(dataSnapshot.hasChild(currentUser)){
                       startNextActivity(1);
                   }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(LoginActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

