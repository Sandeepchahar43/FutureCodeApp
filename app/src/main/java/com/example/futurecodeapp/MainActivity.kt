package com.example.futurecodeapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futurecodeapp.adapter.UserAdapter
import com.example.futurecodeapp.data.RetrofitInstance
import com.example.futurecodeapp.data.User
import com.example.futurecodeapp.data.UserRepository
import com.example.futurecodeapp.data.UserViewModel
import com.example.futurecodeapp.data.UserViewModelFactory
import com.example.futurecodeapp.databinding.ActivityMainBinding
import com.example.futurecodeapp.local.CodeDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private var fullList: List<User> = emptyList()
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root)





        //  Database
        val db = CodeDatabase.getDatabase(this)
        val dao = db.userDao()
        val repository = UserRepository(RetrofitInstance.api,dao)
        val factory = UserViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]



         adapter = UserAdapter()

        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.userRecyclerView.adapter = adapter

        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.userRecyclerView.visibility = View.GONE

        viewModel.getAllData()

        viewModel._userData.observe(this) { list ->
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.userRecyclerView.visibility = View.VISIBLE
            if(list.isEmpty()){
                Toast.makeText(this, "Please On Internet", Toast.LENGTH_SHORT).show()
            }else{
                adapter.submitList(list ?: emptyList())
                fullList = list
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.search)
        val searchView = item.actionView as SearchView

        searchView.queryHint = "Search by name or email"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        return true
    }

    private fun filterList(query: String?) {

        if (query.isNullOrEmpty()) {
            adapter.submitList(fullList)
            return
        }

        val filtered = fullList.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.email.contains(query, ignoreCase = true)
        }

        adapter.submitList(filtered)
    }
}