package com.example.bookinglapangan

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import androidx.recyclerview.widget.RecyclerView

class LapanganAdapter(
    private val list: List<Lapangan>,
    private val onItemSelected: (Lapangan) -> Unit
) : RecyclerView.Adapter<LapanganAdapter.ViewHolder>() {

    private var selectedPosition = -1

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgLapangan: ImageView = view.findViewById(R.id.imgLapangan)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvHarga: TextView = view.findViewById(R.id.tvHarga)
        val tvJam: TextView = view.findViewById(R.id.tvJam)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val cardView: MaterialCardView = view.findViewById(R.id.cardLapangan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lapangan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.imgLapangan.setImageResource(item.gambar)
        holder.tvNama.text = item.nama
        holder.tvHarga.text = item.harga
        holder.tvJam.text = item.jam

        // Set status
        if (item.isBooked) {
            holder.tvStatus.text = "Dibooking"
            holder.tvStatus.setTextColor(Color.parseColor("#F44336"))
            holder.itemView.alpha = 0.6f
        } else {
            holder.tvStatus.text = "Tersedia"
            holder.tvStatus.setTextColor(Color.parseColor("#4CAF50"))
            holder.itemView.alpha = 1.0f
        }

        // Convert dp to pixels untuk stroke
        val context = holder.itemView.context
        val strokeWidthPx = (2 * context.resources.displayMetrics.density).toInt()

        // Highlight item yang dipilih
        if (selectedPosition == position) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#E8F5E9"))
            holder.cardView.strokeWidth = strokeWidthPx
            holder.cardView.strokeColor = Color.parseColor("#0F9D58")
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            holder.cardView.strokeWidth = 0
            holder.cardView.strokeColor = Color.TRANSPARENT
        }

        // Click listener
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            if (previousPosition != -1) {
                notifyItemChanged(previousPosition)
            }
            notifyItemChanged(selectedPosition)

            onItemSelected(item)
        }
    }

    override fun getItemCount() = list.size
}