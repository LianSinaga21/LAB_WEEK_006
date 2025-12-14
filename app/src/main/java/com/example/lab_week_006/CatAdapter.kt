package com.example.lab_week_006

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_006.model.CatModel
import androidx.recyclerview.widget.ItemTouchHelper


class CatAdapter(
    private val inflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {

    val swipeToDeleteCallback = SwipeToDeleteCallback()

    private val cats = mutableListOf<CatModel>()

    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        cats.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = inflater.inflate(R.layout.item_list, parent, false)
        return CatViewHolder(view, imageLoader, onClickListener)
    }

    override fun getItemCount(): Int = cats.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])
    }

    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }

    inner class SwipeToDeleteCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }

}
