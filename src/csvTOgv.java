import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class csvTOgv {
  public static void main(String[] args) throws Exception {

    // Enter your output GV file name here.
    String gvFile = "RoadNetwork";

    String end = "graph " + gvFile + "{ \n\n";
    try {

      // Enter your source CSV file name here
      String csvFile = "roadtrip";

      BufferedReader br = new BufferedReader(new FileReader("src\\" + csvFile + ".csv"));
      for (String line = br.readLine(); line != null; line = br.readLine()) {

        // change these 3 values to mirror your csv's title values. This line skips the
        // first line containing the titles.
        if (!line.equals("origin,destination,distance")) {

          int temp = line.indexOf("\",\"");
          String origin = line.substring(1, temp);
          int temp2 = line.indexOf("\",", temp + 3);
          String destination = line.substring(temp + 3, temp2);
          String distance = line.substring(temp2 + 2);
          String text = "    " + origin + " -- " + destination + "[distance=" + distance + "];\n";
          end = end + text;
          br.close();
        }
      }

      end = end + "\n}";
      Path fileName = Path.of("src\\" + gvFile + ".gv");
      Files.writeString(fileName, end);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}