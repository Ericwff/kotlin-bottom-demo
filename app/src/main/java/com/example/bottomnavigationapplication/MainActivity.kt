package com.example.bottomnavigationapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.bottomnavigationapplication.databinding.ActivityMainBinding
import com.example.bottomnavigationapplication.datastore.UserPreferencesRepository
import com.example.bottomnavigationapplication.http.ApiService
import com.example.bottomnavigationapplication.http.HiOkHttp
import com.example.bottomnavigationapplication.http.HiRetrofit
import com.example.bottomnavigationapplication.http.PetResponse
import com.example.bottomnavigationapplication.ui.login.LoginViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var textview: TextView

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //加载布局


//        val repository = UserPreferencesRepository(this)
//        viewModel = LoginViewModel(repository)
//
//        // 观察数据变化
//        lifecycleScope.launch {
//            viewModel.username.collect { name ->
//                println("Username: $name")
//            }
//        }
//
//        lifecycleScope.launch {
//            viewModel.isLoggedIn.collect { loggedIn ->
//                println("Is logged in: $loggedIn")
//            }
//        }
//
//        lifecycleScope.launch {
//            viewModel.loginCount.collect { count ->
//                println("Login count: $count")
//            }
//        }
//
//        // 示例：保存用户
//        viewModel.saveUser("Alice")


        // Setup toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_study,
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)  // 绑定事件

        /*textview = TextView(this)
        textview.text = "MainActivity"
        textview.gravity = Gravity.CENTER
        setContentView(textview)
        textview.setOnClickListener {
//            显式启动
//            val intent = Intent(MainActivity@this, SecondActivity::class.java)
//            intent.putExtra("extra_data","extra_data")
//            intent.putExtra("extra_int_data",100)`

//            隐式启动
            val intent = Intent()
            intent.action = "com.example.bottomnavigationapplication.components.SECONDACTIVITY"
            intent.addCategory("com.example.bottomnavigationapplication.components.SECONDACTIVITY")
            intent.putExtra("extra_data","extra_data")
            intent.putExtra("extra_int_data",100)
//
//            startActivity(intent)
            startActivityForResult(intent,10086)

//            val uri: Uri = Uri.parse("tel:10086")
//            val intent = Intent(Intent.ACTION_DIAL,uri)
//            startActivity(intent)

//            val uri: Uri = Uri.parse("smsto:10010")
//            val intent = Intent(Intent.ACTION_SENDTO,uri)
//            intent.putExtra("sms_body","stupid")
//            startActivity(intent)
        }*/

        Log.e("MainActivity:","onCreate")

//        HiOkHttp.get()
//        HiOkHttp.getAsync()
//        HiOkHttp.post()
//        HiOkHttp.postAsync()
//        HiOkHttp.postString()

//        val apiService = HiRetrofit.create(ApiService::class.java)
//        apiService.queryPet("123").enqueue(object : Callback<PetResponse> {
//            override fun onResponse(
//                call: Call<PetResponse?>,
//                response: Response<PetResponse?>
//            ) {
//                Log.e("OKHTTP post", "post response onResponse:${response.body()?:"response is null"}")
//            }
//
//            override fun onFailure(
//                call: Call<PetResponse?>,
//                t: Throwable
//            ) {
//                Log.e("Retrofit", t.message?:"unknown reason")
//            }
//
//        })
    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity:","onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.e("MainActivity:","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity:","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity:","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity:","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity:","onRestart")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10086 && resultCode == Activity.RESULT_OK && data != null){
            val stringExtraResult = data.getStringExtra("result_extra_string")
            val intExtraResult = data.getIntExtra("result_extra_int",0)
            textview.text = "MainActivity:${stringExtraResult}--${intExtraResult}"
        }
    }
}