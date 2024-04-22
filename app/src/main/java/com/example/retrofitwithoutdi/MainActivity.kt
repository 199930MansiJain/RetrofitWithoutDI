package com.example.retrofitwithoutdi

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitwithoutdi.adapter.CustomAdapter
import com.example.retrofitwithoutdi.databinding.ActivityMainBinding
import com.example.retrofitwithoutdi.network.ApiResult
import com.example.retrofitwithoutdi.sqlDataBase.dao.User


class MainActivity : AppCompatActivity() {
    private var viewModel: MainActivityViewModel? = null
    private var adapter: CustomAdapter = CustomAdapter()


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        observeViewModel()
        viewModel!!.makeApiCall()
        setupRecyclerView()

        setUpLocalClickEvents()

    }

    private fun setUpLocalClickEvents() {
        binding.insert.setOnClickListener {
            viewModel?.insertUserDataToDb()
        }

        binding.getData.setOnClickListener {
            viewModel?.fetchData()
        }

        binding.update.setOnClickListener {
            val user = User(
                mobileNumber = "9974011595",
                password = "mj",
                username = "Mansi Jain",
                id = 25,
                email = "mj@gmail.com"
            )
            viewModel?.updateUserData(user)
        }


        binding.delete.setOnClickListener {
            viewModel?.deleteUserById(25)
        }


    }


    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel?.recyclerLiveListData?.observe(this) { result ->
            when (result) {
                is ApiResult.Error -> {
                    hideProgressBar()
                    showToast("Error: ${result.exception.message}")
                }

                ApiResult.Loading -> {
                    showProgressBar()
                    binding.recyclerView.visibility = View.GONE
                }

                is ApiResult.Success -> {
                    hideProgressBar()
                    result.data.items?.let { adapter.setItem(it) }
                    binding.recyclerView.visibility = View.VISIBLE
                }

                else -> {}
            }
        }
    }


    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}