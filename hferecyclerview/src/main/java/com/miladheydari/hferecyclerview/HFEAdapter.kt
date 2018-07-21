package com.miladheydari.hferecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class HFEAdapter<T>(_data: List<T>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_FOOTER = 1
        const val TYPE_ITEM = 2

    }

    var header: View? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var footer: View? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    private var data: List<T>? = _data
        set (value) {
            field = value
            notifyDataSetChanged()
        }


    protected abstract fun getItemView(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder


    private fun getHeaderView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder? = if (header != null) HeaderViewHolder(header!!) else null

    private fun getFooterView(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder? = if (footer != null) FooterViewHolder(footer!!) else null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_HEADER -> getHeaderView(inflater, parent)!!
            TYPE_FOOTER -> getFooterView(inflater, parent)!!
            else -> getItemView(inflater, parent, viewType)
        }

    }

    override fun getItemCount(): Int {
        var itemCount = getRealItemCount()
        header?.let { itemCount++ }
        footer?.let { itemCount++ }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        if (header != null && isPositionHeader(position)) return TYPE_HEADER
        return if (footer != null && isPositionFooter(position)) TYPE_FOOTER else TYPE_ITEM
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == itemCount - 1
    }

    fun getRealItemCount(): Int {
        return data!!.size
    }

    protected fun getItem(position: Int): T {
        return if (header != null) data!![position - 1] else data!![position]
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view)
}