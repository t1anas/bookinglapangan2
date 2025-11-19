package com.example.bookinglapangan

class RiwayatAdapter(
    private val list: List<RiwayatModel>,
    private val onClick: (RiwayatModel) -> Unit
) : RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.imgLapangan)
        val tvNama = view.findViewById<TextView>(R.id.tvNamaLapangan)
        val tvTanggal = view.findViewById<TextView>(R.id.tvTanggal)
        val tvWaktu = view.findViewById<TextView>(R.id.tvWaktu)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
        val btn1 = view.findViewById<Button>(R.id.btnAksi1)
        val btn2 = view.findViewById<Button>(R.id.btnAksi2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_riwayat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.img.setImageResource(item.gambar)
        holder.tvNama.text = item.nama
        holder.tvTanggal.text = item.tanggal
        holder.tvWaktu.text = item.waktu
        holder.tvHarga.text = item.harga
        holder.tvStatus.text = item.status

        // Warna status
        holder.tvStatus.setTextColor(
            when (item.status) {
                "Selesai" -> Color.RED
                "Berlangsung" -> Color.GREEN
                "Akan Datang" -> Color.BLUE
                else -> Color.BLACK
            }
        )

        // Tombol sesuai status
        when (item.status) {
            "Selesai" -> {
                holder.btn1.text = "Pesan Lagi"
                holder.btn2.visibility = View.GONE
            }
            "Akan Datang" -> {
                holder.btn1.text = "Batalkan"
                holder.btn2.text = "Lihat Detail"
                holder.btn2.visibility = View.VISIBLE
            }
            "Berlangsung" -> {
                holder.btn1.text = "Lihat Detail"
                holder.btn2.visibility = View.GONE
            }
        }

        holder.btn1.setOnClickListener { onClick(item) }
        holder.btn2.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = list.size
}

}