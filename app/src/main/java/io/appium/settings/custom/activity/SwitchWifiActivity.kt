package io.appium.settings.custom.activity

import android.Manifest
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.thanosfisherman.wifiutils.WifiUtils
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener
import io.appium.settings.R

import kotlinx.android.synthetic.main.activity_switch_wifi.*

class SwitchWifiActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tv_ok.setOnClickListener { view ->
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
            .rationale { shouldRequest ->

            }
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(permissionsGranted: List<String>) {
                   startSwitchWifi()
                }

                override fun onDenied(permissionsDeniedForever: List<String>, permissionsDenied: List<String>) {

                }
            }).request()
    }


}
