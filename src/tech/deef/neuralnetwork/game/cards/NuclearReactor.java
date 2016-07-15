package tech.deef.neuralnetwork.game.cards;

import tech.deef.neuralnetwork.game.Card;
import tech.deef.neuralnetwork.game.Game;


public class NuclearReactor extends Card{
	
	public NuclearReactor(){
		cardClass = CardClass.MANUFACTURING;
		cardType = CardType.NONE;
		cardNumber = CardNumber.NEUCLEAR_REACTOR;
		baseCost = 20;
		baseStockEffect = 0;
		baseMoneyEffect = 5;
		baseTimeEffect = 0;
	}
	
	public NuclearReactor(int inputX, int inputY, Game g){
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
		Card[][] adjcent = getAdjcent(xPosition,yPosition);
		
		int baseCards = 0;

		for (int i = -0; i <= 2; i++){
			for (int j = -0; j <= 2; j++){
				if(adjcent[i][j].cardNumber == Card.CardNumber.BASE){
					baseCards++;
				}		
			}
		}
		
		if (baseCards != 0){
			int makeWaste = gameInstance.gameRandom.nextInt(baseCards);
			int makeWasteCounter = 0;
			
			
			for (int i = 0; i <= 2; i++){
				for (int j = 0; j <= 2; j++){
					if(adjcent[i][j].cardNumber == Card.CardNumber.BASE){
						makeWasteCounter++;
						if(makeWasteCounter > makeWaste){
							
							gameInstance.playmat[xPosition+1-i][yPosition+1-j] = 
									new ToxicWasteDump(xPosition+1-j, yPosition+1-j, gameInstance);
							
							return;
						}
					}		
				}
			}			
		}
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
