package com.adc.om.afterdeathcare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LoginAll extends AppCompatActivity {
    private Spinner spinner1;
    EditText userco,username,passwords;
    Button usersign,login;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    EditText phoneed, codeed;
    TextView registers;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    TextView timertext;
    Timer timer;
    ImageView verifiedimg;
    Boolean mVerified = false;
    String mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_all);





        spinner1 = (Spinner) findViewById(R.id.myspinner);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        usersign=(Button)findViewById(R.id.sendverifybt);
        username=(EditText)findViewById(R.id.username);
        passwords=(EditText)findViewById(R.id.password);
        phoneed = (EditText) findViewById(R.id.numbered);
        codeed = (EditText) findViewById(R.id.verificationed);
        registers=(TextView)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();
        timertext = (TextView) findViewById(R.id.timertv);
        verifiedimg = (ImageView) findViewById(R.id.verifiedsign);
        FirebaseApp.initializeApp(this);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                Log.d("TAG", "onVerificationCompleted:" + credential);

                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("TAG", "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Snackbar snackbar = Snackbar
                            .make((ConstraintLayout) findViewById(R.id.parentlayout), "Verification Failed !! Invalied verification Code", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
                else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar snackbar = Snackbar
                            .make((ConstraintLayout) findViewById(R.id.parentlayout), "Verification Failed !! Too many request. Try after some time. ", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("TAG", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    public void onregclick(View v){
        Intent reg=new Intent(LoginAll.this,Register.class);
        startActivity(reg);
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {


        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(getApplicationContext(), "Please Select Valid Option", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            switch (i) {

                case 0:
                    phoneed.setVisibility(View.INVISIBLE);
                    codeed.setVisibility(View.INVISIBLE);
                    usersign.setVisibility(View.INVISIBLE);
                    username.setVisibility(View.INVISIBLE);
                    passwords.setVisibility(View.INVISIBLE);
                    login.setVisibility(View.INVISIBLE);


                    break;


                case 1: {

                    phoneed.setVisibility(View.VISIBLE);
                    usersign.setVisibility(View.VISIBLE);
                    username.setVisibility(View.INVISIBLE);
                    passwords.setVisibility(View.INVISIBLE);
                    login.setVisibility(View.INVISIBLE);
                    usersign.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i=new Intent(LoginAll.this,User.class);
                            startActivity(i);
                        }
                    });
//
//                    usersign.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (usersign.getTag().equals(getResources().getString(R.string.tag_send))) {
//                                if (!phoneed.getText().toString().trim().isEmpty() && phoneed.getText().toString().trim().length() >= 10) {
//                                    startPhoneNumberVerification(phoneed.getText().toString().trim());
//                                    mVerified = false;
//                                    starttimer();
//                                    codeed.setVisibility(View.VISIBLE);
//
//                                    usersign.setTag(getResources().getString(R.string.tag_verify));
//                                }
//                                else {
//                                    phoneed.setError("Please enter valid mobile number");
//                                }
//                            }
//
//                            if (usersign.getTag().equals(getResources().getString(R.string.tag_verify))) {
//                                if (!codeed.getText().toString().trim().isEmpty() && !mVerified) {
//                                    Snackbar snackbar = Snackbar
//                                            .make((ConstraintLayout) findViewById(R.id.parentlayout), "Please wait...", Snackbar.LENGTH_LONG);
//
//                                    snackbar.show();
//                                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, codeed.getText().toString().trim());
//                                    signInWithPhoneAuthCredential(credential);
//                                }
//                                if (mVerified) {
//                                    startActivity(new Intent(LoginAll.this, User.class));
//                                }
//
//                            }
//
//                        }
//                    });
//                    timertext.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (!phoneed.getText().toString().trim().isEmpty() && phoneed.getText().toString().trim().length() == 10) {
//                                resendVerificationCode(phoneed.getText().toString().trim(), mResendToken);
//                                mVerified = false;
//                                starttimer();
//                                codeed.setVisibility(View.VISIBLE);
//
//                                usersign.setTag(getResources().getString(R.string.tag_verify));
//                                Snackbar snackbar = Snackbar
//                                        .make((CoordinatorLayout) findViewById(R.id.parentlayout), "Resending verification code...", Snackbar.LENGTH_LONG);
//
//                                snackbar.show();
//                            }
//                        }
//                    });
                    break;
                }
                case 2: {

                    phoneed.setVisibility(View.INVISIBLE);
                    codeed.setVisibility(View.INVISIBLE);
                    usersign.setVisibility(View.INVISIBLE);
                    username.setVisibility(View.VISIBLE);
                    passwords.setVisibility(View.VISIBLE);
                    login.setVisibility(View.VISIBLE);
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String email = username.getText().toString();
                            final String password = passwords.getText().toString();

                            if (TextUtils.isEmpty(email)) {
                                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (TextUtils.isEmpty(password)) {
                                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            progressBar.setVisibility(View.VISIBLE);

                            //authenticate user
                            auth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(LoginAll.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            // If sign in fails, display a message to the user. If sign in succeeds
                                            // the auth state listener will be notified and logic to handle the
                                            // signed in user can be handled in the listener.
                                            progressBar.setVisibility(View.GONE);
                                            if (!task.isSuccessful()) {
                                                // there was an error
                                                if (password.length() < 6) {
                                                    passwords.setError(getString(R.string.minimum));
                                                } else {

                                                }
                                            } else {
                                                Intent driver=new Intent(LoginAll.this,MainActivity.class);
                                                startActivity(driver);
                                                finish();
                                            }
                                        }
                                    });
                        }
                    });

                    break;
                }
                case 3: {

                    phoneed.setVisibility(View.INVISIBLE);
                    codeed.setVisibility(View.INVISIBLE);
                    usersign.setVisibility(View.INVISIBLE);
                    username.setVisibility(View.VISIBLE);
                    passwords.setVisibility(View.VISIBLE);
                    login.setVisibility(View.VISIBLE);
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String email = username.getText().toString();
                            final String password = passwords.getText().toString();

                            if (TextUtils.isEmpty(email)) {
                                Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (TextUtils.isEmpty(password)) {
                                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            progressBar.setVisibility(View.VISIBLE);

                            //authenticate user
                            auth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(LoginAll.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            // If sign in fails, display a message to the user. If sign in succeeds
                                            // the auth state listener will be notified and logic to handle the
                                            // signed in user can be handled in the listener.
                                            progressBar.setVisibility(View.GONE);
                                            if (!task.isSuccessful()) {
                                                // there was an error
                                                if (password.length() < 6) {
                                                    passwords.setError(getString(R.string.minimum));
                                                } else {

                                                }
                                            } else {
                                                Intent admin=new Intent(LoginAll.this,Admin.class);
                                                startActivity(admin);
                                                finish();
                                            }
                                        }
                                    });
                        }
                    });

                    break;
                }
            }
        }
    }




    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            mVerified = true;
                            timer.cancel();
                            verifiedimg.setVisibility(View.VISIBLE);
                            timertext.setVisibility(View.INVISIBLE);
                            phoneed.setEnabled(false);
                            codeed.setVisibility(View.INVISIBLE);
                            Snackbar snackbar = Snackbar
                                    .make((ConstraintLayout) findViewById(R.id.parentlayout), "Successfully Verified", Snackbar.LENGTH_LONG);

                            snackbar.show();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Snackbar snackbar = Snackbar
                                        .make((ConstraintLayout) findViewById(R.id.parentlayout), "Invalid OTP ! Please enter correct OTP", Snackbar.LENGTH_LONG);

                                snackbar.show();
                            }
                        }
                    }
                });
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

    }

    public void starttimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            int second = 60;

            @Override
            public void run() {
                if (second <= 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timertext.setText("RESEND CODE");
                            timer.cancel();
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timertext.setText("00:" + second--);
                        }
                    });
                }

            }
        }, 0, 1000);
    }
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }
}

