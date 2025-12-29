package com.example.bottomnavigationapplication.components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bottomnavigationapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("SecondFragment:", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SecondFragment:", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val textview = TextView(context)
        textview.text = "SecondFragment"
        textview.gravity = Gravity.CENTER

        Log.e("SecondFragment:", "onCreateView")
        return textview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val intVal = arguments?.getInt("int_extra")
//        val stringVal = arguments?.getString("string_extra")
        val stringVal = arguments?.getString("tab")
        val textview = view as TextView
        textview.text = "${stringVal}"
        textview.textSize = 50f

        textview.setOnClickListener {
            startActivity(Intent(context, TestServiceActivity::class.java)) // 测试Service
            startActivity(Intent(context, TestBroadcastReceiverActivity::class.java)) // 测试广播 TestBroadcastReceiver
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("SecondFragment:","onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.e("SecondFragment:", "onStart")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
//        当且仅当activity 存在多个fragment，并且我们调用了show-hide
//        hidden：当前fragment可见的时候 hidden=false
//        hidden：当前fragment不可见的时候 hidden=true
        Log.e("SecondFragment:","onHiddenChanged:${arguments?.getString("tab")}--${hidden}")
    }

    override fun onResume() {
        super.onResume()
        Log.e("SecondFragment:", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("SecondFragment:", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("SecondFragment:", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView() // onCreateView返回的view对象被销毁的时候 会执行这个回调
        _binding = null
        Log.e("SecondFragment:", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy() // 是指fragment对象被销毁的时候，会回调
        Log.e("SecondFragment:", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("SecondFragment:", "onDetach")
    }
}