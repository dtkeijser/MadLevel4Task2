package com.example.madlevel4task2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val gameHistory: List<Game>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            itemView.tvDate.text = game.playDate.toString()
            itemView.tvResult.text = game.gameResult

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return gameHistory.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(gameHistory[position])
    }
}