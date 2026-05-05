package com.example.campusx.ui.rentals;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusx.R;
import com.example.campusx.data.FirebaseRepository;
import com.example.campusx.data.MockDataRepository;
import com.example.campusx.model.Booking;
import com.example.campusx.model.BookingStatus;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyRentalsFragment extends Fragment {
    private static final String TAG = "MyRentalsFragment";
    
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private TextView emptyText;
    private ProgressBar progressBar;
    private RentalAdapter adapter;
    private FirebaseRepository firebaseRepo;
    private MockDataRepository mockRepo;
    private int currentTab = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rentals, container, false);
        
        firebaseRepo = FirebaseRepository.getInstance();
        mockRepo = MockDataRepository.getInstance();
        
        initViews(view);
        setupRecyclerView();
        setupTabs();
        loadBookings();
        
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rentals_recycler_view);
        tabLayout = view.findViewById(R.id.tab_layout);
        emptyText = view.findViewById(R.id.empty_text);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    private void setupRecyclerView() {
        adapter = new RentalAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void setupTabs() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = tab.getPosition();
                loadBookings();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadBookings() {
        showLoading(true);
        
        // Try Firebase first
        if (firebaseRepo.getCurrentFirebaseUser() != null) {
            String currentUserId = firebaseRepo.getCurrentUserId();
            firebaseRepo.getBookingsByRenter(currentUserId)
                    .addOnSuccessListener(querySnapshot -> {
                        List<Booking> allBookings = new ArrayList<>();
                        for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                            Booking booking = documentToBooking(doc);
                            if (booking != null) {
                                allBookings.add(booking);
                            }
                        }
                        
                        if (allBookings.isEmpty()) {
                            // No bookings in Firebase, use mock data
                            Log.d(TAG, "No bookings in Firebase, using mock data");
                            loadBookingsFromMock();
                        } else {
                            showLoading(false);
                            filterAndDisplayBookings(allBookings);
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e(TAG, "Error loading bookings from Firebase", e);
                        Toast.makeText(getContext(), "Using offline data", Toast.LENGTH_SHORT).show();
                        loadBookingsFromMock();
                    });
        } else {
            loadBookingsFromMock();
        }
    }
    
    private void loadBookingsFromMock() {
        List<Booking> allBookings = mockRepo.getBookings();
        showLoading(false);
        filterAndDisplayBookings(allBookings);
    }
    
    private void filterAndDisplayBookings(List<Booking> allBookings) {
        List<Booking> filteredBookings = new ArrayList<>();
        
        String currentUserId = firebaseRepo.getCurrentUserId();
        if (currentUserId == null) {
            currentUserId = mockRepo.getCurrentUser().getId();
        }

        for (Booking booking : allBookings) {
            if (!booking.getRenterId().equals(currentUserId)) {
                continue;
            }

            switch (currentTab) {
                case 0: // Ongoing
                    if (booking.getStatus() == BookingStatus.PENDING ||
                        booking.getStatus() == BookingStatus.CONFIRMED ||
                        booking.getStatus() == BookingStatus.ACTIVE) {
                        filteredBookings.add(booking);
                    }
                    break;
                case 1: // Completed
                    if (booking.getStatus() == BookingStatus.COMPLETED) {
                        filteredBookings.add(booking);
                    }
                    break;
                case 2: // Cancelled
                    if (booking.getStatus() == BookingStatus.CANCELLED ||
                        booking.getStatus() == BookingStatus.DECLINED) {
                        filteredBookings.add(booking);
                    }
                    break;
            }
        }

        adapter.setBookings(filteredBookings);
        
        if (filteredBookings.isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
    
    private Booking documentToBooking(DocumentSnapshot doc) {
        try {
            String id = doc.getString("id");
            String itemId = doc.getString("itemId");
            String itemTitle = doc.getString("itemTitle");
            String itemImage = doc.getString("itemImage");
            String renterId = doc.getString("renterId");
            String renterName = doc.getString("renterName");
            String ownerId = doc.getString("ownerId");
            String ownerName = doc.getString("ownerName");
            Long startDate = doc.getLong("startDate");
            Long endDate = doc.getLong("endDate");
            Double totalPrice = doc.getDouble("totalPrice");
            String statusStr = doc.getString("status");
            String otp = doc.getString("otp");
            String pickupLocation = doc.getString("pickupLocation");
            Long createdAt = doc.getLong("createdAt");
            Long updatedAt = doc.getLong("updatedAt");
            
            BookingStatus status = BookingStatus.valueOf(statusStr);
            
            return new Booking(
                    id, itemId, itemTitle, itemImage,
                    renterId, renterName, ownerId, ownerName,
                    startDate != null ? startDate : System.currentTimeMillis(),
                    endDate != null ? endDate : System.currentTimeMillis(),
                    totalPrice != null ? totalPrice : 0.0,
                    status, otp, pickupLocation,
                    createdAt != null ? createdAt : System.currentTimeMillis(),
                    updatedAt != null ? updatedAt : System.currentTimeMillis()
            );
        } catch (Exception e) {
            Log.e(TAG, "Error converting document to Booking", e);
            return null;
        }
    }
    
    private void showLoading(boolean show) {
        if (progressBar != null) {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
