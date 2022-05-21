package com.yurdm.lab6

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class GridAdapter(
    private val inflater: LayoutInflater,
    private val onClick: (MainActivity.Number) -> Unit
) : ListAdapter<MainActivity.Number, GridAdapter.ViewHolder>(NumberDiffCallback) {
    class ViewHolder(
        view: View,
        val onClick: (MainActivity.Number) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val num = view.findViewById<TextView>(R.id.num)
        private val bg = view.findViewById<LinearLayout>(R.id.container)
        private var number: MainActivity.Number? = null

        init {
            view.setOnClickListener {
                number?.let {
                    onClick(it)
                }
            }
        }

        fun bind(number: MainActivity.Number) {
            this.number = number
            num.text = number.num.toString()

            val rnd = Random(System.currentTimeMillis())
            val color = Color.rgb(
                number.red,
                number.green,
                number.blue
            )

            val bgColor = Color.argb(65, number.red, number.green, number.blue)

            num.setTextColor(color)
            bg.backgroundTintList = ColorStateList.valueOf(bgColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.grid_item, parent, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object NumberDiffCallback : DiffUtil.ItemCallback<MainActivity.Number>() {
        override fun areItemsTheSame(
            oldItem: MainActivity.Number,
            newItem: MainActivity.Number
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MainActivity.Number,
            newItem: MainActivity.Number
        ): Boolean {
            return oldItem.num == newItem.num
        }
    }
}