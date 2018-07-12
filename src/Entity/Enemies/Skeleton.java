package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.EmptyStackException;

import javax.imageio.ImageIO;

import Audio.AudioPlayer;
import tileMap.Tile;
import tileMap.TileMap;
import Entity.Animation;
import Entity.Enemy;
import Entity.Player;

public class Skeleton extends Enemy{
	
	private ArrayList<BufferedImage[]> sprites;
	private Player player;
	private int currentaction;
	
	public static final int WALKING = 0;
	public static final int EXPLOSION = 1;
	
	
	private final int[] numFrames = {8,5};
	
	private boolean walking;
	private boolean explosion;
	
	public Skeleton(TileMap tm,Player player) {
		super(tm);
		this.player = player;
		isSkeleton = true;
		moveSpeed = 1.2;
		maxSpeed = 1.2;
		
		fallSpeed = 0.3;
		maxFallSpeed = 10.0;
		
		width = 150;
		height = 150;
		
		cwidth = 10;
		cheight = 80;
		
		health = maxHealth = 100;
		
		damage = 300;
		sfx.put("Explode", new AudioPlayer("/SFX/Explode.mp3"));
		//load spritres
		
		try {
			BufferedImage SP = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Enemy 150x150.png"));
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream("/Sprites/Player/items 150x150.png")
					);
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0 ; i < numFrames.length; i++){
				
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				
				if(i == 0){
					for(int j = 0; j < numFrames[i]; j++){
						bi[j] = SP.getSubimage(j * width, (i+6)* height,
								width,height);
					}
				}
				else{
					for(int j = 0;j<numFrames[i]; j++){
						bi[j]  = spritesheet.getSubimage(j*width,3*height,150,150);
					}
				}
				
				sprites.add(bi);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites.get(WALKING));
		animation.setDelay(150);
		
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
	
public void update() {
	
	// update position
	getNextPosition();
	checkTileMapCollision();
	setPosition(xtemp, ytemp);
	
	//check explosion
	if(currentaction == EXPLOSION){
		if(animation.hasPlayedOnce()){
			explosion = false;
			dead = true;
			if(!player.getBlocking()){
				player.hit(damage);
			}
		}
	}
	// check flinching
	if(flinching) {
		long elapsed =
			(System.nanoTime() - flinchTimer) / 1000000;
		if(elapsed > 400) {
			flinching = false;
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
	
	//explosion
	if(intersects(player)&&!explosion){
		explosion = true;
		sfx.get("Explode").play();
		
		if(!player.getBlocking()){
			player.hit(damage);
		}
	}
	if(explosion){
		if(currentaction!=EXPLOSION){
			currentaction = EXPLOSION;
			animation.setFrames(sprites.get(EXPLOSION));
			animation.setDelay(100);
			
		}
	}
	
	// update animation
	animation.update();
	
}

	
	public void draw(Graphics2D g){
		//if(notOnScreen()) return;
		
		setMapPosition();
		
		super.draw(g);
	}
}
