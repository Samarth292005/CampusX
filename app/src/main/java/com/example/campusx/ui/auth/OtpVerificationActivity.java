package com.example.campusx.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campusx.R;
import com.example.campusx.data.FirebaseRepository;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.ActionCodeSettings;

public class OtpVerificationActivity extends AppCompatActivity {
    private static final String TAG = "OtpVerification";
    private static final long COUNTDOWN_TIME = 60000; // 60 seconds

    private TextView emailText, timerText, resendButton, instructionText;
    private MaterialButton checkEmailButton;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private String email;
    private FirebaseRepository firebaseRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        email = getIntent().getStringExtra("email");
        firebaseRepo = FirebaseRepository.getInstance();

        initViews();
        setupListeners();
        startTimer();
    }

    private void initViews() {
        emailText = findViewById(R.id.email_text);
        timerText = findViewById(R.id.timer_text);
        resendButton = findViewById(R.id.resend_button);
        checkEmailButton = findViewById(R.id.verify_button);
        progressBar = findViewById(R.id.progress_bar);

        emailText.setText(email);
        
        // Update button text
        checkEmailButton.setText("I've Verified My Email");
        
        // Hide OTP input fields (we'll use email link instead)
        hideOtpFields();
        
        // Show instructions
        showEmailInstructions();
    }

    private void hideOtpFields() {
        // Hide the OTP digit input fields
        findViewById(R.id.otp_digit_1).setVisibility(View.GONE);
        findViewById(R.id.otp_digit_2).setVisibility(View.GONE);
        findViewById(R.id.otp_digit_3).setVisibility(View.GONE);
        findViewById(R.id.otp_digit_4).setVisibility(View.GONE);
        findViewById(R.id.otp_digit_5).setVisibility(View.GONE);
        findViewById(R.id.otp_digit_6).setVisibility(View.GONE);
    }

    private void showEmailInstructions() {
        // Find or create instruction text view
        TextView instructions = new TextView(this);
        instructions.setText("📧 We've sent a verification link to your email.\n\n" +
                "Please check your inbox and click the link to verify your account.\n\n" +
                "After clicking the link, come back here and tap the button below.");
        instructions.setTextSize(16);
        instructions.setPadding(32, 32, 32, 32);
        instructions.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        
        // Add to layout (you might need to adjust this based on your layout)
        android.view.ViewGroup layout = findViewById(android.R.id.content);
        if (layout instanceof android.view.ViewGroup) {
            // Find the parent layout and add instructions
            android.view.View rootView = layout.getChildAt(0);
            if (rootView instanceof android.view.ViewGroup) {
                ((android.view.ViewGroup) rootView).addView(instructions, 2);
            }
        }
    }

    private void setupListeners() {
        checkEmailButton.setOnClickListener(v -> checkVerificationStatus());
        resendButton.setOnClickListener(v -> resendCode());
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                timerText.setText(getString(R.string.resend_in, String.format("0:%02d", seconds)));
            }

            @Override
            public void onFinish() {
                timerText.setVisibility(View.GONE);
                resendButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void checkVerificationStatus() {
        showLoading(true);
        
        // Reload user to check if email is verified
        if (firebaseRepo.getCurrentFirebaseUser() != null) {
            firebaseRepo.getCurrentFirebaseUser().reload()
                    .addOnSuccessListener(aVoid -> {
                        if (firebaseRepo.getCurrentFirebaseUser() != null && 
                            firebaseRepo.getCurrentFirebaseUser().isEmailVerified()) {
                            // Email verified, proceed to main
                            showLoading(false);
                            Toast.makeText(this, "Email verified successfully!", Toast.LENGTH_SHORT).show();
                            navigateToMain();
                        } else {
                            // Not verified yet
                            showLoading(false);
                            Toast.makeText(this, "Please click the verification link in your email first", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        showLoading(false);
                        Log.e(TAG, "Error checking verification status", e);
                        Toast.makeText(this, "Please click the verification link in your email", Toast.LENGTH_LONG).show();
                    });
        } else {
            showLoading(false);
            Toast.makeText(this, "Please click the verification link in your email first", Toast.LENGTH_LONG).show();
        }
    }

    private void resendCode() {
        resendButton.setVisibility(View.GONE);
        timerText.setVisibility(View.VISIBLE);
        startTimer();
        
        showLoading(true);
        
        // Resend verification email
        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://campusx.page.link/verify")
                .setHandleCodeInApp(true)
                .setAndroidPackageName(
                        getPackageName(),
                        true,
                        null
                )
                .build();

        firebaseRepo.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnSuccessListener(aVoid -> {
                    showLoading(false);
                    Toast.makeText(this, "Verification email resent! Check your inbox.", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    showLoading(false);
                    Log.e(TAG, "Error resending email", e);
                    Toast.makeText(this, "Failed to resend email. Please try again.", Toast.LENGTH_SHORT).show();
                });
    }

    private void showLoading(boolean show) {
        if (show) {
            checkEmailButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            checkEmailButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, com.example.campusx.ui.main.MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}