package eu.isawsm.accelerate.ax.viewholders;

import android.view.View;

import com.facebook.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import eu.isawsm.accelerate.R;
import eu.isawsm.accelerate.ax.AxAdapter;
import eu.isawsm.accelerate.ax.AxCardItem;
import eu.isawsm.accelerate.ax.MainActivity;

/**
 * Created by olfad on 19.03.2015.
 */
public class AuthentificationViewHolder extends AxViewHolder {

    SignInButton googleButton;
    LoginButton facebookButton;

    public AuthentificationViewHolder(View v, AxAdapter axAdapter, MainActivity context) {
        super(v, axAdapter, context);
    }

    public void onBindViewHolder(AxAdapter.ViewHolder holder, int position, AxCardItem axCardItem) {
        googleButton = (SignInButton) mView.findViewById(R.id.sign_in_button_g);
        facebookButton = (LoginButton) mView.findViewById(R.id.sign_in_button_fb);

    }
}