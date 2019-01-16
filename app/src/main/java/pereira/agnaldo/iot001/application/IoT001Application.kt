package pereira.agnaldo.iot001.application

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import org.androidannotations.annotations.EApplication

@EApplication
open class IoT001Application : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base);

    }

}