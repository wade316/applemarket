package com.example.applemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDeteilBinding
import com.example.applemarket.databinding.ActivityMainBinding

class detail : AppCompatActivity() {

    private lateinit var binding: ActivityDeteilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeteilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myItemimage = intent.getIntExtra("image", 0)
        val myItemadress = intent.getStringExtra("address")
        val myItemaprice = intent.getStringExtra("price")
        val myItemtitle = intent.getStringExtra("title")
        val myItemdetail = intent.getStringExtra("detail")
        val myItemseller = intent.getStringExtra("seller")

        binding.igDeteilImage.setImageResource(myItemimage)
        binding.tvDetailAddress.setText(myItemadress)
        binding.tvDetailPrince.setText(myItemaprice)
        binding.tvDetailTitle.setText(myItemtitle)
        binding.tvDetailDetail.setText(myItemdetail)
        binding.tvDetailSeller.setText(myItemseller)

        binding.igDeteilBack.setOnClickListener {
            finish()
        }

//        val price = "${myItem?.price?.decimalFormat()}원"



    }
}