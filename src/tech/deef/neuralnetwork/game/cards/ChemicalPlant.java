
package tech.deef.neuralnetwork.game.cards;

import tech.deef.neuralnetwork.game.Card;
import tech.deef.neuralnetwork.game.Game;
	
public class ChemicalPlant extends Card{
	
	public ChemicalPlant(){
		cardClass = CardClass.MANUFACTURING;
		cardType = CardType.CHEMICAL;
		cardNumber = CardNumber.CHEMICAL_PLANT;
		baseCost = 14;
		baseStockEffect = 0;
		baseMoneyEffect = 2;
		baseTimeEffect = 0;
	}
	
	public ChemicalPlant(int inputX, int inputY, Game g){
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
		
		int adjcentChemical = 0;
		
		//loops through the adjcent cards
		for (int i = -0; i <= 2; i++){
			for (int j = -0; j <= 2; j++){
				if(adjcent[i][j].cardClass == Card.CardType.CHEMICAL){
					adjcentChemical++;
				}		
			}
		}
		
		//set moneyeffect to +1 for each adjcent;
		moneyEffect = 1*adjcentChemical+baseMoneyEffect;
		
	}
	
	
	
	
}
