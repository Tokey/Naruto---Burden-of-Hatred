package gameState;

import tileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Entity.Animation;
import main.GamePanel;

public class MenuState extends GameState {

	private Background bg;
	// animations
	private Animation animation;
	private BufferedImage[] images;

	public static final int LEFT = 0;
	public static final int RIGHT = 1;

	boolean right = false;

	public int[] numFrames = { 121, 121 };
	public int a = 0;

	boolean animationRight = false;
	int currentAnimation;

	private int currentChoice = 0;
	private String[] options = { "NEW STORY", "HALL OF FAME","BUTTON LAYOUT","INSTRUCTIONS", "QUIT" };
	private String[] options2 = { "CONTINUE", "NEW STORY", "HALL OF FAME","BUTTON LAYOUT","INSTRUCTIONS", "QUIT" };
	private Color titleColor;
	private Font titleFont;

	private Font font;

	private void CP() {
		Scanner x = null;
		try {
			x = new Scanner(new File("Resources/Maps/CP.txt"));
		} catch (Exception e) {
			System.out.println("READ FAILED");
		}
		a = x.nextInt();
		System.out.println(a);
		x.close();
	}
	private void CPW(int p) {
		Formatter x = null;
		try {
			x = new Formatter("Resources/Maps/CP.txt");
		} catch (Exception e) {
			System.out.println("READ FAILED");
		}
		x.format(Integer.toString(p));
		x.close();
	}
	
	public MenuState(GameStateManager gsm) {
		CP();
		this.gsm = gsm;

		try {

			images = new BufferedImage[242];

			for (int i = 0; i < 121; i++) {
				images[i] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Menu" + (i + 1) + ".jpg"));
			}
			int j = 120;
			for (int i = 120; i >= 0; i--) {

				images[j++] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/Menu" + (i + 1) + ".jpg"));

			}

			animation = new Animation();
			animation.setFrames(images);
			animation.setDelay(44);

			font = new Font("MadLines", Font.PLAIN, 80);

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

		// draw bg
		g.drawImage(animation.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

		// draw menu options
		g.setFont(font);
		if (a == 0) {
			for (int i = 0; i < options.length; i++) {
				if (i == currentChoice) {
					g.setColor(Color.CYAN);
				} else {
					g.setColor(Color.GRAY);
				}
				if (i==1)
					g.drawString(options[i], 110, (350 + i * 80 - 10));
				else if (i==2)
					g.drawString(options[i], 81, (350 + i * 80 - 10));
				else if (i==3)
					g.drawString(options[i], 108, (350 + i * 80 - 10));
				else if (i==4)
					g.drawString(options[i], 215, (350 + i * 80 - 10));
				else
					g.drawString(options[i], 145, (350 + i * 80 - 10));
			}
		} else {
			for (int i = 0; i < options2.length; i++) {
				if (i == currentChoice) {
					g.setColor(Color.CYAN);
				} else {
					g.setColor(Color.GRAY);
				}
				 if (i==0)
					g.drawString(options2[i], 170, (300 + i * 80 - 10));
				else if (i==2)
					g.drawString(options2[i], 110, (300 + i * 80 - 10));
				else if (i==3)
					g.drawString(options2[i], 81, (300 + i * 80 - 10));
				else if (i==4)
					g.drawString(options2[i], 108, (300 + i * 80 - 10));
				else if (i==5)
					g.drawString(options2[i], 215, (300 + i * 80 - 10));
				else
					g.drawString(options2[i], 145, (300 + i * 80 - 10));
			}
		}

	}

	private void select() {
		if(a==0){
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.HOF);
		}
		if (currentChoice == 2) {
			gsm.setState(GameStateManager.BUTTON);
		}
		if (currentChoice == 3) {
			gsm.setState(GameStateManager.INS);
		}
		if (currentChoice == 4) {
			System.exit(0);
		}
		}
		else {
			if (currentChoice == 0) {
				gsm.setState(GameStateManager.LEVEL1STATE);
			}
			if (currentChoice == 1) {
				CPW(0);
				gsm.setState(GameStateManager.LEVEL1STATE);
			}
			if (currentChoice == 2) {
				gsm.setState(GameStateManager.HOF);
			}
			if (currentChoice == 3) {
				gsm.setState(GameStateManager.BUTTON);
			}
			if (currentChoice == 4) {
				gsm.setState(GameStateManager.INS);
			}
			if (currentChoice == 5) {
				System.exit(0);
			}
			}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if(a==0){
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}}
		else{
			if (k == KeyEvent.VK_UP) {
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = options2.length - 1;
				}
			}
			if (k == KeyEvent.VK_DOWN) {
				currentChoice++;
				if (currentChoice == options2.length) {
					currentChoice = 0;
				}
			}
		}
	}

	public void keyReleased(int k) {
	}

}
