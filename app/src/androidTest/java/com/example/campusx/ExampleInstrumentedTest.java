package com.example.campusx;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented tests for the CampusX application.
 *
 * These tests run on a real device or emulator.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    // Expected application package name
    private static final String EXPECTED_PACKAGE =
            "com.example.campusx";

    @Test
    public void useAppContext() {

        // Get the target application's context
        Context appContext =
                InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Verify that the package name matches the expected value
        assertEquals(EXPECTED_PACKAGE, appContext.getPackageName());
    }

    @Test
    public void appContextIsNotNull() {

        // Get the target application's context
        Context appContext =
                InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Verify that the context exists
        assertNotNull(appContext);
    }
}
