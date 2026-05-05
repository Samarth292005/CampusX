package com.example.campusx.ui.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.campusx.R;
import com.example.campusx.data.FirebaseRepository;
import com.example.campusx.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.ActionCodeSettings;

public class CampusVerificationActivity extends AppCompatActivity {
    private static final String TAG = "CampusVerification";
    private static final String BMU_DOMAIN = "@bmu.edu.in";
    private static final String PREFS_NAME = "CampusXAuth";
    private static final String KEY_PENDING_EMAIL = "pending_email";
    
    private TextInputEditText emailInput;
    private TextView errorText;
    private MaterialButton getCodeButton;
    private ProgressBar progressBar;
    private FirebaseRepository firebaseRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_verification);

        // Hide action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        firebaseRepo = FirebaseRepository.getInstance();
        initViews();
        setupListeners();
        
        // Check if coming back from email link
        checkEmailLink();
    }

    private void initViews() {
        emailInput = findViewById(R.id.email_input);
        errorText = findViewById(R.id.error_text);
        getCodeButton = findViewById(R.id.get_code_button);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void setupListeners() {
        getCodeButton.setOnClickListener(v -> validateAndSendCode());
    }

    private void checkEmailLink() {
        Intent intent = getIntent();
        String emailLink = intent.getData() != null ? intent.getData().toString() : null;
        
        if (emailLink != null && firebaseRepo.isSignInWithEmailLink(emailLink)) {
            Log.d(TAG, "Email link detected: " + emailLink);
            
            // Get the email from SharedPreferences
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String email = prefs.getString(KEY_PENDING_EMAIL, null);
            
            if (email != null) {
                showLoading(true);
                signInWithEmailLink(email, emailLink);
            } else {
                // Email not found, ask user to enter it
                Toast.makeText(this, "Please enter your email to complete sign in", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void validateAndSendCode() {
        String email = emailInput.getText().toString().trim();

        // Validate email
        if (TextUtils.isEmpty(email)) {
            showError("Please enter your email address");
            return;
        }

        if (!email.endsWith(BMU_DOMAIN)) {
            showError("Please use your BMU campus email (" + BMU_DOMAIN + ")");
            return;
        }

        // Hide error and show loading
        hideError();
        showLoading(true);

        // Send Firebase Email Link
        sendSignInLinkToEmail(email);
    }

    private void sendSignInLinkToEmail(String email) {
        // Configure action code settings
        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://campusx.page.link/verify") // Your dynamic link domain
                .setHandleCodeInApp(true)
                .setAndroidPackageName(
                        getPackageName(),
                        true, // Install if not available
                        null  // Minimum version
                )
                .build();

        firebaseRepo.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnSuccessListener(aVoid -> {
                    showLoading(false);
                    
                    // Save email for later use
                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    prefs.edit().putString(KEY_PENDING_EMAIL, email).apply();
                    
                    Log.d(TAG, "Verification email sent to: " + email);
                    Toast.makeText(this, "Verification email sent! Check your inbox.", Toast.LENGTH_LONG).show();
                    
                    // Navigate to OTP screen to show instructions
                    navigateToOtpVerification(email);
                })
                .addOnFailureListener(e -> {
                    showLoading(false);
                    Log.e(TAG, "Error sending verification email", e);
                    showError("Failed to send verification email. Please try again.");
                });
    }

    private void signInWithEmailLink(String email, String emailLink) {
        firebaseRepo.signInWithEmailLink(email, emailLink)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG, "Sign in successful: " + authResult.getUser().getUid());
                    
                    // Clear pending email
                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    prefs.edit().remove(KEY_PENDING_EMAIL).apply();
                    
                    // Create user profile in Firestore
                    createUserProfile(authResult.getUser().getUid(), email);
                })
                .addOnFailureListener(e -> {
                    showLoading(false);
                    Log.e(TAG, "Error signing in with email link", e);
                    showError("Failed to verify email. Please try again.");
                });
    }

    private void createUserProfile(String userId, String email) {
        // Check if user profile already exists
        firebaseRepo.getUser(userId)
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // User already exists, just navigate to main
                        Log.d(TAG, "User profile already exists");
                        showLoading(false);
                        navigateToMain();
                    } else {
                        // Create new user profile
                        User user = new User(
                                userId,
                                email,
                                email.substring(0, email.indexOf("@")), // Use email prefix as name
                                null,
                                "BMU Student",
                                0.0,
                                0,
                                0,
                                0,
                                false, // isAdmin
                                System.currentTimeMillis(),
                                System.currentTimeMillis()
                        );
                        
                        firebaseRepo.createUser(user)
                                .addOnSuccessListener(v -> {
                                    Log.d(TAG, "User profile created successfully");
                                    showLoading(false);
                                    Toast.makeText(this, "Welcome to CampusX!", Toast.LENGTH_SHORT).show();
                                    navigateToMain();
                                })
                                .addOnFailureListener(e -> {
                                    Log.e(TAG, "Error creating user profile", e);
                                    showLoading(false);
                                    // Still navigate even if profile creation fails
                                    navigateToMain();
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error checking user profile", e);
                    showLoading(false);
                    navigateToMain();
                });
    }

    private void showError(String message) {
        errorText.setText(message);
        errorText.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        errorText.setVisibility(View.GONE);
    }

    private void showLoading(boolean show) {
        if (show) {
            getCodeButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            emailInput.setEnabled(false);
        } else {
            getCodeButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            emailInput.setEnabled(true);
        }
    }

    private void navigateToOtpVerification(String email) {
        Intent intent = new Intent(this, OtpVerificationActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        // Don't finish() - allow user to come back if needed
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, com.example.campusx.ui.main.MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
