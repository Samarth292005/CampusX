# CampusX - Campus Rental Marketplace

A peer-to-peer rental marketplace Android application for BMU campus students, built with **Java and XML**.

## 🎯 Project Overview

CampusX enables students to rent and list items within their campus community. The app supports dual roles (renter and lister), includes campus email verification, and provides administrative oversight capabilities.

## 🛠️ Technology Stack

- **Language**: Java
- **UI**: XML Layouts with Material Design 3
- **Architecture**: Traditional Android (Activities + Fragments)
- **Image Loading**: Glide
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36

## 📱 Features

### Implemented ✅
- Splash screen with app branding
- Onboarding flow (3 screens with skip/next navigation)
- Campus email verification (@bml.edu.in validation)
- OTP verification with countdown timer
- Main app with bottom navigation
- Feed screen with item grid (2 columns)
- Category filtering (All, Electronics, Study Gear, Lifestyle)
- **Item detail screen with image carousel**
- **Date selection with date pickers**
- **Automatic price calculation**
- **Complete booking flow**
- **Booking confirmation with OTP display**
- **My Rentals screen with tab filtering (Ongoing, Completed, Cancelled)**
- **Status badges and rental tracking**
- Mock data repository with sample items

### Coming Soon 🚧
- Search functionality
- Lister hub for managing listings
- New listing creation
- User profile management
- Admin portal
- Firebase backend integration

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version recommended)
- JDK 11 or higher
- Android SDK with API level 36

### Build & Run
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

### Test Credentials
- **Email**: Any email ending with `@bml.edu.in`
- **OTP**: Any 6-digit code (e.g., `123456`)

## 📂 Project Structure

```
app/src/main/java/com/example/campusx/
├── data/
│   └── MockDataRepository.java      # Mock data source
├── model/
│   ├── User.java                    # User entity
│   ├── Item.java                    # Item entity
│   ├── Booking.java                 # Booking entity
│   ├── ItemCategory.java            # Category enum
│   └── BookingStatus.java           # Status enum
├── ui/
│   ├── SplashActivity.java          # Splash screen
│   ├── onboarding/
│   │   ├── OnboardingActivity.java  # Onboarding flow
│   │   ├── OnboardingAdapter.java   # ViewPager adapter
│   │   └── OnboardingPage.java      # Page model
│   ├── auth/
│   │   ├── CampusVerificationActivity.java
│   │   └── OtpVerificationActivity.java
│   ├── main/
│   │   ├── MainActivity.java        # Main container
│   │   └── PlaceholderFragment.java # Temp placeholder
│   └── feed/
│       ├── FeedFragment.java        # Feed screen
│       └── ItemAdapter.java         # RecyclerView adapter
```

## 🎨 Design System

### Colors
- **Primary**: #4A90E2 (Blue)
- **Accent**: #FF6B6B (Red)
- **Success**: #4CAF50 (Green)
- **Warning**: #FFC107 (Amber)
- **Error**: #F44336 (Red)

### Typography
- **Titles**: 24-28sp, Bold
- **Body**: 16sp, Regular
- **Captions**: 14sp, Regular

## 📋 Implementation Roadmap

### Phase 1: Frontend with Mock Data ✅ COMPLETE
- [x] Project setup and dependencies
- [x] Data models and mock repository
- [x] Splash and onboarding screens
- [x] Authentication flow (email + OTP)
- [x] Main app structure with bottom navigation
- [x] Feed screen with category filtering
- [x] Item detail screen with date selection
- [x] Booking flow with OTP generation
- [x] Booking confirmation screen
- [x] My rentals screen with tab filtering
- [ ] Search screen (placeholder)
- [ ] Lister hub screen (placeholder)
- [ ] Profile screen (placeholder)

**Status: 75% Complete - All Core Features Working!**

### Phase 2: Firebase Backend Integration (Future)
- [ ] Firebase Authentication
- [ ] Cloud Firestore for data
- [ ] Firebase Storage for images
- [ ] Firebase Cloud Messaging (FCM)
- [ ] Offline support with Room
- [ ] Real-time updates

## 🔑 Key Components

### MockDataRepository
Provides sample data for development:
- 5 sample items across different categories
- 2 sample users
- Methods for filtering, searching, and CRUD operations

### Bottom Navigation
- **Feed**: Browse available items
- **Search**: Search for specific items
- **List**: Manage your listings (Lister Hub)
- **Rentals**: Track your bookings
- **Profile**: View/edit your profile

### Category Filtering
- All Items
- Electronics (MacBook, iPad, etc.)
- Study Gear (Calculator, Textbooks, etc.)
- Lifestyle (Camping gear, etc.)

## 🧪 Testing

### Manual Testing Checklist
- [ ] Splash screen displays for 2 seconds
- [ ] Onboarding pages swipe correctly
- [ ] Skip button navigates to verification
- [ ] Email validation works (@bml.edu.in required)
- [ ] OTP timer counts down from 5:00
- [ ] OTP accepts any 6-digit code
- [ ] Bottom navigation switches fragments
- [ ] Feed displays items in grid
- [ ] Category filters work correctly
- [ ] Item cards display image, title, price, owner, rating

## 📖 Documentation

- **IMPLEMENTATION_STATUS.md**: Detailed progress tracking
- **app/design.md**: Original design specification (Kotlin/Compose)
- **app/require.md**: Requirements document with 20 detailed requirements
- **app/tasks.md**: Implementation task list

## 🤝 Contributing

This is a student project for BMU campus. Contributions should follow:
1. Java coding standards
2. Material Design guidelines
3. Existing project structure
4. Requirements in require.md

## 📄 License

This project is for educational purposes.

## 📞 Support

For issues or questions, refer to the requirements and design documents in the `app/` directory.

---

**Note**: This implementation uses Java and XML as requested, deviating from the original Kotlin/Jetpack Compose design specification. The app is currently in Phase 1 (mock data) and requires Phase 2 (Firebase integration) for production use.
