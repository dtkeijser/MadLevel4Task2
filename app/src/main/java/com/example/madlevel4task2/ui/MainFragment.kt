package com.example.madlevel4task2.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Moves
import com.example.madlevel4task2.repository.GameRespository
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private lateinit var gameRespository: GameRespository

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews(){
        ivRock.setOnClickListener { playerGame(Moves.Rock) }
        ivPaper.setOnClickListener { playerGame(Moves.Paper) }
        ivScissors.setOnClickListener { playerGame(Moves.Scissors) }
    }

    /**
     * Randomly generate rock, paper scissors
     */
    private fun computerTurn(): Moves{
        val computerMove = when((1..3).random()){
            1 ->Moves.Paper
            2 -> Moves.Rock
            3-> Moves.Scissors
            else -> Moves.Paper
        }
        ivComputer.setImageDrawable(getDrawable(
            computerMove,
        requireContext()))
        return computerMove
    }

      fun getDrawable(moves: Moves, context: Context): Drawable?{
        return when (moves){
            Moves.Paper -> context.getDrawable(R.drawable.paper)
            Moves.Scissors -> context.getDrawable(R.drawable.scissors)
            Moves.Rock -> context.getDrawable(R.drawable.rock)
        }
    }

    /**
     * Player input to play rock, paper scissors
     */
    private fun playerGame(userMoves: Moves){
        ivUser.setImageDrawable(getDrawable(userMoves,requireContext()))

        val computerMove = computerTurn()
        var gameResult = ""

        when(userMoves){
            Moves.Rock -> {
                when(computerMove){
                    Moves.Rock-> gameResult= getString(R.string.draw)
                    Moves.Paper -> gameResult = getString((R.string.computer_wins))
                    Moves.Scissors -> gameResult = getString(R.string.user_wins)
                }
            }
            Moves.Paper -> {
                when(computerMove){
                    Moves.Rock -> gameResult = getString(R.string.user_wins)
                    Moves.Paper -> gameResult = getString(R.string.draw)
                    Moves.Scissors -> gameResult = getString(R.string.computer_wins)
                }
            }
            Moves.Scissors -> {
                when(computerMove){
                    Moves.Rock-> gameResult= getString(R.string.computer_wins)
                    Moves.Paper -> gameResult = getString((R.string.user_wins))
                    Moves.Scissors -> gameResult = getString(R.string.draw)
                }
            }
        }
        tvwin_loss.text = gameResult
    }


}