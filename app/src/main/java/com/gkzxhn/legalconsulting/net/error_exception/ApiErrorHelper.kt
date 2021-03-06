package com.gkzxhn.legalconsulting.net.error_exception

import android.content.Context
import android.content.Intent
import android.util.Log
import com.gkzxhn.legalconsulting.activity.LoginActivity
import com.gkzxhn.legalconsulting.common.App
import com.gkzxhn.legalconsulting.common.Constants
import com.gkzxhn.legalconsulting.utils.TsClickDialog
import com.gkzxhn.legalconsulting.utils.TsDialog
import com.gkzxhn.legalconsulting.utils.showToast
import kotlinx.android.synthetic.main.dialog_ts.*
import retrofit2.adapter.rxjava.HttpException
import java.io.IOException
import java.net.ConnectException


/**
 * Explanation:
 * @author LSX
 *    -----2018/9/6
 */
object ApiErrorHelper {

    fun handleCommonError(context: Context, e: Throwable) {
        print("网络异常：" + e::javaClass)

        when (e) {
            is ConnectException -> context.TsDialog("没有网络，请检查你的网络", false)
            is HttpException -> {
                if (e.code() == 401) {
                    context.TsClickDialog("token已过期", false).dialog_save.setOnClickListener {
                        App.EDIT.putString(Constants.SP_TOKEN, "")?.commit()
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        context.startActivity(intent)
                    }
                } else {
                    context.TsDialog("网络异常请重试", false)

                }

            }
            is IOException -> context.TsDialog("数据加载失败，请检查您的网络", false)
        //后台返回的message
            is ApiException -> {
                context.TsDialog(e.message!!, false)
//                context.showToast(e.message!!)
                Log.e("ApiErrorHelper", e.message, e)
            }
            else -> {
//                context.TsDialog(e.message!!,false)
                context.showToast("数据异常")
                Log.e("ApiErrorHelper", e.message, e)
            }
        }
        //App.mContext?.showToast(e.message!!)
    }
}
