package com.example.ngers.universal_locationmanager.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.example.ngers.universal_locationmanager.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by ngers on 27.03.16.
 */
public class LocationNameService {
    private List<Address> addresses;
    private Context mContext;

    public LocationNameService(Context mContext) {
        this.mContext = mContext;
    }

    public String getLocationName(Location mLastLocation) {
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
            return getAddressName(addresses.get(0));
        } catch (NullPointerException e) {
            e.printStackTrace();
            return getErrorAddress();
        } catch (IndexOutOfBoundsException e) {
            return getErrorAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return getErrorAddress();
        }

    }

    private String getAddressName(Address address) {
        String refundableAddress = address.getAddressLine(0);
        for (int i = 1; i < address.getMaxAddressLineIndex(); i++) {
            refundableAddress = refundableAddress +  ", " + address.getAddressLine(i);
        }
        return refundableAddress;
    }

    private String getErrorAddress() {
        return mContext.getResources().getString(R.string.location_error_define);
    }
}
