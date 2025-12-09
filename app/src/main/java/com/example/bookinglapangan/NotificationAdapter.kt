package com.example.bookinglapangan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(
    private val notifications: List<NotificationItem>
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconNotification: ImageView = itemView.findViewById(R.id.iconNotification)
        val txtTitle: TextView = itemView.findViewById(R.id.txtNotificationTitle)
        val txtMessage: TextView = itemView.findViewById(R.id.txtNotificationMessage)
        val txtTimestamp: TextView = itemView.findViewById(R.id.txtNotificationTimestamp)
        val unreadIndicator: View = itemView.findViewById(R.id.unreadIndicator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]

        holder.txtTitle.text = notification.title
        holder.txtMessage.text = notification.message
        holder.txtTimestamp.text = notification.timestamp

        // Set icon berdasarkan tipe
        when (notification.iconType) {
            "success" -> holder.iconNotification.setImageResource(R.drawable.ic_check_circle)
            "payment" -> holder.iconNotification.setImageResource(R.drawable.ic_payment)
            "reminder" -> holder.iconNotification.setImageResource(R.drawable.ic_reminder)
            "promo" -> holder.iconNotification.setImageResource(R.drawable.ic_promo)
            "cancel" -> holder.iconNotification.setImageResource(R.drawable.ic_cancel)
            "info" -> holder.iconNotification.setImageResource(R.drawable.ic_info)
            else -> holder.iconNotification.setImageResource(R.drawable.ic_notification)
        }

        // Show/hide unread indicator
        holder.unreadIndicator.visibility = if (notification.isRead) View.GONE else View.VISIBLE
    }

    override fun getItemCount(): Int = notifications.size
}