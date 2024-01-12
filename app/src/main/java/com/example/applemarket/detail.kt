package com.example.applemarket

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.applemarket.databinding.ActivityDeteilBinding
import com.example.applemarket.databinding.ActivityMainBinding
import java.text.DecimalFormat

class detail : AppCompatActivity() {

    private lateinit var binding: ActivityDeteilBinding
//버전 체크하는건 거의 고정 정해진 코드는 아니지만 간결하게 표현된 코드임
    private val item: myItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("item_object", myItem::class.java)
        }else {
            intent.getParcelableExtra<myItem>("item_object")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeteilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("detail", "pricd = ${item?.price}")

        val myItemimage = intent.getIntExtra("image", 0)
        val myItemadress = intent.getStringExtra("address")
        val myItemaprice = intent.getStringExtra("price")
        val myItemtitle = intent.getStringExtra("title")
        val myItemdetail = intent.getStringExtra("detail")
        val myItemseller = intent.getStringExtra("seller")
        Log.d("detail", "$myItemaprice")

        binding.igDeteilImage.setImageResource(myItemimage)
        binding.tvDetailAddress.setText(myItemadress)
        binding.tvDetailPrice.text = DecimalFormat("#,###").format(item?.price) + "원"
        binding.tvDetailTitle.setText(myItemtitle)
        binding.tvDetailDetail.setText(myItemdetail)
        binding.tvDetailSeller.setText(myItemseller)

        binding.igDeteilBack.setOnClickListener {
            finish()
        }

//        val price = "${myItem?.price?.decimalFormat()}원"



    }
}