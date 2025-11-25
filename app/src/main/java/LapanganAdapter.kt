package com.example.bookinglapangan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LapanganAdapter(private val list: List<Lapangan>)
    : RecyclerView.Adapter<LapanganAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLap: ImageView = itemView.findViewById(R.id.imgLapangan)
        val txtNama: TextView = itemView.findViewById(R.id.txtNama)
        val txtHarga: TextView = itemView.findViewById(R.id.txtHarga)
        val txtJam: TextView = itemView.findViewById(R.id.txtJam)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lapangan, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.imgLap.setImageResource(data.gambar)
        holder.txtNama.text = data.nama
        holder.txtHarga.text = data.harga
        holder.txtJam.text = data.jam

        if (data.tersedia) {
            holder.txtStatus.text = "Tersedia"
            holder.txtStatus.setTextColor(android.graphics.Color.BLUE)
        } else {
            holder.txtStatus.text = "Tidak Tersedia"
            holder.txtStatus.setTextColor(android.graphics.Color.RED)
        }
    }

    override fun getItemCount(): Int = list.size
}
