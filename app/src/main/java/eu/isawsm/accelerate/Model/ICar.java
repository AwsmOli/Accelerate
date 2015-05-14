package eu.isawsm.accelerate.Model;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by ofade_000 on 04.04.2015.
 */
public interface ICar {
    IModel getModel();

    void setModel(IModel IModel);

    IClazz getClazz();

    void setClazz(IClazz IClazz);

    long getTransponderID();

    void setTransponderID(long transponderID);

    Bitmap getPicture();

    void setPicture(Bitmap picture);

    double getAvgTime(ICourse course);

    double getBestTime(ICourse course);

    int getLapCount(ICourse course);

    double getConsistency(ICourse course);

    int getRank();

    List<Lap> getLaps();

    void addLap(ILap ILap);

    String getName();
}
