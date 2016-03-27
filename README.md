# Universal-LocationManager

The decision let determine the location using googleApi or standard methods of determining. Declare
necessary implementation in the class, where you need determine the location and use the following methods.

    Shows included or not the definition of location.
        boolean isWork();
    
    Return name location or "LocationServes not specified".
      String getLocationName();
   
    Returns latitude.
       double getLat();
    
    Returns longitude.
       double getLong();
    
    Starts definition of location.
       void onStart();
    
    Turning off location determination.
       void onStop();
