package gameState;

import main.GamePanel;
import tileMap.*;
import Audio.AudioPlayer;
import Entity.*;
import Entity.Enemies.Bear;
import Entity.Enemies.Prisoner;
import Entity.Enemies.Sasuke;
import Entity.Enemies.Skeleton;
import Entity.Enemies.Slugger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;

public class Level1State extends GameState {

	private TileMap tileMap;
	private Background bg;
	private Background healthBar;
	private Background healthBar2;
	private double healthWidth;
	private Color healthColor;
	private double healthWidth2;
	private Color healthColor2;

	public AudioPlayer bgmusic;
	protected ArrayList<Poof> poof;
	private Font font;
	private Font font2;
	public int a;
	// player
	private Player player;

	// Enemies
	protected ArrayList<Enemy> enemies;
	protected ArrayList<Health> foods;
	protected ArrayList<Chakra> chakras;
	protected ArrayList<Sasuke> sasukes;
	private double chakraWidth;
	private BufferedImage[] finalAnimation;
	private Animation animationFianl;
	private boolean playAnimation;
	private boolean once = false;
	
	private int differ;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
		playAnimation = false;
		once = true;
		try {
			finalAnimation = new BufferedImage[756];
			String s;
			for(int i  = 0; i < finalAnimation.length;i++){
				s = "/Backgrounds/Finale/Finale"+(i+1)+".jpg";
				finalAnimation[i] =  ImageIO.read(
						getClass().getResourceAsStream(
								s
							)
						);
				
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();	
			}
		animationFianl = new Animation();
		animationFianl.setFrames(finalAnimation);
		animationFianl.setDelay(26);
		
	}

	public void init() {

		CP();
		tileMap = new TileMap(90);
		tileMap.loadTiles("/Tilesets/Tiles 90x90.png");
		tileMap.loadMap("/Maps/naruto.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.17);

		bg = new Background("/Backgrounds/BG.jpg", 0.26);
		healthBar = new Background("/Backgrounds/Status Bar.png", 0);
		healthBar.setDimension(400, 150);

		healthBar2 = new Background("/Backgrounds/Status Bar.png", 0);
		healthBar2.setDimension(400, 150);
		healthBar2.setPosition(200, 400);

		player = new Player(tileMap);
		if (a == 0)
			player.setPosition(160, 600);
		else if (a == 1) {
			player.setPosition(11100, 300);
		} else if (a == 2) {
			player.setPosition(21920, 620);
		} else if (a == 3) {
			player.setPosition(30400, 300);
		} else if (a == 4) {
			player.setPosition(37770, 520);
		} else if (a == 5) {
			player.setPosition(49600, 420);
		} else if (a == 6) {
			player.setPosition(57400, 120);
		}

		poof = new ArrayList<>();
		enemies = new ArrayList<>();
		sasukes = new ArrayList<>();
		foods = new ArrayList<>();
		chakras = new ArrayList<>();

		populateEnemies();
		populateSasukes();
		font = new Font("MadLines", Font.PLAIN, 100);
		font2 = new Font("Agency FB", Font.BOLD, 40);
		

	}

	private void populateSasukes() {
		Sasuke s = new Sasuke(tileMap, player);
		s.setPosition( 59219, 1145);
		sasukes.add(s);

	}

	private void CP() {
		Scanner x = null;
		try {
			x = new Scanner(new File("Resources/Maps/CP.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		a = x.nextInt();
		
		x.close();
	}

	private void CPW(int p) {
		Formatter x = null;
		try {
			x = new Formatter("Resources/Maps/CP.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		x.format(Integer.toString(p));
		x.close();
	}

	private void SW(long p) {
		Formatter x = null;
		try {
			x = new Formatter("Resources/Maps/Score.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		x.format(Long.toString(p));
		x.close();
	}

	private void populateEnemies() {

		enemies = new ArrayList<Enemy>();
		Prisoner p;
		Slugger s;
		Bear b;
		Skeleton sk;
		
		
		
		//prisoner
		p = new Prisoner(tileMap, player);
		p.setPosition(10000, 200);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(16004, 100);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(16921, 100);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(16231, 100);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(31000, 520);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(36590, 400);
		enemies.add(p);
		p= new Prisoner(tileMap, player);
		p.setPosition(45700, 400);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(48500, 100);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(48690, 100);
		enemies.add(p);
		/*p = new Prisoner(tileMap, player);
		p.setPosition(100, 100);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(100, 100);
		enemies.add(p);
		p = new Prisoner(tileMap, player);
		p.setPosition(100, 100);
		enemies.add(p);
		*/
		//p.setPosition(100, 100);
		//p.setPosition(100, 100);
		//p.setPosition(100, 100);
		//p.setPosition(100, 100);
		//p.setPosition(100, 100);
		//p.setPosition(100, 100);
		
		//prisoner add
		//enemies.add(p5);
		//enemies.add(p6);
		//enemies.add(p7);
		//enemies.add(p8);
		//enemies.add(p9);
		//enemies.add(p10);
		//enemies.add(p11);
		
		//slugger
		s= new Slugger(tileMap,player);
		s.setPosition(5200, 100);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(31300, 500);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(36100, 400);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(36500, 100);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(37000, 500);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(39900, 400);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(45500, 400);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(54410, 400);
		enemies.add(s);
		s= new Slugger(tileMap,player);
		s.setPosition(54650, 400);
		enemies.add(s);
		
		
		
		
		
		b= new Bear(tileMap,player);
		b.setPosition(18790, 1080);
		enemies.add(b);
		b= new Bear(tileMap,player);
		b.setPosition(27900, 620);
		enemies.add(b);
		b= new Bear(tileMap,player);
		b.setPosition(31150, 420);
		enemies.add(b);
		b= new Bear(tileMap,player);
		b.setPosition(36000, 400);
		enemies.add(b);
		b= new Bear(tileMap,player);
		b.setPosition(39960, 400);
		enemies.add(b);
		b= new Bear(tileMap,player);
		b.setPosition(45420, 400);
		enemies.add(b);
		b= new Bear(tileMap,player);
		b.setPosition(48600, 100);
		enemies.add(b);
		b= new Bear(tileMap,player);
		b.setPosition(55200,400);
		enemies.add(b);
		
		
		
		
		//skeleton
		sk= new Skeleton(tileMap,player);
		sk.setPosition(700, 800);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(1000, 800);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(5000, 100);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(5400, 100);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(5623, 100);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(10223, 200);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(16523, 200);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(15223, 200);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(18623, 1020);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(18723, 1020);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(40800, 400);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(41300, 400);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(45210, 400);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(45410, 400);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(48520, 400);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(48310, 400);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(48610, 400);
		enemies.add(sk);
		sk= new Skeleton(tileMap,player);
		sk.setPosition(48850, 400);
		enemies.add(sk);
		
		//skeleton add
		
		
		//enemies.add(p);
		//enemies.add(s);
		//enemies.add(b);
		/*Prisoner p;
		Slugger s;
		Bear b;
		Skeleton sk;
		
		Point[] points = new Point[]{
			new Point(904,780),
			new Point(5601,600),
			new Point(9798,1145),
			new Point(16606,240),
			new Point(18459,1230),
			new Point(20344,150)
		};
		
		for(int i = 0; i < points.length;i++){
			if(i == 0){
				b = new Bear(tileMap, player);
				b.setPosition(points[i].x, points[i].y);
				enemies.add(b);
			}
			else if(i ==2){
				s = new Slugger(tileMap, player);
				s.setPosition(points[i].x, points[i].y);
				enemies.add(s);
			}
			else if(i == 3){
				p = new Prisoner(tileMap, player);
				p.setPosition(points[i].x, points[i].y);
				enemies.add(p);
			}
			
			sk = new Skeleton(tileMap, player);
			sk.setPosition(points[i].x, points[i].y);
			enemies.add(sk);
		}
		*/
		Health health;
		Chakra chakra;

		chakra = new Chakra(tileMap,true, player);
		chakra.setPosition(3830,100);
		chakras.add(chakra);
		
		chakra = new Chakra(tileMap,true, player);
		chakra.setPosition(11450,520);
		chakras.add(chakra);
		
		chakra = new Chakra(tileMap,true, player);
		chakra.setPosition(21100,120);
		chakras.add(chakra);
		

		chakra = new Chakra(tileMap,true, player);
		chakra.setPosition(40800,250);
		chakras.add(chakra);

		chakra = new Chakra(tileMap,true, player);
		chakra.setPosition(47800,200);
		chakras.add(chakra);
		
		chakra = new Chakra(tileMap,true, player);
		chakra.setPosition(58150,300);
		chakras.add(chakra);

		chakra = new Chakra(tileMap,true, player);
		chakra.setPosition(58950,300);
		chakras.add(chakra);

		health = new Health(tileMap,true, player);
		health.setPosition(6000,300);
		foods.add(health);

		health = new Health(tileMap,true, player);
		health.setPosition(17080,100);
		foods.add(health);

		health = new Health(tileMap,true, player);
		health.setPosition(29400,900);
		foods.add(health);
		
		health = new Health(tileMap,true, player);
		health.setPosition(40720,250);
		foods.add(health);
		

		health = new Health(tileMap,true, player);
		health.setPosition(47400,250);
		foods.add(health);
		health = new Health(tileMap,true, player);
		health.setPosition(58610,200);
		foods.add(health);

	}

	public void update() {

		if(playAnimation){
			animationFianl.update();
			if(animationFianl.hasPlayedOnce()){
				
				
				playAnimation = false;
				once = false;
			}
		}else{
			if(player.getx()>58300&&!playAnimation&&once){
				playAnimation = true;
				once = false;
				
			}
			bg.update();

			// update player
			player.update();
			
			if (player.gety() > 1280) {
				SW(player.Score);
				gsm.setState(GameStateManager.GAMEOVER);

			}
			if (player.getHealth() <= 0) {
				
				SW(player.Score);
				gsm.setState(GameStateManager.GAMEOVER);

			}
			if (sasukes.get(0).getHealth() <= 0) {
				player.Score+=500;
				SW(player.Score);
				gsm.setState(GameStateManager.GAMEOVER);

			}
			
			
			
			
			tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
					GamePanel.HEIGHT / 2 - player.gety());
			// Background
			bg.setPosition(tileMap.getx(), tileMap.gety());

			// sasuke attack

			// player heath update
			healthWidth = (double) player.getHealth() / player.getMaxHealth() * 261;

			healthColor = new Color(255 * (1 - (int) healthWidth / 260),
					255 * (int) healthWidth / 260, 0);

			healthWidth2 = (double) sasukes.get(0).getHealth()
					/ sasukes.get(0).getMaxHealth() * 261;
			
			differ = 261 - (int)healthWidth2;
			healthColor2 = new Color(255 * (1 - (int) healthWidth2 / 260),
					255 * (int) healthWidth2 / 260, 0);

			chakraWidth = (double) player.getChakra() / player.getMaxChakra() * 112;

			healthBar2.update();
			healthBar2.setPosition(player.getx(), player.gety());

			// attack enemies

			player.checkAttack(enemies);
			player.checkSasuke(sasukes.get(0));

			// upadte sasuke

			Sasuke s = sasukes.get(0);

			s.update();

			// if(s.shouldRemove()){
			// sasukes.remove(0);
			// poof.add(new Poof(s.getx(),s.gety()));
			// }
			//

			// update all enemies
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				e.update();
				if (e.isDead()) {
					enemies.remove(i);
					i--;
					poof.add(new Poof(e.getx(), e.gety()));
				}
			}
			for (int i = 0; i < poof.size(); i++) {
				poof.get(i).update();
				if (poof.get(i).shouldRemove()) {
					poof.remove(i);
					i--;
				}
			}

			// health
			for (int i = 0; i < foods.size(); i++) {
				foods.get(i).update();

				if (foods.get(i).shouldRemove()) {
					foods.remove(i);
					i--;
				}
			}

			// chakras
			for (int i = 0; i < chakras.size(); i++) {
				chakras.get(i).update();

				if (chakras.get(i).shouldRemove()) {
					chakras.remove(i);
					i--;
				}
			}

			if (player.getx() >= 11100 && player.getx() < 11100 + 30)
				CPW(1);
			else if (player.getx() >= 21920 && player.getx() < 21920 + 30)
				CPW(2);
			else if (player.getx() >= 30400 && player.getx() < 30400 + 30)
				CPW(3);
			else if (player.getx() >= 37770 && player.getx() < 37770 + 30)
				CPW(4);
			else if (player.getx() >= 49600 && player.getx() < 49600 + 30)
				CPW(5);
			else if (player.getx() >= 57400 && player.getx() < 57400 + 30)
				CPW(6);
		}
			}

	public void draw(Graphics2D g) {
		if(playAnimation){
			g.drawImage(animationFianl.getImage(), 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
			return;	
		}
		// draw bg
		bg.draw(g);
		
		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);

		// health.draw(g);

		sasukes.get(0).draw(g);

		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {

			enemies.get(i).draw(g);
		}
		for (int i = 0; i < poof.size(); i++) {
			poof.get(i).setMapPosition((int) tileMap.getx(),
					(int) tileMap.gety());
			poof.get(i).draw(g);
		}
		g.setFont(font2);
		g.setPaint(Color.DARK_GRAY);
		g.drawString("Score : " + player.Score, 20, 130);
		g.setColor(healthColor);
		g.fillRect(100, 35, (int) healthWidth, 15);

		// chakra
		g.setColor(Color.BLUE);
		g.fillRect(120, 50, (int) chakraWidth, 15);
		healthBar.draw(g);

		if (!sasukes.get(0).notOnScreen()) {
			g.setColor(healthColor2);
			g.fillRect(920+differ, 35, (int) healthWidth2, 15);
			g.drawImage(healthBar2.getImage(), GamePanel.WIDTH, 0, -healthBar2
					.getImage().getWidth(), healthBar2.getImage().getHeight(),
					null);

		}

		for (int i = 0; i < foods.size(); i++) {
			foods.get(i).draw(g);
		}

		for (int i = 0; i < chakras.size(); i++) {
			chakras.get(i).draw(g);
		}
		g.setFont(font);
		if (player.getx() >= 11100 && player.getx() < 11100 + 30)
			g.drawString("Checkpoint", 450, 240);
		else if (player.getx() >= 21920 && player.getx() < 21920 + 30)
			g.drawString("Checkpoint", 450, 240);
		else if (player.getx() >= 30400 && player.getx() < 30400 + 30)
			g.drawString("Checkpoint", 450, 240);
		if (player.getx() >= 37770 && player.getx() < 37770 + 30)
			g.drawString("Checkpoint", 450, 240);
		else if (player.getx() >= 49600 && player.getx() < 49600 + 30)
			g.drawString("Checkpoint", 450, 240);
		else if (player.getx() >= 57400 && player.getx() < 57400 + 30)
			g.drawString("Checkpoint", 450, 240);
		
		
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(true);
			
		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if (k == KeyEvent.VK_UP)
			player.setUp(true);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(true);
		if (k == KeyEvent.VK_W)
			player.setJumping(true);
		if (k == KeyEvent.VK_D)
			player.setKicking();
		if (k == KeyEvent.VK_A) {
			player.setPunching();

		}
		if (k == KeyEvent.VK_Q)
			player.setSuperpunching();
		if (k == KeyEvent.VK_Z)
			player.Rasentr();
		if (k == KeyEvent.VK_SHIFT)
			player.setDashing();
		if (k == KeyEvent.VK_X)
			player.kunaiThrow();
		if (k == KeyEvent.VK_E)
			player.setFireRoll();
		if (k == KeyEvent.VK_C)
			player.setTransfer();
		if (k == KeyEvent.VK_SPACE)
			player.setBlocking(true);

	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(false);

		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(false);

		}
		if (k == KeyEvent.VK_UP)
			player.setUp(false);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(false);
		if (k == KeyEvent.VK_W)
			player.setJumping(false);
		if (k == KeyEvent.VK_E)
			player.setGliding(false);
		if (k == KeyEvent.VK_SPACE)
			player.setBlocking(false);
		if (k == KeyEvent.VK_Z)
			player.setTakingDamage(false);
	}

}