
	
/**
 * 
{
	//attributes
	public int cardClass;
	public int cardType;
	public int cardNumber;
	public boolean activateEffect;
    
	
	//Initial effects
	public double baseCost;
	public double baseStockEffect;
	public double baseMoneyEffect;
	public double baseTimeEffect;
		
	//modified effects
	public double cost;
	public double stockEffect;
	public double moneyEffect;
	public double timeEffect;
	
	//game attributes
	public int xPosition;
	public int yPosition;
	public Game gameInstance;
	
    public class CardClass{ 
    	public final static int BASE = -1;
    	public final static int MANUFACTURING = 1;
    	public final static int MARKETING = 2;
    	public final static int SALES  = 3;
    	public final static int ADMINISTRATION = 4;    	
    }
    
    public class CardType{
    	public final static int BASE = -1;
    	public final static int NONE = 0;
    	public final static int CHEMICAL = 1;
    	public final static int OFFSHORE = 2;
    	public final static int INTERNET  = 3;
    	public final static int OFFICE = 4;    	
    }
    
    public class CardNumber{
    	public final static int BASE = -1;
    	public final static int NONE = 0;
    	public final static int MINE = 1;
    	public final static int ASSEMBLY_PLANT = 2;
    	public final static int NEUCLEAR_REACTOR = 3;
    	public final static int TOXIC_WASTE_DUMP = 4;
    	public final static int IMPORT_CENTER = 5;
    	public final static int CHEMICAL_PLANT = 6;
    	public final static int PHARMACEUTICALS = 7;
    	public final static int COSMETICS_CO = 8;
    	public final static int SWEAT_SHOP = 9;
    	public final static int SOCIAL_MEDIA_PRESENCE = 10;
    	public final static int MARKETING_FIRM = 11;
    	public final static int SPORTS_ARENA = 12;
    	public final static int VIRAL_VIDEO = 13;
    	public final static int MEME_TEAM = 14;
    	public final static int TV_STATION = 15;
    	public final static int CELEBERTY_ENDORSEMENT = 16;
    	public final static int STRIP_MALL = 17;
    	public final static int DEPARTMENT_STORE = 18;
    	public final static int MAIL_ORDER_SERVICE = 19;
    	public final static int E_COMMERCE_SITE = 20;
    	public final static int FRANCHISE = 21;
    	public final static int OVERSEAS_EXPANSION = 22;
    	public final static int HOSTILE_TAKEOVER = 23;
    	public final static int EXPORT_HUB = 24;
    	public final static int HEADQUARTERS = 25;
    	public final static int HUMAN_RESOURCES = 26;
    	public final static int LEGAL_TEAM = 27;
    	public final static int EXECUTIVE_OFFICE = 28;
    	public final static int CORPERATE_RETREAT = 29;
    	public final static int TAX_SHELTER = 30;
    	public final static int PATENT_LAYWER = 31;
    	public final static int IT_DEPARTMENT = 32;
    	public final static int RESEARCH_DEPARTMENT = 33;
    	public final static int PARKING_LOT = 34;
    	public final static int FINANCE_DEPARTMENT = 35;
    	public final static int SUPPORT_CALL_CENTER = 36;
    	public final static int QUALITY_ASSURANCE = 37;
    	public final static int EMPLOYEE_HOUSING = 38;
    	public final static int MALL_THEATER = 39;
    	public final static int MALL_HOUSING = 40;
    	public final static int ARENA_FIELD = 41;
	}

    public abstract void update();			//update the card.
    public abstract void onTurnChange(); 		//when the turn flips
    public abstract void onPlace(); 		//when the card is placed on the field
    public abstract void nighborEffect();	//the effect determined by the neighbors
    public abstract void extraCards();		//extra cards placed by the inital
    
    
    
    public Card[][] getAdjcent(int x, int y){
    	Card[][] output = new Card[3][3];
    	
    	//loops through all adjcent cards nd if they are 
    	//not out of bounds, adds them to the list. 
    	for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				try{
						output[i+1][j+1] =  gameInstance.getCard(x+i, y+j);
				}catch(NullPointerException e){
					//there is no card at the current location
					output[i+1][j+1] = new BaseCard();
				}catch(IndexOutOfBoundsException e){
					//card can not even exist
					output[i+1][j+1] = null;
				}
			}	
			
		}
    	return output;
    }
    
    //updates all cards around current card, excluding current card. 
    public void updateAdjcent() {
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				try{
					if(i != 0 && j != 0){
						gameInstance.getCard(xPosition+i, yPosition+j).update();
					}
				}catch(NullPointerException e){
					//there is no card at the current location
				}catch(IndexOutOfBoundsException e){
					//card can not exist. do not effect
				}
			}	
		}
	}
}
 * */
	

package tech.deef.neuralnetwork.game.cards;

import tech.deef.neuralnetwork.game.Card;
import tech.deef.neuralnetwork.game.Game;
	
public class BaseCard extends Card{
	
	public BaseCard(){
		cardClass = CardClass.BASE;
		cardType = CardType.BASE;
		cardNumber = CardNumber.BASE;
		baseCost = 0;
		baseStockEffect = 0;
		baseMoneyEffect = 0;
		baseTimeEffect = 0;
	}
	
	public BaseCard(int inputX, int inputY, Game g){
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
