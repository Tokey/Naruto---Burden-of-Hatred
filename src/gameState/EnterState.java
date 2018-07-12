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

public class EnterState extends GameState {
	// animations
	private Animation animation;
	private BufferedImage[] images;

	public EnterState(GameStateManager gsm) {
		
		this.gsm = gsm;

		try {
			
			images = new BufferedImage[40];
			
			for(int i = 0; i < 20;i++){
						images[i] = ImageIO.read(getClass()
								.getResourceAsStream(
										"/Backgrounds/Enter" + (i + 1)
												+ ".jpg"));
			}
			int j = 19;
			for(int i = 19; i>=0;i-- ){
				
					images[j++] = ImageIO.read(getClass()
							.getResourceAsStream(
									"/Backgrounds/Enter" + (i + 1)
											+ ".jpg"));

			}
				
		 animation = new Animation();
		 animation.setFrames(images);
		 animation.setDelay(40);

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
		g.drawImage(animation.getImage(), 0, 0, GamePanel.WIDTH,
				GamePanel.HEIGHT, null);
		}

	

	private void select() {
		gsm.setState(GameStateManager.MENUSTATE);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
	}

	public void keyReleased(int k) {
	}

}
