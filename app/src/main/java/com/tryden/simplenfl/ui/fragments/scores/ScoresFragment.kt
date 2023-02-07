package com.tryden.simplenfl.ui.fragments.scores

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tryden.simplenfl.R
import com.tryden.simplenfl.SharedViewModel
import com.tryden.simplenfl.adapters.HorizontalWeekMenuAdapter
import com.tryden.simplenfl.databinding.FragmentScoresBinding
import com.tryden.simplenfl.domain.models.scores.Scores
import com.tryden.simplenfl.network.response.teams.models.scores.ScoreboardResponse
import com.tryden.simplenfl.ui.fragments.scores.calendar.UiCalendar
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class ScoresFragment: Fragment(R.layout.fragment_scores) {

    private var _binding: FragmentScoresBinding? = null
    val binding: FragmentScoresBinding get() = _binding!!

    private lateinit var weeksMenuAdapter: HorizontalWeekMenuAdapter

    private val viewModel by viewModels<ScoresViewModel>()
    private val epoxyControllerScoresByWeek = ScoresByWeekEpoxyController2()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentScoresBinding.bind(view)
        setupCalendarRecyclerView()

        viewModel.calendarListLiveData.observe(viewLifecycleOwner) { calendarList ->
            // build calendar and weeks
            val uiCalendar: List<UiCalendar> = calendarList.map { calendar ->
                viewModel.uiCalendarMapper.buildFrom(calendar)
            }
            // submit weeks to adapter
            var uiWeeks = mutableListOf<UiCalendar.UiWeek>()
            for (i in 0 until uiCalendar.size-1) {
                uiWeeks.addAll(uiCalendar[i].weeks)
            }
            weeksMenuAdapter.differ.submitList(uiWeeks)
            weeksMenuAdapter.notifyDataSetChanged()
        }
        viewModel.refreshCalendar("1")

        // Default loading to Week 1 todo: load to current week on default
        binding.epoxyScoresByWeekRecyclerView.setController(epoxyControllerScoresByWeek)
        epoxyControllerScoresByWeek.setData(emptyList())
        viewModel.eventListLiveData.observe(viewLifecycleOwner) { eventList ->
            val uiEvents: List<UiEvent> = eventList.map { event ->
                viewModel.uiEventMapper.buildFrom(event)
            }
            epoxyControllerScoresByWeek.setData(uiEvents)
        }
        viewModel.refreshScores("20220908-20220914", "50")
    }

    private fun setupCalendarRecyclerView() = binding?.weeksListRecyclerView?.apply {
        weeksMenuAdapter = HorizontalWeekMenuAdapter(::onWeekSelected)
        adapter = weeksMenuAdapter
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun onWeekSelected(range: String) {
        if (range.isNotEmpty()) {
            Log.e("ScoresFragment", "onWeekSelected: $range")

            epoxyControllerScoresByWeek.setData(emptyList())
            viewModel.eventListLiveData.observe(viewLifecycleOwner) { eventList ->
                val uiEvents: List<UiEvent> = eventList.map { event ->
                    viewModel.uiEventMapper.buildFrom(event)
                }
                epoxyControllerScoresByWeek.setData(uiEvents)
            }
            viewModel.refreshScores(range, "50")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}