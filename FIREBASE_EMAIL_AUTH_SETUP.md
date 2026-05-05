# 🔐 Firebase Email Link Authentication Setup

## Overview
The app now uses **Firebase Email Link Authentication** (passwordless sign-in) which sends a real verification email to users.

## ⚠️ IMPORTANT: Firebase Console Setup Required

Before the email authentication will work, you MUST configure Firebase Dynamic Links in your Firebase Console.

### Step 1: Enable Email Link Sign-In

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Select your project: **campus-43f73**
3. Navigate to **Authentication** → **Sign-in method**
4. Click on **Email/Password**
5. Enable **Email link (passwordless sign-in)**
6. Click **Save**

### Step 2: Set up Firebase Dynamic Links

Firebase Email Link authentication requires Dynamic Links to work.

#### Option A: Use Firebase Hosting (Recommended)

1. In Firebase Console, go to **Hosting**
2. Click **Get Started** and follow the setup
3. Your dynamic link domain will be: `campus-43f73.web.app` or `campus-43f73.firebaseapp.com`

#### Option B: Create a Custom Dynamic Link Domain

1. In Firebase Console, go to **Dynamic Links**
2. Click **Get Started**
3. Set up a domain (e.g., `campusx.page.link`)
4. Follow the verification steps

### Step 3: Update the App Code

Once you have your dynamic link domain, update it in the code:

**File**: `app/src/main/java/com/example/campusx/ui/auth/CampusVerificationActivity.java`

Find this line (around line 95):
```java
.setUrl("https://campusx.page.link/verify")
```

Replace with your actual dynamic link domain:
```java
.setUrl("https://YOUR-PROJECT-ID.page.link/verify")
// OR
.setUrl("https://YOUR-PROJECT-ID.web.app/verify")
```

### Step 4: Configure AndroidManifest.xml

Add an intent filter to handle the email link:

**File**: `app/src/main/AndroidManifest.xml`

Add this inside the `<activity>` tag for `CampusVerificationActivity`:

```xml
<activity
    android:name=".ui.auth.CampusVerificationActivity"
    android:theme="@style/Theme.MaterialComponents.DayNight.NoActionBar"
    android:windowSoftInputMode="adjustResize">
    
    <!-- Add this intent filter -->
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data
            android:scheme="https"
            android:host="YOUR-PROJECT-ID.page.link" />
    </intent-filter>
</activity>
```

Replace `YOUR-PROJECT-ID.page.link` with your actual dynamic link domain.

## How It Works

### User Flow

1. **Email Entry**
   - User enters their BMU email (@bmu.edu.in)
   - App validates email format and domain
   - Firebase sends verification email

2. **Email Verification**
   - User receives email with verification link
   - User clicks the link in their email
   - Link opens the app automatically
   - App verifies the link and signs in the user

3. **Profile Creation**
   - App creates Firebase Auth user
   - App creates Firestore user profile
   - User is redirected to main app

### Technical Details

**Authentication Method**: Firebase Email Link (Passwordless)
- No password required
- More secure than traditional email/password
- Better user experience
- Prevents password-related issues

**Email Template**:
Firebase sends a professional email with:
- Verification link
- App name
- Security information
- Link expiration time (default: 1 hour)

## Testing

### Test Email Flow

1. **Enter Email**
   ```
   test@bmu.edu.in
   ```

2. **Check Email**
   - Open your email inbox
   - Look for email from Firebase
   - Subject: "Sign in to CampusX"

3. **Click Link**
   - Click the verification link in the email
   - App should open automatically
   - You'll be signed in

4. **Verify Sign-In**
   - Check if you're redirected to main app
   - Check Firebase Console → Authentication → Users
   - Your email should appear in the users list

### Troubleshooting

#### Email Not Received
- Check spam/junk folder
- Verify email address is correct
- Check Firebase Console → Authentication → Sign-in method
- Ensure Email Link is enabled
- Wait a few minutes (email delivery can be delayed)

#### Link Doesn't Open App
- Verify AndroidManifest.xml has correct intent filter
- Check dynamic link domain matches in code and manifest
- Ensure app is installed on the device
- Try copying link and opening manually

#### "Invalid Action Code" Error
- Link may have expired (default: 1 hour)
- Link can only be used once
- Request a new verification email

#### App Crashes on Link Click
- Check Logcat for errors
- Verify all Firebase dependencies are correct
- Ensure google-services.json is up to date
- Clean and rebuild project

## Customizing Email Template

### Firebase Console

1. Go to **Authentication** → **Templates**
2. Select **Email link sign-in**
3. Customize:
   - Sender name
   - Reply-to email
   - Email subject
   - Email body

### Recommended Template

**Subject**: Verify your BMU email for CampusX

**Body**:
```
Hello,

Click the link below to verify your BMU email and sign in to CampusX:

%LINK%

This link will expire in 1 hour and can only be used once.

If you didn't request this email, you can safely ignore it.

Happy renting!
The CampusX Team
```

## Security Features

### Built-in Security
- ✅ Link expires after 1 hour
- ✅ Link can only be used once
- ✅ Email must match the one used to request link
- ✅ Domain verification (@bmu.edu.in only)
- ✅ No password to steal or forget
- ✅ Automatic HTTPS encryption

### Additional Security (Optional)

You can add:
- Rate limiting (limit emails per hour)
- CAPTCHA verification
- IP-based restrictions
- Email domain verification on backend

## Production Checklist

Before launching to production:

- [ ] Set up custom dynamic link domain
- [ ] Configure email template
- [ ] Set up custom sender email
- [ ] Test email delivery
- [ ] Test link opening on multiple devices
- [ ] Add rate limiting
- [ ] Configure email quotas
- [ ] Set up monitoring/alerts
- [ ] Test spam folder delivery
- [ ] Verify all error messages
- [ ] Test network failure scenarios

## Current Implementation Status

✅ Firebase Email Link authentication implemented
✅ Email validation (@bmu.edu.in)
✅ Firestore profile creation
✅ Error handling
✅ Loading states
✅ Resend email functionality
⚠️ Dynamic Links need to be configured in Firebase Console
⚠️ AndroidManifest.xml needs intent filter
⚠️ Email template needs customization

## Alternative: Phone OTP (Future Enhancement)

If you prefer SMS OTP instead of email:

1. Enable Phone Authentication in Firebase Console
2. Set up phone number verification
3. Implement SMS OTP flow
4. Update UI to accept phone numbers

This would require:
- Phone number input field
- SMS OTP verification
- Phone number validation
- SMS quota management

## Cost Considerations

### Firebase Email Link
- **Free tier**: 10,000 verifications/month
- **Paid tier**: $0.01 per verification after free tier
- No SMS costs
- Unlimited email sends

### Firebase Phone Auth (Alternative)
- **Free tier**: None
- **Cost**: ~$0.01-0.05 per SMS (varies by country)
- India: ~₹0.50-2 per SMS

## Support

For issues or questions:
1. Check Firebase Console logs
2. Check Android Logcat
3. Review Firebase documentation
4. Check this guide's troubleshooting section

## Next Steps

1. **Configure Firebase Dynamic Links** (REQUIRED)
2. **Update AndroidManifest.xml** (REQUIRED)
3. **Update dynamic link URL in code** (REQUIRED)
4. **Test email flow**
5. **Customize email template**
6. **Deploy to production**
