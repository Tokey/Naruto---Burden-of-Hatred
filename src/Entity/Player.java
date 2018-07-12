package Entity;

import tileMap.*;

import java.util.*;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import Entity.Enemies.Sasuke;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {

	// player stuff
	public long Score=0;
	private int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	

	// fireball
	private boolean firing;
	private boolean blocking;
	private boolean dashing;

	// recover
	private boolean recovering;

	// kicking
	private boolean kicking;
	private int kickCost;
	private int kickDamage;
	private int kickRange;

	// fireroll
	private boolean firerolling;
	private int fireRollDamage;
	private int fireRollCost;
	private int fireRollRange;

	// transfering to fox

	private boolean foxtransferring;
	private int foxBeamsDamage;
	private int foxBeamsCost;

	// punching
	private boolean punching;
	private int punchCost;
	private int punchDamage;
	private int punchRange;
	private int kunaicost;

	// superpunch
	private boolean superpunching;
	private int superPunchCost;
	private int superPunchDamage;
	private int superPunchRange;

	// taking damage
	private boolean takingdamage;
	private int damage;

	private int fireCost;
	private int fireBallDamage;
	// private ArrayList<FireBall> fireBalls;

	// scratch
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;

	// gliding
	private boolean gliding;
	private boolean kunai;
	private int kunaidamage = 40;

	private boolean rasenShuriken;
	private double rasenShurikenDamage = 300;
	private double kohakuDamage = 500;
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private ArrayList<BufferedImage[]> sprites2;
	private ArrayList<BufferedImage[]> spr;
	private ArrayList<Kunai> kunais;
	private ArrayList<RasenShuriken> rasens;
	private ArrayList<KohakuBlast> blasts;
	private final int[] numFrames2 = { 6, 4, 2, 3, 5, 6, 7, 7, 7, 1, 1, 8, 10 };
	// animation actions

	private static final int WALKING = 0;
	private static final int IDLE = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 9;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
	private static final int BLOCKING = 10;
	private static final int DASHING = 3;
	private static final int TAKINGDAMAGE = 4;
	private static final int RECOVERING = 5;
	private static final int PUNCHINGRIGHT = 6;
	private static final int PUNCHINGLEFT = 6;
	private static final int KICKING = 7;
	private static final int SUPERPUNCH = 8;
	private static final int FIREROLL = 11;
	private static final int TRANSFERFOX = 12;
	private static final int RASEN = 13;
	private static final int KUNAI = 14;
	int co=0;
	// SFX
	public HashMap<String, AudioPlayer> sfx;
	private int maxchakra;
	private int chakra;
	private int dashingCost;
	int ch =0 ;
	public Player(TileMap tm) {

		super(tm);
		width = 100;
		height = 100;
		cwidth = 10;
		cheight = 50;

		moveSpeed = 0.8;
		maxSpeed = 5.2;
		stopSpeed = 0.4;
		fallSpeed = 0.18;
		maxFallSpeed = 9.8;
		jumpStart = -10.4;
		stopJumpSpeed = 1.9;
		punchRange = 120;
		superPunchRange = 50;
		kickRange = 120;
		fireRollRange = 60;
		punchDamage = 70;
		kickDamage = 75;
		superPunchDamage = 100;
		fireRollDamage = 300;
		punchCost = 30;
		superPunchCost = 60;
		kickCost = 40;
		kunaicost = 60;
		dashingCost = 20;
		foxBeamsCost = 400;
		facingRight = true;
		
		health = maxHealth = 1000;
		
		chakra = 100;
		maxchakra =  1000;
		
		fire = maxFire = 2500;

		fireCost = 300;
		fireBallDamage = 500;

		scratchDamage = 8;
		scratchRange = 40;
		kunais = new ArrayList<>();
		rasens = new ArrayList<>();
		blasts = new ArrayList<>();
		// load sprites
		try {

			BufferedImage spritesheet3 = ImageIO
					.read(getClass().getResourceAsStream("/Sprites/Player/NarutoSprite100x100.png"));
			BufferedImage RangedSheet = ImageIO
					.read(getClass().getResourceAsStream("/Sprites/Player/Ranged 100x100.png"));
			sprites2 = new ArrayList<BufferedImage[]>();
			sprites = new ArrayList<BufferedImage[]>();
			spr = new ArrayList<BufferedImage[]>();

			for (int i = 0; i <= 12; i++) {

				BufferedImage[] bi2 = new BufferedImage[numFrames2[i]];

				if (i == 9) {
					bi2[0] = spritesheet3.getSubimage(2 * 100, 2 * 100, 100, 100);
				} else if (i == 10) {
					bi2[0] = spritesheet3.getSubimage(5 * 100, 1 * 100, 100, 100);
				} else if (i == 11) {
					for (int j = 0; j < 8; j++) {
						if (j < 5) {
							bi2[j] = spritesheet3.getSubimage(j * 200, 9 * 100, 200, 100);
						} else {
							bi2[j] = spritesheet3.getSubimage((j - 5) * 200, 10 * 100, 200, 100);
						}

					}
				} else if (i == 12) {
					for (int j = 0; j < 10; j++) {
						if (j < 6) {
							bi2[j] = spritesheet3.getSubimage(j * 200, 11 * 100, 200, 200);
						} else {
							bi2[j] = spritesheet3.getSubimage((j - 6) * 200, 13 * 100, 200, 200);
						}

					}
				} else {
					for (int j = 0; j < numFrames2[i]; j++) {

						bi2[j] = spritesheet3.getSubimage(j * 100, i * 100, 100, 100);
					}
				}
				sprites2.add(bi2);

			}
			BufferedImage[] bi = new BufferedImage[3];
			bi[0] = RangedSheet.getSubimage(00, 700, 100, 100);
			bi[1] = RangedSheet.getSubimage(100, 700, 100, 100);
			bi[2] = RangedSheet.getSubimage(200, 700, 100, 100);
			sprites.add(bi);
			BufferedImage[] b = new BufferedImage[10];
			b[0] = RangedSheet.getSubimage(000, 0, 100, 100);
			b[1] = RangedSheet.getSubimage(100, 0, 100, 100);
			b[2] = RangedSheet.getSubimage(200, 0, 100, 100);
			b[3] = RangedSheet.getSubimage(300, 0, 100, 100);
			b[4] = RangedSheet.getSubimage(400, 0, 100, 100);
			b[5] = RangedSheet.getSubimage(0, 100, 100, 100);
			b[6] = RangedSheet.getSubimage(100, 100, 100, 100);
			b[7] = RangedSheet.getSubimage(200, 100, 100, 100);
			b[8] = RangedSheet.getSubimage(300, 100, 100, 100);
			b[9] = RangedSheet.getSubimage(400, 100, 100, 100);
			spr.add(b);

		} catch (Exception e) {
			// e.printStackTrace();
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites2.get(FALLING));
		animation.setDelay(400);

		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("Jump", new AudioPlayer("/SFX/Jump.mp3"));
		sfx.put("Dash", new AudioPlayer("/SFX/Dash.mp3"));
		sfx.put("Kick", new AudioPlayer("/SFX/Kick.mp3"));
		sfx.put("SPunch", new AudioPlayer("/SFX/SPunch.mp3"));
		sfx.put("Punch", new AudioPlayer("/SFX/Punch.mp3"));
		sfx.put("3Tails", new AudioPlayer("/SFX/3Tails.mp3"));
		sfx.put("Hurt", new AudioPlayer("/SFX/Hurt.mp3"));
		sfx.put("Kyuubi", new AudioPlayer("/SFX/Kyuubi.mp3"));
		sfx.put("Punch2", new AudioPlayer("/SFX/Punch2.mp3"));
		sfx.put("Shurt", new AudioPlayer("/SFX/Shurt.mp3"));
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getChakra() {
		return chakra;
	}

	public int getMaxChakra() {
		return maxchakra;
	}
	public int getFire() {
		return fire;
	}

	public int getMaxFire() {
		return maxFire;
	}

	public void setPunching() {
		if(chakra< punchCost)
			return;
		punching = true;
	}

	public void setFireRoll() {
		if(chakra<fireCost)
			return;
		firerolling = true;
	}

	public void setTransfer() {
		if(chakra<foxBeamsCost)
			return;
		foxtransferring = true;
	}

	public void setRecovering() {
		recovering = true;
	}

	public void setKicking() {
		if(chakra<kickCost)
			return;
		kicking = true;
	}

	public void setBlocking(boolean b) {
		blocking = b;
	}
	
	public boolean getBlocking(){
		return blocking;
	}

	public void kunaiThrow() {
		if(chakra<kunaicost)
			return;
		kunai = true;
	}

	public void Rasentr() {
		if(chakra<rasenShurikenDamage)
			return;
		rasenShuriken = true;
	}

	public void setSuperpunching() {
		if(chakra<superPunchCost)
			return;
		superpunching = true;
	}

	public void setDashing() {
		if(chakra<dashingCost)
			return;
		dashing = true;
	}

	public void setFiring() {
		if(chakra<fireCost)
			return;
		firing = true;
	}

	public void setScratching() {
		scratching = true;
	}

	public void setTakingDamage(boolean b) {
		takingdamage = b;
	}
	
	public boolean getFlinching(){
		return flinching;
	}
	public void setGliding(boolean b) {
		gliding = b;
	}
	public void revive(int life){
		if(health+life<=maxHealth){
			health += life;
		}else if(health<0)
		{
			health = 0;
		}
		else if(health+life>maxHealth)
		{
			health = maxHealth;
		}
		
	}
	public void reviveChakra(int life){
		if(chakra+life<=maxchakra){
			chakra += life;
		}
		else if(chakra+life>maxchakra)
		{
			chakra = maxHealth;
		}
		
	}
	public void deplitionChakra(int life){
		if(chakra-life>=0){
			chakra -= life;
		}
		else if(chakra<0){
			chakra = 0;
		}
		
	}
	public void hit(int damage) {
		if (dead || flinching)
			return;

		health -= damage;
		if (health < 0) {
			health = 0;
		}
		if (health == 0) {
			dead = true;
		}

		takingdamage = true;
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

		// cannot move while attacking, except in air
		if ((currentAction == SCRATCHING || currentAction == FIREBALL || currentAction == BLOCKING
				|| currentAction == TRANSFERFOX) && !(jumping || falling)) {
			dx = 0;
		}

		// jumping
		if (jumping && !falling) {

			dy = jumpStart;
			falling = true;
		} else if (canJump() && jumping && falling) {
			dy = jumpStart;
			falling = true;

		}
		
		// dashing
		if (dashing) {

			if (facingRight) {
				dx = 7;
			} else {
				dx = -7;
			}

		}

		// recovering
		if (recovering) {
			if (facingRight) {
				dx = 0.5;
				dy = -1;
			} else {
				dx = -0.5;
				dy = -1;
			}
		}

		// firerolling
		if (firerolling) {
			if (facingRight) {
				dx = 5;

			} else {
				dx = -5;
			}
		}
		// kicking
		if (kicking) {
			if (facingRight) {
				dx = 0.5;
			} else {
				dx = -0.5;
			}
		}

		// superpunching
		if (superpunching) {

			if (facingRight) {
				dx = 1;
			} else {
				dx = -1;
			}

		}

		// taking damage
		if (takingdamage) {
			if (facingRight) {
				dx = -2;
			} else {
				dx = 2;
			}
		}
		// falling
		if (falling) {

			if (dy > 0 && gliding)
				dy += fallSpeed * 0.1;
			else
				dy += fallSpeed;

			if (dy > 0)
				jumping = false;
			if (dy < 0 && !jumping)
				dy += stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;

		}

	}

	public void update() {
		ch++;
		if(ch%5==0)
			chakra+=2;
		if(chakra==maxchakra)
		{
			if(ch%2==0)
				health++;
		}
		if(chakra>=maxchakra)
			chakra=maxchakra;
		if(health>=maxHealth)
			health=maxHealth;
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check attack has stopped
		if (currentAction == KICKING) {
			if (animation.hasPlayedOnce()) {
				kicking = false;
				deplitionChakra(kickCost);
			}
		}
		if (currentAction == SUPERPUNCH) {
			if (animation.hasPlayedOnce()) {
				superpunching = false;
				deplitionChakra(superPunchCost);
			}
		}
		
		if (currentAction == TRANSFERFOX) {
			if (animation.hasPlayedOnce()) {
				foxtransferring = false;
				deplitionChakra(foxBeamsCost);
			}
			if(animation.currentFrame==5)
			{
				if(co==0){
					if(facingRight){
				KohakuBlast rr = new KohakuBlast(tileMap, facingRight);
				rr.setPosition(x + 150, y - 6);
				blasts.add(rr);}
					else
					{
						KohakuBlast rr = new KohakuBlast(tileMap, facingRight);
						rr.setPosition(x - 150, y - 6);
						blasts.add(rr);
					}
				co++;
				}
			}
			else
				co=0;
		}
		if (currentAction == FIREROLL) {
			if (animation.hasPlayedOnce()) {
				firerolling = false;
				deplitionChakra(fireCost);
			}
			
		}
		if (currentAction == RECOVERING) {
			if (animation.hasPlayedOnce()) {
				recovering = false;
				
			}
		}
		if (currentAction == PUNCHINGLEFT) {

			if (animation.hasPlayedOnce()) {

				punching = false;
				deplitionChakra(punchCost);
			}
		} else if (currentAction == DASHING) {
			if (animation.hasPlayedOnce()) {
				dashing = false;
				deplitionChakra(dashingCost);
			}
		} else if (currentAction == SUPERPUNCH) {
			if (animation.hasPlayedOnce()) {
				superpunching = false;
				deplitionChakra(superPunchCost);
			}
		} else if (currentAction == JUMPING) {

			if (animation.hasPlayedOnce()) {
				jumping = false;
			}
		}
		if (currentAction == KUNAI) {
			if (animation.hasPlayedOnce()) {
				kunai = false;
				deplitionChakra(kunaicost);
			}
		}
		if (currentAction == RASEN) {
			if (animation.hasPlayedOnce()) {
				rasenShuriken = false;
				deplitionChakra((int) rasenShurikenDamage);

				
			}
			if (animation.currentFrame == 7) {
				if(co==0){
				if (facingRight) {
					RasenShuriken r = new RasenShuriken(tileMap, facingRight);
					r.setPosition(x + 80, y);
					rasens.add(r);
				} else {
					RasenShuriken r = new RasenShuriken(tileMap, facingRight);
					r.setPosition(x - 80, y);
					rasens.add(r);
				}
				co++;
				}
			}
			else
				co=0;
		}

		// check done flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;

			if (elapsed > 200) {
				takingdamage = false;
			}
			if (elapsed > 1000) {
				flinching = false;
			}
		}

		// set animation
		if (scratching) {
			if (currentAction != SCRATCHING) {
				currentAction = SCRATCHING;
				animation.setFrames(sprites2.get(KICKING));
				animation.setDelay(200);
				width = 60;
			}
		} else if (firerolling) {
			if (currentAction != FIREROLL) {
				currentAction = FIREROLL;
				sfx.get("3Tails").play();
				animation.setFrames(sprites2.get(FIREROLL));

				animation.setDelay(150);
				width = 60;
			}
		} else if (foxtransferring) {
			if (currentAction != TRANSFERFOX) {
				currentAction = TRANSFERFOX;
				sfx.get("Kyuubi").play();
				animation.setFrames(sprites2.get(TRANSFERFOX));

				animation.setDelay(250);
				width = 60;
			}
		} else if (takingdamage) {
			if (currentAction != TAKINGDAMAGE) {
				currentAction = TAKINGDAMAGE;
				sfx.get("Hurt").play();
				animation.setFrames(sprites2.get(TAKINGDAMAGE));
				animation.setDelay(200);
				width = 60;
			}
		} else if (recovering) {
			if (currentAction != RECOVERING) {
				currentAction = RECOVERING;
				animation.setFrames(sprites2.get(RECOVERING));
				animation.setDelay(200);
				width = 60;
			}
		} else if (superpunching) {

			if (currentAction != SUPERPUNCH) {
				currentAction = SUPERPUNCH;
				sfx.get("SPunch").play();
				animation.setFrames(sprites2.get(SUPERPUNCH));
				animation.setDelay(100);
				width = 120;
			}
		} else if (kicking) {
			if (currentAction != KICKING) {
				currentAction = KICKING;
				sfx.get("Kick").play();
				animation.setFrames(sprites2.get(KICKING));
				animation.setDelay(100);
				width = 60;
			}
		} else if (punching) {

			if (currentAction != PUNCHINGLEFT) {
				currentAction = PUNCHINGLEFT;
				animation.setFrames(sprites2.get(PUNCHINGLEFT));
				sfx.get("Punch").play();
				animation.setDelay(100);

				width = 60;
			}

		} else if (blocking) {
			if (currentAction != BLOCKING) {

				currentAction = BLOCKING;
				animation.setFrames(sprites2.get(BLOCKING));
				animation.setDelay(200);
				width = 60;

			}
		} else if (blocking) {
			if (currentAction != SUPERPUNCH) {

				currentAction = SUPERPUNCH;
				animation.setFrames(sprites2.get(SUPERPUNCH));
				animation.setDelay(100);
				width = 60;

			}
		} else if (dashing) {
			if (currentAction != DASHING) {
				sfx.get("Dash").play();
				currentAction = DASHING;
				animation.setFrames(sprites2.get(DASHING));
				animation.setDelay(200);
				width = 60;
		
			}
		} else if (dy > 0) {
			if (gliding) {
				if (currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					// animation.setDelay(200);
					width = 30;
				}
			} else if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites2.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		} else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				sfx.get("Jump").play();
				animation.setFrames(sprites2.get(JUMPING));
				animation.setDelay(200);
				width = 30;
			}
		} else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites2.get(WALKING));
				animation.setDelay(100);
				width = 30;
			}
		} else if (kunai == true) {
			if (currentAction != KUNAI) {
				currentAction = KUNAI;
				 sfx.get("Punch2").play();
				animation.setFrames(sprites.get(0));
				animation.setDelay(100);
				width = 30;
				if (facingRight) {
					Kunai k = new Kunai(tileMap, facingRight);
					k.setPosition(x + 60, y);
					kunais.add(k);
				} else {
					Kunai k = new Kunai(tileMap, facingRight);
					k.setPosition(x - 60, y);
					kunais.add(k);
				}
			}
		} else if (rasenShuriken == true) {
			if (currentAction != RASEN) {
				currentAction = RASEN;
				sfx.get("3Tails").play();
				animation.setFrames(spr.get(0));
				animation.setDelay(150);
				width = 30;

			}
		}

		else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites2.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}

		// set direction
		if (currentAction != SCRATCHING && currentAction != FIREBALL) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
		}
		for (int i = 0; i < kunais.size(); i++) {
			kunais.get(i).update();
			if (kunais.get(i).shouldRemove()) {
				kunais.remove(i);
				i--;
			}
		}
		for (int i = 0; i < rasens.size(); i++) {
			rasens.get(i).update();
			if (rasens.get(i).shouldRemove()) {
				rasens.remove(i);
				i--;
			}
		}
		for (int i = 0; i < blasts.size(); i++) {
			blasts.get(i).update();
			if (blasts.get(i).shouldRemove()) {
				blasts.remove(i);
				i--;
			}
		}
		animation.update();

	}

	public void draw(Graphics2D g) {

		setMapPosition();
		for (int i = 0; i < kunais.size(); i++) {
			kunais.get(i).draw(g);
		}
		for (int i = 0; i < rasens.size(); i++) {
			rasens.get(i).draw(g);
		}
		for (int i = 0; i < blasts.size(); i++) {
			blasts.get(i).draw(g);
		}
		// draw player
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		if (facingRight) {
			if (foxtransferring) {
				g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y - 100 + ymap - height / 2),
						null);
			} else {
				g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
			}
		} else {
			if (foxtransferring) {
				g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width),
						(int) (y - 100 + ymap - height / 2), -animation.getImage().getWidth(),
						animation.getImage().getHeight(), null);
			} else {
				g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2),
						-animation.getImage().getWidth(), animation.getImage().getHeight(), null);
			}

		}

	}
	public void checkSasuke(Sasuke s) {
			// punching
			for (int j = 0; j < kunais.size(); j++) {
				if (kunais.get(j).intersects(s)) {
					s.hit(kunaidamage);
					kunais.get(j).setHit();
					
					break;
				}

			}
			for (int j = 0; j < rasens.size(); j++) {
				if (rasens.get(j).intersects(s)) {
					s.hit((int) rasenShurikenDamage);
					rasens.get(j).setHit();
					break;
				}

			}
			for (int j = 0; j < blasts.size(); j++) {
				if (blasts.get(j).intersects(s)) {
					s.hit((int) kohakuDamage);
					blasts.get(j).setHit();
					break;
				}

			}

			if (punching) {
				if (facingRight) {
					if (s.getx() > x && s.getx() < x + punchRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						s.hit(punchDamage);
						System.out.println(" hits");
					}
				} else {
					if (s.getx() < x && s.getx() > x - punchRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						System.out.println("works");
						s.hit(punchDamage);
					}
				}
			}

			// Super punch

			if (superpunching) {
				if (facingRight) {
					if (s.getx() > x && s.getx() < x + superPunchRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						s.hit(superPunchDamage);
						System.out.println(" hits");
					} 
				}else {
					if (s.getx() < x && s.getx() > x - superPunchRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						s.hit(superPunchDamage);
					}
				}
			}

			// kicking

			if (kicking) {
				if (facingRight) {
					if (s.getx() > x && s.getx() < x + kickRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						s.hit(kickDamage);
						System.out.println(" hits");
					}
				} else {
					if (s.getx() < x && s.getx() > x - kickRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						s.hit(kickDamage);
					}
				}
			}

			// fire roll

			if (firerolling) {
				if (facingRight) {
					if (s.getx() > x && s.getx() < x + fireRollRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						s.hit(fireBallDamage);
						System.out.println(" hits");
					}
				} else {
					if (s.getx() < x && s.getx() > x - fireRollRange && s.gety() > y - height / 2
							&& s.gety() < y + height / 2) {
						s.hit(fireBallDamage);
					}
				}
			}

		}

	int p=0,k=0,sp=0,f=0;
	
	public void checkAttack(ArrayList<Enemy> enemies) {

		for (int i = 0; i < enemies.size(); i++) {

			Enemy e = enemies.get(i);

			// punching
			for (int j = 0; j < kunais.size(); j++) {
				if (kunais.get(j).intersects(e)) {
					e.hit(kunaidamage);
					kunais.get(j).setHit();
					Score+=2;
					break;
				}

			}
			for (int j = 0; j < rasens.size(); j++) {
				if (rasens.get(j).intersects(e)) {
					e.hit(rasenShurikenDamage);
					rasens.get(j).setHit();
					Score+=7;
					break;
				}

			}
			for (int j = 0; j < blasts.size(); j++) {
				if (blasts.get(j).intersects(e)) {
					e.hit(kohakuDamage);
					blasts.get(j).setHit();
					Score+=15;
					break;
				}

			}
			
			if (punching) {
				sp=0;
				k=0;
				f=0;
				if (facingRight) {
					if (e.getx() > x && e.getx() < x + punchRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(punchDamage);
						if(p==0)
						Score+=5;
						p++;
						
						
						
					}
				} else {
					if (e.getx() < x && e.getx() > x - punchRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(punchDamage);
						if(p==0)
						Score+=5;
						p++;
					}
				}
			}

			// Super punch

			if (superpunching) {
				f=0;
				p=0;
				k=0;
				if (facingRight) {
					if (e.getx() > x && e.getx() < x + superPunchRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(superPunchRange);
						if(sp==0)
						Score+=6;
						sp++;
						System.out.println(" hits");
					}
				} else {
					if (e.getx() < x && e.getx() > x - superPunchRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(superPunchRange);
						if(sp==0)
						Score+=6;
						sp++;
					}
				}
			}

			// kicking

			if (kicking) {
				sp=0;
				p=0;
				f=0;
				if (facingRight) {
					if (e.getx() > x && e.getx() < x + kickRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(kickDamage);
						if(k==0)
						Score+=5;
						k++;
						System.out.println(" hits");
					} 
				}else {
					if (e.getx() < x && e.getx() > x - kickRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(kickDamage);
						if(k==0)
						Score+=5;
						k++;
					}
				}
			}

			// fire roll

			if (firerolling) {
				sp=0;
				p=0;
				k=0;
				if (facingRight) {
					if (e.getx() > x && e.getx() < x + fireRollRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(fireBallDamage);
						if(f==0)
						Score+=10;
						f++;
						System.out.println(" hits");
					}
				} else {
					if (e.getx() < x && e.getx() > x - fireRollRange && e.gety() > y - height / 2
							&& e.gety() < y + height / 2) {
						e.hit(fireBallDamage);
						if(f==0)
						Score+=10;
						f++;
					}
				}
			}

		}

	}

}