package com.example.bottomnavigationapplication.ui.study

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.bottomnavigationapplication.R
import com.example.bottomnavigationapplication.model.Course

class StudyAdapter(val context: Context?) : RecyclerView.Adapter<MyViewHolder>() {
    private val courses = mutableListOf<Course>()

    fun setDatas(datas: List<Course>) {
        if (datas.isNotEmpty()) {
            courses.addAll(datas)
            notifyDataSetChanged()
        }
    }

    fun addCourse(course: Course) {
        courses.add(0, course)
        notifyItemInserted(0)
    }

    fun deleteCourse() {
        courses.removeAt(0)
        notifyItemRemoved(0)
    }

    fun updateCourse(position: Int, progress: String) {
        val course = courses[position]
        course.progress = progress
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
//        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_linear_vertical,parent,false)
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_fragment_study, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
//        holder.imageView.setBackgroundResource(R.drawable.ic_launcher_background)
//        holder.titleView.text = "【${position}】移动架构师体系课"
//        holder.messageView.text = "移动开发“两级分化”，没有差不多的“中间层”唯有尽早成长为架构师，你的职业道路才能走的更远更稳"

        val course: Course = courses[position]
        val options = RequestOptions().transform(RoundedCorners(500))
        Glide.with(context!!).load(course.poster).apply(options).into(holder.imageView)

        holder.titleView.text = course.title
        holder.labelView.text = course.label
        holder.progressView.text = "已学：" + course.progress + "%"
    }

    override fun getItemCount(): Int {
        return courses.size
    }
}

// 自定义ViewHolder类，包含列表项中的视图
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.item_course_title)
    val labelView: TextView = itemView.findViewById(R.id.item_course_label)
    val progressView: TextView = itemView.findViewById(R.id.item_course_progress)
    val imageView: ImageView = itemView.findViewById(R.id.item_course_poster)
}