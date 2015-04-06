package eu.isawsm.accelerate.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URI;

/**
 * Created by ofade_000 on 29.03.2015.
 */
public class AxUser extends Driver implements Parcelable {
    public static final Creator<AxUser> CREATOR = new Creator<AxUser>() {
        @Override
        public AxUser createFromParcel(Parcel source) {
            AxUser retVal = new AxUser(source.readString(), (Bitmap) source.readValue(null), (URI) source.readValue(null));
            retVal.setCars((java.util.ArrayList<Car>) source.readValue(null));
            return retVal;
        }

        @Override
        public AxUser[] newArray(int size) {
            return new AxUser[size];
        }
    };


    public AxUser(String name, Bitmap image, URI mail) {
        super(name, image, mail);
    }

    private AxUser(Parcel in) {
        super();
        setName(in.readString());
        setImage((Bitmap) in.readValue(null));
        setMail((URI) in.readValue(null));
        setCars((java.util.ArrayList<Car>) in.readValue(null));
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeValue(getImage());
        dest.writeValue(getMail());
        dest.writeList(getCars());
    }
}