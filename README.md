# EasyShop Version 2 Backend
##### Shankar Magar previously @mango_mentor as Software engineer intern  | Google- Software Engineering Fellowship Program (G-SWEP) X Basta | AI/ML fellow @ Cornell Tech | codePath student (iOS development and android development) 
Linkedin link
https://www.linkedin.com/in/shankar-magar-725b671b7/
### Introduction
- Welcome to the GitHub repository for EasyShop's Version 2 backend development! This repository contains the backend code for the EasyShop e-commerce platform, developed using Spring Boot and MySQL. Our aim is to enhance the existing online store with new features and bug fixes to provide a seamless shopping experience.

![ezgif.com-video-to-gif-converter-4](https://hackmd.io/_uploads/SkZx66r_T.gif)



### Get Started
#### Setup and Installation
##### Prerequisites:

- Java Development Kit (JDK)
- MySQL Server and Workbench
- git
- Postman (for API testing)



##### Cloning the Repository:
- Open Terminal and navigate to directory where you want to clone
- Copy and paste following

```
git clone https://github.com/Shankarmagar/EasyShop.git
```



##### Database Setup:

- Open MySQL Workbench.
- Run the create_database.sql script to set up the EasyShop database.

##### Environment Variables:
- Navigate to project and then do following to navigate to application.properties
```
src/main/resources/application.properties
```
- edit based on your local
```
datasource.url=jdbc:mysql://localhost:3306/<your database name>
datasource.username= <Your Username>
datasource.password=<Your Password>
```



### Running the Application:

- Navigate to the project directory
```
cd src/main/java/org.yearup/EasyshopApplication
```
- run EasyshopApplication



#### Run the frontend
- Navigate to capstone-web-application 
- navigate to index.html
- click on browser icon to run the front end

#### Postman setup
- Click on file and click on import and select the postman colllections
- click on easyshop and set the environment variables for {{baseurl}}, {{usertoken}}, {{admintoken}} and {{newCategoryId}}.
```
{{baseurl}} -> http://localhost:8080
{{usertoken}} -> <you will get once register api is called>
{{admintoken}} -> eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOIiwiZXhwIjoxNzA0MzE0NzA2fQ.ajc0jILxYKSt4_LgWgtyLykXXRIdVzG9MT0rHeT6t0FhThDDOSPr4Wmq2GWwM013dDM8d_fn2azH9WmD5nOY5g
{{newCategoryId}} -> 4
```



## Features
- User registration and login.
- Product display by category.
- Product search and filtering.
- Adding , deleting and updating category
- Adding , updating and deleting Product
- Getting Profile
- Updating Profile



## Bug Fixes
- Search/filter functionality bug.
- Product duplication issue.
- Testing


We heavily rely on Postman for testing our API endpoints. The repository includes a collection of Postman scripts for various API functionalities.

##### API Endpoints

```
{{baseurl}} -> http://localhost:8080
```

| Request      | API Endpoints                                      | Explain                                     |
| ------------ | -------------------------------------------------- | ------------------------------------------- |
| ```POST```   | {{baseUrl}}/register                               | register new user                           |
| ```POST```   | {{baseUrl}}/login                                  | Login user                                  |
| ```GET ```   | {{baseurl}}/categories                             | get all categories                          |
| ```POST```   | {{baseurl}}/categories                             | Add categories                              |
| ```GET ```   | {{baseurl}}/categories/{id}                        | get particular categories                   |
| ```PUT ```   | {{baseurl}}/categories/{id}                        | Update categories                           |
| ```DELETE``` | {{baseurl}}/categories/{id}                        | Delete particular categories                |
| ```GET ```   | {{baseurl}}/profile                                | get profile details                         |
| ```PUT ```   | {{baseurl}}/profile                                | edit profile                                |
| ```GET ```   | {{baseurl}}/cart                                   | get all the items in cart                   |
| ```DELETE``` | {{baseurl}}/cart                                   |   clear the cart                              |
| ```GET ```   | {{baseUrl}}/products?cat=1&minPrice=80&maxPrice=90 | Search the products based on user selection |


##### Project Structure
- src/main/java - Contains the source code.
- src/test/java - Contains the unit tests.
- database - Contains the SQL script for database setup.



##### Contributions
- This project is part of a capstone solo project. Contributions are not currently accepted, but feedback and suggestions are always welcome.





##### Snapshot of ER diagram
<img src="https://github.com/Shankarmagar/EasyShop/blob/main/screenshots/Screenshot%202024-01-05%20at%2011.00.55%E2%80%AFAM.png" width= 1000px height= 600px>

##### Snapshot of web application
<img src="https://github.com/Shankarmagar/EasyShop/blob/main/screenshots/Screenshot%202024-01-05%20at%201.27.07%E2%80%AFAM.png" width= 1000px height= 600px>

<img src="https://github.com/Shankarmagar/EasyShop/blob/main/screenshots/Screenshot%202024-01-05%20at%2010.59.22%E2%80%AFAM.png" width= 1000px height= 600px>

##### Snapshot of Testing in Postman 
- Phase 1 and 2
<img src= "https://github.com/Shankarmagar/EasyShop/blob/main/screenshots/Screenshot%202024-01-05%20at%201.32.18%E2%80%AFAM.png" width= 1000px height= 600px>

- Phase 3 and 4
<img src = "https://github.com/Shankarmagar/EasyShop/blob/main/screenshots/Screenshot%202024-01-05%20at%201.34.24%E2%80%AFAM.png" width= 1000px height= 600px>




### Interesting Code Snippet
```
ShoppingCartItem itemInCart = new ShoppingCartItem();
                Product p = productDao.getById(row.getInt("product_id"));
                int quantity = row.getInt("quantity");
                itemInCart.setQuantity(quantity);
                itemInCart.setProduct(p);

                MyCart.add(itemInCart);
                return MyCart;
```

### Future Implementation

- Fixing bugs and completing phase 5
- Adding AI to provide recommendation to user and high discount products
- Adding payment features in user ends
- Adding saving income in admin ends
- Home delivery features and shipping tracking features