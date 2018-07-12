package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import Entity.Animation;
import Entity.Kunai;
import Entity.MapObject;
import Entity.Player;
import tileMap.TileMap;

public class Sasuke extends MapObject {

	// player stuff
	private int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	private long knifeTime;
	private long rangeTime;

	// knifing
	private ArrayList<Kunai> kunais;

	private boolean knifing;
	private int knifeDamage;
	private int knifeRange;

	// range attack
	private boolean rangeAttacking;
	private int rangeAttackDamage;
	private int rangeAttackRange;

	private int standRange;
	// animation
	private ArrayList<BufferedImage[]> sprites;

	private final int numFrames[] = { 6, 6, 3, 5 };

	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int KNIFING = 2;
	private static final int RANGE = 3;

	private int currentAction;
	private Animation animation;

	private boolean remove;
	public HashMap<String, AudioPlayer> sfx;
	Player player;

	public Sasuke(TileMap tm, Player player) {
		super(tm);
		this.player = player;
		maxHealth = health = 2500;

		rangeAttackDamage = 5;
		rangeAttackRange = 100;

		knifeDamage = 200;
		knifeRange = 300;

		standRange = 400;
		width = 100;
		height = 100;
		cwidth = 10;
		cheight = 50;

		fallSpeed = 0.3;
		maxFallSpeed = 10.0;

		moveSpeed = 0.8;
		maxSpeed = 5.2;
		stopSpeed = 0.4;
		fallSpeed = 0.18;
		maxFallSpeed = 9.8;

		facingRight = true;
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Shurt", new AudioPlayer("/SFX/Shurt.mp3"));
		sfx.put("Chidori", new AudioPlayer("/SFX/Chidori.mp3"));
		sfx.put("Slash", new AudioPlayer("/SFX/Slash.mp3"));
		// loading sprites
		try {
			BufferedImage spritesheet = ImageIO
					.read(getClass().getResourceAsStream("/Sprites/Enemies/Sasuke 100x100.png"));

			sprites = new ArrayList<BufferedImage[]>();

			for (int i = 0; i < numFrames.length; i++) {

				BufferedImage[] bi = new BufferedImage[numFrames[i]];

				for (int j = 0; j < numFrames[i]; j++) {
					if (i == KNIFING || i == RANGE) {
						bi[j] = spritesheet.getSubimage(j * 200, i * height, 200, height);

					} else {
						bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					}

				}

				sprites.add(bi);

				// System.out.println("Loading");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		kunais = new ArrayList<>();
		currentAction = IDLE;
		animation = new Animation();
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(100);
		facingRight = true;

	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getFire() {
		return fire;
	}

	public int getMaxFire() {
		return maxFire;
	}

	public void setKnifing() {
		knifeTime = System.currentTimeMillis();
		knifing = true;
	}

	public void setRange() {
		rangeTime = System.currentTimeMillis();
		rangeAttacking = true;
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void hit(int kunaidamage) {
		
		if (dead || flinching)
			return;

		health -= kunaidamage;
		sfx.get("Shurt").play();
		if (health < 0) {
			health = 0;
		}
		if (health == 0) {
			dead = true;
		}

		flinching = true;
		flinchTimer = System.nanoTime();
	}

	private void getNextPosition() {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		// falling
		if (falling) {
			dy += fallSpeed;
		}

		// kniefing
		if (knifing) {
			if (player.getx() > x) {
				setPosition(player.getx() - width, player.gety());
			} else {
				setPosition(player.getx() + width, player.gety());
			}
		}

	}

	public void comeCloseToAttack() {

		if (player.getx() - standRange > x && player.gety() > y - height / 2 && player.gety() < y + height / 2) {
			left = false;
			right = true;
		} else if (player.getx() + standRange < x) {
			left = true;
			right = false;
		} else if (player.getx() < x && x < player.getx() + standRange) {
			left = false;
			right = false;
		}

		if (player.getx() > x) {
			facingRight = true;
		} else {
			facingRight = false;
		}

	}
	private int op=0;
	public void update() {
		op++;
		if(op%3==0)
			this.health++;
		if(this.health>=this.maxHealth)
			this.health=this.maxHealth;
		// getting direction
				if (currentAction != RANGE && currentAction != KNIFING) {
					if (right)
						facingRight = true;
					if (left)
						facingRight = false;
				}
		if(notOnScreen()){
			return;
		}
		
		getNextPosition();

		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		comeCloseToAttack();

		if (health < 0) {
			health = 0;

		}
		if (health == 0) {
			remove = true;
		}

		attack();
		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}
		// check attack has stopped
		if (currentAction == RANGE) {
			if (animation.hasPlayedOnce()) {
				rangeAttacking = false;
			}
		}
		if (currentAction == KNIFING) {
			if (animation.hasPlayedOnce()) {
				knifing = false;
			}
		}

		// check done flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;

			if (elapsed > 1000) {
				flinching = false;
			}
		}

		// set animation
		if (knifing) {
			if (currentAction != KNIFING) {
				currentAction = KNIFING;
				sfx.get("Slash").play();
				animation.setFrames(sprites.get(KNIFING));
				animation.setDelay(200);
				width = 60;
			}
		} else if (rangeAttacking) {
			if (currentAction != RANGE) {
				currentAction = RANGE;
				sfx.get("Chidori").play();
				animation.setFrames(sprites.get(RANGE));
				animation.setDelay(200);
				width = 60;

				if (facingRight) {
					Kunai k = new Range(tileMap, facingRight);
					k.setPosition(x + 60, y);
					kunais.add(k);
				} else {
					Kunai k = new Range(tileMap, facingRight);
					k.setPosition(x - 60, y);
					kunais.add(k);
				}
			}
		} else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(100);
				width = 30;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}

		
		for (int i = 0; i < kunais.size(); i++) {
			kunais.get(i).update();
			if (kunais.get(i).shouldRemove()) {
				kunais.remove(i);
				i--;
			}
		}
		animation.update();
	}

	private void attack() {

		Random random = new Random();
		int p = random.nextInt(10);
		boolean attack = false;
		long attackEllapsed = System.currentTimeMillis() - knifeTime;
		long rangeEllapsed = System.currentTimeMillis() - rangeTime;
		if (p == 1) {
			attack = true;
		} else {
			attack = false;
		}

		if ((x < player.getx() && player.getx() < x + standRange && attack && !player.getFlinching()
				&& attackEllapsed > 10000 && player.gety() > y - height / 2 && player.gety() < y + height / 2)
				|| (x - standRange < player.getx() && player.getx() < x && attack && !player.getFlinching()
						&& attackEllapsed > 10000 && player.gety() > y - height / 2
						&& player.gety() < y + height / 2)) {
			setKnifing();
			player.hit(knifeDamage);

		} else if (rangeEllapsed > 5000) {
			setRange();

		}

		for (int j = 0; j < kunais.size(); j++) {
			if (kunais.get(j).intersects(player)) {
				if (!player.getBlocking()) {
					player.hit(rangeAttackDamage);
				}
				kunais.get(j).setHit();
				break;
			}

		}

	}

	public void draw(Graphics2D g) {

		setMapPosition();

		// draw player
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		for (int i = 0; i < kunais.size(); i++) {
			kunais.get(i).draw(g);
		}

		if (facingRight) {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
			// System.out.println("right");
		} else {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2),
					-animation.getImage().getWidth(), animation.getImage().getHeight(), null);

			// System.out.println("left");
		}

	}
}