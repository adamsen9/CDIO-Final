package controllers;

import entities.Die;
import entities.Player;
import fields.OurField;
import fields.OurTax;

public class OurTaxController extends FieldController{
	private OurTax tax;
	public OurTaxController(){
		
	}

	@Override
	public boolean[] landOnField(Player player, GUIManager display, OurField field, Die die) {
		tax =  (OurTax) field;
		boolean[] returnValue = {true, false};
		if(tax.getTaxRate() == 0) {
			display.sendMessage(player.getName() + " er landet på " + tax.getName() + " og skal betale " + tax.getTaxAmount() + " kroner i skat.");
			returnValue[0] = player.withdraw(tax.getTaxAmount());
			return returnValue;			
		}else {
			switch (display.choosePayment(player.getName())) {
			case "10%":
				//Samlede værdi hentes
				int totalAssets = player.getTotalAssets();

				returnValue[0] = player.withdraw((int) (totalAssets*tax.getTaxRate()));
				return returnValue;
			case "4000":
				returnValue[0] = player.withdraw(tax.getTaxAmount());
				return returnValue;
			}
		}
		return returnValue;
	}
}
