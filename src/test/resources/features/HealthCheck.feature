@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  Background: Navigation to the URL
    Given User navigated to the home application url

  @t1
  Scenario: Application URL redirection
    Then Launched url redirected to "http://automationpractice.com/index.php"

  @t2
  Scenario: Application logo visibility
    Then The Application logo is displayed
    And The application logo width is 350 & height is 99

  @t3
  Scenario Outline: Application product main category list validation
    Then Main product categories count should be 3
    And Displayed text should be "<product_name>"

    Examples: 
      | product_name |
      | WOMEN        |
      | DRESSES      |
      | T-SHIRTS     |

  @t4
  Scenario: Application Search functionality
    When User Search for product "T-shirt"
    Then The search result contains "T-shirt" as text

  @t5
  Scenario: Application social media handles validation
    When User clicks on the twitter link form footer section form landing page of the application
    Then The url opened on a new tab contains "seleniumfrmwrk"
    And The twitter account name is "Selenium Framework"
