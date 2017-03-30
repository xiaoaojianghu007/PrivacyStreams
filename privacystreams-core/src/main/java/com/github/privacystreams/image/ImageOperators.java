package com.github.privacystreams.image;

import android.media.ExifInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.github.privacystreams.core.Function;
import com.github.privacystreams.core.Item;
import com.github.privacystreams.location.LatLng;
import com.github.privacystreams.utils.annotations.PSOperatorWrapper;

import java.util.Map;

/**
 * A helper class to access image-related operators
 */
@PSOperatorWrapper
public class ImageOperators {

    /**
     * Retrieve the EXIF of the image specified by an ImageData field.
     * The EXIF information is an instance of Android `ExifInterface` class.
     *
     * @param photoField the name of photo field
     * @return the function
     */
    public static Function<Item, ExifInterface> getExif(String photoField) {
        return new ImageExifRetriever(photoField);
    }

    /**
     * Retrieve the location information of the image specified by an ImageData field.
     * The location information is an instance of `LatLng` class.
     *
     * @param photoField the name of photo field
     * @return the function
     */
    public static Function<Item, LatLng> getLatLng(String photoField) {
        return new ImageLatLngRetriever(photoField);
    }

    /**
     * Blur the image specified by an ImageData field,
     * and return the blurred ImageData instance.
     *
     * @param photoField the name of photo field
     * @return the function
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Function<Item, ImageData> blur(String photoField) {
        return new ImageBlurFunction(photoField);
    }
}
