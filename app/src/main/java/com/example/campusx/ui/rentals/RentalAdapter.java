package com.example.campusx.ui.rentals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.campusx.R;
import com.example.campusx.model.Booking;
import com.example.campusx.model.BookingStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.RentalViewHolder> {
    private List<Booking> bookings = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RentalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rental_card, parent, false);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentalViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.bind(booking, dateFormat);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    static class RentalViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemTitle, bookingDates, totalPrice, statusBadge, otpText;

        public RentalViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            bookingDates = itemView.findViewById(R.id.booking_dates);
            totalPrice = itemView.findViewById(R.id.total_price);
            statusBadge = itemView.findViewById(R.id.status_badge);
            otpText = itemView.findViewById(R.id.otp_text);
        }

        public void bind(Booking booking, SimpleDateFormat dateFormat) {
            itemTitle.setText(booking.getItemTitle());
            
            String startDateStr = dateFormat.format(new Date(booking.getStartDate()));
            String endDateStr = dateFormat.format(new Date(booking.getEndDate()));
            bookingDates.setText(startDateStr + " - " + endDateStr);
            
            totalPrice.setText(String.format("₹%.0f", booking.getTotalPrice()));
            
            statusBadge.setText(booking.getStatus().getDisplayName());
            
            // Show OTP for confirmed bookings
            if (booking.getStatus() == BookingStatus.CONFIRMED || 
                booking.getStatus() == BookingStatus.ACTIVE) {
                otpText.setText("OTP: " + booking.getOtp());
                otpText.setVisibility(View.VISIBLE);
            } else {
                otpText.setVisibility(View.GONE);
            }

            // Load image
            Glide.with(itemView.getContext())
                    .load(booking.getItemImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(itemImage);
        }
    }
}
