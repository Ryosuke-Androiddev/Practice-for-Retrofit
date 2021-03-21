package com.example.practiceforretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceforretrofit.databinding.ItemTodoBinding

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
    //update information behind our scene
    private val differ = AsyncListDiffer(this@TodoAdapter,diffCallback)
    var todos: List<Todo>
        get() = differ.currentList
        set(value) {differ.submitList(value)}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.MyViewHolder {
        return MyViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoAdapter.MyViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            textView.text = todo.title
            checkBox.isChecked = todo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}