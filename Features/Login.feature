Feature: Login 
 
 
Scenario: Successful Login with Valid Credentials 
	Given  Open chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/admin" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login 
	Then Page Title should be "Dashboard / nopCommerce administration" 
	When User click on Log out link 
	Then After logout Page Title should be "Just a moment..." 
	And close browser 


