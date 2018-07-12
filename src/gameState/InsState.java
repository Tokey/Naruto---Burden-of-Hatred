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

public class InsState extends GameState {
	// animations
	private Animation animation;
	private BufferedImage[] images;
	

	public InsState(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			images = new BufferedImage[22];

			for (int i = 0; i < 11; i++) {
				images[i] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/ins" + (i + 1) + ".jpg"));
			}
			int j = 10;
			for (int i = 10; i >= 0; i--) {

				images[j++] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/ins" + (i + 1) + ".jpg"));

			}

			animation = new Animation();
			animation.setFrames(images);
			animation.setDelay(50);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void init() {
	}

	public void update() {
		// BG.update();
		animation.update();
	}

	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
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
