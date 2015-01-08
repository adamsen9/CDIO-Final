package entities;

import java.awt.Color;
import java.util.ArrayList;

import fields.*;

public class GameBoard {
	OurField[] fields = new OurField[40];
	
	public GameBoard(){
		fields[0] = new OurStart();
		fields[1] = new OurStreet(new int[] {50, 250, 750, 2250, 4000, 6000}, 1200, 1000, "Rødovrevej", 2, 2, new Color (38, 131, 212), 1); // Lyseblå
		fields[2] = new OurChance(3);
		fields[3] = new OurStreet(new int[] {50, 250, 750, 2250, 4000, 6000}, 1200, 1000, "Hvidovrevej", 4, 4, new Color (38, 131, 212), 1);
		fields[4] = new OurTax(4000, 10, "Betal 10% eller 4.000", 5, 5);
		fields[5] = new OurShipping(4000, "SFL", 6, 6);
		fields[6] = new OurStreet(new int[] {100, 600, 1800, 5400, 8000, 11000}, 2000, 1000, "Roskildevej", 7, 7, new Color (240, 106, 79), 2); // Laksefarve
		fields[7] = new OurChance(8);
		fields[8] = new OurStreet(new int[] {100, 600, 1800, 5400, 8000, 11000}, 2000, 1000, "Valby Langgade", 9, 9, new Color (240, 106, 79), 2);
		fields[9] = new OurStreet(new int[] {150, 800, 2000, 6000, 9000, 12000}, 2400, 1000, "Allégade", 10, 10, new Color (240, 106, 79), 2);
		fields[10] = new OurJail("Fængsel", 11, true);
		fields[11] = new OurStreet(new int[] {200, 1000, 3000, 9000, 12500, 15000}, 2800, 2000, "Frederiksberg Allé", 12, 12, new Color (74, 222, 20),3); // Lysegrøn
		fields[12] = new OurBrewery(3000, "Tuborg", 13, 13);
		fields[13] = new OurStreet(new int[] {200, 1000, 3000, 9000, 12500, 15000}, 2800, 2000, "Bülowsvej", 14, 14, new Color (74, 222, 20),3);
		fields[14] = new OurStreet(new int[] {250, 1250, 3750, 10000, 14000, 18000}, 3200, 2000, "Gl. Kongevej", 15, 15, new Color (74, 222, 20),3);
		fields[15] = new OurShipping(4000, "Kalundborg/Århus", 16, 16);
		fields[16] = new OurStreet(new int[] {300, 1400, 4000, 11000, 15000, 19000}, 3600, 2000, "Bernstorffsvej", 17, 17, new Color (195, 195, 195),4); // Grå
		fields[17] = new OurChance(18);
		fields[18] = new OurStreet(new int[] {300, 1400, 4000, 11000, 15000, 19000}, 3600, 2000, "Hellerupvej", 19, 19, new Color (195, 195, 195),4);
		fields[19] = new OurStreet(new int[] {350, 1600, 4400, 12000, 16000, 20000}, 4000, 2000, "Strandvej", 20, 20, new Color (195, 195, 195),4);
		fields[20] = new OurParking(4000, "Den Danske Bank Parkering", 21, 21);
		fields[21] = new OurStreet(new int[] {350, 1800, 5000, 14000, 17500, 21000}, 4400, 3000, "Trianglen", 22, 22, new Color (255, 0, 0),5); // Rød
		fields[22] = new OurChance(23);
		fields[23] = new OurStreet(new int[] {350, 1800, 5000, 14000, 17500, 21000}, 4400, 3000, "Østerbrogade", 24, 24, new Color (255, 0, 0),5);
		fields[24] = new OurStreet(new int[] {400, 2000, 6000, 15000, 18500, 22000}, 4800, 3000, "Grønningen", 25, 25, new Color (255, 0, 0),5);
		fields[25] = new OurShipping(4000, "DFDS Seaways", 26, 26);
		fields[26] = new OurStreet(new int[] {450, 2200, 6600, 16000, 19500, 23000}, 5200, 3000, "Bredgade", 27, 27, new Color (255, 255, 255),6); // Hvid
		fields[27] = new OurStreet(new int[] {450, 2200, 6600, 16000, 19500, 23000}, 5200, 3000, "Kgs. Nytorv", 28, 28, new Color (255, 255, 255),6);
		fields[28] = new OurBrewery(3000, "Coca-Cola Tapperi", 29, 29);
		fields[29] = new OurStreet(new int[] {500, 2400, 7200, 17000, 20500, 24000}, 5600, 3000, "Østergade", 30, 30, new Color (255, 255, 255),6); 
		fields[30] = new OurJail("De Fængsles", 31, false);
		fields[31] = new OurStreet(new int[] {550, 2600, 7800, 18000, 22000, 25000}, 6000, 4000, "Amagertorv", 32, 32, new Color (246, 255, 0),7);
		fields[32] = new OurStreet(new int[] {550, 2600, 7800, 18000, 22000, 25000}, 6000, 4000, "Vimmelskaftet", 33, 33, new Color (246, 255, 0),7);
		fields[33] = new OurChance(34);
		fields[34] = new OurStreet(new int[] {600, 3000, 9000, 20000, 24000, 28000}, 6400, 4000, "Nygade", 35, 35, new Color (246, 255, 0),7); // Gul
		fields[35] = new OurShipping(4000, "Halsskov/Knudshoved", 36, 36);
		fields[36] = new OurChance(37);
		fields[37] = new OurStreet(new int[] {700, 3500, 10000, 22000, 26000, 30000}, 7000, 4000, "Frederiksberggade", 38, 38, new Color (111, 77, 117),8); // Lilla
		fields[38] = new OurTax(2000, 0, "Statsskat, betal 2000", 39, 39);
		fields[39] = new OurStreet(new int[] {1000, 4000, 12000, 28000, 34000, 40000}, 8000, 4000, "Rådhuspladsen", 40, 40, new Color (111, 77, 117),8);
	}
	
	
	public OurField getField(int id){
		OurField field = fields[id];
		return field;
	}
	
	public OurField[] getFields(){
		return fields;
	}
	
	public int getNumberOfFields(){
		return fields.length;
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i <= fields.length-1; i++) {
			s += fields[i] + "\n";
		}
		return s;
	}
	
	public ArrayList<OurStreet> getAllInCategory(int category){
		ArrayList<OurStreet> categoryFields = new ArrayList<OurStreet>();
		for(int i = 0; i < fields.length; i++){
			if (fields[i].getType() == "Street"){
				OurStreet street = (OurStreet) fields[i];
				if(street.getCategory() == category+1){
					categoryFields.add(street);
				}
			}
		}
		return categoryFields;
	}
	
	public ArrayList<OurStreet> getBuildableFields(int playerId){
		ArrayList<OurStreet> buildableFields = new ArrayList<OurStreet>();
		for(int i = 1; i <= 8; i++){
			ArrayList<OurStreet> categoryFields = getAllInCategory(i-1);
			int counter = 0;
			System.out.println(categoryFields.size());
			for(int j = 0; j < categoryFields.size(); j++){
				System.out.println(categoryFields.get(j).getName());
				if(categoryFields.get(j).isOwned()){
					if(categoryFields.get(j).getOwner().getId() == playerId){
						counter++;
					}
				}
			}
			if (counter != categoryFields.size()) continue; // skips this category if the player doens't own all streets in it
			// Checks if a field is allowed to be build on.
			if(categoryFields.size() == 2){
				int numberOfHousesZero = categoryFields.get(0).getNumberOfHouses();
				int numberOfHousesOne = categoryFields.get(1).getNumberOfHouses();
				if(numberOfHousesZero == numberOfHousesOne){
					buildableFields.add(categoryFields.get(0));
					buildableFields.add(categoryFields.get(1));
				} else if(numberOfHousesZero > numberOfHousesOne){
					buildableFields.add(categoryFields.get(1));
				} else if(numberOfHousesZero < numberOfHousesOne){
					buildableFields.add(categoryFields.get(0));
				}
			} else if(categoryFields.size() == 3){
				int numberOfHousesZero = categoryFields.get(0).getNumberOfHouses();
				int numberOfHousesOne = categoryFields.get(1).getNumberOfHouses();
				int numberOfHousesTwo = categoryFields.get(2).getNumberOfHouses();
				if(numberOfHousesZero == numberOfHousesOne && numberOfHousesZero == numberOfHousesTwo){
					buildableFields.add(categoryFields.get(0));
					buildableFields.add(categoryFields.get(1));
					buildableFields.add(categoryFields.get(2));
				} else if(numberOfHousesZero == numberOfHousesOne && numberOfHousesZero > numberOfHousesTwo){
					buildableFields.add(categoryFields.get(2));
				} else if(numberOfHousesZero > numberOfHousesOne && numberOfHousesZero == numberOfHousesOne){
					buildableFields.add(categoryFields.get(1));
				} else if(numberOfHousesZero > numberOfHousesOne && numberOfHousesZero > numberOfHousesTwo){
					buildableFields.add(categoryFields.get(1));
					buildableFields.add(categoryFields.get(2));
				} else if(numberOfHousesZero < numberOfHousesOne && numberOfHousesOne == numberOfHousesTwo){
					buildableFields.add(categoryFields.get(0));
				} else if(numberOfHousesZero < numberOfHousesOne && numberOfHousesOne > numberOfHousesTwo){
					buildableFields.add(categoryFields.get(0));
					buildableFields.add(categoryFields.get(2));
				} else if(numberOfHousesZero < numberOfHousesTwo && numberOfHousesOne < numberOfHousesTwo){
					buildableFields.add(categoryFields.get(0));
					buildableFields.add(categoryFields.get(1));
				}
			}
		}
		return buildableFields;
	}
	
	public OurField getFieldWhereName(String name){
		for(int i = 0; i < fields.length; i++){
			if(fields[i].getName() == name){
				return fields[i];
			}
		}
		return null;
	}
}
