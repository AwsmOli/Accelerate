package eu.isawsm.accelerate.ax;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.HashSet;

import eu.isawsm.accelerate.Model.Car;
import eu.isawsm.accelerate.Model.Club;
import eu.isawsm.accelerate.R;
import eu.isawsm.accelerate.ax.viewholders.AuthenticationViewHolder;
import eu.isawsm.accelerate.ax.viewholders.AxViewHolder;
import eu.isawsm.accelerate.ax.viewholders.CarSettingsViewHolder;
import eu.isawsm.accelerate.ax.viewholders.CarViewHolder;
import eu.isawsm.accelerate.ax.viewholders.ClubViewHolder;
import eu.isawsm.accelerate.ax.viewholders.ConnectionViewHolder;
import eu.isawsm.accelerate.ax.viewmodel.Authentication;
import eu.isawsm.accelerate.ax.viewmodel.AxDataset;
import eu.isawsm.accelerate.ax.viewmodel.CarSetup;
import eu.isawsm.accelerate.ax.viewmodel.ConnectionSetup;

public class AxAdapter extends RecyclerView.Adapter<AxViewHolder> {
    private AxDataset<AxCardItem> mDataset;

    private MainActivity context;
    private int lastPosition = -1;
    private HashSet<AxViewHolder> viewHolders;

    public AxAdapter(MainActivity context) {
        this.context = context;
        viewHolders = new HashSet<>();
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public void addCarSetup() {
        if (!mDataset.add(new AxCardItem<>(new CarSetup()))) {
            getCarSettingsViewHolder().tvManufacturer.requestFocus();
        }
    }

    public void removeAllCars() {
        ArrayList<AxViewHolder> toBeRemoved = new ArrayList<>();
        for(AxViewHolder vh : viewHolders){
            if (vh instanceof CarViewHolder) {
                mDataset.remove(vh.getPosition());
                toBeRemoved.add(vh);
            }
        }
        viewHolders.removeAll(toBeRemoved);
    }

    public void removeAll() {
        ArrayList<AxViewHolder> toBeRemoved = new ArrayList<>();
        for (AxViewHolder vh : viewHolders) {
            mDataset.remove(vh.getPosition());
            toBeRemoved.add(vh);
        }
        viewHolders.removeAll(toBeRemoved);
    }

    public AuthenticationViewHolder getAuthentificationViewHolder() {
        return getViewHolder(AuthenticationViewHolder.class);
    }

    public CarViewHolder getCarViewHolder(Car car) {
        for (AxViewHolder vh : viewHolders) {
            if (vh instanceof CarViewHolder) {
                if (((CarViewHolder) vh).car.equals(car)) {
                    return (CarViewHolder) vh;
                }
            }
        }
        return null;
    }

    public void setDataset(AxDataset<AxCardItem> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).get() instanceof Car)
            return R.layout.ax_car_cardview;
        else if (mDataset.get(position).get() instanceof ConnectionSetup)
            return R.layout.ax_connection_cardview;
        else if (mDataset.get(position).get() instanceof Club) {
            return R.layout.ax_club_cardview;
        } else if (mDataset.get(position).get() instanceof CarSetup) {
            return R.layout.ax_car_settings_cardview;
        } else if (mDataset.get(position).get() instanceof Authentication) {
            return R.layout.ax_welcome_cardview;
        } else {
            return -1;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AxViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        if (viewType == R.layout.ax_car_cardview) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ax_car_cardview, parent, false);
            CarViewHolder carViewHolder = new CarViewHolder(v, this, context);
            viewHolders.add(carViewHolder);
            return carViewHolder;

        } else if (viewType == R.layout.ax_connection_cardview) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ax_connection_cardview, parent, false);
            ConnectionViewHolder connectionViewHolder = new ConnectionViewHolder(v, this, context);
            viewHolders.add(connectionViewHolder);
            return connectionViewHolder;

        } else if (viewType == R.layout.ax_club_cardview) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ax_club_cardview, parent, false);
            ClubViewHolder clubViewHolder = new ClubViewHolder(v, this, context);
            viewHolders.add(clubViewHolder);
            return clubViewHolder;

        } else if (viewType == R.layout.ax_car_settings_cardview) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ax_car_settings_cardview, parent, false);
            CarSettingsViewHolder carSettingsViewHolder = new CarSettingsViewHolder(v, this, context);
            viewHolders.add(carSettingsViewHolder);
            return carSettingsViewHolder;

        } else if (viewType == R.layout.ax_welcome_cardview) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ax_welcome_cardview, parent, false);
            AuthenticationViewHolder authenticationViewHolder = new AuthenticationViewHolder(v, this, context);
            viewHolders.add(authenticationViewHolder);
            return authenticationViewHolder;

        } else {
            throw new RuntimeException("Cardview not supported");
        }

    }

    @Override
    public void onBindViewHolder(AxViewHolder holder, int position) {
        setAnimation(holder.mView, position);
        holder.onBindViewHolder(holder, position, mDataset.get(position));
    }

    public ConnectionViewHolder getConnectionViewHolder() {
        return getViewHolder(ConnectionViewHolder.class);
    }

    public CarSettingsViewHolder getCarSettingsViewHolder() {
        return getViewHolder(CarSettingsViewHolder.class);
    }

    public ClubViewHolder getClubVieHolder() {
        return getViewHolder(ClubViewHolder.class);
    }

    private <T> T getViewHolder(Class<T> type) {  //TODO this is kinda awful
        for (ViewHolder v : viewHolders) {
            if (type.isInstance(v)) {
                return (T) v;
            }
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (lastPosition == -1) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        } else if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;

        }
    }
}