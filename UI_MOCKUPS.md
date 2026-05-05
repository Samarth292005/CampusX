# CampusX UI Mockups (Visual Guide)

## 1. Splash Screen
```
┌─────────────────────────┐
│                         │
│                         │
│         ┌─────┐         │
│         │     │         │
│         │ 🏢  │         │  ← Logo
│         │     │         │
│         └─────┘         │
│                         │
│       CampusX           │  ← App Name
│                         │
│  Campus Rental          │  ← Tagline
│    Marketplace          │
│                         │
│                         │
│          ⟳              │  ← Loading
│                         │
└─────────────────────────┘
```

## 2. Onboarding Page 1
```
┌─────────────────────────┐
│              Skip    →  │  ← Skip button
│                         │
│                         │
│         ┌─────┐         │
│         │     │         │
│         │ 🔍  │         │  ← Illustration
│         │     │         │
│         └─────┘         │
│                         │
│   Discover Campus       │  ← Title
│      Rentals            │
│                         │
│  Browse through a wide  │  ← Description
│  variety of items       │
│  available for rent     │
│                         │
│      ● ○ ○              │  ← Indicators
│                         │
│  ┌───────────────────┐  │
│  │      Next         │  │  ← Button
│  └───────────────────┘  │
└─────────────────────────┘
```

## 3. Campus Verification
```
┌─────────────────────────┐
│                         │
│         ┌───┐           │
│         │🏢 │           │  ← Logo
│         └───┘           │
│                         │
│   Campus Verification   │  ← Title
│                         │
│  Enter your campus      │  ← Subtitle
│       email             │
│                         │
│  ┌───────────────────┐  │
│  │ your.email@bml... │  │  ← Email Input
│  └───────────────────┘  │
│                         │
│  ⚠️ Error message       │  ← Error (hidden)
│                         │
│  ┌───────────────────┐  │
│  │ Get Security Code │  │  ← Button
│  └───────────────────┘  │
│                         │
└─────────────────────────┘
```

## 4. OTP Verification
```
┌─────────────────────────┐
│                         │
│         ┌───┐           │
│         │🏢 │           │  ← Logo
│         └───┘           │
│                         │
│   OTP Verification      │  ← Title
│                         │
│  Enter the 6-digit      │  ← Subtitle
│  code sent to           │
│  john@bml.edu.in        │  ← Email
│                         │
│  ┌─┐ ┌─┐ ┌─┐ ┌─┐ ┌─┐ ┌─┐│
│  │1│ │2│ │3│ │4│ │5│ │6││  ← OTP Boxes
│  └─┘ └─┘ └─┘ └─┘ └─┘ └─┘│
│                         │
│   Resend in 0:45        │  ← Timer
│                         │
│  ┌───────────────────┐  │
│  │     Verify        │  │  ← Button
│  └───────────────────┘  │
└─────────────────────────┘
```

## 5. Feed Screen
```
┌─────────────────────────┐
│  Feed                   │  ← Toolbar
├─────────────────────────┤
│ [All] Electronics Study │  ← Category Chips
│  Lifestyle →            │
├─────────────────────────┤
│ ┌─────────┐ ┌─────────┐ │
│ │  📷     │ │  📷     │ │  ← Item Cards
│ │MacBook  │ │Calculat │ │
│ │₹500/day │ │₹50/day  │ │
│ │Jane 4.8⭐│ │Jane 4.8⭐│ │
│ └─────────┘ └─────────┘ │
│ ┌─────────┐ ┌─────────┐ │
│ │  📷     │ │  📷     │ │
│ │Tent     │ │iPad Air │ │
│ │₹200/day │ │₹300/day │ │
│ │John 4.5⭐│ │Jane 4.8⭐│ │
│ └─────────┘ └─────────┘ │
├─────────────────────────┤
│ 🏠 🔍 ➕ 📅 👤          │  ← Bottom Nav
└─────────────────────────┘
```

## 6. Item Detail Screen
```
┌─────────────────────────┐
│ ←                       │  ← Back button
│ ┌─────────────────────┐ │
│ │                     │ │
│ │      📷 Image       │ │  ← Image Carousel
│ │                     │ │
│ └─────────────────────┘ │
├─────────────────────────┤
│ MacBook Pro 2021        │  ← Title
│ ₹500/day                │  ← Price
├─────────────────────────┤
│ Description             │
│ 14-inch MacBook Pro     │
│ with M1 chip, perfect   │
│ for coding...           │
├─────────────────────────┤
│ Owner                   │
│ 👤 Jane Smith  4.8⭐    │
├─────────────────────────┤
│ 📍 Library Building     │  ← Location
├─────────────────────────┤
│ Select Dates            │
│ ┌─────────┐ ┌─────────┐│
│ │Start    │ │End      ││  ← Date Pickers
│ │Jan 15   │ │Jan 18   ││
│ └─────────┘ └─────────┘│
│ Total: ₹1500            │
├─────────────────────────┤
│ ┌───────────────────┐   │
│ │ Book Now - ₹1500  │   │  ← Book Button
│ └───────────────────┘   │
└─────────────────────────┘
```

## 7. Booking Confirmation
```
┌─────────────────────────┐
│                         │
│         ✅              │  ← Success Icon
│                         │
│  Booking Confirmed!     │  ← Title
│                         │
│ ┌───────────────────┐   │
│ │   Your OTP        │   │
│ │                   │   │
│ │     123456        │   │  ← OTP Card
│ │                   │   │
│ │ Show this code to │   │
│ │ the owner         │   │
│ └───────────────────┘   │
│                         │
│ ┌───────────────────┐   │
│ │ Booking Details   │   │
│ │                   │   │
│ │ MacBook Pro 2021  │   │  ← Details Card
│ │ Jan 15 - Jan 18   │   │
│ │ ₹1500             │   │
│ │ 📍 Library Bldg   │   │
│ │ Status: Pending   │   │
│ └───────────────────┘   │
│                         │
│ ┌─────────┐ ┌─────────┐ │
│ │ Details │ │  Done   │ │  ← Buttons
│ └─────────┘ └─────────┘ │
└─────────────────────────┘
```

## 8. My Rentals Screen
```
┌─────────────────────────┐
│  My Rentals             │  ← Toolbar
├─────────────────────────┤
│ [Ongoing] Completed     │  ← Tabs
│  Cancelled              │
├─────────────────────────┤
│ ┌───────────────────┐   │
│ │ 📷 MacBook Pro    │   │
│ │    Jan 15-18      │   │  ← Rental Card
│ │    ₹1500          │   │
│ │    [Confirmed]    │   │
│ │    OTP: 123456    │   │
│ └───────────────────┘   │
│                         │
│ ┌───────────────────┐   │
│ │ 📷 Calculator     │   │
│ │    Jan 20-22      │   │
│ │    ₹150           │   │
│ │    [Pending]      │   │
│ └───────────────────┘   │
├─────────────────────────┤
│ 🏠 🔍 ➕ 📅 👤          │  ← Bottom Nav
└─────────────────────────┘
```

## 9. Search Screen (Placeholder)
```
┌─────────────────────────┐
│  Search                 │  ← Toolbar
├─────────────────────────┤
│                         │
│                         │
│                         │
│                         │
│    Search - Coming      │  ← Placeholder
│        Soon             │
│                         │
│                         │
│                         │
│                         │
├─────────────────────────┤
│ 🏠 🔍 ➕ 📅 👤          │  ← Bottom Nav
└─────────────────────────┘
```

## 10. Lister Hub (Placeholder)
```
┌─────────────────────────┐
│  Lister Hub             │  ← Toolbar
├─────────────────────────┤
│                         │
│                         │
│                         │
│                         │
│  Lister Hub - Coming    │  ← Placeholder
│        Soon             │
│                         │
│                         │
│                         │
│                         │
├─────────────────────────┤
│ 🏠 🔍 ➕ 📅 👤          │  ← Bottom Nav
└─────────────────────────┘
```

## 11. Profile Screen (Placeholder)
```
┌─────────────────────────┐
│  Profile                │  ← Toolbar
├─────────────────────────┤
│                         │
│                         │
│                         │
│                         │
│   Profile - Coming      │  ← Placeholder
│        Soon             │
│                         │
│                         │
│                         │
│                         │
├─────────────────────────┤
│ 🏠 🔍 ➕ 📅 👤          │  ← Bottom Nav
└─────────────────────────┘
```

## Navigation Flow Diagram
```
     Splash (2s)
         ↓
    Onboarding
    (3 pages)
         ↓
   Campus Email
   Verification
         ↓
       OTP
   Verification
         ↓
    ┌────────────┐
    │  Main App  │
    └────────────┘
         ↓
    ┌────┴────┬────┬────┬────┐
    ↓         ↓    ↓    ↓    ↓
  Feed    Search List Rent Prof
    ↓
Item Detail
    ↓
  Booking
    ↓
Confirmation
```

## Color Legend
- 🏢 = Logo/Icon
- 📷 = Image
- ⭐ = Rating
- 📍 = Location
- ✅ = Success
- ⚠️ = Warning/Error
- 👤 = Profile
- 🏠 = Home/Feed
- 🔍 = Search
- ➕ = Add/List
- 📅 = Calendar/Rentals
- ⟳ = Loading

## Screen Sizes
- Phone: 360dp × 800dp
- Tablet: 600dp × 960dp
- All screens responsive

## Interaction Patterns
- Tap: Navigate, select, submit
- Swipe: Carousel, tabs, dismiss
- Long press: Context menu
- Pull to refresh: Update data
- Scroll: Browse content
