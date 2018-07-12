package Entity;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import tileMap.TileMap;

public class Chakra extends Kunai{
	
	private int nutrition;
	private Player player;
	public Chakra(TileMap tm, boolean right,Player player) {
		super(tm, right);
		nutrition = 800;
		this.player = player;
		
		moveSpeed = 0;
		dx = 0;
		width = 100;
		height = 100;
		cwidth = 20;
		cheight = 50;
		
		fallSpeed = 3;
		maxFallSpeed = 3;
		try {
			
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/Sprites/Player/items 100x100.png"
					)
				);
			sprites = new BufferedImage[2];
			for(int i = 0;i <sprites.length;i++){
				sprites[i] = spritesheet.getSubimage(0, 0, width, height);
			}
			
			BufferedImage spr2 = ImageIO.read(
					getClass().getResourceAsStream(
						"/Sprites/Player/items 100x100.png"
					)
				);
			
			hitSprites = new BufferedImage[4];
			
			for(int i = 0 ; i < hitSprites.length;i++){
				hitSprites[i] = spr2.getSubimage(i*width,100,width,height);
			}
			
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void setHit() {
		if(hit) return;
		hit = true;
		setPosition(player.getx(), player.gety());
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
		
		player.reviveChakra(nutrition);
		nutrition = 0;
	}
public void update() {
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		// falling
		if(falling) {
			dy += fallSpeed;
		}
		if(intersects(player)){
			setHit();
		}
		
		animation.update();
		if(hit && animation.hasPlayedOnce()) {
			remove = true;
		}
		
	}
	
	

}
