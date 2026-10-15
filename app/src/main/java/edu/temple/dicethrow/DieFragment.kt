package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

const val DIESIDE = "sidenumber"
class DieFragment : Fragment() {

    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(this)[DieViewModel::class.java]
    }

    val CURRENTROLL = "current_roll"


    lateinit var dieTextView: TextView

    var dieSides: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
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

        dieViewModel.getRoll().observe(viewLifecycleOwner) { rollValue ->
            dieTextView.text = rollValue.toString()
        }

        view.setOnClickListener {
            throwDie()
        }
    }

    fun throwDie() {
        dieViewModel.setRoll(Random.nextInt(dieSides) + 1 )
    }

    companion object {
        fun newInstance(sides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
        }
    }
}