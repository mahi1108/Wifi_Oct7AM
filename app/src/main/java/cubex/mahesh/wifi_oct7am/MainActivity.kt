package cubex.mahesh.wifi_oct7am

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wManager = applicationContext.getSystemService(
                Context.WIFI_SERVICE) as WifiManager
        var state = wManager.wifiState
        if(state == 0 || state==1)
        {
            s1.isChecked = false
        }else{
            s1.isChecked = true
        }

        s1.setOnCheckedChangeListener(
                object:CompoundButton.OnCheckedChangeListener{
                    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                        wManager.setWifiEnabled(p1)
                    }
                })

        getWifiDevices.setOnClickListener {
            var list:List<ScanResult> = wManager.scanResults
            var temp_list = mutableListOf<String>()
            for(result in list)
            {
                temp_list.add(result.SSID +"\n" + result.frequency)
            }
            var adapter = ArrayAdapter<String>(this@MainActivity,
                    android.R.layout.simple_spinner_item,temp_list)
            lview.adapter = adapter
        }

        getPairedDevices.setOnClickListener {
            var list:List<WifiConfiguration> = wManager.configuredNetworks
            var temp_list = mutableListOf<String>()
            for(result in list)
            {
                temp_list.add(result.SSID +"\n" + result.status)
            }
            var adapter = ArrayAdapter<String>(this@MainActivity,
                    android.R.layout.simple_spinner_item,temp_list)
            lview.adapter = adapter
        }
    }
}
