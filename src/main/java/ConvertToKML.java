/**
 * Program to general a KML file from GPS track data stored in a file,
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author Chanyoung Park
 */

import java.io.IOException;

public class ConvertToKML {

    public static void main(String[] args) {
        // check the number of arguments
        if (args.length < 2) {
            System.out.println("Usage: ConvertToKML <CSV file> <KML file>");
            System.exit(1);
        }

        String csvFilename = args[0];
        String kmlFilename = args[1];

        try {
            Track track = new Track();
            track.readFile(csvFilename); 
            track.writeKML(kmlFilename);
            System.out.println("Conversion to KML completed successfully.");
        } catch (IOException e) {
            System.err.println("Error while reading or writing files: " + e.getMessage());
            System.exit(2);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            System.exit(3);
        }
    }
}

