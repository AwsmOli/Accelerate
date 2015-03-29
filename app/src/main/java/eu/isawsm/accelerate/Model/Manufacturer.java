package eu.isawsm.accelerate.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Oliver on 31.01.2015.
 */
public class Manufacturer implements Parcelable {
    public static final Creator<Manufacturer> CREATOR = new Creator<Manufacturer>() {
        @Override
        public Manufacturer createFromParcel(Parcel source) {
            Manufacturer retVal = new Manufacturer(source.readString(), (Bitmap) source.readValue(null));
            return retVal;
        }

        @Override
        public Manufacturer[] newArray(int size) {
            return new Manufacturer[size];
        }
    };
    private String name;
    private Bitmap image;

    public Manufacturer(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    private Manufacturer(Parcel in) {
        super();
        setName(in.readString());
        setImage((Bitmap) in.readValue(null));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
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
        dest.writeValue(image);

    }
}
