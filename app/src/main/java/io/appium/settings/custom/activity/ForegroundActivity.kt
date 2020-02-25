package io.appium.settings.custom.activity

import android.os.Build
import android.os.Bundle
import io.appium.settings.ForegroundService
import io.appium.settings.custom.netty.SocketServer

/**
 * 启动前台service
 */
class ForegroundActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SocketServer.setServerIp(intent)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startService(ForegroundService.getForegroundServiceIntent(instance))
//        }

        window.decorView.postDelayed(
            { finish() }
        , 1000)
    }

    override fun getLayoutId(): Int {
        return 0
    }
}
