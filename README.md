# Vehicle-Insurance-Management-System
Visit Vehicle-Insurance-Management-System at :- http://apram-vims.herokuapp.com/home

Vehicle Insurance Management System (VIMS) is a web application built using springboot and SpringMVC is completely based on MVC design pattern.

MySQL database is used to save the data for application.

SpringDataJPA is used for interaction between database and application and perform various operations like adding deleting and fetching data from datsbase.

Hibernate is used for object relational mapping ie.. mapping objects to tables in database.

Views of application are dynamically preared using JSP and JSTL along with HTML, CSS, JS.

Working of VIMS:-

This web-app provides automation for the operations in a vehicle insurance company.

This application haves 4 types of user :- 

1. Customer/Simple user - The person who wants to apply for an insurance in the company, can register as a customer and apply for insurance and give feedback after approval of his application. Customer can also raise a ticket for help which will be resolved by admin. Customer can also renew the existing insurance policy.

2. Policy Admin (PA) - Policy admin is responsilble for maintaining the policies offered byt he company, she can add or remove the policies and can make changes in existing policy. The other role of Policy Admin is to review the customer's application for insurance and to approve or reject it based on the verification.

3. Field Officer (FO) - Field officer is responsible for ground verification of vehicle and then approve or reject the application from customer.

4. Admin - He/She is the administrator of the application, for registering as a policy admin or field officer approval from admin is required, before being able to login. Any new application from customer first reaches admin which is then assigned to one of the policy admins listed, after approval of appliation from policy admin    application again reaches admin and is then assigned to a field officer. Admin is responsible to provide a resolution for the help ticket raised by the customers.

Application Flow :- 

The application for insurance of a vehicle from a customer with all the details first reaches the Admin.

Admin can view all the details of the application in his dashboard, and then assign this application to a policy admin from his policy admin list.

The application then reaches the policy admin, PA reviews the application and approves/ rejects the application based on his observation.

If the application is approved/rejected by PA it comes under validated poicies/rejected policies listin admin dashboard repectively. The validated policy then needs to be verified on basis of ground reports, for this task application is assigned to field officer for field verfication.

The field officer then reviews the application and verifies the vehicle and provides the final approval for the application.

After approval from FO the application comes under approved policies in admin dashboard and status of policy is changed to approve in user dashboard which was pending.

The customer can give feedback after approval or rejection of his application.

