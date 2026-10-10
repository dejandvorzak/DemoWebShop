package com.codeTribeDemoWebShop.hooks;

import com.codeTribeDemoWebShop.config.ConfigReader;
import com.codeTribeDemoWebShop.context.ScenarioContext;
import com.codeTribeDemoWebShop.core.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private final ScenarioContext context;

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void setUp(Scenario scenario) {
        context.setScenario(scenario);
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.baseUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        } finally {
            DriverManager.quitDriver();
        }
    }
}
