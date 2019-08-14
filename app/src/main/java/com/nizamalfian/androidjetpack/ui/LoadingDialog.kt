package com.nizamalfian.androidjetpack.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.nizamalfian.androidjetpack.R

/**
 * Created by nizamalfian on 20/07/2019.
 */
class LoadingDialog:DialogFragment() {
    companion object{
        @LayoutRes private const val layout=R.layout.dialog_loading
        fun getInstance():LoadingDialog= LoadingDialog()
        fun show(activity:AppCompatActivity,dialog:LoadingDialog = getInstance()){
            dialog.show(activity.supportFragmentManager,layout.toString())
        }
        fun dismiss(dialog:LoadingDialog){
            dialog.dismiss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout,container,false)
    }

    override fun setupDialog(dialog: Dialog?, style: Int) {
        dialog?.apply{
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setCanceledOnTouchOutside(false)
            setCancelable(false)
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
            window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}