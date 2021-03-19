package com.stackanswer.idlingresource;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource2 {
    private static final String RESOURCE = "GLOBAL2";
    private static final CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource(RESOURCE);
    public static void increment() {
        espressoTestIdlingResource.increment();
    }
    public static void decrement() {
        espressoTestIdlingResource.decrement();
    }
    public static IdlingResource getEspressoIdlingResource() {
        return espressoTestIdlingResource;
    }
}