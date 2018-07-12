package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Entity.Animation;
import main.GamePanel;

public class GameOverState extends GameState {
	;
	// animations
	private Animation animation;
	private BufferedImage[] images;

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public String str[] = new String[12];

	public String sc;
	boolean right = false;
	long ar[] = new long[6];
	boolean inputname = false;
	boolean animationRight = false;
	int currentAnimation;
	private String[] options = { "Press Enter To Return To Main Menu" };

	private Font font, font2,font3;

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
		try {
			x = new Scanner(new File("Resources/Maps/Score.txt"));
		} catch (Exception e) {
			System.out.println("READ FAILED");
		}
		sc = x.next();
		System.out.println(sc);
		x.close();
	}
	private void SW() {
		Formatter x = null;
		try {
			x = new Formatter("Resources/Maps/Highscore.txt");
		} catch (Exception e) {
			System.out.println("READ FAILED");
		}
		int i;
		for(i=0;i<5;i++)
		{
		x.format(str[i*2]+" "+ar[i]);
		if(i<4)
		x.format("\n");
		}
		x.close();
	}
	public void sort()
	{
		int i,j;
		long t;
		String s;
		for(i=0;i<ar.length;i++)
		{
			for(j=i+1;j<ar.length;j++)
			{
				if(ar[i]<ar[j])
				{
					t=ar[i];
					ar[i]=ar[j];
					ar[j]=t;
					
					s=str[i*2];
					str[i*2]=str[j*2];
					str[j*2]=s;
				}
			}
		}
	}

	public GameOverState(GameStateManager gsm) {

		this.gsm = gsm;
		str[10] = "";
		try {
			images = new BufferedImage[42];

			for (int i = 0; i < 21; i++) {
				images[i] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/GO" + (i + 1) + ".jpg"));

			}
			int j = 20;
			for (int i = 20; i >= 0; i--) {
				images[j++] = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/GO" + (i + 1) + ".jpg"));

			}

			animation = new Animation();
			animation.setFrames(images);
			animation.setDelay(60);

			font = new Font("MadLines", Font.PLAIN, 80);
			font2 = new Font("Ninja Naruto", Font.PLAIN, 50);
			font3 = new Font("MadLines", Font.PLAIN, 50);
		} catch (Exception e) {
			e.printStackTrace();
		}
		readScore();
		ar[0] = Long.parseLong(str[1]);
		ar[1] = Long.parseLong(str[3]);
		ar[2] = Long.parseLong(str[5]);
		ar[3] = Long.parseLong(str[7]);
		ar[4] = Long.parseLong(str[9]);
		ar[5] = Long.parseLong(sc);
		System.out.println(ar[5]);
		System.out.println(ar[4]);
	}

	public void init() {
	}

	int c = 0;

	public void update() {
		// BG.update();
		animation.update();
		if (c == 0) {
			c++;
			if (ar[4] < ar[5])
				inputname = true;
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(animation.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
		g.setFont(font);
		g.drawString(options[0], 130, 670);

		if (inputname) {
			g.setColor(Color.orange);
			g.drawRect(330, 500+36, 600, 60);
			g.setFont(font3);
			g.drawString("Enter Your Name", 480, 480+36);
			
			g.setColor(Color.black);
			g.setFont(font2);
			g.drawString("NEW HIGH SCORE!!!  Your Score is : " + ar[5], 100, 70);
			g.drawString(str[10], 400, 552+36);
		}
	}

	private void select() {
		gsm.setState(GameStateManager.MENUSTATE);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER && inputname == false) {
			select();
		}
		if (inputname) {
			if (str[10].length() != 15) {
				if (k >= 65 && k <= 91) {
					str[10] += (char) k;
				}
			}
			if(k==KeyEvent.VK_BACK_SPACE&&str[10].length()>=1)
				str[10] = str[10].substring(0, str[10].length()-1);
			if (k == KeyEvent.VK_ENTER ) {
				inputname=false;
				
				sort();
				SW();
				
				gsm.setState(GameStateManager.HOF);
			}
		}
	}

	public void keyReleased(int k) {
	}

}
