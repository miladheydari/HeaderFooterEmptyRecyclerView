package com.miladheydari.hferecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

class HFERecyclerView : RecyclerView {

    var emptyView: View? = null

    private val emptyObserver = object : RecyclerView.AdapterDataObserver() {


        override fun onChanged() {
            val adapter: HFEAdapter<*>? = adapter as HFEAdapter<*>
            if (adapter != null && emptyView != null) {
                if (adapter.getRealItemCount() == 0) {

                    emptyView!!.visibility = View.VISIBLE
                    this@HFERecyclerView.visibility = View.GONE

                } else {
                    emptyView!!.visibility = View.GONE
                    this@HFERecyclerView.visibility = View.VISIBLE
                }
            }

        }
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, int: Int) : super(context, attributeSet, int)


    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)

        adapter?.registerAdapterDataObserver(emptyObserver)

        emptyObserver.onChanged()
    }

    fun setHeader(view: View?) {
        (adapter as HFEAdapter<*>).header = view
    }

    fun setFooter(view: View?) {
        (adapter as HFEAdapter<*>).footer = view
    }

    fun getFooter(): View? {
        return (adapter as HFEAdapter<*>).footer

    }


    fun getHeader(): View? {
        return (adapter as HFEAdapter<*>).header
    }

}