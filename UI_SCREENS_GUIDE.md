# CampusX UI Screens Guide

## Screen Flow

```
Splash Screen (2s)
    ↓
Onboarding (3 pages)
    ↓
Campus Verification
    ↓
OTP Verification
    ↓
Main App (Bottom Navigation)
    ├── Feed
    ├── Search
    ├── Lister Hub
    ├── My Rentals
    └── Profile
```

## Screen Descriptions

### 1. Splash Screen
**File**: `activity_splash.xml`
**Duration**: 2 seconds
**Elements**:
- CampusX logo (centered)
- App name "CampusX" (below logo)
- Tagline "Campus Rental Marketplace"
- Loading spinner (bottom)
- Background: Primary blue color (#4A90E2)

**Purpose**: Brand introduction and app initialization

---

### 2. Onboarding Screens (3 pages)
**File**: `activity_onboarding.xml` + `item_onboarding_page.xml`

#### Page 1: Discover Campus Rentals
- **Illustration**: Browse/search icon
- **Title**: "Discover Campus Rentals"
- **Description**: "Browse through a wide variety of items available for rent from fellow students"

#### Page 2: List Your Items
- **Illustration**: Upload/list icon
- **Title**: "List Your Items"
- **Description**: "Earn money by renting out items you own to other students on campus"

#### Page 3: Hassle-Free Booking
- **Illustration**: Booking/calendar icon
- **Title**: "Hassle-Free Booking"
- **Description**: "Secure bookings with OTP verification and easy pickup coordination"

**Navigation**:
- Skip button (top right)
- Page indicators (dots)
- Next/Get Started button (bottom)

---

### 3. Campus Verification Screen
**File**: `activity_campus_verification.xml`
**Elements**:
- Logo (top center)
- Title: "Campus Verification"
- Subtitle: "Enter your campus email"
- Email input field (hint: your.email@bml.edu.in)
- Error message (hidden by default)
- "Get Security Code" button
- Loading spinner (hidden by default)

**Validation**:
- Email must end with @bml.edu.in
- Shows error if invalid

---

### 4. OTP Verification Screen
**File**: `activity_otp_verification.xml`
**Elements**:
- Logo (top center)
- Title: "OTP Verification"
- Subtitle: "Enter the 6-digit code sent to"
- User's email (bold, primary color)
- 6 OTP input boxes (side by side)
- Timer countdown (e.g., "Resend in 0:45")
- "Resend Code" button (appears after timer)
- "Verify" button
- Loading spinner (hidden by default)

**Behavior**:
- Auto-focus next box on digit entry
- Auto-submit when 6 digits entered
- 60-second countdown for resend

---

### 5. Main App - Feed Screen
**File**: `fragment_feed.xml`
**Elements**:
- Toolbar: "Feed" title
- Category chips (horizontal scroll):
  - All Items (selected)
  - Electronics
  - Study Gear
  - Lifestyle
- Grid of item cards (2 columns):
  - Item image
  - Item title
  - Price per day (₹500/day)
  - Owner name
  - Rating (4.5 ⭐)

**Sample Items**:
1. MacBook Pro 2021 - ₹500/day
2. Scientific Calculator - ₹50/day
3. Camping Tent - ₹200/day
4. iPad Air - ₹300/day
5. Engineering Textbooks - ₹100/day

---

### 6. Item Detail Screen
**File**: `activity_item_detail.xml`
**Elements**:
- Image carousel (swipeable)
- Back button (top left)
- Item title (large, bold)
- Price per day (₹500/day)
- Description section
- Owner section:
  - Profile picture
  - Name
  - Rating
- Pickup location (with map icon)
- Date selection:
  - Start date picker
  - End date picker
  - Total price calculation
- "Book Now - ₹1500" button (bottom)

---

### 7. Booking Confirmation Screen
**File**: `activity_booking_confirmation.xml`
**Elements**:
- Success icon (green checkmark)
- Title: "Booking Confirmed!"
- OTP card:
  - "Your OTP"
  - 6-digit code (large, bold)
  - "Show this code to the owner during pickup"
- Booking details card:
  - Item title
  - Booking dates
  - Total price
  - Pickup location
  - Status: "Pending Approval"
- "View Details" button
- "Done" button

---

### 8. My Rentals Screen
**File**: `fragment_my_rentals.xml`
**Elements**:
- Toolbar: "My Rentals" title
- Tabs:
  - Ongoing (selected)
  - Completed
  - Cancelled
- List of rental cards:
  - Item image (small)
  - Item title
  - Booking dates
  - Total price
  - Status badge
  - OTP (for ongoing bookings)
- Empty state: "No rentals yet"

---

### 9. Search Screen (Placeholder)
**File**: `fragment_placeholder.xml`
**Elements**:
- Text: "Search - Coming Soon"

**Future Features**:
- Search bar
- Recent searches
- Popular categories
- Search results grid

---

### 10. Lister Hub Screen (Placeholder)
**File**: `fragment_placeholder.xml`
**Elements**:
- Text: "Lister Hub - Coming Soon"

**Future Features**:
- My listings tab
- Pending requests tab
- Add new listing button
- Listing cards with edit/delete options

---

### 11. Profile Screen (Placeholder)
**File**: `fragment_placeholder.xml`
**Elements**:
- Text: "Profile - Coming Soon"

**Future Features**:
- Profile picture
- Name and email
- Bio
- Statistics (listings, rentals, rating)
- Edit profile button
- Logout button

---

## Bottom Navigation

**File**: `bottom_navigation_menu.xml`
**Items**:
1. Feed (home icon) - Default selected
2. Search (search icon)
3. List (add icon)
4. Rentals (calendar icon)
5. Profile (person icon)

---

## Color Scheme

- **Primary**: #4A90E2 (Blue)
- **Primary Dark**: #357ABD
- **Primary Light**: #7AB3F5
- **Accent**: #FF6B6B (Red)
- **Success**: #4CAF50 (Green)
- **Warning**: #FFC107 (Yellow)
- **Error**: #F44336 (Red)
- **Text Primary**: #212121 (Dark Gray)
- **Text Secondary**: #757575 (Gray)
- **Background**: #FFFFFF (White)
- **Background Light**: #F5F5F5 (Light Gray)

---

## Typography

- **Large Title**: 28sp, Bold
- **Title**: 24sp, Bold
- **Subtitle**: 20sp, Bold
- **Body**: 16sp, Regular
- **Caption**: 14sp, Regular
- **Small**: 12sp, Regular

---

## Spacing

- **Extra Small**: 4dp
- **Small**: 8dp
- **Medium**: 16dp
- **Large**: 24dp
- **Extra Large**: 32dp

---

## UI Components Used

- MaterialButton
- MaterialToolbar
- MaterialCardView
- TextInputLayout
- Chips (ChipGroup)
- BottomNavigationView
- RecyclerView
- ViewPager2
- TabLayout
- ProgressBar
- ImageView (with Glide)

---

## Screen Dimensions

- **Design Width**: 1080px (360dp)
- **Design Height**: 2400px (800dp)
- **Status Bar**: 24dp
- **Action Bar**: 56dp
- **Bottom Navigation**: 56dp

---

## Image Assets Needed

### Icons:
- ✅ App launcher icon (ic_launcher)
- ⏳ Onboarding illustrations (3 images)
- ⏳ Category icons (Electronics, Study Gear, Lifestyle)
- ⏳ Empty state illustrations

### Placeholder Images:
- Item images (using placeholder.com for now)
- Profile pictures (using default avatar)

---

## Accessibility

- All images have contentDescription
- Touch targets are at least 48dp
- Color contrast meets WCAG AA standards
- Text is scalable
- Focus indicators visible

---

## Responsive Design

- Layouts use ConstraintLayout for flexibility
- Text sizes use sp units
- Dimensions use dp units
- Images scale properly
- Works on phones and tablets

---

## Animation & Transitions

- Fragment transitions: Slide
- Button clicks: Ripple effect
- Loading states: Progress indicators
- Success states: Scale animation
- Error states: Shake animation

---

## Next Steps for UI

1. ✅ All layouts created
2. ✅ All strings defined
3. ✅ All colors defined
4. ✅ All styles defined
5. ⏳ Add custom illustrations for onboarding
6. ⏳ Add category icons
7. ⏳ Add empty state illustrations
8. ⏳ Implement remaining placeholder screens
9. ⏳ Add animations and transitions
10. ⏳ Conduct usability testing
