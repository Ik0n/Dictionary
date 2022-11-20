package ru.geekbrains.dictionary.view.base.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.dictionary.R
import ru.geekbrains.dictionary.databinding.ActivityMainBinding
import ru.geekbrains.dictionary.model.data.AppState
import ru.geekbrains.dictionary.model.data.DataModel
import ru.geekbrains.dictionary.utils.isOnline
import ru.geekbrains.dictionary.view.base.BaseActivity
import ru.geekbrains.dictionary.view.base.main.adapter.MainAdapter

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: android.view.View.OnClickListener =
        android.view.View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: MainViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        with(binding) {
            searchFab.setOnClickListener(fabClickListener)
            mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
            mainActivityRecyclerview.adapter = adapter
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initViews()
    }

    override fun renderData(dataModel: AppState) {
        when (dataModel) {
            is AppState.Success -> {
                showViewWorking()
                val data = dataModel.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_title_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (dataModel.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = dataModel.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(
                    getString(R.string.error_stub),
                    dataModel.error.message
                )
            }
        }
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
        private const val GONE = android.view.View.GONE
        private const val VISIBLE = android.view.View.VISIBLE
    }
}