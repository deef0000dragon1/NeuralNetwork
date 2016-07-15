package tech.deef.neuralnetwork.game.cards;

import tech.deef.neuralnetwork.game.Card;
import tech.deef.neuralnetwork.game.Game;

//SPECIAL:
//gets +1 money per adjacent manufacturing. 
//Includes this one. 

public class Mine extends Card{
	
	public Mine(){
		cardClass = CardClass.MANUFACTURING;
		cardType = CardType.NONE;
		cardNumber = CardNumber.MINE;
		baseCost = 8;
		baseStockEffect = 0;
		baseMoneyEffect = 0;
		baseTimeEffect = 0;
	}
	
	public Mine(int inputX, int inputY, Game g){
		this();
		xPosition = inputX;
		yPosition = inputY;
		gameInstance = g;
	}
	
	
	@Override
	public void onPlace() {
		cost = baseCost;
		stockEffect = baseStockEffect;
		moneyEffect = baseMoneyEffect;
		timeEffect = baseTimeEffect; 
		updateAdjcent();
		update();
	}

	
	
	/**method that does something to all neighboring cards. 
	 * should be applied after update
	 * 
	 * IE avoid things like mine resetting income
	 * */
	@Override
	public void nighborEffect() {
		//none
	}

	
	
	//place extra cards if any
	@Override
	public void extraCards() {
		//none
	}

	
	
	//what effects are done every time the turn triggers
	@Override
	public void onTurnChange() {
		//nothing 
	}

	
	
	/**
	 * updates the card to what is wanted
	 * Mine, for example resets money gain.
	 * 
	 * */
	@Override
	public void update() {
		Card[][] adjcent = getAdjcent(xPosition,yPosition);
		
		int adjcentManufacturing = 0;
		
		//loops through the adjcent cards
		for (int i = -0; i <= 2; i++){
			for (int j = -0; j <= 2; j++){
				if(adjcent[i][j].cardClass == Card.CardClass.MANUFACTURING){
					adjcentManufacturing++;
				}		
			}
		}
		
		//set moneyeffect to +1 for each adjcent;
		moneyEffect = 1*adjcentManufacturing;
		
	}
	
	
	
	
}
