package com.bamboogeeks.julyacademy2.todolistapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.databinding.ActivityTodolistBinding
import com.bamboogeeks.julyacademy2.todolistapp.model.Todo
import com.bamboogeeks.julyacademy2.todolistapp.utils.TodoAdapter

class TodolistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodolistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodolistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listOfTodos = mutableListOf(
            Todo("Go To Gym", false),
            Todo("Finish Todo App", false),
            Todo("Finish Shopping Item APp", false),
            Todo("Finish Calculator App", true),
            Todo("Finish Restaurant App", true),
            Todo("Go To Sleep", false),
        )
        val ourAdapter = TodoAdapter(listOfTodos)
        binding.rvListOfTodos.adapter = ourAdapter
        binding.rvListOfTodos.layoutManager = LinearLayoutManager(this)
        binding.btnAddTodo.setOnClickListener {
            val newTodoTitle = binding.etTodo.text.toString()
            val newTodo = Todo(newTodoTitle, false)
            listOfTodos.add(newTodo)
            ourAdapter.notifyDataSetChanged()

        }
    }
}