# GPS Tracker OOP Project

This project focuses on developing an Object-Oriented Programming (OOP) solution for handling GPS data, implemented in Java. The main tasks involve creating two classes, `Point` and `Track`, designed to represent GPS points and tracks respectively.

## Objectives

- **Implementing Classes:** Develop the `Point` and `Track` classes.
- **Demonstration Program:** Create a program demonstrating the use of these classes.

## Key Features

- **Point Class:** Represents a GPS location with attributes for longitude, latitude, elevation, and a timestamp using `ZonedDateTime`.
- **Track Class:** Manages a sequence of `Point` objects, representing a track with methods to handle file input and track statistics.

## Implementation Steps

### 1. Class Definitions

- **Point:** Initialize fields for timestamp, longitude, latitude, and elevation. Implement validation to ensure coordinates are within valid ranges and handle errors with `GPSException`.
- **Track:** Store a sequence of points, and provide functionality to add points and handle file input.

### 2. Program Functionality

- Calculate metrics like the total distance (using great-circle distance) and average speed.
- Identify the lowest and highest points based on elevation.

### 3. Demonstration Program (`TrackInfoProgram`)

- Construct a `Track` object from file data.
- Display the number of points, lowest and highest points, total distance, and average speed.
- Handle command-line inputs for specifying file paths and manage exceptions accordingly.

## Advanced Features

- **KML File Output:** Extend the `Track` class to support exporting data in KML format for visualization in mapping software.

## Development Environment

- Utilize Gradle for building and testing the application.
- Implement unit tests to ensure each method performs as expected.

## Usage

To run the demonstration program, execute the following command:
