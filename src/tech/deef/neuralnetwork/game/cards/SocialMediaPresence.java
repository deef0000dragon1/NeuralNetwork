
package tech.deef.neuralnetwork.game.cards;

import tech.deef.neuralnetwork.game.Card;
import tech.deef.neuralnetwork.game.Game;
	
public class SocialMediaPresence extends Card{
	
	public SocialMediaPresence(){
		cardClass = CardClass.MARKETING;
		cardType = CardType.INTERNET;
		cardNumber = CardNumber.SOCIAL_MEDIA_PRESENCE;
		baseCost = 5;
		baseStockEffect = 2;
		baseMoneyEffect = 0;
		baseTimeEffect = 0;
	}
	
	public SocialMediaPresence(int inputX, int inputY, Game g){
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