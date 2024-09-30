@AllFlow
Feature: UAT Tempo Juli
  User journey on tempo satu

# @TC001-004
#   Scenario: To verify if the Main Logo in the header tab is displayed and clickable
#             To verify if all links such as Fokus, Mingguan, and Tempo Plus are clickable and work as per the requirements
#             To verify if the Langganan button in the header tab works as per the requirements
#             To verify if the Masuk button in the header tab works as per the requirements
#   Given User already on homepage
#   When User click on main logo
#   Then Verify header tab links
#   Then Verify langganan button
#   Then Verify Masuk button

# @TC005
# Scenario: user register and login
#   Given User already on homepage
#   When User click to login page
#   Then User already on login page
#   When User input unregistered email and continue
#   And User input birthday date
#   And User input password and confirm password
#   And User checklist agreement and register
# #  Then User already on logged in homepage

# @TC007
# Scenario: user login with email
#  Given User already on homepage
#  When User click to login page
#  Then User already on login page
#  When User input registered "keianggoro12@gmail.com" as email and continue
#  And User input "_Dwi51143633" as password
#  And  User verified captcha and login
#  Then User already on logged in homepage

# @TC008
#   Scenario: user login with invalid email
#   Given User already on homepage
#   When User click to login page
#   Then User already on login page
#   When User input invalid email and continue
#   Then User verify message email is invalid

# @TC011
# Scenario: user logout flow
#  Then User already on logged in homepage
#  And User go to Akun sidebar
#  And User click on logout button
#  And User already on homepage

# @TC023&027
# Scenario: To verify if the Menu tab is clickable and works as per the requirements
#           To verify if the X button in the Menu works as per the requirements
#   Given User already on homepage
#    When User click on menu
#   And User click x button menu

# @TC024 - not yet created
# Scenario: To verify if the "Cari artikel disini" search bar in the Menu works as per the requirements
#   Given User already on homepage 
#   When User click on menu
#   And User search "Kopi" keywords
#   Then User verify the displayed articles and hashtags related to the entered keywords

# @TC025&026 - not yet created
# Scenario: To verify if the category dropdown in the Menu works as per the requirements
#           To verify if the category text in the Menu is clickable
#   Given User already on homepage 
#   When User click on menu
#   And User click on dropdown

  @TC032-035
  Scenario: To verify if Title and Sub-title Article is show same as requirement
            To verify if post date Article is show same as requirement
            To verify article image display
            To verify article content display
    Given User already on homepage
    When User clicked random article
    Then User verify Title and Sub-title and post date Article
    Then User verifies the article content and image
