package com.example.campusx;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented tests for the CampusX application.
 *
 * These tests run on a real device or emulator.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {

        // Get the target application's context
        Context appContext =
                InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Verify that the package name matches the expected value
        assertEquals("com.example.campusx", appContext.getPackageName());
    }
}
