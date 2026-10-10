# Demo Web Shop Test Automation

UI test automation for https://demowebshop.tricentis.com/ covering product search, product details, category pagination and sorting.

**Stack:** Java 17, Maven, Selenium 4, Cucumber 7, TestNG, WebDriverManager, Allure.

## Prerequisites

- JDK 17
- Maven 3.6+
- Chrome, Firefox or Edge installed (driver is downloaded automatically)

## Run the tests

```
mvn clean test
```

Run only a subset by tag:

```
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@TS-04"
```

## Configuration

`src/test/resources/config.properties`

| Property | Description |
|---|---|
| `baseUrl` | Application URL |
| `browser` | `chrome`, `firefox` or `edge` |
| `headless` | `true` or `false` |
| `explicitWaitSeconds` | Timeout for explicit waits |

## Reports

- Allure report: run the tests, then open the report in the browser (no Allure installation needed, Maven downloads it):

```
mvn clean test
mvn allure:serve
```

- Cucumber HTML report: `target/cucumber-report.html` (opens in any browser)

A screenshot is attached to the report when a scenario fails. Scenarios affected by a known defect have "(if failed: Known defect <id>)" in their name.

## Project structure

```
src/main/java/com/codeTribeDemoWebShop
  config/        ConfigReader
  core/          DriverFactory, DriverManager (one driver per thread)
  pages/         Page objects (BasePage and one class per page)
    components/  Reusable page parts (Header, ProductGrid)
src/test/java/com/codeTribeDemoWebShop
  stepdefinitions/  Cucumber steps
  hooks/            Driver setup and teardown, screenshot on failure
  context/          State shared between steps of one scenario
  runners/          TestNG Cucumber runner (parallel per scenario)
src/test/resources
  features/      Gherkin scenarios (search, product details, category, sorting)
  config.properties
```

## Test scenarios

Scenarios are tagged `@TS-01` to `@TS-10` and map to the Test Plan. `@smoke` marks the core subset.

