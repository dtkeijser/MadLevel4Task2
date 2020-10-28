package com.example.madlevel4task2.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.model.Moves
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private lateinit var gameRepository: GameRepository

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        CoroutineScope(Dispatchers.Main).launch {
            updateStats()
        }
        initViews()
    }

    private fun initViews(){
        ivRock.setOnClickListener { playerGame(Moves.ROCK) }
        ivPaper.setOnClickListener { playerGame(Moves.PAPER) }
        ivScissors.setOnClickListener { playerGame(Moves.SCISSORS) }
    }

    /**
     * Randomly generate rock, paper scissors
     */
    private fun computerTurn(): String{
        val computerMove = when((1..3).random()){
            1 ->Moves.PAPER
            2 -> Moves.ROCK
            3-> Moves.SCISSORS
            else -> Moves.PAPER
        }
        ivComputer.setImageDrawable(getDrawable(
            computerMove,
        requireContext()))
        return computerMove
    }

      fun getDrawable(moves: String, context: Context): Drawable?{
        return when (moves){
            Moves.PAPER -> context.getDrawable(R.drawable.paper)
            Moves.SCISSORS -> context.getDrawable(R.drawable.scissors)
            Moves.ROCK -> context.getDrawable(R.drawable.rock)
            else -> null
        }
    }

    /**
     * Player input to play rock, paper scissors
     */
    private fun playerGame(userMoves: String){
        ivUser.setImageDrawable(getDrawable(userMoves,requireContext()))

        val computerMove = computerTurn()
        var gameResult = ""

        when(userMoves){
            Moves.ROCK -> {
                when(computerMove){
                    Moves.ROCK-> gameResult= getString(R.string.draw)
                    Moves.PAPER -> gameResult = getString((R.string.computer_wins))
                    Moves.SCISSORS -> gameResult = getString(R.string.user_wins)
                }
            }
            Moves.PAPER -> {
                when(computerMove){
                    Moves.ROCK -> gameResult = getString(R.string.user_wins)
                    Moves.PAPER -> gameResult = getString(R.string.draw)
                    Moves.SCISSORS -> gameResult = getString(R.string.computer_wins)
                }
            }
            Moves.SCISSORS -> {
                when(computerMove){
                    Moves.ROCK-> gameResult= getString(R.string.computer_wins)
                    Moves.PAPER -> gameResult = getString(R.string.user_wins)
                    Moves.SCISSORS -> gameResult = getString(R.string.draw)
                }
            }
        }
        tvwin_loss.text = gameResult
        updateDatabase(userMoves, computerMove, gameResult)
    }

    suspend private fun updateStats(){
        val wins = gameRepository.getWins()
        val draws = gameRepository.getDraws()
        val losses = gameRepository.getLosses()

        tvStats.text = resources.getString(R.string.all_time_stats, wins,draws,losses)
    }

    private fun updateDatabase(userMoves: String, computerMoves: String, result: String){

        var win = false
        var draw = false
        var loss = false

        when (result){
            getString(R.string.user_wins) -> win = true
            getString(R.string.draw) -> draw = true
            getString(R.string.computer_wins) -> loss = true
        }
        val game = Game(userMoves,computerMoves, Date(), result, win, draw, loss )

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){
                gameRepository.insertGame(game)
                CoroutineScope(Dispatchers.Main).launch {
                    updateStats()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_history -> {findNavController().navigate(R.id.historyFragment)
             true}
        else -> super.onOptionsItemSelected(item)
    }
}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}