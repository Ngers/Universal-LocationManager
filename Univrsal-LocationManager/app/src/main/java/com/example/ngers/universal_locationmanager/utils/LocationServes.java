package com.example.ngers.universal_locationmanager.utils;

/**
 * Created by ngers on 25.03.16.
 */
public interface LocationServes {
    /**
     * Shows included or not the definition of location.
     */
    boolean isWork();
    /**
     * Return name location or "LocationServes not specified".
     */
    String getLocationName();
    /**
     * Returns latitude.
     */
    double getLat();
    /**
     * Returns longitude.
     */
    double getLong();
    /**
     * Starts definition of location.
     */
    void onStart();
    /**
     * Turning off location determination.
     */
    void onStop();


}
