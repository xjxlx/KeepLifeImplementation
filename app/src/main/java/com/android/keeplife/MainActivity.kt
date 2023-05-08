package com.android.keeplife

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.android.apphelper2.utils.permission.PermissionMultipleCallBackListener
import com.android.apphelper2.utils.permission.PermissionUtil
import com.android.keeplife.account.AccountHelper
import com.android.keeplife.account.KeepNotification
import com.android.keeplife.account.LifecycleManager
import com.android.keeplife.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val permissionUtil = PermissionUtil.PermissionActivity(this)
    private val accountHelper = AccountHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = LayoutInflater.from(this)
            .inflate(R.layout.activity_main, null, false)

        mBinding = ActivityMainBinding.bind(inflater)

        setContentView(mBinding.root)

        mBinding.btnStart.setOnClickListener {
            permissionUtil.requestArray(
                arrayOf(Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_SYNC_SETTINGS, Manifest.permission.FOREGROUND_SERVICE),
                object : PermissionMultipleCallBackListener {
                    override fun onCallBack(allGranted: Boolean,
                        map: MutableMap<String, Boolean>) { //                        accountHelper.addAccount(this@MainActivity) {

                        LifecycleManager.instance.startLifecycle(this@MainActivity)

//                        SystemUtil.jumpApplicationSetting(this@MainActivity)
                    }
                })
        }

        mBinding.btnRemove.setOnClickListener {
//            accountHelper.removeAccount(this@MainActivity) {
//                ToastUtil.show(it)
//            }
        }
    }

    val keepNotification: KeepNotification by lazy {
        return@lazy KeepNotification(this)
    }
}