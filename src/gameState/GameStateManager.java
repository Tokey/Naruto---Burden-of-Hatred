package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;
import Audio.AudioPlayer;
import Entity.Animation;

public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;
	private BufferedImage[] loading = new BufferedImage[21];
	private BufferedImage[] pauseb = new BufferedImage[29];
	public static final int NUMSTATES = 8;
	public static final int SPONSORSTATE = 0;
	public static final int ENTERSTATE = 1;
	public static final int MENUSTATE = 2;
	public static final int LEVEL1STATE = 3;
	public static final int GAMEOVER = 4;
	public static final int HOF = 5;
	public static final int BUTTON = 6;
	public static final int INS = 7;
	String options[] = {"Resume","Return to Main Menu"};
	public Animation anim = new Animation();
	int c = 0;
	int z = 1;
	public boolean pause = false;
	public Font font;

	public GameStateManager() {
		font= new Font("MadLines", Font.PLAIN, 80);
		try {

			for (int i = 1; i <= loading.length - 1; i++) {
				String s = "/Tilesets/Loading" + i + ".png";
				loading[i - 1] = ImageIO
						.read(getClass().getResourceAsStream(s));

			}
			loading[20] = ImageIO.read(getClass().getResourceAsStream(
					"/Backgrounds/naimul1.jpg"));
		} catch (Exception P) {
			P.printStackTrace();
		}
		gameStates = new GameState[NUMSTATES];

		currentState = SPONSORSTATE;
		loadState(currentState);
		try {
			for (int i = 1; i <= 11; i++) {
				String s = "/Backgrounds/pause" + i + ".jpg";
				pauseb[i - 1] = ImageIO.read(getClass().getResourceAsStream(s));
				
			}
			int j = 11;
			for (int i = 11; i >= 1; i--) {
				String s = "/Backgrounds/pause" + i + ".jpg";
				pauseb[j - 1] = ImageIO.read(getClass().getResourceAsStream(s));
				
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Mara khao");
		}
		anim.setFrames(pauseb);
		anim.setDelay(50);

	}

	private void loadState(int state) {
		if (state == SPONSORSTATE) {
			gameStates[state] = new SponsorState(this);
		}
		if (state == ENTERSTATE) {
			gameStates[state] = new EnterState(this);
		}
		if (state == MENUSTATE) {
			gameStates[state] = new MenuState(this);
		}
		if (state == LEVEL1STATE) {
			gameStates[state] = new Level1State(this);
		}
		if (state == GAMEOVER) {
			gameStates[state] = new GameOverState(this);
		}
		if (state == HOF) {
			gameStates[state] = new HallOfFame(this);
		}if (state == BUTTON) {
			gameStates[state] = new ButtonState(this);
		}if (state == INS) {
			gameStates[state] = new InsState(this);
		}
		System.gc();
	}

	public void unloadState(int state) {
		gameStates[state] = null;

	}

	public void setState(int state) {
		unloadState(currentState);

		currentState = state;
		loadState(currentState);

	}

	public void update() {

		try {
			if (!pause) {
				gameStates[currentState].update();
			} 
			else
				anim.update();
			
				

		} catch (Exception e) {

		}
		

	}

	int p = 0;
	private int currentChoice;

	public void draw(java.awt.Graphics2D g) {

		try {
			
			
			if(pause)
			{
				g.drawImage(anim.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
				g.setFont(font);
				for (int i = 0; i < options.length; i++) {
					if (i == currentChoice) {
						g.setColor(Color.CYAN);
					} else {
						g.setColor(Color.GRAY);
					}
					if (i==0)
						g.drawString(options[i], 580-55, (300 + i * 90));
					else
						g.drawString(options[i], 400-55, (300 + i * 90 ));
				}
			}
			else
				gameStates[currentState].draw(g);

		} catch (Exception e) {
			p++;
			g.drawImage(loading[20], 0, 0, 1280, 720, null);
			if (c >= 20)
				z = -1;
			if (c <= 0)
				z = 1;
			g.drawImage(loading[c], 320, 219, 640, 282, null);
			if (p % 3 == 0)
				c = c + z;

		}
	}

	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);

		if (k == KeyEvent.VK_P && !pause && currentState == LEVEL1STATE) {
			pause = true;
		} else if (k == KeyEvent.VK_P && pause && currentState == LEVEL1STATE) {
			pause = false;
		}
		if(pause)
		{
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
			}
			if (k == KeyEvent.VK_ENTER && currentChoice==0)
				pause=false;
			if (k == KeyEvent.VK_ENTER && currentChoice==1)
				{
				pause=false;
				setState(MENUSTATE);
				
				}	
		}

	}

	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}

}