package gameState;

import tileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import Entity.Animation;
import main.GamePanel;

public class SponsorState extends GameState {
	// animations
	private Animation animation;
	private BufferedImage[] images;

	public SponsorState(GameStateManager gsm) {
		
		this.gsm = gsm;

		try {
			
			images = new BufferedImage[88];
			
			for(int i = 0; i < 11;i++){
						images[i] = ImageIO.read(getClass()
								.getResourceAsStream(
										"/Backgrounds/aust" + (i + 1)
												+ ".jpg"));
			}
			int j = 10;
			for(int i = 10; i>=0;i-- ){
				
					images[j++] = ImageIO.read(getClass()
							.getResourceAsStream(
									"/Backgrounds/aust" + (i + 1)
											+ ".jpg"));

			}
			for(int i = 0; i < 11;i++){
				images[j++] = ImageIO.read(getClass()
						.getResourceAsStream(
								"/Backgrounds/tokey" + (i + 1)
										+ ".jpg"));
	}
			for(int i = 10; i>=0;i-- ){
				
				images[j++] = ImageIO.read(getClass()
						.getResourceAsStream(
								"/Backgrounds/tokey" + (i + 1)
										+ ".jpg"));

		}
			for(int i = 0; i < 11;i++){
				images[j++] = ImageIO.read(getClass()
						.getResourceAsStream(
								"/Backgrounds/niloy" + (i + 1)
										+ ".jpg"));
	}
			for(int i = 10; i>=0;i-- ){
				
				images[j++] = ImageIO.read(getClass()
						.getResourceAsStream(
								"/Backgrounds/niloy" + (i + 1)
										+ ".jpg"));

		}
			for(int i = 0; i < 11;i++){
				images[j++] = ImageIO.read(getClass()
						.getResourceAsStream(
								"/Backgrounds/naimul" + (i + 1)
										+ ".jpg"));
	}
			for(int i = 10; i>=0;i-- ){
				
				images[j++] = ImageIO.read(getClass()
						.getResourceAsStream(
								"/Backgrounds/naimul" + (i + 1)
										+ ".jpg"));

		}
			
			
				
		 animation = new Animation();
		 animation.setFrames(images);
		 animation.setDelay(65);
		 

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void init() {
	}

	public void update() {
		// BG.update();
		animation.update();
		if(animation.hasPlayedOnce())
			 select();
	}

	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), 0, 0, GamePanel.WIDTH,
				GamePanel.HEIGHT, null);
		}

	

	private void select() {
		gsm.setState(GameStateManager.ENTERSTATE);
	}

	public void keyPressed(int k) {
		
		
	}

	public void keyReleased(int k) {
	}

}
