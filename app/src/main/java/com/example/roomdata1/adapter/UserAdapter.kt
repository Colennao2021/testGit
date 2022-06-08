package com.example.roomdata1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdata1.R
import com.example.roomdata1.fragment.ListFragmentDirections
import com.example.roomdata1.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData: List<User> = listOf()

    public fun setListData(listData: List<User>) {
        this.listData = listData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.custom_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val userItem = listData[position]
            holder.bindingData(userItem)
            holder.itemView.row_layout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(userItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder constructor(item: View) : RecyclerView.ViewHolder(item) {

        private val id: TextView = item.row_id
        private val firstName = item.row_first_name
        private val lastName = item.row_last_name
        private val age = item.row_age

        fun bindingData(user: User) {
            id.text = user.id.toString()
            firstName.text = user.firstName
            lastName.text = user.lastName
            age.text = user.age.toString()
        }
    }
}