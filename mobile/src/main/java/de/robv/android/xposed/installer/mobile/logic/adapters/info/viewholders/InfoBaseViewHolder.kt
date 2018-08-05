package de.robv.android.xposed.installer.mobile.logic.adapters.info.viewholders

import android.support.v4.content.ContextCompat
import android.view.View
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import de.robv.android.xposed.installer.R
import de.robv.android.xposed.installer.core.models.*
import kotlinx.android.synthetic.main.list_item_tab.view.*

class InfoBaseViewHolder(view: View, private val delegate: InfoDelegate) : BaseViewHolder(view) {

    private lateinit var infoItem: InfoModel

    override fun bindData(data: Any) {
        if(data is InfoModel) {
            infoItem = data
            drawItem()
        }
    }

    private fun drawItem() {
        itemView.run {

            val red500 = ContextCompat.getColor(context, R.color.warning)
            //val red500 = Color.parseColor("#F44336")
            if(infoItem.key == resources.getString(R.string.verified_boot_active)){
                list_item_tab_key.setTextColor(red500)
            }else if(infoItem.desciption == resources.getString(R.string.verified_boot_explanation)){
                list_item_tab_description.setTextColor(red500)
            }
            list_item_tab_icon.setImageDrawable(infoItem.icon)
            list_item_tab_key.text = infoItem.key
            list_item_tab_description.text = infoItem.desciption
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(this.infoItem)
    }

    override fun onLongClick(v: View?) = false
}