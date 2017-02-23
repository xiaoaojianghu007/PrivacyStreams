package com.github.privacystreams.core;

import com.github.privacystreams.core.utils.Assertions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanchun on 19/12/2016.
 * A compound function combines two functions, by taking function1's output as function2's input
 */

final class CompoundFunction<Tin, Ttemp, Tout> extends Function<Tin, Tout> {
    private Function<Tin, ? extends Ttemp> function1;
    private Function<? super Ttemp, Tout> function2;

    CompoundFunction(Function<Tin, ? extends Ttemp> function1, Function<? super Ttemp, Tout> function2) {
        this.function1 = Assertions.notNull("function1", function1);
        this.function2 = Assertions.notNull("function2", function2);;
        this.addParameters(function1, function2);
    }

    @Override
    public Tout apply(UQI uqi, Tin input) {
        return function2.apply(uqi, function1.apply(uqi, input));
    }

    public String toString() {
        return function1.toString() + " --> " + function2.toString();
    }
}