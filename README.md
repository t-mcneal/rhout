# Rhout

Rhout is a web application project that finds the top 5 highest-rated music venues 
between two addresses. The application was built using Java, Spring Boot, ReactJS
and Google Maps API. 

## Table of Contents

* [Installation](https://github.com/t-mcneal/rhout/blob/main/README.md#installation)
* [Usage](https://github.com/t-mcneal/rhout/blob/main/README.md#usage)
* [Demo](https://github.com/t-mcneal/rhout/blob/main/README.md#demo)


## Installation

1. **Install Java and Node**

   If not currently installed on your computer, download and install the following:
   
   - [Java](https://adoptopenjdk.net)
   - [Node](https://nodejs.org/en/)

2. **Download Project**

   Download a zip of the Rhout project from GitHub or clone the repository.

3. **Add an API Key**
   
   An API key is needed to access Google Maps API. Visit [Google Maps Platform](https://developers.google.com/maps) 
   to set up an account and API key. Then, create an `apikey.properties` file in the 
   project's root directory. Add the text below to the file, replacing `API_KEY` with a
   Google Maps API key, and save the file. 
   
   ```
   GMAP_KEY = API_KEY
   ```

   Make sure to include the `apikey.properties` file in `.gitignore` to secure 
   your API key from version control.
   
## Usage

Next, you will need to start the local servers for the frontend and backend using the
command line. 

   **Backend**
      
   Navigate to the project's root directory and enter  `$ ./gradlew bootrun`. This step 
   starts a local sever on `http://localhost:8080/`.

   **Frontend**

   Open a second command line window. Then, navigate to the project directory `/src/main/frontend` 
   and enter the following:
   ```
   $ npm install
   $ npm start
   ```
   
   This step installs the React dependency and starts a local server on `http://localhost:3000/`. 
   The application will automatically open in a default browser.


## Demo

Click this [link](https://youtu.be/0zG4X2-0jjU) to watch a demo.

![Rhout Screenshot](https://github.com/t-mcneal/rhout/blob/main/readmeimages/rhout_screenshot.png)


## License

[MIT](https://github.com/t-mcneal/rhout/blob/main/LICENSE)






