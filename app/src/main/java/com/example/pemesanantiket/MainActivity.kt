package com.example.pemesanantiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketBookingScreen()
        }
    }
}

@Composable
fun TicketBookingScreen() {
    val hargaTiket = 25000
    var jumlahTiket by remember { mutableStateOf(1) }
    val totalBayar = hargaTiket * jumlahTiket

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F8))
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF5B7CFA))
                .padding(vertical = 32.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pemesanan Tiket",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Pesan tiket dengan mudah!",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 13.sp
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                // ---- Harga Tiket ----
                Text("Harga Tiket", fontSize = 13.sp, color = Color.Gray)
                Text(
                    text = "Rp${formatRupiah(hargaTiket)}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5B7CFA)
                )
                Text("per tiket", fontSize = 12.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(20.dp))

                // ---- Jumlah Tiket ----
                Text("Jumlah Tiket", fontSize = 13.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleActionButton(
                        symbol = "-",
                        enabled = jumlahTiket > 1,
                        onClick = { jumlahTiket-- }
                    )

                    Text(
                        text = jumlahTiket.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    CircleActionButton(
                        symbol = "+",
                        enabled = true,
                        onClick = { jumlahTiket++ }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text("Total", fontSize = 13.sp, color = Color.Gray)
                Text(
                    text = "Rp${formatRupiah(totalBayar)}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { jumlahTiket = 1 },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE05353)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("RESET")
                }
            }
        }
    }
}

@Composable
private fun CircleActionButton(symbol: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.size(44.dp),
        contentPadding = PaddingValues(0.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B7CFA))
    ) {
        Text(symbol, fontSize = 18.sp, color = Color.White)
    }
}

private fun formatRupiah(amount: Int): String {
    return amount.toString().reversed().chunked(3).joinToString(".").reversed()
}