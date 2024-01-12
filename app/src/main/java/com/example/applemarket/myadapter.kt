package com.example.applemarket

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding
import com.example.applemarket.databinding.ItemLayoutBinding
import java.text.DecimalFormat

// 데이터 클래스(myItem)의 데이터를 받아온다
class myadapter (val mitem: MutableList<myItem>) : RecyclerView.Adapter<myadapter.Holder>(){

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }
    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null

//  화면에 띄울 viewholder개수(뷰껍데기) 만듬
//  스크롤 할때 위아래로 잘려있지만 존재하는 viewholder가 있기때문에 여유로 추가생성하고 더이상 생성하지않음
//  초기에만 실행
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myadapter.Holder {
        Log.d("myadapter", "view group = $viewType")
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
//  스크롤 했을때 새로생겨서 재사용하는 viewhloder에 데이터를 넣어준다
//  초기 실행떄도 실행
    override fun onBindViewHolder(holder: myadapter.Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnClickListener {
            itemLongClick?.onLongClick(it, position)
        }
//        int로 값을 전달받았을때 오류나는 이유
//        DecimalFormat했을때 오류가 안나는 이유
        Log.d("myadapter", "holder = $position")
        holder.itemimage.setImageResource(mitem[position].imageicon)
        holder.title.text = mitem[position].title
        holder.address.text = mitem[position].address
        val price = mitem[position].price
        holder.price.text = DecimalFormat("#,###").format(price)+"원"
        holder.like.text = mitem[position].like
        holder.comment.text = mitem[position].commet
    //"${dec.format(it.itemprice)}원"
    //dec.format(mitem[position].price
    }
//
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
//  데이터 개수, 띄워야하는 viewholder개수
    override fun getItemCount(): Int {
        return mitem.size
    }
//  inner class 클래스 안에 속하는 클래스
//  상위 클래스를 할당받을수 있다
    inner class Holder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val itemimage = binding.igItemimage
        val title = binding.tvDetail
        val address = binding.tvAddress
        val price = binding.tvPrince
        val like = binding.tvLikeCont
        val comment = binding.tvCommentCont
    }
}