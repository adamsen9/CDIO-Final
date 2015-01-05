package controllers;

import entities.*;
import fields.*;

public abstract class FieldController {
	abstract public boolean landOnField(Player player, GUIManager display, OurField field, Die die);
}
