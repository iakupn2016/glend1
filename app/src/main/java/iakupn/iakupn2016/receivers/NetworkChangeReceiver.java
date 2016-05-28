package iakupn.iakupn2016.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by malfunction on 30/04/16.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            Log.d("NETWORK", "On power disconnected");
        } else if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            Log.d("NETWORK", "On network change");
        }
    }
}
