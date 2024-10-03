# Automation Testing for Satu.Tempo.co Web Application

This repository contains automated test scripts for the web application [Satu.Tempo.co](https://satu.tempo.co). The tests are designed using Selenium, Maven, Java, Cucumber, and Gherkin for behavior-driven development (BDD).

## Technologies Used

- **Selenium**: For browser automation
- **Maven**: For dependency management and build automation
- **Java**: The programming language used to write the tests
- **Cucumber**: For BDD framework integration
- **Gherkin**: For writing test scenarios in a readable format

## Prerequisites

Before you can run the tests, make sure you have the following installed on your machine:

- [Java JDK 8+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [Google Chrome](https://www.google.com/chrome/) (or any other supported browser)
- [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads) (Ensure it's compatible with your browser version)

## Project Setup

1. Clone the repository:
   \`\`\`bash
   git clone https://github.com/your-username/satu-tempo-automation.git
   \`\`\`
   
2. Navigate to the project directory:
   \`\`\`bash
   cd satu-tempo-automation
   \`\`\`

3. Install the dependencies:
   \`\`\`bash
   mvn clean install
   \`\`\`

4. Make sure your ChromeDriver path is set in your system environment or modify the WebDriver configuration in the project if needed.

## Running Tests

Some test cases are disabled to avoid CAPTCHA issues from Cloudflare during repeated test execution. To enable all test cases:

1. Open the following file:
   \`\`\`
   src/test/resources/features/SatuTempoLogin.feature
   \`\`\`

2. Uncomment the test cases by removing the \`#\` at the beginning of each scenario. This will enable the full suite of tests.

   Example:
   \`\`\`gherkin
   ```  # Scenario: To verify if Title and Sub-title Article is show same as requirement
     #           To verify if post date Article is show same as requirement
     #           To verify article image display
     #           To verify article content display
     #   Given User already on homepage
     #   When User clicked random article
     #   Then User verify Title and Sub-title and post date Article
     #   Then User verifies the article content and image
   ```
   \`\`\`

   Change to:

   \`\`\`gherkin
  Scenario: To verify if Title and Sub-title Article is show same as requirement
            To verify if post date Article is show same as requirement
            To verify article image display
            To verify article content display
    Given User already on homepage
    When User clicked random article
    Then User verify Title and Sub-title and post date Article
    Then User verifies the article content and image
   \`\`\`

### Note

The reason for disabling certain scenarios is that running all test cases in sequence may trigger Cloudflare's security verification (CAPTCHA) on Satu.Tempo.co after the 4th or 5th scenario. This issue is due to the automated bot-like activity being detected by Cloudflare. To avoid this, only some test cases are enabled by default.

## Running the Tests

Once the necessary test cases are enabled, you can run the test suite using Maven:

\`\`\`bash
mvn clean test
\`\`\`

## Author

This project was created by **Juliardi Dwi Anggoro**.

- Email: keianggoro12@gmail.com
- Instagram: [@keianggoro12](https://instagram.com/keianggoro12)

Thank you for your interest in this automation testing project. Your contributions and feedback are greatly appreciated!
