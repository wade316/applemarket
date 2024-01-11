package com.example.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.ProgressDialog.show
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.databinding.ActivityMainBinding
import com.google.android.engage.food.datamodel.ProductEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private val callback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//        }
//    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로가기 클릭 시 실행시킬 코드 입력
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("종료")
            builder.setIcon(R.mipmap.ic_launcher)
            builder.setMessage("정말 종료하시겠습니까?")
            // 버튼 클릭시 이벤트
//            클릭시 데이터 처리방식?
            val listener = DialogInterface.OnClickListener { _, p0 ->
                if (p0 == DialogInterface.BUTTON_POSITIVE) {
                    finish()
                }
            }
            builder.setPositiveButton("확인", listener)
            builder.setNegativeButton("취소", null)
            builder.show()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //        this대신 MainActivity를 하면 안되는이유?
        this.onBackPressedDispatcher.addCallback(this, callback)

        binding.igMainNotification.setOnClickListener {

        }

//      알림설정
        val myNotificationID = 1
        val channelID = "default"

        fun createNotificationChannel(){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channerl = NotificationChannel(channelID, "default channel", NotificationManager.IMPORTANCE_DEFAULT)
                channerl.description = "description text of this channel"
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager //as는 왜쓰이는지
                notificationManager.createNotificationChannel(channerl)
            }
        }


        fun showNotification() {
            val builder = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setContentText("notification text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            NotificationManagerCompat.from(this).notify(myNotificationID, builder.build())
        }

//        fun onBackPressed() {   최근에는 OnBackPressedCallback사용을 권장한다
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("종료")
//            builder.setIcon(R.mipmap.ic_launcher)
//            builder.setMessage("정말 종료하시겠습니까?")
//            // 버튼 클릭시 이벤트
//            val listener = DialogInterface.OnClickListener { p0: DialogInterface?, p1: Int ->
//                if (p1 == DialogInterface.BUTTON_POSITIVE) {
//                    finish()
//                }
//            }
//            builder.setPositiveButton("확인", listener)
//            builder.setNegativeButton("취소", null)
//            builder.show()
//        }

        //        뒤로가기 버튼 눌렀을때 이벤트
//        fun showdialog() {
//                var builder = AlertDialog.Builder(this@MainActivity)
//                builder.setTitle("기본 다이얼로그 타이틀")
//                builder.setMessage("기본 다이얼로그 메세지")
//                builder.setIcon(R.mipmap.ic_launcher)
//
//                // 버튼 클릭시에 무슨 작업을 할 것인가!
//
//                    val listener = object : DialogInterface.OnClickListener {
//                        override fun onClick(p0: DialogInterface?, p1: Int) {
////                        when (p1) {
////                            DialogInterface.BUTTON_POSITIVE ->
////                                binding.tvTitle.text = "BUTTON_POSITIVE"
////                            DialogInterface.BUTTON_NEUTRAL ->
////                                binding.tvTitle.text = "BUTTON_NEUTRAL"
////                            DialogInterface.BUTTON_NEGATIVE ->
////                                binding.tvTitle.text = "BUTTON_NEGATIVE"
////                        }
//                        }
//                    }
//
//
//                    builder.setPositiveButton("Positive", listener)
//                    builder.setNegativeButton("Negative", listener)
//                    builder.setNeutralButton("Neutral", listener)
//
//                    builder.show()
//                }





//        데이터 원본
        val itemlist = mutableListOf<myItem>()
        itemlist.add(myItem(R.drawable.sample1, "산진 한달된 선풍기 팝니다", "서울 서대문구 창천동", "1,000","13","25","이사가서 필요가 없어졌어요 급하게 내놓습니다", "대현동"))
        itemlist.add(myItem(R.drawable.sample2, "김치냉장고", "인천 계양구 귤현동", "20,000", "8", "28","이사로인해 내놔요", "안마담"))
        itemlist.add(myItem(R.drawable.sample3, "샤넬 카드지갑", "수성구 범어동", "10,000", "23", "5","고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다", "코코유"))
        itemlist.add(myItem(R.drawable.sample4, "금고", "해운대구 우제2동", "10,000", "14", "17","금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다", "Nicole"))
        itemlist.add(myItem(R.drawable.sample5, "갤럭시Z플립3 팝니다", "연제구 연산제8동", "150,000", "22", "9","갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!", "절명"))
        itemlist.add(myItem(R.drawable.sample6, "프라다 복조리백", "수원시 영통구 원천동", "50,000", "25", "16","까임 오염없고 상태 깨끗합니다\n정품여부모름", "미니멀하게"))
        itemlist.add(myItem(R.drawable.sample7, "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장", "남구 옥동", "150000", "142", "54","울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.", "굿리치"))
        itemlist.add(myItem(R.drawable.sample8, "샤넬 탑핸들 가방", "동래구 온천제2동", "180,000", "31", "7","샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n\n색상 : 블랙\n사이즈 : 25.5cm * 17.5cm * 8cm\n구성 : 본품더스트\n\n급하게 돈이 필요해서 팝니다 ㅠ ㅠ", "난쉽"))
        itemlist.add(myItem(R.drawable.sample9, "4행정 엔진분무기 판매합니다.", "원주시 명륜2동", "30,000", "7", "28","3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n", "알뜰한"))
        itemlist.add(myItem(R.drawable.sample10, "셀린느 버킷 가방", "중구 동화동", "190,000", "40", "6","22년 신세계 대전 구매입니당\n셀린느 버킷백\n구매해서 몇번사용했어요\n까짐 스크래치 없습니다.\n타지역에서 보내는거라 택배로 진행합니당!\n", "똑태현"))

//        어떤뷰로 띄울건지 layoutManager로 정한다
//        어댑터를 만들고 그어댑터에 넣어준다
        val adapter = myadapter(itemlist)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

//        아이템 클릭시 이벤트 처리
        adapter.itemClick = object : myadapter.ItemClick {
            override fun onClick(view: View, position: Int) {
//                val name: String = itemlist[position].detail
//                Toast.makeText(this@MainActivity," $name 선택!", Toast.LENGTH_SHORT).show()
//                val mitem = mutableListOf<myItem>()

                val intent = Intent(this@MainActivity, detail::class.java).apply {
                    putExtra("image", itemlist[position].imageicon)
                    putExtra("title", itemlist[position].title)
                    putExtra("address", itemlist[position].address)
                    putExtra("price", itemlist[position].price)
                    putExtra("detail", itemlist[position].detail)
                    putExtra("seller", itemlist[position].seller)

                }
//                val data = mutableListOf<myItem>()
//                intent.putExtra("data", data)
                startActivity(intent)

//                val intent = Intent(this@MainActivity, detail::class.java)
//                val data = (
//                        itemlist[position].imageicon
//                        )
//                intent.putExtra("data", data)
            }
        }

//        알림 채널 설정







    }
}

// 1000단위 콤마찍는거 어디에 찍어야할지 모르겠음
