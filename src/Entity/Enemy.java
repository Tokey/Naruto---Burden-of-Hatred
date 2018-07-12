package Entity;

import java.util.ArrayList;

import tileMap.TileMap;

public class Enemy extends MapObject{

	protected  int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;
	protected boolean isSkeleton;

	private boolean rolling;
	private boolean walking;
	private int rollSpeed;
	
	private int hitRange;
	private int rollRange;
	private int currentAction;
	
	protected boolean flinching;
	protected long flinchTimer;
	
	public Enemy(TileMap tm) {
		super(tm);
		
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public int getHealth(){ return health;	}

	public void hit(double kunaidamage){
		if(dead||flinching) return;
		
		health -= kunaidamage;
		if(health<0){ 	health = 0; 	}
		if(health == 0){	dead = true;	}
		
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	
	public void checkAttack(Player player) {
		
		if(rolling){
			if(facingRight){
				if(player.getx()>x && player.getx() < x + hitRange ){
					 player.hit(damage);
					 System.out.println("Health "+player.getHealth());
				 }
				
			}else{
				
				if(player.getx()<x && player.getx() > x - hitRange ){
					player.hit(damage);
					System.out.println("Health "+player.getHealth());
				 }
				
			}
		}
		
	}
	public void update(){
		
	}
}