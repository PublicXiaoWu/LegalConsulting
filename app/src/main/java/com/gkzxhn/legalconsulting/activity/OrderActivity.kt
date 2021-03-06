package com.gkzxhn.legalconsulting.activity

import android.graphics.Bitmap
import android.view.View
import com.gkzxhn.legalconsulting.R
import com.gkzxhn.legalconsulting.presenter.OrderPresenter
import com.gkzxhn.legalconsulting.utils.ProjectUtils
import com.gkzxhn.legalconsulting.utils.showToast
import com.gkzxhn.legalconsulting.view.OrderView
import kotlinx.android.synthetic.main.activity_oder.*
import kotlinx.android.synthetic.main.default_top.*

/**
 * Explanation：订单详情
 * @author LSX
 * Created on 2018/9/25.
 */

class OrderActivity : BaseActivity(), OrderView {

    lateinit var mPresenter: OrderPresenter
    var bitmap1: Bitmap? = null
    var bitmap2: Bitmap? = null


    override fun provideContentViewId(): Int {
        return R.layout.activity_oder
    }

    override fun init() {
        mPresenter = OrderPresenter(this, this)
        iv_default_top_back.setOnClickListener { finish() }
        tv_default_top_title.text = "订单详情"

        val orderID = intent.getStringExtra("orderId")
        val orderState = intent.getIntExtra("orderState", 0)

        when (orderState) {
        /****** 1，获取 抢单的明细 ******/
            1 -> mPresenter.getOrderRushInfo(orderID)
        /****** 2，获取 指定订单的明细 ******/
            2 -> mPresenter.getOrderMyInfo(orderID)
        }

        tv_order_next.setOnClickListener {
            if (tv_order_next.text.trim().toString() == resources.getString(R.string.send_message)) {
                /****** 发消息 ******/
                mPresenter.sendMessage()
            } else {
                if (ProjectUtils.certificationStatus()) {
                    /****** 抢单 ******/
                    mPresenter.acceptRushOrder(orderID)
                } else {
                    showToast("您认证尚未通过，不能进行此操作！")
                }
            }
        }

        /****** 拒绝订单 ******/
        tv_order_reject.setOnClickListener {
            mPresenter.rejectMyOrder(orderID)
        }
        /****** 接受订单 ******/
        tv_order_accept.setOnClickListener {
            mPresenter.acceptMyOrder(orderID)
        }

        iv_oder_image1.setOnClickListener {
            iv_order_big.visibility = View.VISIBLE
            iv_order_big.setImageBitmap(bitmap1)
        }

        iv_oder_image2.setOnClickListener {
            iv_order_big.visibility = View.VISIBLE
            iv_order_big.setImageBitmap(bitmap2)
        }

        /****** 大图 ******/
        iv_order_big.setOnClickListener {
            iv_order_big.visibility = View.GONE
        }
    }

    override fun onFinish() {
        finish()
    }

    override fun setDescription(description: String) {
        tv_order_context.text = description
    }

    override fun setName(name: String) {
        tv_order_name.text = name
    }

    override fun setTime(time: String) {
        tv_order_time.text = time
    }

    override fun setOrderNumber(time: String) {
        tv_order_number.text = "编号：$time"
    }

    override fun setOrderType(str1: String, str2: String, str3: String) {
        tv_order_type1.text = str1
        if (str2.isNotEmpty()) {
            tv_order_type2.visibility = View.VISIBLE
            tv_order_type2.text = str2
        }
        if (str3.isNotEmpty()) {
            tv_order_type3.visibility = View.VISIBLE
            tv_order_type3.text = str3
        }
    }

    override fun setReward(reward: String) {
        tv_order_price.text = reward
    }

    override fun setNextText(str: String) {
        tv_order_next.visibility = View.VISIBLE
        tv_order_next.text = str
    }

    override fun setOrderState(str: String) {
        tv_order_state.text = str
    }

    override fun setImage1(bitmap: Bitmap) {
        iv_oder_image1.visibility = View.VISIBLE
        bitmap1 = bitmap
        iv_oder_image1.setImageBitmap(bitmap)
    }

    override fun setImage2(bitmap: Bitmap) {
        iv_oder_image2.visibility = View.VISIBLE
        bitmap2 = bitmap
        iv_oder_image2.setImageBitmap(bitmap)
    }

    override fun setAllbgColor(color: Int) {
        ll_order_all.setBackgroundColor(color)
    }

    override fun setOrderStateColor(color: Int) {
        tv_order_state.setTextColor(color)
    }

    override fun setBottomSelectVisibility(visibility: Int) {
        cl_order_bottom_select.visibility = visibility
    }

    override fun setShowOrderInfo(visibility: Int, time: String, name: String) {
        tv_order_get_time.visibility = visibility
        tv_order_get_time.text = time
        tv_order_state_name.visibility = visibility
        tv_order_state_name.text = name
        v_order_state_name.visibility = visibility
        v_order_white_bg.visibility = visibility
    }

}