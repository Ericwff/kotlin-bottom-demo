package com.example.bottomnavigationapplication.ui.study

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.bottomnavigationapplication.R
import com.example.bottomnavigationapplication.database.AppDatabase
import com.example.bottomnavigationapplication.databinding.FragmentStudyBinding
import com.example.bottomnavigationapplication.http.ApiService
import com.example.bottomnavigationapplication.http.HiRetrofit
import com.example.bottomnavigationapplication.model.Course
import dev.marcosfarias.pokedex.di.courseDao
import dev.marcosfarias.pokedex.di.db
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudyFragment : Fragment() {

    private var _binding: FragmentStudyBinding? = null
    private val scope = MainScope()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(StudyViewModel::class.java)

        _binding = FragmentStudyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = StudyAdapter(context)
        val studyRecyclerView: RecyclerView = binding.recyclerView
        studyRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        studyRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        studyRecyclerView.adapter = adapter
//        homeViewModel.text.observe(viewLifecycleOwner) {
////            textView.text = it
//        }

        var index = 100;
        _binding.apply {
            this?.tabAddCourse?.setOnClickListener {
                val course = Course(
                    index++,
                    "Kotlin出类拔萃培训",
                    "培训课",
                    "https://www.cwl.gov.cn/images/home/kl8.png",
                    "50"
                )
                adapter.addCourse(course)
                studyRecyclerView.scrollToPosition(0)
            }
            this?.tabDeleteCourse?.setOnClickListener {
                adapter.deleteCourse()
            }
            this?.tabUpdateCourse?.setOnClickListener {
                adapter.updateCourse(0, "80")
            }
        }

        // Example with coroutines, usually inside a ViewModel or Repository
        scope.launch {

//                val newCourse = Course(
//                    id = 2,
//                    title = "Jetpack全组件实战短视频App",
//                    label = "实战课",
//                    progress = "80",
//                    poster = "https://www.cwl.gov.cn/images/home/fc3d.png"
//                )
//            courseDao.insert(newCourse)

//            val courseDao = db.courseDao()
//            val courses = courseDao.getAllCourses()
//            Log.e("courseDao", "$courses")
//            adapter.setDatas(courses)
        }

        HiRetrofit.create(ApiService::class.java)
            .getStudy().enqueue(object : Callback<List<Course>> {
                override fun onResponse(
                    call: Call<List<Course>?>,
                    response: Response<List<Course>?>
                ) {
                    Log.e(
                        "StudyFragment onResponse",
                        response.body()?.toString() ?: "unknown error"
                    )
                    response.body()?.let {
                        adapter.setDatas(it)
                    }
                }

                override fun onFailure(
                    call: Call<List<Course>?>,
                    t: Throwable
                ) {
                    Log.e("StudyFragment onFailure", t.message ?: "unknown error")
                }
            })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel() // 自动取消所有子协程
    }
}
