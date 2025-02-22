package com.mycompany.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Headers;

import java.util.Map;
import java.util.Optional;

public class CDPHeaderUtil {

    public static void applyNgrokHeader(WebDriver driver) {
//        if (!(driver instanceof ChromeDriver)) {
//            throw new IllegalArgumentException("Error: El WebDriver debe ser una instancia de ChromeDriver.");
//        }
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setExtraHTTPHeaders(new Headers(Map.of("ngrok-skip-browser-warning", "true"))));
    }
}
