import java.io.IOException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Chanyoung Park
 */
public class TrackInfo {
  public static void main(String[] args) {
    // TODO: Implementation TrackInfo application here
     if (args.length < 1) {
      System.out.println("No filename provided.");
      System.exit(0);
    }

    try {
      String filename = args[0];
      Track track = new Track(filename);
      
      System.out.println(track.size() + " points in track");
      System.out.println("Lowest point is " + track.lowestPoint());
      System.out.println("Highest point is " + track.highestPoint());
      System.out.printf("Total distance = %.3f km\n", track.totalDistance() / 1000);
      System.out.printf("Average speed = %.3f m/s\n", track.averageSpeed());
      
    } catch (IOException e) {
      System.err.println("Error reading from file: " + e.getMessage());
      System.exit(1);
    } catch (GPSException e) {
      System.err.println("Error processing GPS data: " + e.getMessage());
      System.exit(1);
    }
  }
}
