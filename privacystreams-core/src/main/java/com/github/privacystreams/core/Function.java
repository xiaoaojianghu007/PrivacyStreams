package com.github.privacystreams.core;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuanchun on 14/12/2016.
 * A Function convert a input in type Tin to a output in type Tout
 */

public abstract class Function<Tin, Tout> {

    private Set<String> requiredPermissions;
    private List<Object> parameters;

    public final Set<String> getRequiredPermissions() {
        return this.requiredPermissions;
    }

    protected final String getOperator() {
        return this.getClass().getSimpleName();
    }

    protected final List<Object> getParameters() {
        return this.parameters;
    }

    protected void addParameters(Object... parameters) {
        for (Object parameter : parameters) {
            this.parameters.add(parameter);
            if (parameter instanceof Function<?,?>) {
                this.requiredPermissions.addAll(((Function<?,?>) parameter).getRequiredPermissions());
            }
        }
    }

    protected void addRequiredPermissions(String... permissions) {
        this.requiredPermissions.addAll(Arrays.asList(permissions));
    }

    /**
     * Each Function must initialize requiredPermissions field in constructor.
     */
    public Function() {
        this.requiredPermissions = new HashSet<>();
    }

    /**
     * Apply this function
     * @param uqi the instance of UQI
     * @param input the function input
     * @return the function output
     */
    public abstract Tout apply(UQI uqi, Tin input);

    /**
     * Compound this function with another function
     * @param function another function
     * @param <Ttemp> the intermediate variable type between two functions
     * @return the compound function
     */
    public <Ttemp> Function<Tin, Ttemp> compound(Function<Tout, Ttemp> function) {
        return new CompoundFunction<>(this, function);
    }

    public String toString() {
        return this.getOperator() + this.getParameters();
    }

}