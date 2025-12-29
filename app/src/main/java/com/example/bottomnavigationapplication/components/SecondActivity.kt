package com.example.bottomnavigationapplication.components

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnavigationapplication.R
import com.example.bottomnavigationapplication.components.SecondFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val toggleGroup: MaterialButtonToggleGroup =
            findViewById<MaterialButtonToggleGroup>(R.id.toggle_group)
        toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val childCount = group.childCount
            var selectId = 0
            for (index in 0 until childCount) {
                val button = group.getChildAt(index) as MaterialButton
                if (button.id == checkedId) {
                    selectId = index
//                    选中的按钮
                    button.setTextColor(Color.RED)
                    button.iconTint = ColorStateList.valueOf(Color.RED)
                    switchFragment(selectId)
                } else {
                    button.setTextColor(Color.BLACK)
                    button.iconTint = ColorStateList.valueOf(Color.BLACK)
                }
            }
        }
        toggleGroup.check(R.id.tab1)

//        动态添加fragment
//        val fragment = SecondFragment()
//        val bundle = Bundle()
//        bundle.putInt("int_extra",58)
//        bundle.putString("string_extra","paul")
//        fragment.arguments = bundle
//
//        val ft = supportFragmentManager.beginTransaction()
//        ft.add(R.id.container,fragment)
//        ft.commitAllowingStateLoss()

//        两个Activity之间的数据传递
//        val stringVal = intent.getStringExtra("extra_data")
//        val intVal = intent.getIntExtra("extra_int_data", 0)
//
//        val textview = TextView(this)
//        textview.text = "SecondActivity:${stringVal}--${intVal}"
//        textview.gravity = Gravity.CENTER
//        setContentView(textview)
//
//        textview.setOnClickListener {
//            val resultIntent = Intent()
//            resultIntent.putExtra("result_extra_string","result_extra_string")
//            resultIntent.putExtra("result_extra_int",10010)
//            setResult(RESULT_OK,resultIntent)
//            finish()
//        }
        Log.e("SecondActivity:", "onCreate")
    }

    private var tab1Fragment: SecondFragment? = null
    private var tab2Fragment: SecondFragment? = null
    private var tab3Fragment: SecondFragment? = null
    private var showFragment: SecondFragment? = null

    @SuppressLint("CommitTransaction")
    private fun switchFragment(selectIndex: Int) {
        val fragment = when (selectIndex) {
            0 -> {
                if (tab1Fragment == null) {
                    tab1Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab", "tab1")
                    tab1Fragment!!.arguments = bundle
                }
                tab1Fragment
            }

            1 -> {
                if (tab2Fragment == null) {
                    tab2Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab", "tab2")
                    tab2Fragment!!.arguments = bundle
                }
                tab2Fragment
            }

            2 -> {
                if (tab3Fragment == null) {
                    tab3Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab", "tab3")
                    tab3Fragment!!.arguments = bundle
                }
                tab3Fragment
            }

            else -> {
                throw IllegalStateException("下标不符合预期")
            }
        } ?: return
//        if (fragment == null)
//            return
        val ft = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            ft.add(R.id.container, fragment)
        }
        ft.show(fragment)
        if (showFragment != null) {
            ft.hide(showFragment!!)
        }
        showFragment = fragment
        ft.commitAllowingStateLoss()
    }

    override fun onStart() {
        super.onStart()
        Log.e("SecondActivity:", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("SecondActivity:", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("SecondActivity:", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("SecondActivity:", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("SecondActivity:", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("SecondActivity:", "onRestart")
    }
}