package ru.geekbrains.dictionary.view.base.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import org.koin.android.ext.android.inject
import ru.geekbrains.core.BaseActivity
import ru.geekbrains.dictionary.R
import ru.geekbrains.dictionary.databinding.ActivityMainBinding
import ru.geekbrains.dictionary.utils.convertMeaningsToString
import ru.geekbrains.dictionary.view.base.description.DescriptionActivity
import ru.geekbrains.dictionary.view.base.main.adapter.MainAdapter
import ru.geekbrains.historyscreen.view.history.HistoryActivity
import ru.geekbrains.model.AppState
import ru.geekbrains.model.DataModel
import ru.geekbrains.utils.isOnline
import ru.geekbrains.utils.viewById




private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: ru.geekbrains.dictionary.view.base.main.MainViewModel

    private val mainActivityRecyclerView by viewById<RecyclerView>(R.id.main_activity_recyclerview)
    private val searchFAB by viewById<FloatingActionButton>(R.id.search_fab)

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
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl
                    )
                )
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

        val viewModel: MainViewModel by inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity) { renderData(it) }
    }

    private fun initViews() {
        searchFAB.setOnClickListener(fabClickListener)
        mainActivityRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        mainActivityRecyclerView.adapter = adapter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, "QWE", Toast.LENGTH_SHORT).show()
        initViewModel()
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
}