# Red Alert 2 Board Game

Object Orientated Software Design Assignment 1 & 2.

## Development Environment

In order to run this we need to match these requirements:

- Any OS
- Java version **11** SDK
- Eclipse or IntelliJ (my bias says IntelliJ 😅)
- A will to live 😩👌

### Running the game

There are specific ways to start up this game depend on which IDE you use.

#### Eclipse

Make sure you do not have any other board game copies on your system.

- Pull the project from **GitHub desktop**
- Import an existing **Maven** project `Maven > Existing Maven Projects`
- Right click on the project and go to `Maven > Update Project`
- Right click on the top status menu `Run > Run Configurations...`
- Create a `Maven Build` step with the following **goals**

```$xslt
clean install exec:java
``` 

#### IntelliJ

- Pull the project from **GitHub desktop**
- Import the project from Maven
- Right click on the top status menu `Run > Edit Configurations...`
- Create a `Maven` step with the following `command line`

```$xslt
clean install exec:java
``` 