package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Config {
    GamePanel gp;
    private static Config instance;

    private Config(GamePanel gp) {
        this.gp = gp;
    }
    public static Config getInstance(GamePanel gp) {
        if (instance == null) {
            instance = new Config(gp);
        }
        return instance;
    }

    public void saveConfig()
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"))) {

            // Full Screen
            bw.write(gp.fullScreenOn ? "On" : "Off");
            bw.newLine();

            //Music Volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE Volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfig()
    {
        try(BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {

            String s = br.readLine();

            //Full Screen
            gp.fullScreenOn = s.equals("On");

            //Music Volume
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //SE Volume
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
