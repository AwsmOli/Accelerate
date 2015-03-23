package eu.isawsm.accelerate.ax.viewholders;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import eu.isawsm.accelerate.R;
import eu.isawsm.accelerate.ax.AxAdapter;
import eu.isawsm.accelerate.ax.AxCardItem;
import eu.isawsm.accelerate.ax.MainActivity;
import eu.isawsm.accelerate.ax.viewmodel.ConnectionSetup;

/**
 * Created by ofade_000 on 21.02.2015.
 */
public class ConnectionViewHolder extends AxViewHolder {

    public MultiAutoCompleteTextView mAcTVServerAdress;

    public ConnectionViewHolder(View v, AxAdapter axAdapter, MainActivity context) {
        super(v, axAdapter, context);
        mAcTVServerAdress = (MultiAutoCompleteTextView) v.findViewById(R.id.etServer);
    }


    public void onBindViewHolder(AxAdapter.ViewHolder holder, int position, AxCardItem axCardItem) {

    }


    public String tryGetAddress() {
        String address = mAcTVServerAdress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            mAcTVServerAdress.setError(context.getResources().getString(R.string.invalid_address_error));
            mAcTVServerAdress.requestFocus();
            return null;
        }
        return address;
    }

}
