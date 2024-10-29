package com.example.bai3_20215491
import android.view.View
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: StudentAdapter
    private lateinit var searchView: EditText
    private lateinit var textViewError: TextView

    private val studentList = listOf(
        Student("Nguyễn Hoàng Đức Trung", "20215491"),
        Student("Vũ Minh Hoàng", "20215489"),
        Student("Lương Đức Trọng", "20215435"),
        Student("Nguyễn Văn An", "20210001"),
        Student("Hoàng Văn Long", "20210005"),
        Student("Bùi Văn Phúc", "20210007"),
        Student("Hồ Văn Khánh", "20215438"),
        Student("Dương Văn Toàn", "20216235"),
        Student("Phạm Thị Lan Anh", "20215436"),
        Student("Nguyễn Minh Tâm", "20215437"),
        Student("Lê Thị Thu Trang", "20215439"),
        Student("Trần Văn Bảo", "20215440"),
        Student("Đỗ Thị Hồng Nhung", "20215441"),
        Student("Nguyễn Quang Hải", "20215442"),
        Student("Phạm Văn Hùng", "20215443"),
        Student("Lý Thị Hương Giang", "20215444"),
        Student("Võ Văn Hoài", "20215445"),
        Student("Hoàng Minh Tuấn", "20215446"),
        Student("Nguyễn Thanh Tú", "20215447"),
        Student("Lê Văn Nam", "20215448"),
        Student("Bùi Thị Mai", "20215449"),
        Student("Nguyễn Hoàng Yến", "20215450"),
        Student("Trương Văn Đạt", "20215451"),
        Student("Nguyễn Thị Thảo", "20215452"),
        Student("Vũ Văn Quang", "20215453"),
        Student("Lê Thị Ngọc Diệp", "20215454"),
        Student("Nguyễn Đình Khánh", "20215455"),
        Student("Phan Văn Hậu", "20215456")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        textViewError = findViewById(R.id.textViewError)
        val listViewStudents: ListView = findViewById(R.id.listViewStudents)

        adapter = StudentAdapter(this, studentList)
        listViewStudents.adapter = adapter

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                filterList(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterList(query: String) {
        if (query.length < 3) {
            adapter.updateList(studentList)
            textViewError.visibility = View.GONE
            return
        }

        val filteredStudents = studentList.filter {
            it.name.contains(query, ignoreCase = true) || it.mssv.contains(query)
        }

        if (filteredStudents.isEmpty()) {
            textViewError.text = "No students found."
            textViewError.visibility = View.VISIBLE
        } else {
            textViewError.visibility = View.GONE
        }

        adapter.updateList(filteredStudents)
    }
}