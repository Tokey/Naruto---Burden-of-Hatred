package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import tileMap.Tile;
import tileMap.TileMap;
import Entity.Animation;
import Entity.Enemy;
import Entity.Player;

public class Bear extends Enemy{
	
	private ArrayList<BufferedImage[]> sprites;
	private Player player;
	
	private int currentAction;
	public static final int WALKING = 0;
	public static final int ROLLING = 1;
	
	private boolean rolling;
	private boolean walking;
	
	private int rollSpeed;
	
	private int hitRange;
	private int rollRange;
	
	private final int[] numFrames = { 6,6}
	;
	public Bear(TileMap tm,Player player) {
		super(tm);
		this.player = player;
		moveSpeed = 4;
		maxSpeed = 3;
		
		fallSpeed = 0.3;
		maxFallSpeed = 10.0;
		
		width = 150;
		height = 150;
		
		cwidth = 50;
		cheight = 90;
		
		health = maxHealth = 700;
		
		damage = 200;
		rollRange = 150;
		rollSpeed = 10;
		hitRange = 100;
		//load spritres
		
		try {
			BufferedImage SP = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Enemy 150x150.png"));
			
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0 ; i < numFrames.length; i++){
				
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++){
					bi[j] = SP.getSubimage(j * width, (i+7)* height,
							width,height);
				}
				
				sprites.add(bi);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites.get(WALKING));
		animation.setDelay(200);
		
		right = true;
		facingRight = true;
		walking = true;
	}
	
	
private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		
		// falling
		if(falling) {
			dy += fallSpeed;
		}
		
	}
public void checkAttack(Player player) {
	
	if(rolling){
		if(facingRight){
			if(player.getx()>x && player.getx() < x + hitRange&&player.gety() > y - height/2 && player.gety() < y + height/2){
				if(!player.getBlocking()){
					player.hit(damage);
				}
			 }
			
		}else{
			
			if(player.getx()<x && player.getx() > x - hitRange&&player.gety() > y - height/2 && player.gety() < y + height/2){
				if(!player.getBlocking()){
					player.hit(damage);
				}
			 }
			
		}
	}
	
}
		
public void update() {
	
	// update position
	getNextPosition();
	checkTileMapCollision();
	setPosition(xtemp, ytemp);
	
	// check flinching
	if(flinching) {
		long elapsed =
			(System.nanoTime() - flinchTimer) / 1000000;
		if(elapsed > 400) {
			flinching = false;
		}
	}
	//check if player gets hits
	checkAttack(player);
	//check if player is close enough
	
		if(facingRight){
			if(player.getx()>x && player.getx() < x + rollRange ){
				 rolling = true;
				 walking = false;
			 }
			
		}else{
			
			if(player.getx()<x && player.getx() > x - rollRange ){
				 rolling = true;
				 walking = false;
			 }
			
		}
		
		
		
	//attacking player
	if(rolling){
		if(currentAction != ROLLING){
			currentAction = ROLLING;
			
			animation.setFrames(sprites.get(ROLLING));
		
			animation.setDelay(100);
			width = 60;
		}
	}else{
		if(currentAction != WALKING){
			currentAction = WALKING;
			
			animation.setFrames(sprites.get(WALKING));
		
			animation.setDelay(200);
			width = 60;
		}
	}
	
	// if it hits a wall, go other direction
	if(right && (int)dx == 0) {
		right = false;
		left = true;
		facingRight = false;
	}
	else if(left && (int)dx == 0) {
		right = true;
		left = false;
		facingRight = true;
	}
	
	// update animation
	animation.update();
	if(rolling){
		if(animation.hasPlayedOnce()){
			rolling = false;
			walking = true;
		}
	}
}

	
	public void draw(Graphics2D g){
		//if(notOnScreen()) return;
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		setMapPosition();
		
		super.draw(g);
	}
}