# Midtronics Android Application

This Android application was developed as part of the Midtronics Android Coding Evaluation. It consists of three main screens: Profile Creation, Country List, and Country Detail.

## Introduction

The project aims to provide users with a comprehensive Android application that allows them to create their profile, explore a list of countries, and view detailed information about selected countries.

## Features

- Profile Creation Screen:
  - Users can input their name, upload a picture, and provide a scrollable summary of their education and work experience.
- Country List Screen:
  - Displays a list of countries sourced from a predefined XML file.
- Country Detail Screen:
  - Retrieves detailed information about a selected country from the Rest Countries API, including its name, capital, population, area, region, and sub-region.

## Screenshots

Profile Creation Screen        |  Country List Screen    |     Country Detail Screen
:-------------------------: | :-------------------------: | :-------------------------:
![1](https://github.com/Samkit02/Mosaic_Assignment/assets/45647688/245082c6-1e3f-44b4-81cf-40554558fdba)   |  ![2](https://github.com/Samkit02/Mosaic_Assignment/assets/45647688/ef7e4c3a-8e1d-4b70-9b1a-b91516c86c43)   |  ![3](https://github.com/Samkit02/Mosaic_Assignment/assets/45647688/92f92ff5-6b5b-4d67-a645-0efb9af43303)

## Technologies Used

- Kotlin
- Android SDK
- Retrofit for making HTTP requests
- Gson for JSON parsing
- Glide for image loading
- XML for defining layouts

## Setup

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an Android emulator or physical device.

## Usage

1. On the Profile Creation screen, input your name, upload a picture, and provide a summary of your education and work experience.
2. Navigate to the Country List screen to explore a list of countries.
3. Select a country from the list to view detailed information on the Country Detail screen.

## API Reference

- The project uses the Rest Countries API for retrieving detailed country information. API documentation can be found [here](https://restcountries.com/#api-endpoints-v3-all).

## Contributing

Contributions to the project are welcome! If you'd like to contribute, please follow these guidelines:
- Fork the repository.
- Create a new branch for your feature or bug fix.
- Commit your changes and push your branch to GitHub.
- Submit a pull request with a detailed description of your changes.

## License

This project is licensed under the [MIT License](LICENSE).
