package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

const val DIESIDE = "sidenumber"
class DieFragment : Fragment() {

    val CURRENTROLL = "current_roll"


    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int = 0
    var clickable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }

        savedInstanceState?.let {
            currentRoll = it.getInt(CURRENTROLL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (currentRoll == 0)
            throwDie()
        else
            dieTextView.text = currentRoll.toString()


        if (clickable)
            view.setOnClickListener{
                throwDie()
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(CURRENTROLL, currentRoll)
    }
    fun throwDie() {
        currentRoll = (Random.nextInt(dieSides) + 1 )
        dieTextView.text = currentRoll.toString()
    }

    companion object {
        fun newInstance(sides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
        }
    }
}