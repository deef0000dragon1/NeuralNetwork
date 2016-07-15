
package tech.deef.neuralnetwork.game.cards;

import tech.deef.neuralnetwork.game.Card;
import tech.deef.neuralnetwork.game.Game;
	
public class CosmeticsCompany extends Card{
	
	public CosmeticsCompany(){
		cardClass = CardClass.MANUFACTURING;
		cardType = CardType.CHEMICAL;
		cardNumber = CardNumber.COSMETICS_COMPANY;
		baseCost = 10;
		baseStockEffect = 1;
		baseMoneyEffect = 2;
		baseTimeEffect = 0;
	}
	
	public CosmeticsCompany(int inputX, int inputY, Game g){
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
		

	}
	
	
	
	
}