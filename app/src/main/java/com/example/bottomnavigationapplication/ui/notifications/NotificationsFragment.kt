package com.example.bottomnavigationapplication.ui.notifications

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigationapplication.R
import com.example.bottomnavigationapplication.databinding.FragmentNotificationsBinding
import com.example.bottomnavigationapplication.datastore.UserPreferencesRepository
import com.example.bottomnavigationapplication.ui.login.LoginViewModel
import kotlinx.coroutines.launch

class NotificationsFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding.apply {
            this?.studyLogoutButton?.setOnClickListener {
                // 在 Fragment 或 Activity 中
                findNavController().navigate(R.id.study_login_button)
            }
        }

        val textView: TextView = binding.textNotifications
        val repository = UserPreferencesRepository(requireContext())
        viewModel = LoginViewModel(repository)
        lifecycleScope.launch {
            viewModel.username.collect { name ->
                println("Username: $name")
                textView.text = "用户名：$name"
            }
            viewModel.isLoggedIn.collect { loggedIn ->
                println("Is logged in: $loggedIn")
                textView.text = "${textView.text}|Is logged in: $loggedIn"
            }
        }

//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}