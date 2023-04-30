# restapi-sample-caller-service
<!-- PROJECT SHIELDS -->

<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/yewin-mm/restapi-sample-caller-service.svg?style=for-the-badge
[contributors-url]: https://github.com/yewin-mm/restapi-sample-caller-service/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/yewin-mm/restapi-sample-caller-service.svg?style=for-the-badge
[forks-url]: https://github.com/yewin-mm/restapi-sample-caller-service/network/members
[stars-shield]: https://img.shields.io/github/stars/yewin-mm/restapi-sample-caller-service.svg?style=for-the-badge
[stars-url]: https://github.com/yewin-mm/restapi-sample-caller-service/stargazers
[issues-shield]: https://img.shields.io/github/issues/yewin-mm/restapi-sample-caller-service.svg?style=for-the-badge
[issues-url]: https://github.com/yewin-mm/restapi-sample-caller-service/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/ye-win-1a33a292/
[product-screenshot]: images/screenshot.png


<h1 align="center">
  Overview
  <img src="https://github.com/yewin-mm/restapi-sample-caller-service/blob/master/github/template/images/overview/restapi_sample_caller_service.png" /><br/>
</h1>

# restapi-sample-caller-service
* This is the sample spring boot java project for calling many types of API endpoints.
* This project is communicated with Rest API receiver service that I already uploaded in here [RestAPI Sample Receiver Service](https://github.com/yewin-mm/restapi-sample-receiver-service).

<!-- TABLE OF CONTENTS -->
## Table of Contents
- [About The Project](#about-the-project)
    - [Built With](#built-with)
- [Getting Started](#getting-started)
    - [Before you begin](#before-you-begin)
    - [Clone Project](#clone-project)
    - [Prerequisites](#prerequisites)
    - [Instruction](#instruction)
- [Contact Me](#contact)
- [Becoming a Sponsor](#becoming-a-sponsor)
- [Contributing](#Contributing)


<a name="about-the-project"></a>
## ⚡️About The Project
This is the rest API caller project for calling many types of API endpoints. <br>
This is demo project and so, I don't add any other logic like communication with db, etc.
So, you will see hard coded values in service class. <br>
This service will call to receiver service [RestAPI Sample Receiver Service](https://github.com/yewin-mm/restapi-sample-receiver-service),
so, you need to run that receiver service first in your machine. <br>
You can learn how to call that many types of API endpoints by using RestTemplate in this project. <br>
Please note that RestTemplate will deprecate after spring version 5. <br>
So, the new API calling (Http Client) component is introduced by Spring, and it's call WebClient. <br>
You need to use that in future version of Spring Framework, and you can learn more about WebClient in google. <br>
I will add calling APIs by using WebClient in this project later. <br>
You can learn how to call many types of API endpoints by using RestTemplate in this project like below,
1. Calling APIs which need to add Request as APIs requirements
2. Calling APIs which need to get (catch) Response data from APIs responses
3. Calling APIs which need to add Request and catch Response data as API requirements (this is the main point and to learn that calling APIs, you need to learn above step 1 and 2 first)
4. Calling APIs which need to handle Error Response from APIs responses (this is for handling Error Response)

<a name="built-with"></a>
### 🪓 Built With
This project is built with
* [Java](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)


<a name="getting-started"></a>
## 🔥 Getting Started
This project is built with java, maven and use `Project Lombok` as plugin.
So, please make sure all are installed in your machine.


<a name="before-you-begin"></a>
### 🔔 Before you begin
If you are new in Git, Github and new in Spring Boot configuration structure, <br>
You should see basic detail instructions first in here [Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction)<br>
You need to clone that [RestAPI Sample Receiver Service](https://github.com/yewin-mm/restapi-sample-receiver-service) into your machine and need to run that service first as this project will call that service.
If you are not good enough in basic API knowledge with Java Spring Boot, you should learn below example projects first. <br>
Click below links.
* [Spring Boot Sample CRUD Application](https://github.com/yewin-mm/spring-boot-sample-crud) (for sample CRUD application)
* [Reading Values from Properties files](https://github.com/yewin-mm/reading-properties-file-values) (for reading values from properties files)



<a name="clone-project"></a>
### 🥡 Clone Project
* Clone the repo
   ```sh
   git clone https://github.com/yewin-mm/restapi-sample-caller-service.git

<a name="prerequisites"></a>
### 🔑 Prerequisites
Prerequisites can be found in here [Spring Boot Application Instruction](https://github.com/yewin-mm/spring-boot-app-instruction).


<a name="instruction"></a>
### 📝 Instruction
* Run the project in your IDE.

* Import `restapi-sample-caller-service.postman_collection.json` file under project directory (see that file in project directory) into your installed Postman application.
    * Click in your Postman (top left corner) import -> file -> upload files -> {choose that json file} and open/import.
    * After that, you can see the folder which you import from file in your Postman.
* Now, you can 'Test' this project by calling API from Postman.
    * Open your imported folder in postman and inside this folder, you will see total of 4 folder and many APIs, you can test that all APIs by clicking `Send` button one by one and see the response.
    * For calling file upload APIs, I get that file under src/main/resources folder. Please note that this is demo hard coded file, you can request it from user or get it from file server or etc. that's depend on your logic. 
    * You can also check in your IDE log for some print out values.

* After that you can see the code and check the code which you don't know. You can learn it, and you can apply in your job or study fields.

***Have Fun and Enjoy in Learning Code***


<a name="contact"></a>
## ✉️ Contact Me
Name - Ye Win <br> LinkedIn profile -  [Ye Win](https://www.linkedin.com/in/ye-win-1a33a292/)  <br> Email Address - <a href="mailto:yewin.mmr@gmail.com?">yewin.mmr@gmail.com</a> <br> WhatsApp - [+959252656065](https://wa.me/959252656065?text=Hi) <br> Website - [My Website](https://yewin.me/)

Project Link: [Rest API Sample Caller Service](https://github.com/yewin-mm/restapi-sample-caller-service)


<a name="becoming-a-sponsor"></a>
## 🥰 Becoming a Sponsor
If you like any of my projects or if you want to support my work, please kindly consider becoming a sponsor. <br>
It gives me great motivation and I can relentlessly maintain my projects and contribute to the open-source community.

<a href="https://www.buymeacoffee.com/yewin" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" width="150" ></a>


<a name="contributing"></a>
## ⭐ Contributing
Contributions are what make the open source community such an amazing place to be learnt, inspire, and create. Any contributions you make are **greatly appreciated**.
<br>If you want to contribute....
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/yourname`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeatures'`)
4. Push to the Branch (`git push -u origin feature/yourname`)
5. Open a Pull Request