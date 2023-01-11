package com.example.sqldatabse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqldatabse.databinding.ActivityMainBinding
import com.example.sqldatabse.util.DBHelper
import com.example.sqldatabse.util.ModelClass

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var list = arrayListOf<ModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = DBHelper(this)
        binding.insertBtn.setOnClickListener {
            db.insertData(binding.nameEdt.text.toString(),
                binding.mobileEdt.text.toString(),
                binding.stdEdt.text.toString())

//            live Show data
            list = db.readData()
            RvView(list)
        }

        binding.readBtn.setOnClickListener {
            list = db.readData()
            RvView(list)
        }
    }

    fun RvView(l1: ArrayList<ModelClass>) {
        var adapter = DataAdapter(this, l1)
        var lm = LinearLayoutManager(this)
        binding.RvView.adapter = adapter
        binding.RvView.layoutManager = lm
    }
}