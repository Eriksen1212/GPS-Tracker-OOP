/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Chanyoung Park
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Track {
  // TODO: Create a stub for the constructor

  private ArrayList<Point> points;

    // If you need a no-argument constructor for tests or other uses, define it here
    public Track() {
        points = new ArrayList<>();
    }

    // If this constructor is used, it must be visible in the test context
    public Track(String filename) throws IOException {
        this(); // Call the no-argument constructor to initialize the list
        readFile(filename); // Assuming readFile should be public to be used in tests
    }

  // TODO: Create a stub for readFile()
 
  public void readFile(String filename) throws IOException {
    this.points.clear();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    Scanner scanner = new Scanner(Path.of(filename));
    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        // Pass the first line
        if (line.startsWith("Time")) {
            continue;
        }
        try {
            String[] values = line.split(",");
            if (values.length != 4) {
                throw new GPSException("Incorrect number of values to create a Point");
            }
            ZonedDateTime timestamp = ZonedDateTime.parse(values[0], formatter);
            double longitude = Double.parseDouble(values[1]);
            double latitude = Double.parseDouble(values[2]);
            double elevation = Double.parseDouble(values[3]);
            points.add(new Point(timestamp, longitude, latitude, elevation));
        } catch (DateTimeParseException e) {
            throw new GPSException("Timestamp could not be parsed: " + e.getMessage());
        }
    }
    scanner.close();
  }

  // TODO: Create a stub for add()
  public void add(Point point) {
    points.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index < 0 || index >= points.size()) {
        throw new GPSException("Index out of bounds");
    }
    return points.get(index);
  }

  // TODO: Create a stub for size()
  public int size() {
    return points.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() {
      if (points.size() < 1) throw new GPSException("Not enough points to determine the lowest point.");
      return Collections.min(points, Comparator.comparingDouble(Point::getElevation));
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {
    if (points.size() < 1) throw new GPSException("Not enough points to determine the highest point.");
    return Collections.max(points, Comparator.comparingDouble(Point::getElevation));
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    if (points.size() < 2) throw new GPSException("Not enough points to calculate total distance.");
    double distance = 0.0;
    for (int i = 0; i < points.size() - 1; i++) {
        distance += Point.greatCircleDistance(points.get(i), points.get(i + 1));
    }
    return distance;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    if (points.size() < 2) throw new GPSException("Not enough points to calculate average speed.");
    Point firstPoint = points.get(0);
    Point lastPoint = points.get(points.size() - 1);
    double distance = totalDistance();
    long seconds = ChronoUnit.SECONDS.between(firstPoint.getTime(), lastPoint.getTime());
    return distance / seconds;
  }

  public void writeKML(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            // write the first part of the KML file
            out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            out.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
            out.println("<Document>");
            out.println("<name>Track</name>");

            // add a placemark for each point in the track
            for (Point point : this.points) {
                out.println("<Placemark>");
                out.println("<Point>");
                out.println("<coordinates>" + point.getLongitude() + "," + point.getLatitude() + "," + point.getElevation() + "</coordinates>");
                out.println("</Point>");
                out.println("</Placemark>");
            }

            // write the last part of the KML file
            out.println("</Document>");
            out.println("</kml>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}