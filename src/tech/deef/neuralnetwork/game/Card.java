package tech.deef.neuralnetwork.game;

import tech.deef.neuralnetwork.game.cards.BaseCard;

public abstract class Card {
	//attributes
	public double cardClass;
	public double cardType;
	public double cardNumber;
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
    	public final static double BASE = -1;
    	public final static double MANUFACTURING = 1;
    	public final static double MARKETING = 2;
    	public final static double SALES  = 3;
    	public final static double ADMINISTRATION = 4;    	
    }
    
    public class CardType{
    	public final static double BASE = -1;
    	public final static double NONE = 0;
    	public final static double CHEMICAL = 1;
    	public final static double OFFSHORE = 2;
    	public final static double INTERNET  = 3;
    	public final static double OFFICE = 4;    	
    }
    
    public class CardNumber{
    	public final static double BASE = -1;
    	public final static double NONE = 0;
    	public final static double MINE = 1;
    	public final static double ASSEMBLY_PLANT = 2;
    	public final static double NEUCLEAR_REACTOR = 3;
    	public final static double TOXIC_WASTE_DUMP = 4;
    	public final static double IMPORT_CENTER = 5;
    	public final static double CHEMICAL_PLANT = 6;
    	public final static double PHARMACEUTICALS = 7;
    	public final static double COSMETICS_COMPANY = 8;
    	public final static double SWEAT_SHOP = 9;
    	public final static double SOCIAL_MEDIA_PRESENCE = 10;
    	public final static double MARKETING_FIRM = 11;
    	public final static double SPORTS_ARENA = 12;
    	public final static double VIRAL_VIDEO = 13;
    	public final static double MEME_TEAM = 14;
    	public final static double TV_STATION = 15;
    	public final static double CELEBERTY_ENDORSEMENT = 16;
    	public final static double STRIP_MALL = 17;
    	public final static double DEPARTMENT_STORE = 18;
    	public final static double MAIL_ORDER_SERVICE = 19;
    	public final static double E_COMMERCE_SITE = 20;
    	public final static double FRANCHISE = 21;
    	public final static double OVERSEAS_EXPANSION = 22;
    	public final static double HOSTILE_TAKEOVER = 23;
    	public final static double EXPORT_HUB = 24;
    	public final static double HEADQUARTERS = 25;
    	public final static double HUMAN_RESOURCES = 26;
    	public final static double LEGAL_TEAM = 27;
    	public final static double EXECUTIVE_OFFICE = 28;
    	public final static double CORPERATE_RETREAT = 29;
    	public final static double TAX_SHELTER = 30;
    	public final static double PATENT_LAYWER = 31;
    	public final static double IT_DEPARTMENT = 32;
    	public final static double RESEARCH_DEPARTMENT = 33;
    	public final static double PARKING_LOT = 34;
    	public final static double FINANCE_DEPARTMENT = 35;
    	public final static double SUPPORT_CALL_CENTER = 36;
    	public final static double QUALITY_ASSURANCE = 37;
    	public final static double EMPLOYEE_HOUSING = 38;
    	public final static double MALL_THEATER = 39;
    	public final static double MALL_HOUSING = 40;
    	public final static double ARENA_FIELD = 41;
}

    public abstract void update();			//update the card.
    public abstract void onTurnChange(); 		//when the turn flips
    public abstract void onPlace(); 		//when the card is placed on the field
    public abstract void nighborEffect();	//the effect determined by the neighbors
    public abstract void extraCards();		//extra cards placed by the inital
    
    
    
    public Card[][] getAdjcent(int x, int y){
    	Card[][] output = new Card[3][3];
    	
    	//loops through all adjcent cards nd if they are not out of bounds, adds them to the list. 
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
