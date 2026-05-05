# Design Document

## 1. System Architecture

### 1.1 High-Level Architecture

CampusFind follows **Clean Architecture** principles with **MVVM** pattern, organized into three main layers:

```
┌─────────────────────────────────────────────────────────┐
│                    Presentation Layer                    │
│  (Jetpack Compose UI + ViewModels + Navigation)         │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│                     Domain Layer                         │
│     (Use Cases + Domain Models + Repository Interfaces) │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│                      Data Layer                          │
│  (Repository Implementations + Firebase + Room + API)   │
└─────────────────────────────────────────────────────────┘
```

### 1.2 Technology Stack

**Frontend:**
- **UI Framework**: Jetpack Compose with Material 3
- **Language**: Kotlin
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Navigation**: Compose Navigation
- **Async Operations**: Kotlin Coroutines + Flow
- **Image Loading**: Coil
- **Local Storage**: Room Database + DataStore (Preferences)

**Backend:**
- **Authentication**: Firebase Authentication
- **Database**: Cloud Firestore
- **File Storage**: Firebase Storage
- **Push Notifications**: Firebase Cloud Messaging (FCM)
- **Analytics**: Firebase Analytics

### 1.3 Module Structure

```
app/
├── data/
│   ├── local/          # Room database, DataStore
│   ├── remote/         # Firebase services
│   ├── repository/     # Repository implementations
│   └── model/          # Data transfer objects
├── domain/
│   ├── model/          # Domain entities
│   ├── repository/     # Repository interfaces
│   └── usecase/        # Business logic use cases
├── presentation/
│   ├── navigation/     # Navigation graph
│   ├── theme/          # Material 3 theme
│   ├── components/     # Reusable UI components
│   └── screens/        # Screen composables + ViewModels
│       ├── onboarding/
│       ├── auth/
│       ├── feed/
│       ├── search/
│       ├── detail/
│       ├── booking/
│       ├── lister/
│       ├── rentals/
│       ├── profile/
│       └── admin/
└── di/                 # Hilt modules
```

## 2. Data Models

### 2.1 Domain Models

```kotlin
// User entity
data class User(
    val id: String,
    val email: String,
    val name: String,
    val profileImageUrl: String?,
    val bio: String?,
    val rating: Double,
    val ratingCount: Int,
    val listingCount: Int,
    val rentalCount: Int,
    val isAdmin: Boolean,
    val createdAt: Long,
    val lastActive: Long
)

// Item entity
data class Item(
    val id: String,
    val ownerId: String,
    val ownerName: String,
    val ownerRating: Double,
    val title: String,
    val description: String,
    val category: ItemCategory,
    val pricePerDay: Double,
    val images: List<String>,
    val pickupLocation: String,
    val isActive: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)

enum class ItemCategory {
    ELECTRONICS, STUDY_GEAR, LIFESTYLE
}

// Booking entity
data class Booking(
    val id: String,
    val itemId: String,
    val itemTitle: String,
    val itemImage: String,
    val renterId: String,
    val renterName: String,
    val listerId: String,
    val listerName: String,
    val startDate: Long,
    val endDate: Long,
    val totalPrice: Double,
    val status: BookingStatus,
    val otp: String?,
    val pickupLocation: String,
    val createdAt: Long,
    val updatedAt: Long
)

enum class BookingStatus {
    PENDING, CONFIRMED, ACTIVE, COMPLETED, DECLINED, CANCELLED
}

// Rating entity
data class Rating(
    val id: String,
    val bookingId: String,
    val fromUserId: String,
    val toUserId: String,
    val rating: Int,
    val comment: String?,
    val createdAt: Long
)

// Notification entity
data class Notification(
    val id: String,
    val userId: String,
    val title: String,
    val message: String,
    val type: NotificationType,
    val relatedId: String?,
    val isRead: Boolean,
    val createdAt: Long
)

enum class NotificationType {
    BOOKING_REQUEST, BOOKING_APPROVED, BOOKING_DECLINED,
    BOOKING_REMINDER, RETURN_REMINDER, RATING_RECEIVED
}
```

### 2.2 Firebase Schema Design

**Firestore Collections:**

```
users/
  {userId}/
    - email: string
    - name: string
    - profileImageUrl: string
    - bio: string
    - rating: number
    - ratingCount: number
    - listingCount: number
    - rentalCount: number
    - isAdmin: boolean
    - createdAt: timestamp
    - lastActive: timestamp

items/
  {itemId}/
    - ownerId: string
    - ownerName: string
    - ownerRating: number
    - title: string
    - description: string
    - category: string
    - pricePerDay: number
    - images: array<string>
    - pickupLocation: string
    - isActive: boolean
    - createdAt: timestamp
    - updatedAt: timestamp

bookings/
  {bookingId}/
    - itemId: string
    - itemTitle: string
    - itemImage: string
    - renterId: string
    - renterName: string
    - listerId: string
    - listerName: string
    - startDate: timestamp
    - endDate: timestamp
    - totalPrice: number
    - status: string
    - otp: string
    - pickupLocation: string
    - createdAt: timestamp
    - updatedAt: timestamp

ratings/
  {ratingId}/
    - bookingId: string
    - fromUserId: string
    - toUserId: string
    - rating: number
    - comment: string
    - createdAt: timestamp

notifications/
  {notificationId}/
    - userId: string
    - title: string
    - message: string
    - type: string
    - relatedId: string
    - isRead: boolean
    - createdAt: timestamp
```

**Firebase Storage Structure:**

```
/users/{userId}/profile.jpg
/items/{itemId}/image_0.jpg
/items/{itemId}/image_1.jpg
...
```

## 3. Screen Components Design

### 3.1 Onboarding Screens

**Components:**
- `OnboardingScreen`: Main container with ViewPager
- `OnboardingPage`: Individual page with illustration, title, body
- `ProgressDots`: Page indicator
- `SkipButton`: Skip to verification

**ViewModel:**
```kotlin
class OnboardingViewModel : ViewModel() {
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()
    
    fun nextPage()
    fun skipOnboarding()
}
```

### 3.2 Authentication Screens

**Campus Verification Screen:**
- Email input field with @bml.edu.in domain
- "Get Security Code" button
- Email validation logic

**OTP Verification Screen:**
- 6-digit OTP input fields
- Countdown timer (300 seconds)
- Resend code button
- Numeric keyboard

**ViewModels:**
```kotlin
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    fun sendVerificationCode(email: String)
    fun verifyCode(code: String)
    fun resendCode()
}

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class CodeSent(val email: String) : AuthState()
    data class Success(val user: User) : AuthState()
    data class Error(val message: String) : AuthState()
}
```

### 3.3 Feed Screen

**Components:**
- `FeedScreen`: Main container with LazyColumn
- `CategoryFilterRow`: Horizontal scrollable category chips
- `ItemCard`: Individual item card with image, title, price, rating
- `SearchBar`: Top app bar with search icon

**ViewModel:**
```kotlin
class FeedViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()
    
    private val _selectedCategory = MutableStateFlow<ItemCategory?>(null)
    val selectedCategory: StateFlow<ItemCategory?> = _selectedCategory.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    fun loadItems()
    fun filterByCategory(category: ItemCategory?)
    fun refreshItems()
}
```

### 3.4 Search Screen

**Components:**
- `SearchBar`: Input field with search icon
- `RecentSearches`: List of recent search queries
- `PopularCategories`: Grid of category cards
- `SearchResults`: LazyColumn of item cards

**ViewModel:**
```kotlin
class SearchViewModel @Inject constructor(
    private val searchItemsUseCase: SearchItemsUseCase,
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    private val _searchResults = MutableStateFlow<List<Item>>(emptyList())
    val searchResults: StateFlow<List<Item>> = _searchResults.asStateFlow()
    
    private val _recentSearches = MutableStateFlow<List<String>>(emptyList())
    val recentSearches: StateFlow<List<String>> = _recentSearches.asStateFlow()
    
    fun search(query: String)
    fun clearSearchHistory()
}
```

### 3.5 Item Detail Screen

**Components:**
- `ItemImageCarousel`: Horizontal pager for images
- `ItemInfo`: Title, description, price, owner info
- `AvailabilityCalendar`: Calendar with date selection
- `BookingButton`: Bottom CTA button

**ViewModel:**
```kotlin
class ItemDetailViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
    private val getBookingsUseCase: GetBookingsUseCase
) : ViewModel() {
    private val _item = MutableStateFlow<Item?>(null)
    val item: StateFlow<Item?> = _item.asStateFlow()
    
    private val _bookedDates = MutableStateFlow<List<DateRange>>(emptyList())
    val bookedDates: StateFlow<List<DateRange>> = _bookedDates.asStateFlow()
    
    private val _selectedDateRange = MutableStateFlow<DateRange?>(null)
    val selectedDateRange: StateFlow<DateRange?> = _selectedDateRange.asStateFlow()
    
    private val _totalPrice = MutableStateFlow(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice.asStateFlow()
    
    fun loadItem(itemId: String)
    fun selectDateRange(startDate: Long, endDate: Long)
    fun proceedToBooking()
}
```

### 3.6 Booking Confirmation Screen

**Components:**
- `SuccessAnimation`: Lottie or custom animation
- `BookingSummary`: Item, dates, price, OTP
- `ActionButtons`: "Call Owner", "View Details"

**ViewModel:**
```kotlin
class BookingViewModel @Inject constructor(
    private val createBookingUseCase: CreateBookingUseCase
) : ViewModel() {
    private val _bookingState = MutableStateFlow<BookingState>(BookingState.Initial)
    val bookingState: StateFlow<BookingState> = _bookingState.asStateFlow()
    
    fun createBooking(itemId: String, startDate: Long, endDate: Long)
}

sealed class BookingState {
    object Initial : BookingState()
    object Loading : BookingState()
    data class Success(val booking: Booking) : BookingState()
    data class Error(val message: String) : BookingState()
}
```

### 3.7 Lister Hub Screen

**Components:**
- `ListerHeader`: Title and "Add New Listing" button
- `InventoryList`: List of owned items with toggle switches
- `PendingRequests`: List of booking requests

**ViewModel:**
```kotlin
class ListerHubViewModel @Inject constructor(
    private val getMyItemsUseCase: GetMyItemsUseCase,
    private val getPendingBookingsUseCase: GetPendingBookingsUseCase,
    private val updateItemStatusUseCase: UpdateItemStatusUseCase,
    private val approveBookingUseCase: ApproveBookingUseCase,
    private val declineBookingUseCase: DeclineBookingUseCase
) : ViewModel() {
    private val _myItems = MutableStateFlow<List<Item>>(emptyList())
    val myItems: StateFlow<List<Item>> = _myItems.asStateFlow()
    
    private val _pendingBookings = MutableStateFlow<List<Booking>>(emptyList())
    val pendingBookings: StateFlow<List<Booking>> = _pendingBookings.asStateFlow()
    
    fun loadMyItems()
    fun toggleItemStatus(itemId: String, isActive: Boolean)
    fun approveBooking(bookingId: String)
    fun declineBooking(bookingId: String)
}
```

### 3.8 New Listing Screen

**Components:**
- `ImageUploadSection`: Grid of image upload slots
- `ListingForm`: Title, description, category, price, location inputs
- `PublishButton`: Bottom CTA

**ViewModel:**
```kotlin
class NewListingViewModel @Inject constructor(
    private val createItemUseCase: CreateItemUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {
    private val _images = MutableStateFlow<List<Uri>>(emptyList())
    val images: StateFlow<List<Uri>> = _images.asStateFlow()
    
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()
    
    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()
    
    private val _category = MutableStateFlow<ItemCategory?>(null)
    val category: StateFlow<ItemCategory?> = _category.asStateFlow()
    
    private val _pricePerDay = MutableStateFlow("")
    val pricePerDay: StateFlow<String> = _pricePerDay.asStateFlow()
    
    private val _pickupLocation = MutableStateFlow("")
    val pickupLocation: StateFlow<String> = _pickupLocation.asStateFlow()
    
    fun addImage(uri: Uri)
    fun removeImage(uri: Uri)
    fun publishListing()
}
```

### 3.9 My Rentals Screen

**Components:**
- `TabRow`: Ongoing, Completed, Cancelled tabs
- `RentalCard`: Booking details with status badge
- `ContactButton`: Call/message owner

**ViewModel:**
```kotlin
class MyRentalsViewModel @Inject constructor(
    private val getMyBookingsUseCase: GetMyBookingsUseCase,
    private val cancelBookingUseCase: CancelBookingUseCase
) : ViewModel() {
    private val _bookings = MutableStateFlow<List<Booking>>(emptyList())
    val bookings: StateFlow<List<Booking>> = _bookings.asStateFlow()
    
    private val _selectedTab = MutableStateFlow(RentalTab.ONGOING)
    val selectedTab: StateFlow<RentalTab> = _selectedTab.asStateFlow()
    
    fun loadBookings()
    fun selectTab(tab: RentalTab)
    fun cancelBooking(bookingId: String)
}

enum class RentalTab {
    ONGOING, COMPLETED, CANCELLED
}
```

### 3.10 Admin Portal Screen

**Components:**
- `StatsCards`: Active rentals, revenue metrics
- `OrderList`: All bookings with filters
- `OrderDetail`: Detailed booking view

**ViewModel:**
```kotlin
class AdminViewModel @Inject constructor(
    private val getAllBookingsUseCase: GetAllBookingsUseCase,
    private val getStatsUseCase: GetStatsUseCase,
    private val cancelBookingUseCase: CancelBookingUseCase
) : ViewModel() {
    private val _stats = MutableStateFlow<AdminStats?>(null)
    val stats: StateFlow<AdminStats?> = _stats.asStateFlow()
    
    private val _allBookings = MutableStateFlow<List<Booking>>(emptyList())
    val allBookings: StateFlow<List<Booking>> = _allBookings.asStateFlow()
    
    fun loadStats()
    fun loadAllBookings()
    fun filterBookings(status: BookingStatus?)
}

data class AdminStats(
    val activeRentals: Int,
    val todayRevenue: Double,
    val totalOrders: Int
)
```

## 4. Navigation Design

### 4.1 Navigation Graph

```kotlin
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object CampusVerification : Screen("campus_verification")
    object OtpVerification : Screen("otp_verification/{email}")
    object Feed : Screen("feed")
    object Search : Screen("search")
    object ItemDetail : Screen("item_detail/{itemId}")
    object BookingConfirmation : Screen("booking_confirmation/{bookingId}")
    object ListerHub : Screen("lister_hub")
    object NewListing : Screen("new_listing")
    object EditListing : Screen("edit_listing/{itemId}")
    object MyRentals : Screen("my_rentals")
    object Profile : Screen("profile/{userId}")
    object AdminPortal : Screen("admin_portal")
}
```

### 4.2 Bottom Navigation

```kotlin
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Feed : BottomNavItem("feed", Icons.Default.Home, "Feed")
    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
    object ListerHub : BottomNavItem("lister_hub", Icons.Default.Add, "List")
    object MyRentals : BottomNavItem("my_rentals", Icons.Default.ShoppingBag, "Rentals")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
```

## 5. Repository Layer Design

### 5.1 Repository Interfaces (Domain Layer)

```kotlin
interface AuthRepository {
    suspend fun sendVerificationCode(email: String): Result<Unit>
    suspend fun verifyCode(email: String, code: String): Result<User>
    suspend fun getCurrentUser(): User?
    suspend fun signOut()
}

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    fun getItemsByCategory(category: ItemCategory): Flow<List<Item>>
    suspend fun getItemById(itemId: String): Result<Item>
    suspend fun createItem(item: Item): Result<String>
    suspend fun updateItem(item: Item): Result<Unit>
    suspend fun deleteItem(itemId: String): Result<Unit>
    fun searchItems(query: String): Flow<List<Item>>
}

interface BookingRepository {
    fun getMyBookings(): Flow<List<Booking>>
    fun getBookingsForItem(itemId: String): Flow<List<Booking>>
    suspend fun createBooking(booking: Booking): Result<String>
    suspend fun updateBookingStatus(bookingId: String, status: BookingStatus): Result<Unit>
    suspend fun verifyOtp(bookingId: String, otp: String): Result<Boolean>
}

interface RatingRepository {
    suspend fun createRating(rating: Rating): Result<Unit>
    fun getRatingsForUser(userId: String): Flow<List<Rating>>
}

interface NotificationRepository {
    fun getNotifications(): Flow<List<Notification>>
    suspend fun markAsRead(notificationId: String): Result<Unit>
}
```

### 5.2 Repository Implementations (Data Layer)

```kotlin
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val dataStore: DataStore<Preferences>
) : AuthRepository {
    // Implementation with Firebase Auth
}

class ItemRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val itemDao: ItemDao
) : ItemRepository {
    // Implementation with Firestore + Room cache
}

class BookingRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val bookingDao: BookingDao
) : BookingRepository {
    // Implementation with Firestore + Room cache
}
```

## 6. Use Cases (Domain Layer)

```kotlin
class GetItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    operator fun invoke(): Flow<List<Item>> = itemRepository.getItems()
}

class CreateBookingUseCase @Inject constructor(
    private val bookingRepository: BookingRepository,
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(
        itemId: String,
        startDate: Long,
        endDate: Long
    ): Result<String> {
        // Create booking and send notification
    }
}

class ApproveBookingUseCase @Inject constructor(
    private val bookingRepository: BookingRepository,
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(bookingId: String): Result<Unit> {
        // Approve booking and notify renter
    }
}
```

## 7. Dependency Injection (Hilt Modules)

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    
    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
    
    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore,
        dataStore: DataStore<Preferences>
    ): AuthRepository = AuthRepositoryImpl(firebaseAuth, firestore, dataStore)
    
    // Other repository providers
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "campusfind_db"
        ).build()
    }
}
```

## 8. State Management Strategy

### 8.1 UI State Pattern

```kotlin
data class FeedUiState(
    val items: List<Item> = emptyList(),
    val selectedCategory: ItemCategory? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class FeedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()
    
    fun loadItems() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getItemsUseCase()
                .catch { e -> _uiState.update { it.copy(error = e.message) } }
                .collect { items ->
                    _uiState.update { it.copy(items = items, isLoading = false) }
                }
        }
    }
}
```

### 8.2 Event Handling

```kotlin
sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateBack : UiEvent()
}

class MyViewModel : ViewModel() {
    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()
    
    fun onAction() {
        viewModelScope.launch {
            _events.send(UiEvent.ShowSnackbar("Action completed"))
        }
    }
}
```

## 9. Error Handling Strategy

### 9.1 Result Wrapper

```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
```

### 9.2 Exception Handling

```kotlin
suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.Success(apiCall())
    } catch (e: FirebaseException) {
        Result.Error(e)
    } catch (e: IOException) {
        Result.Error(Exception("Network error"))
    } catch (e: Exception) {
        Result.Error(Exception("Unknown error"))
    }
}
```

## 10. Testing Strategy

### 10.1 Unit Tests
- ViewModel tests with fake repositories
- Use case tests with mock repositories
- Repository tests with fake data sources

### 10.2 Integration Tests
- Repository tests with Firebase emulator
- End-to-end flow tests

### 10.3 UI Tests
- Compose UI tests for screens
- Navigation tests
- Accessibility tests

## 11. Performance Optimizations

### 11.1 Image Loading
- Use Coil with memory and disk caching
- Compress images before upload
- Load thumbnails in lists, full images in detail

### 11.2 Data Caching
- Room database for offline support
- DataStore for preferences
- Firestore offline persistence

### 11.3 Pagination
- Implement pagination for feed and search
- Load 20 items per page
- Prefetch next page when scrolling

## 12. Security Considerations

### 12.1 Authentication
- Firebase Auth with email verification
- Secure token storage in DataStore
- Auto-logout after 30 days

### 12.2 Data Validation
- Client-side validation for all inputs
- Server-side validation with Firestore rules
- Sanitize user inputs

### 12.3 Firestore Security Rules

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId} {
      allow read: if request.auth != null;
      allow write: if request.auth.uid == userId;
    }
    
    match /items/{itemId} {
      allow read: if request.auth != null;
      allow create: if request.auth != null;
      allow update, delete: if request.auth.uid == resource.data.ownerId;
    }
    
    match /bookings/{bookingId} {
      allow read: if request.auth != null && 
        (request.auth.uid == resource.data.renterId || 
         request.auth.uid == resource.data.listerId);
      allow create: if request.auth != null;
      allow update: if request.auth.uid == resource.data.listerId;
    }
  }
}
```

## 13. Implementation Phases

### Phase 1: Frontend with Mock Data (Weeks 1-3)
1. Setup project structure and dependencies
2. Implement UI theme and components
3. Build all screens with Jetpack Compose
4. Implement navigation
5. Create ViewModels with mock data
6. Test UI flows

### Phase 2: Firebase Backend Integration (Weeks 4-6)
1. Setup Firebase project
2. Implement authentication
3. Create Firestore collections
4. Implement repository layer
5. Connect ViewModels to real data
6. Setup Firebase Storage for images
7. Implement FCM for notifications
8. Add offline support with Room
9. Testing and bug fixes

## 14. Monitoring and Analytics

### 14.1 Firebase Analytics Events
- Screen views
- Button clicks
- Booking conversions
- Search queries
- Error events

### 14.2 Crashlytics
- Crash reporting
- Non-fatal error logging
- Custom keys for debugging

## 15. Accessibility

### 15.1 Compose Accessibility
- Content descriptions for all images
- Semantic properties for screen readers
- Minimum touch target size (48dp)
- Color contrast compliance

### 15.2 Localization
- String resources for all text
- Hindi language support
- RTL layout support
