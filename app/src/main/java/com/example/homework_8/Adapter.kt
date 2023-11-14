package com.example.homework_8

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_8.UsersActivity.Companion.REQUESTCODE
import com.example.homework_8.databinding.UsersBinding

class Adapter(private var usersList: MutableList<UserData>) :
    RecyclerView.Adapter<Adapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: UsersBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(
            UsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = usersList[position]
        holder.binding.tvFirstName.text = user.firstName
        holder.binding.tvLastName.text = user.lastName
        holder.binding.tvEmail.text = user.email

        //delete button
        holder.binding.deleteBtn.setOnClickListener {
            deleteUser(position)
        }
        //edit button
        holder.binding.editBtn.setOnClickListener {
            //intent to start the UserActivity with user data
            val intent = Intent(holder.binding.root.context, UserActivity::class.java)
            intent.putExtra("userData", user)
            (holder.binding.root.context as Activity).startActivityForResult(intent, REQUESTCODE)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    //delete button function
    private fun deleteUser(position: Int) {
        usersList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)

    }
}

