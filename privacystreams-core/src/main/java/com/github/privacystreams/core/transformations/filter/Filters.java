package com.github.privacystreams.core.transformations.filter;

import com.github.privacystreams.core.Function;
import com.github.privacystreams.core.Item;
import com.github.privacystreams.core.MultiItemStream;
import com.github.privacystreams.core.transformations.M2MTransformation;

/**
 * Created by yuanchun on 30/12/2016.
 * A helper class to access filter functions
 */

public class Filters {
    /**
     * A function that keeps all items that satisfies a predicate.
     * @param predicate the predicate to check for each item
     * @return the filter function
     */
    public static M2MTransformation keep(Function<Item, Boolean> predicate) {
        return new PredicateFilter(predicate);
    }
}