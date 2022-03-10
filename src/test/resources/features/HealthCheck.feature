@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  Background: Navigation to the URL
    Given User navigated to the home application url

  @t1
  Scenario: Application URL redirection
    When User redirected to another url
    Then Result Page is Displayed

  @t2
  Scenario: Application logo visibility
    Then logo is displayed

  @t3
  Scenario: Application product main category list validation
    Then product category list is displayed

  @t4
  Scenario: Application Search functionality
    When User Search for product "T-shirt"
    Then Search Result page is displayed

  @t5
  Scenario: Application social media handles validation
    When User click on the twitter link at footer section of a page
    Then User navigated to twitter page
    And Details of user account displayed
