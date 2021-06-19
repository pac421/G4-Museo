import dao.MysqlConnect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
                    URL fontURL = ClassLoader.getSystemResource("montserrat_regular_400.ttf");
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(fontURL.getFile())));

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
