# BT UI + API Automation

Selenium WebDriver (Java) + TestNG for UI tests, and RestAssured for API tests.

Targets:
- **UI**: Google Search – valid search and result navigation.
- **API**: Swagger PetStore – simple CRUD flow plus a negative check.

> Note: These tests hit public endpoints. Google may show CAPTCHA and the PetStore API can be flaky.  
> The suite is intentionally minimal and demonstrates clean design and readable tests.

---

## Tech Stack

- Java 17
- Maven
- Selenium WebDriver 4.x
- TestNG 7.x
- RestAssured 5.x
- Gson (payload building)
- WebDriverManager / Selenium Manager (driver binaries)

---

## Project Structure

automation-project
└─ src
├─ main
│ └─ java
└─ test
├─ java
│ ├─ api
│ │ └─ PetStoreApiTest.java
│ ├─ base
│ │ └─ BaseTest.java
│ ├─ pages
│ │ ├─ SearchPage.java
│ │ └─ ResultsPage.java
│ └─ tests
│ └─ SearchTest.java
└─ resources
├─ config.properties
└─ testng.xml


---

## Prerequisites

- **Java 17** installed and on PATH
- **Maven** installed
- **Google Chrome** installed
- Internet access (tests call live endpoints)

> WebDriver binaries are handled automatically by Selenium Manager / WebDriverManager.

---

## Configuration

`src/test/resources/config.properties`
```properties
base.url=https://petstore3.swagger.io/api/v3
google.url=https://www.google.com/ncr

How to Run
From IDE (Eclipse/IntelliJ)

Right‑click src/test/resources/testng.xml → Run As → TestNG Suite

From CLI (Maven)
mvn -q test

Test Design
UI: Google Search (Selenium + TestNG)

Pages

SearchPage

Locators: name=q (search box), name=btnK (button)

goToGoogle() → navigates to google.url

searchFor(value) → types value; tries clicking Search; on failure, presses ENTER

ResultsPage

Locators: #search (results container), #search a h3 (first result title)

hasResults() → checks that results container is present

clickFirstResult() → clicks first result (with a short explicit wait inside)

Tests (tests/SearchTest)

validSearch
Steps: open Google → search “Selenium” → assert results appear.
Assertion: results container #search is present.

searchResultNavigation
Steps: open Google → search “Selenium WebDriver site:selenium.dev” → click first result.
Assertion: driver.getCurrentUrl() contains selenium.dev.

Assumptions

Occasional Google consent/CAPTCHA pages may appear; for a real project we’d use a staging search page.

API: PetStore (RestAssured + TestNG)

Flow

createPet_success → POST /pet with a timestamp ID.

getPet_success → GET the same ID and expect 200.

updatePet_success → PUT update name/status, expect 200.

deletePet_success → DELETE the pet, expect 200/204.

getPet_afterDelete_notFound → GET the same ID, expect 404.

Assumptions

The public PetStore API can be intermittently inconsistent; tests keep assertions simple and linear.

Payloads are built with Gson and sent as strings for compatibility.
