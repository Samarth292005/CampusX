# CampusX App Flow - Visual Guide

## 📱 Complete User Journey

```
┌─────────────────────────────────────────────────────────────────┐
│                         APP LAUNCH                               │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  SPLASH SCREEN                                                   │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │                    [App Logo]                              │  │
│  │                                                            │  │
│  │                    CampusX                                 │  │
│  │              Campus Rental Marketplace                     │  │
│  │                                                            │  │
│  │                   [Loading...]                             │  │
│  │                                                            │  │
│  └───────────────────────────────────────────────────────────┘  │
│  Duration: 2 seconds → Auto-advances                            │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  ONBOARDING (Page 1/3)                                    [Skip]│
│  ┌───────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │                  [Illustration]                            │  │
│  │                                                            │  │
│  │            Discover Campus Rentals                         │  │
│  │                                                            │  │
│  │   Browse through a wide variety of items available        │  │
│  │        for rent from fellow students                       │  │
│  │                                                            │  │
│  │                    ● ○ ○                                   │  │
│  │                                                            │  │
│  │              [        Next        ]                        │  │
│  │                                                            │  │
│  └───────────────────────────────────────────────────────────┘  │
│  Swipe left or tap Next → Page 2                               │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  ONBOARDING (Page 2/3)                                    [Skip]│
│  ┌───────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │                  [Illustration]                            │  │
│  │                                                            │  │
│  │                List Your Items                             │  │
│  │                                                            │  │
│  │   Earn money by renting out items you own to other        │  │
│  │              students on campus                            │  │
│  │                                                            │  │
│  │                    ○ ● ○                                   │  │
│  │                                                            │  │
│  │              [        Next        ]                        │  │
│  │                                                            │  │
│  └───────────────────────────────────────────────────────────┘  │
│  Swipe left or tap Next → Page 3                               │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  ONBOARDING (Page 3/3)                                    [Skip]│
│  ┌───────────────────────────────────────────────────────────┐  │
│  │                                                            │  │
│  │                  [Illustration]                            │  │
│  │                                                            │  │
│  │             Hassle-Free Booking                            │  │
│  │                                                            │  │
│  │   Secure bookings with OTP verification and easy           │  │
│  │              pickup coordination                           │  │
│  │                                                            │  │
│  │                    ○ ○ ●                                   │  │
│  │                                                            │  │
│  │            [     Get Started     ]                         │  │
│  │                                                            │  │
│  └───────────────────────────────────────────────────────────┘  │
│  Tap Get Started → Campus Verification                         │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  CAMPUS VERIFICATION                                            │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │                    [Logo]                                  │  │
│  │                                                            │  │
│  │            Campus Verification                             │  │
│  │         Enter your campus email                            │  │
│  │                                                            │  │
│  │  ┌──────────────────────────────────────────────────────┐ │  │
│  │  │ your.email@bml.edu.in                                │ │  │
│  │  └──────────────────────────────────────────────────────┘ │  │
│  │                                                            │  │
│  │  [Error: Must use @bml.edu.in email]  ← If invalid        │  │
│  │                                                            │  │
│  │            [  Get Security Code  ]                         │  │
│  │                                                            │  │
│  └───────────────────────────────────────────────────────────┘  │
│  Validates email → Sends code → OTP Verification               │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  OTP VERIFICATION                                               │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │                    [Logo]                                  │  │
│  │                                                            │  │
│  │              OTP Verification                              │  │
│  │      Enter the 6-digit code sent to                        │  │
│  │           john.doe@bml.edu.in                              │  │
│  │                                                            │  │
│  │         ┌───┐ ┌───┐ ┌───┐ ┌───┐ ┌───┐ ┌───┐              │  │
│  │         │ 1 │ │ 2 │ │ 3 │ │ 4 │ │ 5 │ │ 6 │              │  │
│  │         └───┘ └───┘ └───┘ └───┘ └───┘ └───┘              │  │
│  │                                                            │  │
│  │              Resend in 04:58                               │  │
│  │                                                            │  │
│  │                 [    Verify    ]                           │  │
│  │                                                            │  │
│  └───────────────────────────────────────────────────────────┘  │
│  Verifies code → Main App                                      │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  MAIN APP - FEED SCREEN (Default)                              │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │  Feed                                          [Search]    │  │
│  ├───────────────────────────────────────────────────────────┤  │
│  │  [All Items] [Electronics] [Study Gear] [Lifestyle]       │  │
│  ├───────────────────────────────────────────────────────────┤  │
│  │                                                            │  │
│  │  ┌──────────────────┐  ┌──────────────────┐              │  │
│  │  │   [Image]        │  │   [Image]        │              │  │
│  │  │                  │  │                  │              │  │
│  │  │  MacBook Pro     │  │  Calculator      │              │  │
│  │  │  ₹500/day        │  │  ₹50/day         │              │  │
│  │  │  Jane Smith ⭐4.8│  │  Jane Smith ⭐4.8│              │  │
│  │  └──────────────────┘  └──────────────────┘              │  │
│  │                                                            │  │
│  │  ┌──────────────────┐  ┌──────────────────┐              │  │
│  │  │   [Image]        │  │   [Image]        │              │  │
│  │  │                  │  │                  │              │  │
│  │  │  Camping Tent    │  │  iPad Air        │              │  │
│  │  │  ₹200/day        │  │  ₹300/day        │              │  │
│  │  │  John Doe ⭐4.5  │  │  Jane Smith ⭐4.8│              │  │
│  │  └──────────────────┘  └──────────────────┘              │  │
│  │                                                            │  │
│  │  ┌──────────────────┐                                     │  │
│  │  │   [Image]        │                                     │  │
│  │  │                  │                                     │  │
│  │  │  Textbooks       │                                     │  │
│  │  │  ₹100/day        │                                     │  │
│  │  │  John Doe ⭐4.5  │                                     │  │
│  │  └──────────────────┘                                     │  │
│  │                                                            │  │
│  └───────────────────────────────────────────────────────────┘  │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │  [Feed]  [Search]  [List]  [Rentals]  [Profile]          │  │
│  └───────────────────────────────────────────────────────────┘  │
│  Tap category chips to filter items                            │
│  Tap item card → Item Detail (not implemented yet)             │
└─────────────────────────────────────────────────────────────────┘
                              │
                    ┌─────────┴─────────┐
                    │                   │
                    ▼                   ▼
        ┌──────────────────┐  ┌──────────────────┐
        │  SEARCH SCREEN   │  │  LISTER HUB      │
        │  (Coming Soon)   │  │  (Coming Soon)   │
        └──────────────────┘  └──────────────────┘
                    │                   │
                    ▼                   ▼
        ┌──────────────────┐  ┌──────────────────┐
        │  MY RENTALS      │  │  PROFILE         │
        │  (Coming Soon)   │  │  (Coming Soon)   │
        └──────────────────┘  └──────────────────┘
```

## 🎯 Implemented Screens (✅)

### 1. Splash Screen
- **Duration**: 2 seconds
- **Content**: App logo, name, tagline, loading indicator
- **Action**: Auto-navigates to Onboarding

### 2. Onboarding (3 Pages)
- **Page 1**: Discover Campus Rentals
- **Page 2**: List Your Items
- **Page 3**: Hassle-Free Booking
- **Features**: 
  - Swipe navigation
  - Page indicators (dots)
  - Skip button (top right)
  - Next/Get Started button

### 3. Campus Verification
- **Input**: Email address
- **Validation**: Must end with @bml.edu.in
- **Button**: Get Security Code
- **Error Handling**: Shows error if invalid email
- **Loading**: Progress indicator during code sending

### 4. OTP Verification
- **Input**: 6-digit OTP (separate boxes)
- **Timer**: 5-minute countdown (05:00 → 00:00)
- **Features**:
  - Auto-focus next digit on input
  - Backspace moves to previous digit
  - Resend Code button (appears after timer expires)
- **Verification**: Accepts any 6-digit code (mock)

### 5. Main App - Feed Screen
- **Top Bar**: "Feed" title with search icon
- **Category Filters**: Horizontal scrollable chips
  - All Items (default selected)
  - Electronics
  - Study Gear
  - Lifestyle
- **Item Grid**: 2-column grid layout
- **Item Cards**: 
  - Image (200dp height)
  - Title
  - Price per day (₹)
  - Owner name
  - Rating (stars + number)
- **Bottom Navigation**: 5 tabs (Feed, Search, List, Rentals, Profile)

## 🚧 Pending Screens

### 6. Item Detail Screen
```
┌─────────────────────────────────────────┐
│  [← Back]        Item Detail            │
├─────────────────────────────────────────┤
│  [Image Carousel with dots]             │
├─────────────────────────────────────────┤
│  MacBook Pro 2021                       │
│  ₹500/day                                │
│                                          │
│  Description:                            │
│  14-inch MacBook Pro with M1 chip...    │
│                                          │
│  Owner: Jane Smith ⭐ 4.8 (15)          │
│  Pickup: Library Building               │
│                                          │
│  [Calendar - Select Dates]              │
│                                          │
│  [     Book Now - ₹1500     ]           │
└─────────────────────────────────────────┘
```

### 7. Search Screen
```
┌─────────────────────────────────────────┐
│  [← Back]  [Search items...]            │
├─────────────────────────────────────────┤
│  Recent Searches:                        │
│  • MacBook                               │
│  • Calculator                            │
│                                          │
│  Popular Categories:                     │
│  ┌──────────┐  ┌──────────┐            │
│  │Electronics│  │Study Gear│            │
│  └──────────┘  └──────────┘            │
│  ┌──────────┐                           │
│  │Lifestyle │                           │
│  └──────────┘                           │
│                                          │
│  [Search Results appear here]           │
└─────────────────────────────────────────┘
```

### 8. Lister Hub
```
┌─────────────────────────────────────────┐
│  Lister Hub              [+ New Listing]│
├─────────────────────────────────────────┤
│  My Listings:                            │
│  ┌─────────────────────────────────────┐│
│  │ MacBook Pro        [Active ✓]       ││
│  │ ₹500/day                            ││
│  └─────────────────────────────────────┘│
│                                          │
│  Pending Requests:                       │
│  ┌─────────────────────────────────────┐│
│  │ John wants to rent MacBook          ││
│  │ May 10-15, 2026                     ││
│  │ [Approve] [Decline]                 ││
│  └─────────────────────────────────────┘│
└─────────────────────────────────────────┘
```

### 9. My Rentals
```
┌─────────────────────────────────────────┐
│  My Rentals                              │
├─────────────────────────────────────────┤
│  [Ongoing] [Completed] [Cancelled]       │
├─────────────────────────────────────────┤
│  ┌─────────────────────────────────────┐│
│  │ MacBook Pro                         ││
│  │ May 10-15, 2026                     ││
│  │ OTP: 123456                         ││
│  │ Status: Confirmed                   ││
│  │ [Call Owner] [Cancel]               ││
│  └─────────────────────────────────────┘│
└─────────────────────────────────────────┘
```

### 10. Profile
```
┌─────────────────────────────────────────┐
│  Profile                    [Edit]       │
├─────────────────────────────────────────┤
│         [Profile Picture]                │
│                                          │
│         John Doe                         │
│         john.doe@bml.edu.in              │
│         ⭐ 4.5 (10 ratings)              │
│                                          │
│  Bio: Engineering student...             │
│                                          │
│  📦 3 Listings  |  🛍️ 5 Rentals         │
│                                          │
│  [Logout]                                │
└─────────────────────────────────────────┘
```

## 🎨 Design System

### Colors
- **Primary**: #4A90E2 (Blue) - Buttons, links, active states
- **Accent**: #FF6B6B (Red) - Important actions
- **Success**: #4CAF50 (Green) - Success states
- **Warning**: #FFC107 (Amber) - Ratings, warnings
- **Error**: #F44336 (Red) - Error messages
- **Text Primary**: #212121 (Dark gray) - Main text
- **Text Secondary**: #757575 (Medium gray) - Secondary text
- **Background**: #FFFFFF (White) - Main background
- **Background Light**: #F5F5F5 (Light gray) - Feed background

### Typography
- **Large Title**: 28sp, Bold - Screen titles
- **Title**: 24sp, Bold - Section titles
- **Headline**: 18sp, Bold - Card titles
- **Body**: 16sp, Regular - Main text
- **Caption**: 14sp, Regular - Secondary text

### Spacing
- **Screen Padding**: 24dp
- **Card Margin**: 8dp
- **Element Spacing**: 16dp
- **Small Spacing**: 8dp

### Components
- **Cards**: 12dp corner radius, 2dp elevation
- **Buttons**: 8dp corner radius, 56dp height
- **Input Fields**: Material outlined style
- **Bottom Nav**: 5 items, labeled

## 📊 Data Flow

```
User Input → Activity/Fragment → MockDataRepository → Display
                                        ↓
                                  (Phase 2: Firebase)
```

### Current (Phase 1):
- All data from `MockDataRepository.java`
- No persistence (resets on app restart)
- No network calls

### Future (Phase 2):
- Firebase Authentication for users
- Cloud Firestore for data
- Firebase Storage for images
- FCM for notifications
- Room for offline caching

## 🔄 Navigation Flow

```
SplashActivity
    → OnboardingActivity
        → CampusVerificationActivity
            → OtpVerificationActivity
                → MainActivity
                    ├─ FeedFragment (default)
                    ├─ SearchFragment (pending)
                    ├─ ListerHubFragment (pending)
                    ├─ MyRentalsFragment (pending)
                    └─ ProfileFragment (pending)
```

## ✨ Key Features Implemented

1. **Smooth Onboarding**: Professional 3-page introduction
2. **Email Validation**: Enforces campus email domain
3. **OTP Timer**: Countdown with resend functionality
4. **Category Filtering**: Real-time item filtering
5. **Grid Layout**: Responsive 2-column item display
6. **Image Loading**: Glide integration with placeholders
7. **Bottom Navigation**: Easy tab switching
8. **Material Design**: Modern, polished UI

## 🎯 Next Implementation Priority

1. **Item Detail** → Users can view full item info
2. **Booking Flow** → Users can create bookings
3. **Search** → Users can find specific items
4. **Lister Hub** → Users can manage listings
5. **My Rentals** → Users can track bookings
6. **Profile** → Users can manage their account

---

**Current Status**: Foundation complete, ready for feature expansion! 🚀
