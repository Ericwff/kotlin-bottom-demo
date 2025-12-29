package com.example.bottomnavigationapplication.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bottomnavigationapplication.MainActivity
import com.example.bottomnavigationapplication.R
import com.example.bottomnavigationapplication.datastore.UserPreferencesRepository
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)


        val repository = UserPreferencesRepository(this)
        viewModel = LoginViewModel(repository)

        // 观察数据变化

        lifecycleScope.launch {
            viewModel.loginCount.collect { count ->
                println("Login count: $count")
            }
        }


        val etUsername = findViewById<EditText>(R.id.username)
        val etPassword = findViewById<EditText>(R.id.password)
        val btnLogin = findViewById<Button>(R.id.study_login_button)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // 简单验证（实际项目中应连接服务器验证）
            if (username == "admin" && password == "password") {
                // 登录成功，保存用户
                viewModel.saveUser(username)
                // 登录成功，跳转到主页面（Bottom Navigation）
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // 关闭登录页面，防止返回键回到此页面
            } else {
                Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }
}