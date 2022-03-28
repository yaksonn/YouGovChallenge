package com.example.yougovchallenge.ui.timer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yougovchallenge.databinding.RowTimerRecyclerBinding
import com.example.yougovchallenge.model.TimerModel


class TimersAdapter(private val items: List<TimerModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TimersViewHolder(
            RowTimerRecyclerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val timerItem = items[position]
        when (holder) {
            is TimersViewHolder -> {
                holder.bind(timerItemModel = timerItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    inner class TimersViewHolder(private val binding: RowTimerRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(timerItemModel: TimerModel) {
            binding.apply {
                with(timerItemModel) {
                    timeCountTextView.text = this.hours + ":" + this.minutes + ":" + this.seconds
                }
            }
        }
    }
}