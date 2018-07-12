package Entity;

import tileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class KohakuBlast extends MapObject {
	
	private boolean hit;
	private boolean remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public KohakuBlast(TileMap tm, boolean right) {
		
		super(tm);
		
		facingRight = right;
		
		moveSpeed = 9.8;
		if(right) dx = moveSpeed;
		else dx = -moveSpeed;
		
		width = 160;
		height = 160;
		cwidth = 20;
		cheight = 50;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Player/Ranged 80x80.png"
				)
			);
			BufferedImage spr2 = ImageIO.read(
					getClass().getResourceAsStream(
						"/Sprites/Player/items 150x150.png"
					)
				);
			
			sprites = new BufferedImage[4];
			
				sprites[0] = spritesheet.getSubimage(
					0,
					640,
					width,
					height
				);
				sprites[1] = spritesheet.getSubimage(
						160,
						640,
						width,
						height
					);
				sprites[2] = spritesheet.getSubimage(
						320,
						640,
						width,
						height
					);
				sprites[3] = spritesheet.getSubimage(
						480,
						640,
						width,
						height
					);
			
			
			hitSprites = new BufferedImage[3];
			hitSprites[0]= spr2.getSubimage(
					0,
					750,
					150,
					150
				);
			hitSprites[1]= spr2.getSubimage(
					150,
					750,
					150,
					150
				);
			hitSprites[2]= spr2.getSubimage(
					300,
					750,
					150,
					150
				);
			
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(50);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setHit() {
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(100);
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


















