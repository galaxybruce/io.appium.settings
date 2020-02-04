package io.appium.settings.custom.activity

import android.os.Bundle
import android.widget.Toast
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.thanosfisherman.wifiutils.WifiUtils
import io.appium.settings.R
import kotlinx.android.synthetic.main.activity_switch_wifi.*

class SwitchWifiActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tv_ok.setOnClickListener {
            requestPermission()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_switch_wifi
    }

    fun startSwitchWifi() {
        val ssid: String = edit_ssid.text.toString().trim()
        val pwd: String = edit_pwd.text.toString().trim()
        if (ssid.isNotEmpty() && pwd.isNotEmpty()) {
            WifiUtils.withContext(applicationContext)
                .connectWith(ssid, pwd)
                .onConnectionResult { isSuccess ->
                    Toast.makeText(
                        instance,
                        if (isSuccess) "连接成功" else "连接失败",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .start()
        }
    }

    fun requestPermission() {
        PermissionUtils.permission(PermissionConstants.LOCATION)
            .rationale {}
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                   startSwitchWifi()
                }

                override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {

                }
            }).request()
    }


}
