package gameState;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import Entity.Animation;
import main.GamePanel;

public class HallOfFame extends GameState {
	// animations
	private Animation animation;
	private BufferedImage[] images;
	String[] str = new String[10];
	Font font,font2;
	private String[] options = { "Press Backspace To Return To Main Menu" };

	public HallOfFame(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			images = new BufferedImage[22];

			for (int i = 0; i < 11; i++) {
				images[i] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/HOF" + (i + 1) + ".jpg"));
			}
			int j = 10;
			for (int i = 10; i >= 0; i--) {

				images[j++] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/HOF" + (i + 1) + ".jpg"));

			}

			animation = new Animation();
			animation.setFrames(images);
			animation.setDelay(40);
			font = new Font("MadLines", Font.PLAIN, 60);
			font2 = new Font("Ninja Naruto", Font.PLAIN, 40);

		} catch (Exception e) {
			e.printStackTrace();
		}
		readScore();
	}

	public void readScore() {
		Scanner x = null;
		try {
			x = new Scanner(new File("Resources/Maps/Highscore.txt"));
		} catch (Exception e) {
			System.out.println("READ FAILED");
		}
		int i = 0;
		while (x.hasNextLine()) {
			str[i] = x.next();
			System.out.println(str[i]);
			i++;

		}

		x.close();
	}

	public void init() {
	}

	public void update() {
		// BG.update();
		animation.update();
	}

	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.setFont(font);
		g.drawString(options[0], 180, 670);
		g.setFont(font2);
		for (int i = 0; i < str.length; i++) {

			g.setColor(Color.red);
			if (i %2 ==0)
				g.drawString((i/2+1)+" : "+str[i], 100, (200 + i * 40+10));
			else
				g.drawString(str[i], 600, (200 + (i-1) * 40+10));
		}

	}

	private void select() {
		gsm.setState(GameStateManager.MENUSTATE);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_BACK_SPACE) {
			select();
		}
	}

	public void keyReleased(int k) {
	}

}
