package com.example.patronobservable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patronobservable.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding

    private val userList = mutableListOf<User>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java)

        //val adapter = UserRecyclerView(mutableListOf(viewModel.getUser()))
        val adapter = UserRecyclerView(userList)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter

        viewModel.recyclerList.observe(this) {

        }

        binding.btnAdd.setOnClickListener {
            //viewModel.addItemToList()
            onAddItem(adapter)
        }


        binding.btnDelete.setOnClickListener {
            //viewModel.deleteItemToList()
            onRemoveItem(adapter)
        }

    }

    fun onAddItem(adapter: UserRecyclerView) {
        val newItem = getUser()
        userList.add(newItem)
        adapter.notifyItemInserted(userList.size - 1)
    }

    fun getUser():User {
        return User(R.drawable.messi_campeon, "Lionel Messi", "Campeón del mundo - Argentina 2022")
    }

    fun onRemoveItem(adapter: UserRecyclerView) {
        if (userList.isNotEmpty()) {
            userList.removeAt(userList.size - 1)
            adapter.notifyItemRemoved(userList.size)
        }
    }




}