package com.gkzxhn.legalconsulting.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gkzxhn.legalconsulting.R
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.item_conversation.view.*
import kotlinx.android.synthetic.main.item_order_dispose.view.*
import java.util.*


/**
 * Explanation：
 * @author LSX
 * Created on 2018/9/10.
 */

class OrderDisposeAdapter(private val mContext: Context, private val data: List<String>?) : RecyclerView.Adapter<OrderDisposeAdapter.ViewHolder>() {


    private var mDatas: ArrayList<String> = ArrayList()
    private var onItemClickListener: MultiItemTypeAdapter.OnItemClickListener? = null
    private var mCurrentIndex = -1

    /**
     *  获取当前项实体
     */
    fun getCurrentItem(): String {
        return mDatas[mCurrentIndex]
    }

    fun setOnItemClickListener(onItemClickListener: MultiItemTypeAdapter.OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    /**
     * 更新数据
     */
    fun updateItems(mDatas: List<String>?) {
        this.mDatas.clear()
        if (mDatas != null && mDatas.size > 0) {
            this.mDatas.addAll(mDatas)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_order_dispose, null)
        view.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        return ViewHolder(view)
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val entity = mDatas[position]
            tv_order_dispose_name.text = "待处理单："+position
            holder.itemView.setOnClickListener(android.view.View.OnClickListener {
                mCurrentIndex = position
                onItemClickListener?.onItemClick(this,holder,position)
            })
        }
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }


}
