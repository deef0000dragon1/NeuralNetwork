package tech.deef.neuralnetwork.game;

import java.util.Random;

public class Game {

    public Card playmat[][];
    public Random gameRandom;
    private int sizeX;
    private int sizeY;
    public int turnsRemaning;
    
    //[Horizontal Location][Verticle Location]
    
    
    public Game(){
    	this(100,100);        
    }
    
    public Game(int x, int y){
        this(x,y,-1); 
         
    }
    
    public Game(int x, int y, int key){
    	sizeX = x;
    	sizeY = y;
    	initalizePlayField(sizeX,sizeY);
    	if(key == -1){
    		gameRandom = new Random();
    	}
    	else
    	{
    		gameRandom = new Random(key);
    	}
    	
    	//game loop begin
    	
    	
    }
    

    public Card getCard(int x, int y){
		try{
			Card returnCard = playmat[x][y];
			return returnCard;
			
		}catch(IndexOutOfBoundsException e ){
			return null;
		}
	}
    
    
    
    private void initalizePlayField(int x, int y){
        playmat = new Card[x][y];
        
    }
}
