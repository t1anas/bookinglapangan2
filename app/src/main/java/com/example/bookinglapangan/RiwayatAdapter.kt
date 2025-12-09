package com.example.bookinglapangan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RiwayatAdapter(private val data: List<RiwayatModel>) :
    RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.imgRiwayat)
        val nama = view.findViewById<TextView>(R.id.txtNamaRiwayat)
        val tanggal = view.findViewById<TextView>(R.id.txtTanggalRiwayat)
        val jam = view.findViewById<TextView>(R.id.txtJamRiwayat)
        val harga = view.findViewById<TextView>(R.id.txtHargaRiwayat)
        val status = view.findViewById<TextView>(R.id.txtStatusRiwayat)
        val btnBayar = view.findViewById<Button>(R.id.btnBayarRiwayat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_riwayat, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.img.setImageResource(item.gambar)
        holder.nama.text = item.nama
        holder.tanggal.text = item.tanggal
        holder.jam.text = item.jam
        holder.harga.text = item.harga
        holder.status.text = item.status

        //Sembunyikan tombol jika status "Selesai" atau "Berlangsung"
        if (item.status == "Akan datang" || item.status == "Berlangsung") {
            holder.btnBayar.visibility = View.GONE
        } else {
            holder.btnBayar.visibility = View.VISIBLE
        }

        // Klik tombol untuk ke BayarActivity
        holder.btnBayar.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookingActivity::class.java)

            intent.putExtra("nama", item.nama)
            intent.putExtra("tanggal", item.tanggal)
            intent.putExtra("jam", item.jam)
            intent.putExtra("harga", item.harga)

            context.startActivity(intent)
        }
    }
}