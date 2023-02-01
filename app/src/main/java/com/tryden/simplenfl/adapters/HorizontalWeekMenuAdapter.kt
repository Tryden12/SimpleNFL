package com.tryden.simplenfl.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.tryden.simplenfl.databinding.ModelScoresCarouselDateItemBinding
import com.tryden.simplenfl.domain.models.scores.Scores.EntryWeek


class HorizontalWeekMenuAdapter(
    private val onWeekSelected: (String) -> Unit
): RecyclerView.Adapter<HorizontalWeekMenuAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val binding: ModelScoresCarouselDateItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<EntryWeek>() {
        override fun areItemsTheSame(oldItem: EntryWeek, newItem: EntryWeek) : Boolean {
            return oldItem.range == newItem.range
        }

        override fun areContentsTheSame(oldItem: EntryWeek, newItem: EntryWeek) : Boolean {
            return oldItem == newItem
        }
    } // end of diffCallback

    val differ = AsyncListDiffer(this, diffCallback)

    var weeks: MutableList<EntryWeek>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    fun updateWeeksList(newItems: MutableList<EntryWeek>) {
        weeks.clear()
        weeks.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(ModelScoresCarouselDateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.apply {
            val week = weeks[position]
            labelTextView.text = week.label
            datesTextView.text = week.dates
            root.setOnClickListener {
                onWeekSelected(weeks[position].range)
            }

        }
    }

    override fun getItemCount(): Int {
        return weeks.size
    }
}