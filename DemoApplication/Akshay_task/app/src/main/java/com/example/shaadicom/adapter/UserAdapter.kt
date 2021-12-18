package com.example.shaadicom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.shaadicom.R
import com.example.shaadicom.BR
import com.example.shaadicom.database.UserDetailsEntity

class UserAdapter(val mlist:List<UserDetailsEntity>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemClickListener: RecyclerViewOnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.usermatchcard,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data=mlist[position]
        if (holder is UserViewHolder) {
            holder.binding.setVariable(
                BR.viewModel,
                UserMatchCardViewModel(data, holder.adapterPosition)
            )
            holder.binding.setVariable(BR.clickHandler, itemClickListener)//Action Listner
            holder.binding.setVariable(BR.adapterPosition, holder.adapterPosition)
            holder.binding.executePendingBindings()
        }

    }

    override fun getItemCount(): Int {
        return mlist.size
    }
    internal inner class UserViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)

}