package com.example.madlevel4task2.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.model.Moves.Companion.PAPER
import com.example.madlevel4task2.model.Moves.Companion.ROCK
import com.example.madlevel4task2.model.Moves.Companion.SCISSORS
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val gameHistory: List<Game>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            var context = itemView.context

            itemView.tvResult.text = game.gameResult
            itemView.tvDate.text = game.playDate.toString()
            itemView.ivUser2.setImageDrawable(getDrawable(game.userMove, context))
            itemView.ivComputer2.setImageDrawable(getDrawable(game.computerMove, context))
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

    fun getDrawable(moves: String, context: Context): Drawable?{
        return when (moves){
            PAPER -> context.getDrawable(R.drawable.paper)
            SCISSORS -> context.getDrawable(R.drawable.scissors)
            ROCK-> context.getDrawable(R.drawable.rock)
            else -> null
        }
    }
}