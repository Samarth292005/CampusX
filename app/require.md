# Requirements Document

## Introduction

CampusFind is a peer-to-peer rental marketplace Android application designed specifically for BMU (Bhartiya Maitri University) campus students. The application enables students to rent and list items within their campus community, facilitating resource sharing for electronics, study gear, and lifestyle items. The system supports dual roles (renter and lister), includes campus email verification for security, and provides administrative oversight capabilities.

## Glossary

- **App**: The CampusFind Android mobile application
- **User**: A verified BMU student who can both rent items and list items for rent
- **Renter**: A User who is browsing or booking items to rent
- **Lister**: A User who has listed one or more items for rent
- **Admin**: A privileged User with access to the Admin Portal for managing the platform
- **Item**: A physical object listed for rent (electronics, study gear, or lifestyle items)
- **Listing**: A published Item with details, pricing, and availability
- **Booking**: A rental request created by a Renter for a specific Item and date range
- **OTP**: One-Time Password used for pickup verification
- **BMU_Email**: An email address with the BMU domain used for campus verification
- **Feed**: The main browsing interface displaying available Items
- **Lister_Hub**: The interface where Listers manage their inventory
- **Admin_Portal**: The interface where Admins manage orders and view statistics
- **Pickup_Location**: The physical location where Item exchange occurs
- **Rating**: A 1-5 star evaluation given after a rental transaction
- **Category**: A classification for Items (All Items, Electronics, Study Gear, Lifestyle)

## Requirements

### Requirement 1: User Authentication and Campus Verification

**User Story:** As a BMU student, I want to verify my identity using my campus email, so that only verified students can access the marketplace.

#### Acceptance Criteria

1. WHEN a new User opens the App for the first time, THE App SHALL display the onboarding screens explaining the platform features
2. WHEN a User completes onboarding, THE App SHALL prompt for BMU_Email verification
3. WHEN a User enters an email address, THE App SHALL validate that the email domain matches the BMU domain
4. IF the email domain does not match BMU domain, THEN THE App SHALL display an error message and prevent account creation
5. WHEN a valid BMU_Email is entered, THE App SHALL send a verification code to that email address
6. WHEN a User enters the verification code, THE App SHALL validate the code within 300 seconds
7. IF the verification code is invalid or expired, THEN THE App SHALL display an error message and allow retry
8. WHEN verification succeeds, THE App SHALL create a User account and grant access to the Feed

### Requirement 2: Onboarding Experience

**User Story:** As a new user, I want to understand the platform's key features, so that I know how to use the app effectively.

#### Acceptance Criteria

1. THE App SHALL display three onboarding screens in sequence
2. THE App SHALL display the first onboarding screen explaining item discovery features
3. THE App SHALL display the second onboarding screen explaining item listing features
4. THE App SHALL display the third onboarding screen explaining hassle-free booking features
5. WHEN a User views an onboarding screen, THE App SHALL provide navigation controls to proceed to the next screen or skip onboarding
6. WHEN a User completes or skips onboarding, THE App SHALL proceed to campus verification

### Requirement 3: Item Browsing and Feed Display

**User Story:** As a renter, I want to browse available items in a feed, so that I can discover items to rent.

#### Acceptance Criteria

1. WHEN a verified User accesses the Feed, THE App SHALL display all available Listings sorted by recency
2. THE App SHALL display each Listing with item image, title, price per day, owner name, and rating
3. WHEN a User selects a Category filter, THE App SHALL display only Listings matching that Category
4. THE App SHALL provide Category filters for "All Items", "Electronics", "Study Gear", and "Lifestyle"
5. WHEN a User scrolls to the bottom of the Feed, THE App SHALL load additional Listings if available
6. WHEN a User taps on a Listing, THE App SHALL navigate to the Item Detail screen

### Requirement 4: Search Functionality

**User Story:** As a renter, I want to search for specific items, so that I can quickly find what I need.

#### Acceptance Criteria

1. WHEN a User accesses the search interface, THE App SHALL display a search input field
2. WHEN a User enters search text, THE App SHALL filter Listings by matching item title or description
3. THE App SHALL display search results in real-time as the User types
4. WHEN a User has performed previous searches, THE App SHALL display recent search history
5. THE App SHALL display popular Categories as quick search options
6. WHEN a User taps a recent search or popular category, THE App SHALL execute that search
7. WHEN a User clears search text, THE App SHALL return to the default Feed view

### Requirement 5: Item Detail and Availability

**User Story:** As a renter, I want to view detailed information about an item including availability, so that I can make an informed rental decision.

#### Acceptance Criteria

1. WHEN a User views an Item Detail screen, THE App SHALL display the item image, title, description, price per day, and owner information
2. THE App SHALL display an availability calendar showing booked and available dates
3. THE App SHALL display the Pickup_Location for the Item
4. THE App SHALL display the owner's Rating and profile information
5. WHEN a User selects a date range on the calendar, THE App SHALL calculate and display the total rental cost
6. THE App SHALL prevent selection of date ranges that conflict with existing Bookings
7. WHEN a User taps the booking button with a valid date range selected, THE App SHALL proceed to booking confirmation

### Requirement 6: Booking and Rental Request

**User Story:** As a renter, I want to request a rental booking, so that I can reserve an item for specific dates.

#### Acceptance Criteria

1. WHEN a User confirms a Booking, THE App SHALL create a Booking record with selected dates, Item, and Renter information
2. THE App SHALL generate a unique OTP for the Booking
3. THE App SHALL display a booking confirmation screen showing the OTP and booking details
4. THE App SHALL send a notification to the Lister about the new Booking request
5. THE App SHALL add the Booking to the Renter's "My Rentals" list with status "Pending"
6. WHEN the Lister approves the Booking, THE App SHALL update the Booking status to "Confirmed"
7. IF the Lister declines the Booking, THEN THE App SHALL update the Booking status to "Declined" and notify the Renter

### Requirement 7: OTP Verification for Pickup

**User Story:** As a lister, I want to verify pickup using an OTP, so that I can ensure the item goes to the correct renter.

#### Acceptance Criteria

1. WHEN a Renter arrives for pickup, THE Lister SHALL request the OTP from the Renter
2. WHEN the Lister enters an OTP in the App, THE App SHALL validate the OTP against the Booking
3. IF the OTP is valid, THEN THE App SHALL mark the Booking as "Active" and record the pickup timestamp
4. IF the OTP is invalid, THEN THE App SHALL display an error message and allow retry
5. THE App SHALL allow OTP verification only on or after the Booking start date
6. WHEN pickup is verified, THE App SHALL send a confirmation notification to both Renter and Lister

### Requirement 8: Item Listing Creation

**User Story:** As a lister, I want to create a new listing for an item I want to rent out, so that other students can discover and rent it.

#### Acceptance Criteria

1. WHEN a User accesses the Lister_Hub, THE App SHALL provide an option to create a new Listing
2. WHEN creating a Listing, THE App SHALL require item title, description, category, price per day, and at least one image
3. THE App SHALL allow the Lister to upload up to 5 images for the Item
4. THE App SHALL require the Lister to specify a Pickup_Location
5. WHEN a Lister submits a new Listing, THE App SHALL validate all required fields are completed
6. IF any required field is missing, THEN THE App SHALL display validation errors and prevent submission
7. WHEN validation passes, THE App SHALL create the Listing and make it visible in the Feed
8. THE App SHALL add the new Listing to the Lister's inventory in the Lister_Hub

### Requirement 9: Lister Hub and Inventory Management

**User Story:** As a lister, I want to manage my listed items, so that I can update availability and track bookings.

#### Acceptance Criteria

1. WHEN a Lister accesses the Lister_Hub, THE App SHALL display all Listings owned by that Lister
2. THE App SHALL display each Listing with current status (Available, Booked, Inactive)
3. WHEN a Lister selects a Listing, THE App SHALL allow editing of title, description, price, and images
4. THE App SHALL allow the Lister to mark a Listing as inactive to temporarily remove it from the Feed
5. THE App SHALL display pending Booking requests for the Lister's Items
6. WHEN a Lister views a Booking request, THE App SHALL provide options to approve or decline
7. THE App SHALL display upcoming and active Bookings for the Lister's Items

### Requirement 10: My Rentals Tracking

**User Story:** As a renter, I want to track my active and upcoming rentals, so that I can manage my bookings.

#### Acceptance Criteria

1. WHEN a Renter accesses "My Rentals", THE App SHALL display all Bookings created by that Renter
2. THE App SHALL categorize Bookings as "Pending", "Confirmed", "Active", "Completed", or "Declined"
3. THE App SHALL display each Booking with Item details, dates, total cost, and current status
4. WHEN a Booking is "Confirmed", THE App SHALL display the OTP and Pickup_Location
5. WHEN a Booking is "Active", THE App SHALL display the return date and Lister contact information
6. WHEN a Booking is "Completed", THE App SHALL prompt the Renter to provide a Rating
7. THE App SHALL allow the Renter to cancel a "Pending" or "Confirmed" Booking at least 24 hours before the start date

### Requirement 11: Rating and Review System

**User Story:** As a user, I want to rate completed transactions, so that the community can make informed decisions.

#### Acceptance Criteria

1. WHEN a Booking is marked as "Completed", THE App SHALL prompt both Renter and Lister to provide a Rating
2. THE App SHALL require a Rating value between 1 and 5 stars
3. THE App SHALL allow optional text feedback with the Rating
4. WHEN a User submits a Rating, THE App SHALL associate it with the rated User's profile
5. THE App SHALL calculate and display the average Rating for each User based on all received Ratings
6. THE App SHALL display the Rating count alongside the average Rating
7. THE App SHALL prevent Users from rating the same transaction multiple times

### Requirement 12: Admin Portal and Order Management

**User Story:** As an admin, I want to manage orders and view platform statistics, so that I can oversee platform operations.

#### Acceptance Criteria

1. WHEN an Admin accesses the Admin_Portal, THE App SHALL display platform statistics including total orders, active rentals, and total revenue
2. THE App SHALL display a list of all Bookings across the platform with filtering options
3. THE App SHALL allow the Admin to filter Bookings by status, date range, or User
4. WHEN an Admin selects a Booking, THE App SHALL display full details including Renter, Lister, Item, and transaction history
5. THE App SHALL allow the Admin to cancel or modify Bookings in case of disputes
6. THE App SHALL display flagged or reported Listings and Users
7. THE App SHALL allow the Admin to deactivate Listings or suspend User accounts

### Requirement 13: Notification System

**User Story:** As a user, I want to receive notifications about booking updates, so that I stay informed about my transactions.

#### Acceptance Criteria

1. WHEN a Lister receives a new Booking request, THE App SHALL send a push notification
2. WHEN a Booking is approved or declined, THE App SHALL send a push notification to the Renter
3. WHEN a Booking start date is within 24 hours, THE App SHALL send a reminder notification to both Renter and Lister
4. WHEN a Booking end date is within 24 hours, THE App SHALL send a return reminder notification to the Renter
5. WHEN a Rating is received, THE App SHALL send a notification to the rated User
6. THE App SHALL display a notification history accessible from the main navigation
7. THE App SHALL allow Users to configure notification preferences for each notification type

### Requirement 14: User Profile Management

**User Story:** As a user, I want to manage my profile information, so that other users can learn about me.

#### Acceptance Criteria

1. WHEN a User accesses their profile, THE App SHALL display their name, BMU_Email, profile picture, and Rating
2. THE App SHALL allow the User to update their name and profile picture
3. THE App SHALL display the User's listing count and completed rental count
4. THE App SHALL display received Ratings and reviews
5. WHEN another User views a profile, THE App SHALL display public profile information excluding BMU_Email
6. THE App SHALL allow Users to add a bio text with a maximum of 200 characters
7. THE App SHALL prevent modification of the BMU_Email after verification

### Requirement 15: Offline Support and Data Synchronization

**User Story:** As a user, I want to view my bookings and listings when offline, so that I can access information without internet connectivity.

#### Acceptance Criteria

1. WHEN the App loses network connectivity, THE App SHALL display cached data for Feed, My Rentals, and Lister_Hub
2. THE App SHALL indicate to the User when displaying cached data with a visual indicator
3. WHEN the User attempts to create or modify data while offline, THE App SHALL queue the operation
4. WHEN network connectivity is restored, THE App SHALL synchronize queued operations with the server
5. IF a synchronization conflict occurs, THEN THE App SHALL prioritize server data and notify the User
6. THE App SHALL cache Item images for offline viewing
7. THE App SHALL refresh cached data when the User manually pulls to refresh with network connectivity

### Requirement 16: Security and Data Privacy

**User Story:** As a user, I want my personal information protected, so that my privacy is maintained.

#### Acceptance Criteria

1. THE App SHALL encrypt all network communication using TLS 1.3 or higher
2. THE App SHALL store authentication tokens securely using Android Keystore
3. THE App SHALL require re-authentication after 30 days of inactivity
4. THE App SHALL not display sensitive information (OTP, full email) in screenshots or recent apps view
5. WHEN a User logs out, THE App SHALL clear all cached personal data
6. THE App SHALL comply with data minimization principles by collecting only necessary information
7. THE App SHALL provide a data deletion option that removes all User data from the system within 30 days

### Requirement 17: Performance and Responsiveness

**User Story:** As a user, I want the app to respond quickly, so that I have a smooth experience.

#### Acceptance Criteria

1. WHEN a User navigates between screens, THE App SHALL complete the transition within 300 milliseconds
2. WHEN the Feed loads, THE App SHALL display the first page of Listings within 2 seconds on a 4G connection
3. WHEN a User uploads an image, THE App SHALL compress images larger than 2MB before upload
4. THE App SHALL display loading indicators for operations taking longer than 500 milliseconds
5. WHEN the App performs background synchronization, THE App SHALL not block user interactions
6. THE App SHALL cache frequently accessed data to reduce network requests
7. WHEN image loading fails, THE App SHALL display a placeholder image and provide a retry option

### Requirement 18: Accessibility and Localization

**User Story:** As a user with accessibility needs, I want the app to support accessibility features, so that I can use the app effectively.

#### Acceptance Criteria

1. THE App SHALL provide content descriptions for all interactive elements for screen readers
2. THE App SHALL support dynamic text sizing based on system font size settings
3. THE App SHALL maintain a minimum touch target size of 48dp for all interactive elements
4. THE App SHALL provide sufficient color contrast ratios (4.5:1 for normal text, 3:1 for large text)
5. THE App SHALL support keyboard navigation for all interactive features
6. THE App SHALL provide alternative text for all images
7. WHERE the device language is set to Hindi, THE App SHALL display all interface text in Hindi

### Requirement 19: Error Handling and Recovery

**User Story:** As a user, I want clear error messages when something goes wrong, so that I understand how to resolve issues.

#### Acceptance Criteria

1. WHEN a network error occurs, THE App SHALL display a user-friendly error message with retry option
2. WHEN a server error occurs, THE App SHALL log the error details and display a generic error message to the User
3. IF the App crashes, THEN THE App SHALL restore the User's previous screen state on restart
4. WHEN form validation fails, THE App SHALL highlight invalid fields and display specific error messages
5. WHEN an image upload fails, THE App SHALL allow the User to retry without re-entering other form data
6. THE App SHALL implement exponential backoff for failed network requests with a maximum of 3 retry attempts
7. WHEN the server is unreachable, THE App SHALL display a maintenance message if available from cached configuration

### Requirement 20: Analytics and Monitoring

**User Story:** As a product owner, I want to track app usage and errors, so that I can improve the platform.

#### Acceptance Criteria

1. THE App SHALL log user navigation events including screen views and button taps
2. THE App SHALL track Booking conversion rates from Item view to Booking confirmation
3. THE App SHALL log error events including error type, screen, and user action
4. THE App SHALL track app performance metrics including screen load times and API response times
5. THE App SHALL anonymize user identifiers in analytics data
6. THE App SHALL batch analytics events and send them when network conditions are favorable
7. THE App SHALL respect user opt-out preferences for analytics tracking
