#Auto Testerino
#_____________________________________________________________________________________________________________________________
Selenium & Java based frontend automation suite utilising the BDD methodologies of Cucumber and Gherkin 

1. Install JAVA SDK 8
2. Install Maven
3. Install Git
4. Install Intellji
5. Download chromedriver 2.2 https://sites.google.com/a/chromium.org/chromedriver/downloads

#Set Path Variables

User Variable Path = directory for java sdk 8 
Set Maven home in environment Variables 

#For Windows
Create the following System variables
- JAVA_HOME = PAth to java sdk
- M2_HOME = Path to maven installation
- MAVEN_HOME = Path to maven installation

#For Windows Edit Path System variable 
- Add %M2_HOME%\bin 
- Add directory to your chromedriver.exe

To run the project navigate to the folder repo and type 
```
mvn clean install
```
# 