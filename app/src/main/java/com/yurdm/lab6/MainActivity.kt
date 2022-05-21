package com.yurdm.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.yurdm.lab6.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GridAdapter
    private var list = mutableListOf<Number>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GridAdapter(layoutInflater) {
            val dialog = NumberDialog(it.num)
            dialog.show(supportFragmentManager, "num")
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = GridLayoutManager(this, 4)
    }

    override fun onStart() {
        super.onStart()

        val rnd = Random(System.currentTimeMillis())
        for (i in 1..30) list.add(
            Number(
                rnd.nextInt(100),
                rnd.nextInt(256),
                rnd.nextInt(256),
                rnd.nextInt(256)
            )
        )

        adapter.submitList(list.toList())
    }

    data class Number(val num: Int, val red: Int, val green: Int, val blue: Int)
}