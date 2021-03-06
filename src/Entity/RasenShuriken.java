package Entity;

import tileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class RasenShuriken extends MapObject {
	
	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public RasenShuriken(TileMap tm, boolean right) {
		
		super(tm);
		
		facingRight = right;
		
		moveSpeed = 9.8;
		if(right) dx = moveSpeed;
		else dx = -moveSpeed;
		
		width = 100;
		height = 100;
		cwidth = 20;
		cheight = 50;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/Ranged 100x100.png"
				)
			);
			BufferedImage spr2 = ImageIO.read(
					getClass().getResourceAsStream(
						"/Sprites/Player/items 100x100.png"
					)
				);
			
			sprites = new BufferedImage[3];
			
				sprites[0] = spritesheet.getSubimage(
					0,
					200,
					width,
					height
				);
				sprites[1] = spritesheet.getSubimage(
						100,
						200,
						width,
						height
					);
				sprites[2] = spritesheet.getSubimage(
						200,
						200,
						width,
						height
					);
			
			
			hitSprites = new BufferedImage[3];
			hitSprites[0]= spr2.getSubimage(
					0,
					500,
					width,
					height
				);
			hitSprites[1]= spr2.getSubimage(
					100,
					500,
					width,
					height
				);
			hitSprites[2]= spr2.getSubimage(
					200,
					500,
					width,
					height
				);
			
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setHit() {
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	
	public boolean shouldRemove() { return remove; }
	
	public void update() {
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		if(dx == 0 && !hit) {
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()) {
			remove = true;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		super.draw(g);
		
	}
	
}


















