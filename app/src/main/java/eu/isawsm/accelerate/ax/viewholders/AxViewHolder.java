package eu.isawsm.accelerate.ax.viewholders;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import eu.isawsm.accelerate.Model.Club;
import eu.isawsm.accelerate.R;
import eu.isawsm.accelerate.ax.AxAdapter;
import eu.isawsm.accelerate.ax.AxCardItem;
import eu.isawsm.accelerate.ax.MainActivity;
import eu.isawsm.accelerate.ax.Util.AxPreferences;
import eu.isawsm.accelerate.ax.Util.AxSocket;
import eu.isawsm.accelerate.ax.viewmodel.ConnectionSetup;


/**
 * Created by ofade_000 on 21.02.2015.
 */
public abstract class AxViewHolder extends AxAdapter.ViewHolder {

    public static AxAdapter axAdapter;
    public static MainActivity context;
    public static Socket socket;
    public View view;

    private SwipeRefreshLayout swipeLayout;

    public AxViewHolder(View v, AxAdapter axAdapter, MainActivity context) {
        super(v);
        view = v;
        if(AxViewHolder.axAdapter == null) AxViewHolder.axAdapter = axAdapter;
        if(AxViewHolder.context == null) AxViewHolder.context = context;

        //TODO: figure out how to clean up Socket:
//        socket.disconnect();
//        socket.off();
    }


    public static void showToast(final String s) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    public abstract void onBindViewHolder(AxAdapter.ViewHolder holder, int position);

    /**
     * Override this to get the RefreshEvent.
     */
    public void refresh(){

    }
}
