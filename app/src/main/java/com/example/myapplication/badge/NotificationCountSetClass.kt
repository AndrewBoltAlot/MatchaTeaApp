package com.example.myapplication.badge

import android.app.Activity
import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.view.MenuItem

class NotificationCountSetClass : Activity(){
    companion object{
        private var icon: LayerDrawable? = null

        fun setAddtoCart(context: Context, item: MenuItem, numMessages: Int){
            icon = item.icon as LayerDrawable
            SetNotificationCount.setBadgeCount(context, icon!!, NotificationCountSetClass.setNotifyCount(numMessages))
        }

        fun setNotifyCount(numMessages: Int): Int{
            return numMessages
        }
    }
}