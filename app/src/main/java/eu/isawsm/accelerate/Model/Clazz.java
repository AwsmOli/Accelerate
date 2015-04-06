package eu.isawsm.accelerate.Model;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Oliver on 31.01.2015.
 */
public class Clazz implements Parcelable, IClazz {
    public static final Creator<IClazz> CREATOR = new Creator<IClazz>() {
        @Override
        public IClazz createFromParcel(Parcel source) {
            IClazz retVal = new Clazz(source.readString(), source.readString());

            return retVal;
        }

        @Override
        public IClazz[] newArray(int size) {
            return new IClazz[size];
        }
    };
    private String name;
    private String description;


    public Clazz(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private Clazz(Parcel in) {
        super();
        setName(in.readString());
        setDescription(in.readString());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
        dest.writeString(getDescription());
    }
}
