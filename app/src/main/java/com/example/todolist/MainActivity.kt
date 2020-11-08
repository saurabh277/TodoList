package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.todolist.db.MyDbHelper
import com.example.todolist.db.ToDoTable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val todos = ArrayList<ToDo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val todoAdapter = ArrayAdapter<ToDo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            todos
        )
        val db=MyDbHelper(this).writableDatabase
        fun refreshTodoList(){
           val todoList=ToDoTable.getAllNodos(db)
           // Log.d("TODOS",todoList.toString())
            todos.clear()
            todos.addAll(todoList)
            todoAdapter.notifyDataSetChanged()
        }
        refreshTodoList()
        //set todoadapter in listView
        lvTools.adapter = todoAdapter
        btnAddToDo.setOnClickListener {
            val newToDo=ToDo(
                etNewToDo.text.toString(),
                false
            )
           ToDoTable.insertTodo(db,newToDo)
            refreshTodoList()
        }
    }

}
