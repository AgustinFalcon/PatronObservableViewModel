package com.example.patronobservable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patronobservable.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java)


        viewModel.recyclerList.observe(this) {
            val adapter = UserRecyclerView(it)
            binding.recyclerview.layoutManager = LinearLayoutManager(this)
            binding.recyclerview.adapter = adapter
        }



        binding.btnAdd.setOnClickListener {
            viewModel.addItemToList()
        }


        binding.btnDelete.setOnClickListener {
            viewModel.deleteItemToList()
        }


    }





}