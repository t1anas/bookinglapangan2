package com.example.bookinglapangan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.bookinglapangan.utils.GoogleAuthManager

class homeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Ambil ID dari layout fragment
        val btnNotification = view.findViewById<ImageView>(R.id.btnNotification)
        val btnProfile = view.findViewById<ImageView>(R.id.btnProfile)

        val btnJadwal = view.findViewById<Button>(R.id.btnJadwal)

        // --- Event listener ---

        btnNotification.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            intent.putExtra("OPEN_NOTIFICATION", true)
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        btnJadwal.setOnClickListener {
            Toast.makeText(requireContext(), "Silahkan Memilih jadwal", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), JadwalActivity::class.java))
        }


    }
}
