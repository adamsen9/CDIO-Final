package entities;

import fields.Fleet;
import fields.LaborCamp;
import fields.OurField;
import fields.OurRefuge;
import fields.OurTax;
import fields.Territory;

public class GameBoard {
	OurField[] fields = new OurField[21];
	
	public GameBoard(){
		fields[0] = new OurRefuge(5000, "Walled city", 12, 1);
		fields[1] = new Territory(100, 1000, "Tribe Encampment", 1, 2);
		fields[2] = new Territory(300, 1500, "Crater", 2, 3);
		fields[3] = new Territory(500, 2000, "Mountain", 3, 4);
		fields[4] = new Fleet(4000, "Second Sail", 18, 5);
		fields[5] = new Territory(700, 3000, "Cold Desert", 4, 6);
		fields[6] = new LaborCamp(2500, "Huts in the mountain", 14, 7);
		fields[7] = new Territory(1000, 4000, "Black Cave", 5, 8);
		fields[8] = new OurTax(2000, 0, "Goldmine", 16, 9);
		fields[9] = new Fleet(4000, "Sea Grover", 19, 10);
		fields[10] = new Territory(1300, 4300, "The Werewall", 6, 11);
		fields[11] = new OurRefuge(500, "Monastery", 13, 12);
		fields[12] = new Territory(1600, 4750, "Mountain village", 7, 13);
		fields[13] = new LaborCamp(2500, "The pit", 15, 14);
		fields[14] = new Fleet(4000, "The Buccaneers", 20, 15);
		fields[15] = new Territory(2000, 5000, "South Citadel", 8, 16);
		fields[16] = new Territory(2600, 5500, "Palace gates", 9, 17);
		fields[17] = new Territory(3200, 6000, "Tower", 10, 18);
		fields[18] = new OurTax(4000, 10,  "Caravan", 17, 19);
		fields[19] = new Fleet(4000, "Privateer armade", 21, 20);
		fields[20] = new Territory(4000, 8000, "Castle", 11, 21);
	}
	
	public OurField getField(int id){
		OurField field = fields[id];
		return field;
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
	
}
