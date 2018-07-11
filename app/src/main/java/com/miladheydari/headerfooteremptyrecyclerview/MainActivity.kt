package com.miladheydari.headerfooteremptyrecyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.miladheydari.hferecyclerview.HFERecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var hfeRecyclerView: HFERecyclerView
    private val listString: MutableList<String> = ArrayList()

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hfeRecyclerView = findViewById(R.id.recycler)

        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        hfeRecyclerView.layoutManager = mLayoutManager
        hfeRecyclerView.itemAnimator = DefaultItemAnimator()

        hfeRecyclerView.emptyView = findViewById(R.id.empty_view)
        for (i in 1..20)
            listString.add("hii $i")

        adapter = Adapter(listString)
        hfeRecyclerView.adapter = adapter

        hfeRecyclerView.setFooter(LayoutInflater.from(this).inflate(R.layout.footer, null))
        hfeRecyclerView.setHeader(LayoutInflater.from(this).inflate(R.layout.header, null))

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.add_header -> {
                hfeRecyclerView.setHeader(LayoutInflater.from(this).inflate(R.layout.header, null))


            }
            R.id.add_footer -> {
                hfeRecyclerView.setFooter(LayoutInflater.from(this).inflate(R.layout.footer, null))

            }
            R.id.add_all_items -> {
                listString.clear()
                for (i in 1..20)
                    listString.add("hii $i")
                adapter.notifyDataSetChanged()

            }


            R.id.remove_header -> {
                hfeRecyclerView.setHeader(null)


            }
            R.id.remove_footer -> {
                hfeRecyclerView.setFooter(null)

            }


            R.id.remove_all_items -> {
                listString.clear()

                adapter.notifyDataSetChanged()

            }
            else -> return super.onOptionsItemSelected(item)

        }
        return true


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }
}
