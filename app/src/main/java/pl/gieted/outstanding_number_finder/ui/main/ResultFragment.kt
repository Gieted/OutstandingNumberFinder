package pl.gieted.outstanding_number_finder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import pl.gieted.outstanding_number_finder.databinding.ResultFragmentBinding
import pl.gieted.outstanding_number_finder.findOutstandingInteger

class ResultFragment : Fragment() {

    private val args by navArgs<ResultFragmentArgs>()
    private lateinit var binding: ResultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ResultFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val result = findOutstandingInteger(args.integers.toList())
        binding.textView.text = result.toString()
    }
}
