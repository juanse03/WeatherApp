# WeatherApp - OpenWeatherMapAPI.
WeatherApp is a simple Android application that allows users to check the current weather
and forecast for the next 5 days. It utilizes the OpenWeatherMap API to fetch weather data.

## Features

- Display current weather information based on user's location.
- Show a 5-day forecast with weather details in a 3-hour interval.
- Bottom app bar with two items for easy navigation: Current Weather and 5-Day Forecast.

## Permissions

WeatherApp requires the following permissions:

- Internet
- Location: Used to determine the user's current location for fetching accurate weather data.

## Libraries Used

- Retrofit: For making HTTP requests to the OpenWeatherMap API.
- Hilt: For dependency injection to manage dependencies in the app.
- Gson: For parsing JSON responses from the API.
- Flow: For handling asynchronous operations.
- Accompanist-permissions: For handling location permission requests.
- Mockito: For mocking dependencies in unit tests.
- Jetpack Compose: For building the user interface.
- Coil: For asynchronous image loading and caching.

## Architecture Overview

The architecture of this project is designed to ensure a clear separation of concerns
and maintainability.
It consists of the following layers:

### Data Layer

The data layer is responsible for handling data operations such as fetching data from external
sources and caching. It contains:

- DTOs (Data Transfer Objects): Objects that represent data transferred between the application and
  the OpenWeatherMap API.
- Repository Implementations: Concrete implementations of repository interfaces responsible for
  interacting with data sources.

### Domain Layer

The domain layer contains the business logic of the application and serves as an intermediary
between the data and UI layers. It includes:

- Entities: Objects that represent business entities in the application. These entities are
  typically mapped from the DTOs received from the data layer.
- Repository Interfaces: Interfaces defining the contract for interacting with data sources. These
  interfaces are implemented by classes in the data layer.
- Use Cases: Classes that encapsulate the application's business logic and represent specific
  actions or operations.

### UI Layer

The UI layer is responsible for presenting data to the user and handling user interactions. It
includes:

- UI Models: Objects that represent the data to be displayed on the user interface. These UI models
  are typically mapped from entities in the domain layer.
- Mappers: Classes responsible for mapping entities from the domain layer to UI models.
- ViewModels: Components that manage UI-related data and state, exposing it to the UI layer.
  ViewModel instances use MutableStateFlow to emit pre-defined states for each screen, ensuring
  seamless updates to UI elements.





