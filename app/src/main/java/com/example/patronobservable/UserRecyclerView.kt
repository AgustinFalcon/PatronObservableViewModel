package com.example.patronobservable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patronobservable.databinding.ItemRecyclerViewBinding


class UserRecyclerView(private val userList: MutableList<User>): RecyclerView.Adapter<UserRecyclerView.UserViewHolder>() {


    inner class UserViewHolder(private val binding: ItemRecyclerViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.imageUser.setImageResource(user.image)
            binding.nameUser.text = user.title
            binding.descUser.text = user.description
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    fun addItem(user: User) {
        userList.add(userList.size, user)
    }


}