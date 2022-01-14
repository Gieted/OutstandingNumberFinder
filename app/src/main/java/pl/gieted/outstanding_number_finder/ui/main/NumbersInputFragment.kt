package pl.gieted.outstanding_number_finder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pl.gieted.outstanding_number_finder.BR
import pl.gieted.outstanding_number_finder.IntegersParsingError
import pl.gieted.outstanding_number_finder.R
import pl.gieted.outstanding_number_finder.databinding.NumbersInputFragmentBinding
import pl.gieted.outstanding_number_finder.parseIntegersInput

class NumbersInputViewModel(private val context: Fragment) : BaseObservable() {
    @Bindable
    var input = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.input)
            notifyPropertyChanged(BR.errorMessage)
        }

    @get:Bindable
    val errorMessage: String?
        get() {
            val parsingResult = parseIntegersInput(input)

            return when {
                parsingResult.isValid || !validationStarted -> null
                else -> when (parsingResult.error) {
                    IntegersParsingError.InputBlank -> context.getString(R.string.input_cannot_be_blank)
                    IntegersParsingError.IncorrectFormat -> context.getString(R.string.incorrect_input)
                    IntegersParsingError.LessThan3Numbers -> context.getString(R.string.less_than_3_numbers)
                    IntegersParsingError.MultipleOutstandingNumbers -> context.getString(R.string.not_single_outstanding)
                    IntegersParsingError.NoOutstandingNumber -> context.getString(R.string.no_outstanding_number)
                    else -> throw IllegalStateException()
                }
            }
        }

    var validationStarted = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.errorMessage)
        }
}

class NumbersInputFragment : Fragment() {

    private lateinit var viewModel: NumbersInputViewModel
    private lateinit var binding: NumbersInputFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NumbersInputFragmentBinding.inflate(inflater, container, false)
        viewModel = NumbersInputViewModel(this)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            viewModel.validationStarted = true
            val parsingResult = parseIntegersInput(viewModel.input)
            if (parsingResult.isValid) {
                val integers = parsingResult.integers ?: emptyList()
                val showResult = NumbersInputFragmentDirections.showResult(integers.toIntArray())
                findNavController().navigate(showResult)
            }
        }
    }
}
