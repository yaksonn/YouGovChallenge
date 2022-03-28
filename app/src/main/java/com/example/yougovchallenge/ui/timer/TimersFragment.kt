package com.example.yougovchallenge.ui.timer

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.yougovchallenge.base.BaseFragment
import com.example.yougovchallenge.databinding.FragmentTimersBinding
import com.example.yougovchallenge.model.TimerModel
import com.example.yougovchallenge.utils.*
import java.util.ArrayList

class TimersFragment : BaseFragment<FragmentTimersBinding>(FragmentTimersBinding::inflate) {

    private val viewModel: TimersViewModel by viewModels()
    private var countDownTimer: CountDownTimer? = null
    private var timerList = ArrayList<TimerModel>()
    private var adapter: TimersAdapter? = null

    override fun getViewBinding() = FragmentTimersBinding.inflate(layoutInflater)


    override fun prepareViews() {
        with(binding) {
            runningTimerRecyclerView.adapter = adapter
        }
    }

    override fun listeners() {
        with(binding) {
            hourTextInputLayout.editText?.addTextChangedListener {
                checkFields()
            }

            minuteTextInputLayout.editText?.addTextChangedListener {
                checkFields()
            }

            secondTextInputLayout.editText?.addTextChangedListener {
                checkFields()
            }

            startTimerButton.setOnClickListener {

                if (timerList.isNotEmpty()) {
                    countDownTimer?.cancel()
                }

                timerList.add(
                    TimerModel(
                        hourTextInputEditText.text.toString(),
                        minuteTextInputEditText.text.toString(),
                        secondTextInputEditText.text.toString()
                    )
                )

                val hourMillis = (hourTextInputEditText.text.toString().toInt() * (DURATION_1000
                        * DURATION_60 * DURATION_60)).toLong()

                val minuteMillis = (minuteTextInputEditText.text.toString().toInt() *
                        (DURATION_1000 * DURATION_60)).toLong()

                val secondMillis = (secondTextInputEditText.text.toString().toInt() *
                        (DURATION_1000)).toLong()

                val millis = hourMillis + minuteMillis + secondMillis
                startTimer(millis = millis)

            }


        }
    }

    override fun observers() {
        viewModel.timerData.observe(requireActivity()) { result ->
            if (result) {
                adapter?.notifyItemChanged(0)
                binding.runningTimerRecyclerView.adapter = adapter
            }
        }
    }

    private fun startTimer(millis: Long) {
        countDownTimer = object : CountDownTimer(millis, DURATION_1000.toLong()) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                val minute = ((millisUntilFinished / (DURATION_1000 * DURATION_60)) % DURATION_60)
                val seconds = (millisUntilFinished / DURATION_1000) % DURATION_60
                val hours = ((millisUntilFinished / (DURATION_1000 * DURATION_60 * DURATION_60))
                        % DURATION_24)

                timerList[0] = TimerModel(hours.toString(), minute.toString(), seconds.toString())
                adapter = TimersAdapter((timerList))
                viewModel.initChangeListener()
            }

            override fun onFinish() {

            }
        }
        clearFields()
        countDownTimer?.start()
    }


    private fun checkFields() = binding.apply {
        var isValid = true
        if (hourTextInputEditText.text?.length!! <= EMPTY_LENGTH) isValid = false
        if (minuteTextInputEditText.text?.length!! <= EMPTY_LENGTH) isValid = false
        if (secondTextInputEditText.text?.length!! <= EMPTY_LENGTH) isValid = false

        startTimerButton.isEnabled = isValid
    }

    private fun clearFields() {
        binding.apply {
            hourTextInputEditText.text?.clear()
            minuteTextInputEditText.text?.clear()
            secondTextInputEditText.text?.clear()
            secondTextInputEditText.clearFocus()
        }
    }

    override fun onPause() {
        super.onPause()
        countDownTimer?.cancel()
    }
}