import dao.MysqlConnect;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EntryPoint {
    public static void main(String[] args) {
        System.out.println("EntryPoint starting..");

        try {
            List<String> lines = Files.readAllLines(Paths.get("credentials.txt"));

            if(lines.size() != 2) {
                System.out.println("Credential file doesn't have two lines, starting login frame..");
                new LoginFrame();
            } else {
                String email = lines.get(0);
                String password = lines.get(1);
                String response = MysqlConnect.tryAuthentication(email, password, true);

                if(response.equals("success")) {
                    System.out.println("Credential file authentication success, starting main frame..");

                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/montserrat_regular_400.ttf")));

                    new MainFrame();
                } else {
                    System.out.println("Credential file return error : "+response);
                    new LoginFrame();
                }
            }
        } catch (IOException | FontFormatException e) {
            System.out.println("No credential file found. Displaying login form..");
            new LoginFrame();
        }
    }
}
