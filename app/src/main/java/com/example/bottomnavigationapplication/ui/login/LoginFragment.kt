package com.example.bottomnavigationapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigationapplication.R
import com.example.bottomnavigationapplication.databinding.FragmentLoginBinding
import com.example.bottomnavigationapplication.model.Course
import com.example.bottomnavigationapplication.ui.home.HomeViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding.apply {
            this?.studyLoginButton?.setOnClickListener {
                // 在 Fragment 或 Activity 中
                findNavController().navigate(R.id.navigation_study)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}