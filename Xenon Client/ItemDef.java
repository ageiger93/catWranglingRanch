import java.io.*;
import sign.signlink;

public final class ItemDef {

	public static ItemDef forID(int i) {
		for (int j = 0; j < 10; j++)
			if (cache[j].id == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 10;
		ItemDef itemDef = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		itemDef.id = i;
		itemDef.setDefaults();
		itemDef.readValues(stream);
if (itemDef.editedModelColor != null) {
			for (int i2 = 0; i2 < itemDef.editedModelColor.length; i2++) {
				if (itemDef.newModelColor[i2] == 0) {
					itemDef.newModelColor[i2] = 1;
				}
			}
		}
		if (itemDef.certTemplateID != -1)
			itemDef.toNote();
		if (itemDef.lentItemID != -1)
			itemDef.toLend();
		if (!isMembers && itemDef.membersObject) {
			itemDef.name = "Members Object";
			itemDef.description = "Login to a members' server to use this object.";
			itemDef.groundActions = null;
			itemDef.itemActions = null;
			itemDef.team = 0;
		}
		int[] BLACK_FIX = {6568,10636,12158,12159,12160,12161,12162,12163,12164,12165,12166,12167,12168,12527,
            18017,18018,18019,18020,3140,13481,14479,14481,19337,19342};
for (int a : BLACK_FIX) {
    if(itemDef.id == a) {
        itemDef.editedModelColor = new int[1];
        itemDef.newModelColor = new int[1];
        itemDef.editedModelColor[0] = 0;
        itemDef.newModelColor[0] = 1;
    }
}
		switch (itemDef.id) {
		case 12150:
		itemDef.modelid = 14125;
            itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[4] = "Drop";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.anInt204 = 14;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffset2 = 1;
                itemDef.anInt165 = 14126;
                itemDef.anInt200 = 14126;
                itemDef.name = "Mod Cape";
                itemDef.description = "A cape worn by player Moderators.";
    break;
  
	case 11722:
itemDef.modelid = 61361;
    itemDef.name = "Armadyl chainskirt";
    itemDef.modelZoom = 1488;
    itemDef.modelRotationY = 369;
    itemDef.modelRotationX = 1850;
    itemDef.modelOffset1 = 1;
    itemDef.modelOffset2 = 7;
    itemDef.stackable = false;
    itemDef.anInt165 = 61445;
    itemDef.anInt200 = 66892;
    itemDef.itemActions = new String[5];
    itemDef.itemActions[1] = "Wear";
    itemDef.itemActions[4] = "Drop";
break;

case 11720:
	itemDef.name = "Armadyl chestplate";
	itemDef.description = "It's an Armadyl chestplate";
	itemDef.groundActions = new String[5];
    itemDef.groundActions[2] = "Take";
 itemDef.itemActions = new String[5];
    itemDef.itemActions[1] = "Wield";
    itemDef.itemActions[4] = "Drop";
	itemDef.modelid = 61452;
	itemDef.anInt165 = 61451;
	itemDef.anInt200 = 67004;
	itemDef.modelZoom = 1400;
	itemDef.modelRotationY = 595;
	itemDef.modelRotationX = 2039;
	itemDef.modelOffset1 = 3;
	itemDef.modelOffset2 = 0;
	itemDef.value = 60000;
	itemDef.membersObject = true;
break;

case 11718:
	itemDef.name = "Armadyl helmet";
	itemDef.description = "It's an Armadyl helmet";
	itemDef.groundActions = new String[5];
    itemDef.groundActions[2] = "Take";
 itemDef.itemActions = new String[5];
    itemDef.itemActions[1] = "Wield";
    itemDef.itemActions[4] = "Drop";
	itemDef.modelid = 61360;
	itemDef.anInt165 = 61339;
	itemDef.anInt200 = 66787;
	itemDef.modelZoom = 850;
	itemDef.modelRotationY = 2047;
	itemDef.modelRotationX = 1697;
	itemDef.modelOffset1 = 3;
	itemDef.modelOffset2 = -1;
	itemDef.value = 20000;
	itemDef.membersObject = true;
break;
case 21364:
	 itemDef.modelid = 10088;
	itemDef.name = "Sagaie";
	itemDef.modelZoom = 1854;
	itemDef.modelRotationY = 271;
	itemDef.modelRotationX = 986;
	itemDef.modelOffset2 = -2;
	itemDef.modelOffset1 = 1;
	itemDef.groundActions[2] = "Take";
	 itemDef.itemActions[1] = "Wear";
      itemDef.itemActions[4] = "Drop";
	itemDef.anInt165 = 10236;
	itemDef.anInt200 = 10236;
break;
case 21365:
	itemDef.name = "Bolas";
	itemDef.modelZoom = 1232;
	itemDef.modelRotationY = 288;
	itemDef.modelRotationX = 1554;
	itemDef.modelOffset2 = 18;
	itemDef.modelOffset1 = 23;
	itemDef.groundActions[2] = "Take";
	 itemDef.itemActions[1] = "Wear";
      itemDef.itemActions[4] = "Drop";
	 itemDef.modelid = 10096;
	itemDef.anInt165 = 10243;
	itemDef.anInt200 = 10243;
	itemDef.anInt196 = 25;
	itemDef.anInt184 = 75;
break;
case 20171:
	itemDef.name = "Zaryte bow";
	itemDef.modelZoom = 1703;
	itemDef.modelRotationY = 221;
	itemDef.modelRotationX = 404;
	itemDef.modelOffset1 = -13;
	itemDef.groundActions[2] = "Take";
	 itemDef.itemActions[1] = "Wear";
      itemDef.itemActions[4] = "Drop";
	itemDef.modelid = 62692;
	itemDef.anInt165 = 62750;
	itemDef.anInt200 = 62750;
break;
 case 24116:
 itemDef.name = "Bakriminel bolts";
 itemDef.modelZoom = 720;
 itemDef.modelRotationY = 606;
 itemDef.modelRotationX = 117;
 itemDef.modelOffset2 = 1;
 itemDef.groundActions[2] = "Take";
	 itemDef.itemActions[1] = "Wear";
      itemDef.itemActions[4] = "Drop";
 itemDef.modelid = 68817;
break;
case 12742:
                itemDef.modelid = 4;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[4] = "Drop";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.anInt204 = 14;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffset2 = 1;
                itemDef.anInt165 = 5;
                itemDef.anInt200 = 5;
                itemDef.name = "@gre@ Donator Cape @gre@";
                itemDef.description = "Donator Cape";
                break;
				case 12151:
            itemDef.itemActions = new String[5];
            itemDef.itemActions[1] = "Wear";
            itemDef.modelid = 14127;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 572;
                itemDef.modelRotationX = 0;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffset2 = 1;
            itemDef.anInt204 = 0;
            itemDef.anInt165 = 14128;
            itemDef.anInt200 = 14128;
            itemDef.anInt188 = -1;
            itemDef.anInt164 = -1;
            itemDef.anInt175 = -1;
            itemDef.anInt197 = -1;
                itemDef.name = "Admin Cape";
                itemDef.description = "A cape worn by Administrators";
    break;
  
case 12152:
            itemDef.itemActions = new String[5];
            itemDef.itemActions[1] = "Wear";
            itemDef.modelid = 14129;
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 572;
                itemDef.modelRotationX = 0;
            itemDef.modelOffset1 = 0;
            itemDef.modelOffset2 = 1;
            itemDef.anInt204 = 0;
            itemDef.anInt165 = 14130;
            itemDef.anInt200 = 14130;
            itemDef.anInt188 = -1;
            itemDef.anInt164 = -1;
            itemDef.anInt175 = -1;
            itemDef.anInt197 = -1;
                itemDef.name = "Owner Cape";
                itemDef.description = "A cape worn by Owners.";
    break;
	case 19111:
itemDef.name ="TokHaar-Kal";
itemDef.value = 60000;
itemDef.anInt165 = 62575;
itemDef.anInt200 = 62582;
itemDef.groundActions = new String[5];
itemDef.groundActions[2] = "Take";
itemDef.modelOffset1 = -4;
itemDef.modelid = 62592;
itemDef.stackable = false;
itemDef.description = "A cape made of ancient, enchanted rocks.";
itemDef.modelZoom = 2086;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.itemActions[4] = "Drop";
itemDef.modelOffset2 = 0;
itemDef.modelRotationY = 533;
itemDef.modelRotationX = 333;
break;

case 962:
itemDef.name = "Christmas cracker";
itemDef.description = "A christmas cracker from the 2012 Christmas event.";
itemDef.groundActions = new String[5];
itemDef.groundActions[2] = "Take";
itemDef.itemActions = new String[5];
itemDef.itemActions[2] = "Open";
itemDef.itemActions[4] = "Drop";
break; 
								case 995:
			itemDef.name = "Coins";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[4] = "Drop";
			itemDef.itemActions[3] = "Add-to-pouch";
		break;
		 case 14207:
    itemDef.name = "Potion flask";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { null, null, null, null, "Drop" };
    itemDef.modelid = 61741;
break;
case 14200:
    itemDef.name = "Prayer flask (6)";
    itemDef.description = "6 doses of prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 28488 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14198:
    itemDef.name = "Prayer flask (5)";
    itemDef.description = "5 doses of prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 28488 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14196:
    itemDef.name = "Prayer flask (4)";
    itemDef.description = "4 doses of prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 28488 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14194:
    itemDef.name = "Prayer flask (3)";
    itemDef.description = "3 doses of prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 28488 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14192:
    itemDef.name = "Prayer flask (2)";
    itemDef.description = "2 doses of prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 28488 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14190:
    itemDef.name = "Prayer flask (1)";
    itemDef.description = "1 dose of prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 28488 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14188:
    itemDef.name = "Super attack flask (6)";
    itemDef.description = "6 doses of super attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 43848 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14186:
    itemDef.name = "Super attack flask (5)";
    itemDef.description = "5 doses of super attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 43848 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14184:
    itemDef.name = "Super attack flask (4)";
    itemDef.description = "4 doses of super attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 43848 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14182:
    itemDef.name = "Super attack flask (3)";
    itemDef.description = "3 doses of super attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 43848 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14180:
    itemDef.name = "Super attack flask (2)";
    itemDef.description = "2 doses of super attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 43848 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14178:
    itemDef.name = "Super attack flask (1)";
    itemDef.description = "1 dose of super attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 43848 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14176:
    itemDef.name = "Super strength flask (6)";
    itemDef.description = "6 doses of super strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 119 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14174:
    itemDef.name = "Super strength flask (5)";
    itemDef.description = "5 doses of super strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 119 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14172:
    itemDef.name = "Super strength flask (4)";
    itemDef.description = "4 doses of super strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 119 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14170:
    itemDef.name = "Super strength flask (3)";
    itemDef.description = "3 doses of super strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 119 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14168:
    itemDef.name = "Super strength flask (2)";
    itemDef.description = "2 doses of super strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 119 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14166:
    itemDef.name = "Super strength flask (1)";
    itemDef.description = "1 dose of super strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 119 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14164:
    itemDef.name = "Super defence flask (6)";
    itemDef.description = "6 doses of super defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 8008 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14162:
    itemDef.name = "Super defence flask (5)";
    itemDef.description = "5 doses of super defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 8008 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14160:
    itemDef.name = "Super defence flask (4)";
    itemDef.description = "4 doses of super defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 8008 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14158:
    itemDef.name = "Super defence flask (3)";
    itemDef.description = "3 doses of super defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 8008 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14156:
    itemDef.name = "Super defence flask (2)";
    itemDef.description = "2 doses of super defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 8008 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14154:
    itemDef.name = "Super defence flask (1)";
    itemDef.description = "1 dose of super defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 8008 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14152:
    itemDef.name = "Ranging flask (6)";
    itemDef.description = "6 doses of ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 36680 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14150:
    itemDef.name = "Ranging flask (5)";
    itemDef.description = "5 doses of ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 36680 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14148:
    itemDef.name = "Ranging flask (4)";
    itemDef.description = "4 doses of ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 36680 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14146:
    itemDef.name = "Ranging flask (3)";
    itemDef.description = "3 doses of ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 36680 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14144:
    itemDef.name = "Ranging flask (2)";
    itemDef.description = "2 doses of ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 36680 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14142:
    itemDef.name = "Ranging flask (1)";
    itemDef.description = "1 dose of ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 36680 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14140:
    itemDef.name = "Super antipoison flask (6)";
    itemDef.description = "6 doses of super antipoison.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62404 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14138:
    itemDef.name = "Super antipoison flask (5)";
    itemDef.description = "5 doses of super antipoison.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62404 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14136:
    itemDef.name = "Super antipoison flask (4)";
    itemDef.description = "4 doses of super antipoison.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62404 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14134:
    itemDef.name = "Super antipoison flask (3)";
    itemDef.description = "3 doses of super antipoison.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62404 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14132:
    itemDef.name = "Super antipoison flask (2)";
    itemDef.description = "2 doses of super antipoison.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62404 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14130:
    itemDef.name = "Super antipoison flask (1)";
    itemDef.description = "1 dose of super antipoison.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62404 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14128:
    itemDef.name = "Saradomin brew flask (6)";
    itemDef.description = "6 doses of saradomin brew.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10939 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
    itemDef.anInt196 = 40;
    itemDef.anInt184 = 200;
break;
case 14126:
    itemDef.name = "Saradomin brew flask (5)";
    itemDef.description = "5 doses of saradomin brew.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10939 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
    itemDef.anInt196 = 40;
    itemDef.anInt184 = 200;
break;
case 14124:
    itemDef.name = "Saradomin brew flask (4)";
    itemDef.description = "4 doses of saradomin brew.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10939 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
    itemDef.anInt196 = 40;
    itemDef.anInt184 = 200;
break;
case 14122:
    itemDef.name = "Saradomin brew flask (3)";
    itemDef.description = "3 doses of saradomin brew.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10939 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
    itemDef.anInt196 = 40;
    itemDef.anInt184 = 200;
break;
case 14419:
    itemDef.name = "Saradomin brew flask (2)";
    itemDef.description = "2 doses of saradomin brew.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10939 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
    itemDef.anInt196 = 40;
    itemDef.anInt184 = 200;
break;
case 14417:
    itemDef.name = "Saradomin brew flask (1)";
    itemDef.description = "1 dose of saradomin brew.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10939 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
    itemDef.anInt196 = 40;
    itemDef.anInt184 = 200;
break;
case 14415:
    itemDef.name = "Super restore flask (6)";
    itemDef.description = "6 doses of super restore potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62135 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14413:
    itemDef.name = "Super restore flask (5)";
    itemDef.description = "5 doses of super restore potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62135 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14411:
    itemDef.name = "Super restore flask (4)";
    itemDef.description = "4 doses of super restore potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62135 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14409:
    itemDef.name = "Super restore flask (3)";
    itemDef.description = "3 doses of super restore potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62135 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14407:
    itemDef.name = "Super restore flask (2)";
    itemDef.description = "2 doses of super restore potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62135 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14405:
    itemDef.name = "Super restore flask (1)";
    itemDef.description = "1 dose of super restore potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 62135 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14403:
    itemDef.name = "Magic flask (6)";
    itemDef.description = "6 doses of magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 37440 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14401:
    itemDef.name = "Magic flask (5)";
    itemDef.description = "5 doses of magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 37440 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14399:
    itemDef.name = "Magic flask (4)";
    itemDef.description = "4 doses of magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 37440 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14397:
    itemDef.name = "Magic flask (3)";
    itemDef.description = "3 doses of magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 37440 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14395:
    itemDef.name = "Magic flask (2)";
    itemDef.description = "2 doses of magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 37440 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14393:
    itemDef.name = "Magic flask (1)";
    itemDef.description = "1 dose of magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 37440 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14385:
    itemDef.name = "Recover special flask (6)";
    itemDef.description = "6 doses of recover special.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 38222 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14383:
    itemDef.name = "Recover special flask (5)";
    itemDef.description = "5 doses of recover special.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 38222 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14381:
    itemDef.name = "Recover special flask (4)";
    itemDef.description = "4 doses of recover special.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 38222 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14379:
    itemDef.name = "Recover special flask (3)";
    itemDef.description = "3 doses of recover special.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 38222 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14377:
    itemDef.name = "Recover special flask (2)";
    itemDef.description = "2 doses of recover special.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 38222 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14375:
    itemDef.name = "Recover special flask (1)";
    itemDef.description = "1 dose of recover special.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 38222 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14373:
    itemDef.name = "Extreme attack flask (6)";
    itemDef.description = "6 doses of extreme attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33112 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14371:
    itemDef.name = "Extreme attack flask (5)";
    itemDef.description = "5 doses of extreme attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33112 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14369:
    itemDef.name = "Extreme attack flask (4)";
    itemDef.description = "4 doses of extreme attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33112 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14367:
    itemDef.name = "Extreme attack flask (3)";
    itemDef.description = "3 doses of extreme attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33112 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14365:
    itemDef.name = "Extreme attack flask (2)";
    itemDef.description = "2 doses of extreme attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33112 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14363:
    itemDef.name = "Extreme attack flask (1)";
    itemDef.description = "1 dose of extreme attack potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33112 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14361:
    itemDef.name = "Extreme strength flask (6)";
    itemDef.description = "6 doses of extreme strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 127 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14359:
    itemDef.name = "Extreme strength flask (5)";
    itemDef.description = "5 doses of extreme strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 127 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14357:
    itemDef.name = "Extreme strength flask (4)";
    itemDef.description = "4 doses of extreme strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 127 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14355:
    itemDef.name = "Extreme strength flask (3)";
    itemDef.description = "3 doses of extreme strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 127 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14353:
    itemDef.name = "Extreme strength flask (2)";
    itemDef.description = "2 doses of extreme strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 127 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14351:
    itemDef.name = "Extreme strength flask (1)";
    itemDef.description = "1 dose of extreme strength potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 127 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14349:
    itemDef.name = "Extreme defence flask (6)";
    itemDef.description = "6 doses of extreme defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10198 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14347:
    itemDef.name = "Extreme defence flask (5)";
    itemDef.description = "5 doses of extreme defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10198 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14345:
    itemDef.name = "Extreme defence flask (4)";
    itemDef.description = "4 doses of extreme defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10198 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14343:
    itemDef.name = "Extreme defence flask (3)";
    itemDef.description = "3 doses of extreme defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10198 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14341:
    itemDef.name = "Extreme defence flask (2)";
    itemDef.description = "2 doses of extreme defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10198 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14339:
    itemDef.name = "Extreme defence flask (1)";
    itemDef.description = "1 dose of extreme defence potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 10198 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14337:
    itemDef.name = "Extreme magic flask (6)";
    itemDef.description = "6 doses of extreme magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33490 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14335:
    itemDef.name = "Extreme magic flask (5)";
    itemDef.description = "5 doses of extreme magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33490 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14333:
    itemDef.name = "Extreme magic flask (4)";
    itemDef.description = "4 doses of extreme magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33490 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14331:
    itemDef.name = "Extreme magic flask (3)";
    itemDef.description = "3 doses of extreme magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33490 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14329:
    itemDef.name = "Extreme magic flask (2)";
    itemDef.description = "2 doses of extreme magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33490 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14327:
    itemDef.name = "Extreme magic flask (1)";
    itemDef.description = "1 dose of extreme magic potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 33490 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14325:
    itemDef.name = "Extreme ranging flask (6)";
    itemDef.description = "6 doses of extreme ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 13111 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14323:
    itemDef.name = "Extreme ranging flask (5)";
    itemDef.description = "5 doses of extreme ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 13111 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14321:
    itemDef.name = "Extreme ranging flask (4)";
    itemDef.description = "4 doses of extreme ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 13111 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14319:
    itemDef.name = "Extreme ranging flask (3)";
    itemDef.description = " 3 doses of extreme ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 13111 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14317:
    itemDef.name = "Extreme ranging flask (2)";
    itemDef.description = "2 doses of extreme ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 13111 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14315:
    itemDef.name = "Extreme ranging flask (1)";
    itemDef.description = "1 dose of extreme ranging potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 13111 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14313:
    itemDef.name = "Super prayer flask (6)";
    itemDef.description = "6 doses of super prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 3016 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14311:
    itemDef.name = "Super prayer flask (5)";
    itemDef.description = "5 doses of super prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 3016 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14309:
    itemDef.name = "Super prayer flask (4)";
    itemDef.description = "4 doses of super prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 3016 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14307:
    itemDef.name = "Super prayer flask (3)";
    itemDef.description = "3 doses of super prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 3016 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14305:
    itemDef.name = "Super prayer flask (2)";
    itemDef.description = "2 doses of super prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 3016 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14303:
    itemDef.name = "Super prayer flask (1)";
    itemDef.description = "1 dose of super prayer potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 3016 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14301:
    itemDef.name = "Overload flask (6)";
    itemDef.description = "6 doses of overload potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 0 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14299:
    itemDef.name = "Overload flask (5)";
    itemDef.description = "5 doses of overload potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 0 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 14297:
    itemDef.name = "Overload flask (4)";
    itemDef.description = "4 doses of overload potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 0 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 14295:
    itemDef.name = "Overload flask (3)";
    itemDef.description = "3 doses of overload potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 0 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 14293:
    itemDef.name = "Overload flask (2)";
    itemDef.description = "2 doses of overload potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 0 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
case 14291:
    itemDef.name = "Overload flask (1)";
    itemDef.description = "1 dose of overload potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 0 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61812;
break;
case 14289:
    itemDef.name = "Prayer renewal flask (6)";
    itemDef.description = "6 doses of prayer renewal.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 926 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61732;
break;
case 14287:
    itemDef.name = "Prayer renewal flask (5)";
    itemDef.description = "5 doses of prayer renewal.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 926 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61729;
break;
case 15123:
    itemDef.name = "Prayer renewal flask (4)";
    itemDef.description = "4 doses of prayer renewal potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 926 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61764;
break;
case 15121:
    itemDef.name = "Prayer renewal flask (3)";
    itemDef.description = "3 doses of prayer renewal potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 926 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61727;
break;
case 15119:
    itemDef.name = "Prayer renewal flask (2)";
    itemDef.description = "2 doses of prayer renewal potion.";
    itemDef.modelZoom = 804;
    itemDef.modelRotationY = 131;
    itemDef.modelRotationX = 198;
    itemDef.modelOffset2 = 1;
    itemDef.modelOffset1 = -1;
    itemDef.newModelColor = new int[] { 926 };
    itemDef.editedModelColor  = new int[] { 33715 };
    itemDef.groundActions = new String[] { null, null, "Take", null, null };
    itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
    itemDef.modelid = 61731;
break;
        case 7340:
            itemDef.name = "Prayer renewal flask (1)";
            itemDef.description = "1 dose of prayer renewal potion";
            itemDef.modelZoom = 804;
            itemDef.modelRotationY = 131;
            itemDef.modelRotationX = 198;
            itemDef.modelOffset2 = 1;
            itemDef.modelOffset1 = -1;
            itemDef.newModelColor = new int[] { 926 };
            itemDef.editedModelColor  = new int[] { 33715 };
            itemDef.groundActions = new String[] { null, null, "Take", null, null };
            itemDef.itemActions = new String[] { "Drink", null, null, null, "Drop" };
            itemDef.modelid = 61812;
            break;
			case 630:
itemDef.name = "Ragefire boots";
itemDef.modelid = 53897;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53330;
itemDef.anInt200 = 53330;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Ragefire boots.";
break;

			case 632:
itemDef.name = "Steadfast Boots";
itemDef.modelid = 53835;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53327;
itemDef.anInt200 = 53327;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Steadfast boots.";
break;

case 634:
itemDef.name = "Glaiven boots";
itemDef.modelid = 53828;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53309;
itemDef.anInt200 = 53309;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Glaiven boots.";
break;

case 20061:
itemDef.modelid = 10247;
itemDef.name = "Abyssal vine whip";
itemDef.description = "Abyssal vine whip";
itemDef.modelZoom = 848;
itemDef.modelRotationY = 324;
itemDef.modelRotationX = 1808;
itemDef.modelOffset1 = 5;
itemDef.modelOffset2 = 38;
itemDef.anInt165 = 10253;
itemDef.anInt200 = 10253;
itemDef.groundActions = new String[5];
itemDef.groundActions[2] = "Take";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wield";
itemDef.itemActions[2] = "Split";
itemDef.itemActions[4] = "Drop";
break;
		case 13362:
		itemDef.modelid = 62714;
		itemDef.name = "Torva full helm";
		itemDef.description = "Torva full helm.";
		itemDef.modelZoom = 672;
		itemDef.modelRotationY = 85;
		itemDef.modelRotationX = 1867;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -3;
		itemDef.anInt165 = 62738;
		itemDef.anInt200 = 62754;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		break;	
/*case 13636:
itemDef.modelid = 14045;
itemDef.name = "Bandos whip";
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 572;
itemDef.modelRotationX = 0;
itemDef.modelOffset1 = 25;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 3287;
itemDef.anInt200 = 3287;
itemDef.stackable = false;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.anInt165 = 14046;
itemDef.anInt200 = 14046;
itemDef.anInt164 = -1;
itemDef.anInt188 = -1;
break;*/
case 18743:
      itemDef.modelid = 3288;
      itemDef.name = "Death cape";
      itemDef.modelZoom = 1385;
      itemDef.modelRotationY = 500;
      itemDef.modelRotationX = 2000;
      itemDef.modelOffset1 = 1;
      itemDef.modelOffset2 = -3;
      itemDef.stackable = false;
      itemDef.value = 1;
      itemDef.membersObject = true;
      itemDef.anInt165 = 3287;
      itemDef.anInt200 = 3287;
      itemDef.itemActions = new String[5];
      itemDef.itemActions[1] = "Wear";
      itemDef.itemActions[2] = "Skull";
      itemDef.itemActions[4] = "Drop";
      itemDef.anInt175 = 14;
      itemDef.anInt197 = 7;
   break;
case 20060:
itemDef.modelid = 2429;
itemDef.name = "Whip vine";
itemDef.modelZoom = 760;
itemDef.modelRotationY = 552;
itemDef.modelRotationX = 28;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 2;
itemDef.stackable = false;
itemDef.value = 50000;
itemDef.membersObject = true;
itemDef.itemActions = new String[5];
itemDef.itemActions[4] = "Drop";
break;
case 13360:
		itemDef.modelid = 62701;
		itemDef.name = "Torva platelegs";
		itemDef.description = "Torva platelegs.";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 474;
		itemDef.modelRotationX = 2045;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62743;
		itemDef.anInt200 = 62760;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;
/*case 15309:
        itemDef.modelid = 1;
		itemDef.name = "Deadly Slayer Cape";
		itemDef.description = "Large winged deadly slayer cape";
	    itemDef.modelZoom = 2000;
		itemDef.modelRotationY = 500;
        itemDef.modelRotationX = 0;
        itemDef.modelOffset1 = -6;
        itemDef.modelOffset2 = 1;
		itemDef.anInt204 = 2;
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
        itemDef.itemActions[4] = "Drop";
    break;*/
	case 13358:
		itemDef.modelid = 62699;
		itemDef.name = "Torva platebody";
		itemDef.description = "Torva Platebody.";
		itemDef.modelZoom = 1506;
		itemDef.modelRotationY = 473;
		itemDef.modelRotationX = 2042;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62746;
		itemDef.anInt200 = 62762;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;
		case 13355:
		itemDef.modelid = 62693;
		itemDef.name = "Pernix cowl";
		itemDef.description = "Pernix cowl";
		itemDef.modelZoom = 800;
		itemDef.modelRotationY = 532;
		itemDef.modelRotationX = 14;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 1;
		itemDef.anInt165 = 62739;
		itemDef.anInt200 = 62756;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62731;
		itemDef.anInt197 = 62727;
	break;

			
case 13679:
itemDef.modelid = 14129;
itemDef.name = "Owner cape";
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 572;
itemDef.modelRotationX = 0;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 14130;
itemDef.anInt200 = 14130;
itemDef.stackable = false;
itemDef.anInt175 = -1;
itemDef.anInt197 = -1;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.anInt204 = 0;
break;

case 1007:
        itemDef.modelid = 65270;
        itemDef.name = "Completionist cape";
        itemDef.modelZoom = 1316;
        itemDef.modelRotationY = 252;
        itemDef.modelRotationX = 1020;
        itemDef.modelOffset1 = -1;
        itemDef.modelOffset2 = 24;
        itemDef.value = 5000000;
        itemDef.anInt165 = 65297;
        itemDef.anInt200 = 65316;
        itemDef.groundActions = new String[5];
        itemDef.groundActions[2] = "Take";
        itemDef.itemActions = new String[5];
        itemDef.itemActions[1] = "Wear";
        itemDef.itemActions[2] = "Customise";
        itemDef.itemActions[3] = "Features";
        itemDef.itemActions[4] = "Destroy";
        itemDef.editedModelColor = new int[4];
        itemDef.newModelColor = new int[4];
        itemDef.editedModelColor[0] = -322;
        itemDef.editedModelColor[1] = -336;
        itemDef.editedModelColor[2] = -350;
        itemDef.editedModelColor[3] = -2541;
        itemDef.newModelColor[0] = -322;
        itemDef.newModelColor[1] = -336;
        itemDef.newModelColor[2] = -350;
        itemDef.newModelColor[3] = -2541;
    break;

case 4317:
        itemDef.modelid = 65273;
        itemDef.name = "Completionist hood";
        itemDef.modelZoom = 760;
        itemDef.modelRotationY = 11;
        itemDef.modelRotationX = 81;
        itemDef.modelOffset1 = 1;
        itemDef.modelOffset2 = -3;
        itemDef.anInt165 = 65292;
        itemDef.anInt200 = 65310;
        itemDef.groundActions = new String[5];
        itemDef.groundActions[2] = "Take";
        itemDef.itemActions = new String[5];
        itemDef.itemActions[1] = "Wear";
        itemDef.itemActions[4] = "Drop";
        itemDef.editedModelColor = new int[4];
        itemDef.newModelColor = new int[4];
        itemDef.editedModelColor[0] = -322;
        itemDef.editedModelColor[1] = -336;
        itemDef.editedModelColor[2] = -350;
        itemDef.editedModelColor[3] = -2541;
        itemDef.newModelColor[0] = -322;
        itemDef.newModelColor[1] = -336;
        itemDef.newModelColor[2] = -350;
        itemDef.newModelColor[3] = -2541;
    break;

                case 12743:
                itemDef.modelid = 6;
                itemDef.groundActions = new String[5];
                itemDef.groundActions[2] = "Take";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[1] = "Wear";
                itemDef.itemActions[4] = "Drop";
                itemDef.modelZoom = 2000;
                itemDef.modelRotationY = 500;
                itemDef.modelRotationX = 0;
                itemDef.anInt204 = 14;
                itemDef.modelOffset1 = -6;
                itemDef.modelOffset2 = 1;
                itemDef.anInt165 = 7;
                itemDef.anInt200 = 7;
                itemDef.name = "@red@ Donator Cape @red@";
                itemDef.description = "Donator Cape";
                break;

             


	case 13354:
		itemDef.modelid = 62709;
		itemDef.name = "Pernix body";
		itemDef.description = "Pernix body";
		itemDef.modelZoom = 1378;
		itemDef.modelRotationY = 485;
		itemDef.modelRotationX = 2042;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 7;
		itemDef.anInt165 = 62744;
		itemDef.anInt200 = 62765;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13352:
		itemDef.modelid = 62695;
		itemDef.name = "Pernix chaps";
		itemDef.description = "Pernix chaps";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 504;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = 4;
		itemDef.modelOffset2 = 3;
		itemDef.anInt165 = 62741;
		itemDef.anInt200 = 62757;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13350:
		itemDef.modelid = 62710;
		itemDef.name = "Virtus mask";
		itemDef.description = "Virtus mask";
		itemDef.modelZoom = 928;
		itemDef.modelRotationY = 406;
		itemDef.modelRotationX = 2041;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62736;
		itemDef.anInt200 = 62755;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62728;
		itemDef.anInt197 = 62728;
	break;

	case 13348:
		itemDef.modelid = 62704;
		itemDef.name = "Virtus robe top";
		itemDef.description = "Virtus robe top";
		itemDef.modelZoom = 1122;
		itemDef.modelRotationY = 488;
		itemDef.modelRotationX = 3;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62748;
		itemDef.anInt200 = 62764;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13346:
		itemDef.modelid = 62700;
		itemDef.name = "Virtus robe legs";
		itemDef.description = "Virtus robe legs";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 498;
		itemDef.modelRotationX = 2045;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 4;
		itemDef.anInt165 = 62742;
		itemDef.anInt200 = 62758;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;
	case 13344:
		itemDef.modelid = 62694;
		itemDef.name = "Ancient ceremonial hood";
		itemDef.modelZoom = 980;
		itemDef.modelRotationY = 208;
		itemDef.modelRotationX = 220;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -18;
		itemDef.anInt165 = 62737;
		itemDef.anInt200 = 62753;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62730;
		itemDef.anInt197 = 62730;
	break;

	case 13342:
		itemDef.modelid = 62705;
		itemDef.name = "Ancient ceremonial top";
		itemDef.modelZoom = 1316;
		itemDef.modelRotationY = 477;
		itemDef.modelRotationX = 9;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 13;
		itemDef.anInt165 = 62745;
		itemDef.anInt200 = 62763;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt204 = 54;
	break;

	case 13340:
		itemDef.modelid = 62707;
		itemDef.name = "Ancient ceremonial legs";
		itemDef.modelZoom = 1828;
		itemDef.modelRotationY = 539;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62740;
		itemDef.anInt200 = 62759;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt204 = 40;
		itemDef.anInt196 = 30;
		itemDef.anInt184 = 100;
	break;

	case 13370:
		itemDef.modelid = 62697;
		itemDef.name = "Ancient ceremonial gloves";
		itemDef.modelZoom = 548;
		itemDef.modelRotationY = 618;
		itemDef.modelRotationX = 1143;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62735;
		itemDef.anInt200 = 62752;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13336:
		itemDef.modelid = 62696;
		itemDef.name = "Ancient ceremonial boots";
		itemDef.modelZoom = 676;
		itemDef.modelRotationY = 63;
		itemDef.modelRotationX = 106;
		itemDef.modelOffset1 = 5;
		itemDef.modelOffset2 = -1;
		itemDef.anInt165 = 62734;
		itemDef.anInt200 = 62751;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;

}
		return itemDef;
	}
	
	public static void unpackConfig(NamedArchive streamLoader) {
		stream = new Stream(FileOperations.ReadFile(signlink.findcachedir() + "obj.dat"));
		Stream stream = new Stream(FileOperations.ReadFile(signlink.findcachedir() + "obj.idx"));
		totalItems = stream.readUnsignedWord();
		streamIndices = new int[totalItems + 50000];
		int i = 2;
		for (int j = 0; j < totalItems; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		cache = new ItemDef[10];
		for (int k = 0; k < 10; k++)
			cache[k] = new ItemDef();
	}

	public void setDefaults() {
		modelid = 0;
		name = null;
		description = null;
		editedModelColor = null;
		newModelColor = null;
		modelZoom = 2000;
		modelRotationY = 0;
		modelRotationX = 0;
		anInt204 = 0;
		modelOffset1 = 0;
		modelOffset2 = 0;
		stackable = false;
		value = 1;
		membersObject = false;
		groundActions = null;
		itemActions = null;
		anInt165 = -1;
		anInt188 = -1;
		aByte205 = 0;
		anInt200 = -1;
		anInt164 = -1;
		aByte154 = 0;
		anInt185 = -1;
		anInt162 = -1;
		anInt175 = -1;
		anInt166 = -1;
		anInt197 = -1;
		anInt173 = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		anInt196 = 0;
		anInt184 = 0;
		team = 0;
		lendID = -1;
		lentItemID = -1;
	}
	
	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				modelid = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readString();
			else if (i == 3)
				description = stream.readString();
			else if (i == 4)
				modelZoom = stream.readUnsignedWord();
			else if (i == 5)
				modelRotationY = stream.readUnsignedWord();
			else if (i == 6)
				modelRotationX = stream.readUnsignedWord();
			else if (i == 7) {
				modelOffset1 = stream.readUnsignedWord();
				if (modelOffset1 > 32767)
					modelOffset1 -= 0x10000;
			} else if (i == 8) {
				modelOffset2 = stream.readUnsignedWord();
				if (modelOffset2 > 32767)
					modelOffset2 -= 0x10000;
			} else if (i == 10)
				stream.readUnsignedWord();
			else if (i == 11)
				stackable = true;
			else if (i == 12)
				value = stream.readUnsignedWord();
			else if (i == 16)
				membersObject = true;
			else if (i == 23) {
				anInt165 = stream.readUnsignedWord();
				aByte205 = stream.readSignedByte();
			} else if (i == 24)
				anInt188 = stream.readUnsignedWord();
			else if (i == 25) {
				anInt200 = stream.readUnsignedWord();
				aByte154 = stream.readSignedByte();
			} else if (i == 26)
				anInt164 = stream.readUnsignedWord();
			else if (i >= 30 && i < 35) {
				if (groundActions == null)
					groundActions = new String[5];
				groundActions[i - 30] = stream.readString();
				if (groundActions[i - 30].equalsIgnoreCase("hidden"))
					groundActions[i - 30] = null;
			} else if (i >= 35 && i < 40) {
				if (itemActions == null)
					itemActions = new String[5];
				itemActions[i - 35] = stream.readString();
				if (itemActions[i - 35].equalsIgnoreCase("null"))
					itemActions[i - 35] = null;
			} else if (i == 40) {
				int j = stream.readUnsignedByte();
				editedModelColor = new int[j];
				newModelColor = new int[j];
				for (int k = 0; k < j; k++) {
					editedModelColor[k] = stream.readUnsignedWord();
					newModelColor[k] = stream.readUnsignedWord();
				}
			} else if (i == 78)
				anInt185 = stream.readUnsignedWord();
			else if (i == 79)
				anInt162 = stream.readUnsignedWord();
			else if (i == 90)
				anInt175 = stream.readUnsignedWord();
			else if (i == 91)
				anInt197 = stream.readUnsignedWord();
			else if (i == 92)
				anInt166 = stream.readUnsignedWord();
			else if (i == 93)
				anInt173 = stream.readUnsignedWord();
			else if (i == 95)
				anInt204 = stream.readUnsignedWord();
			else if (i == 97)
				certID = stream.readUnsignedWord();
			else if (i == 98)
				certTemplateID = stream.readUnsignedWord();
			else if (i >= 100 && i < 110) {
				if (stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}
				stackIDs[i - 100] = stream.readUnsignedWord();
				stackAmounts[i - 100] = stream.readUnsignedWord();
			} else if (i == 110)
				anInt167 = stream.readUnsignedWord();
			else if (i == 111)
				anInt192 = stream.readUnsignedWord();
			else if (i == 112)
				anInt191 = stream.readUnsignedWord();
			else if (i == 113)
				anInt196 = stream.readSignedByte();
			else if (i == 114)
				anInt184 = stream.readSignedByte() * 5;
			else if (i == 115)
				team = stream.readUnsignedByte();
			else if (i == 116)
				lendID = stream.readUnsignedWord();
			else if (i == 117)
				lentItemID = stream.readUnsignedWord();
		} while (true);
	}
	
	public static void nullLoader() {
		mruNodes2 = null;
		mruNodes1 = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public boolean method192(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		return flag;
	}

	public Model method194(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return null;
		Model model = Model.method462(k);
		if (l != -1) {
			Model model_1 = Model.method462(l);
			Model models[] = { model, model_1 };
			model = new Model(2, models);
		}
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.method476(editedModelColor[i1], newModelColor[i1]);
		}
		return model;
	}

	public boolean method195(int j) {
		int k = anInt165;
		int l = anInt188;
		int i1 = anInt185;
		if (j == 1) {
			k = anInt200;
			l = anInt164;
			i1 = anInt162;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		if (i1 != -1 && !Model.method463(i1))
			flag = false;
		return flag;
	}

	public Model method196(int i) {
		int j = anInt165;
		int k = anInt188;
		int l = anInt185;
		if (i == 1) {
			j = anInt200;
			k = anInt164;
			l = anInt162;
		}
		if (j == -1)
			return null;
		Model model = Model.method462(j);
		if (k != -1)
			if (l != -1) {
				Model model_1 = Model.method462(k);
				Model model_3 = Model.method462(l);
				Model model_1s[] = { model, model_1, model_3 };
				model = new Model(3, model_1s);
			} else {
				Model model_2 = Model.method462(k);
				Model models[] = { model, model_2 };
				model = new Model(2, models);
			}
		if (i == 0 && aByte205 != 0)
			model.method475(0, aByte205, 0);
		if (i == 1 && aByte154 != 0)
			model.method475(0, aByte154, 0);
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.method476(editedModelColor[i1], newModelColor[i1]);
		}
		return model;
	}
	
	public void toNote() {
		ItemDef itemDef = forID(certTemplateID);
		modelid = itemDef.modelid;
		modelZoom = itemDef.modelZoom;
		modelRotationY = itemDef.modelRotationY;
		modelRotationX = itemDef.modelRotationX;
		anInt204 = itemDef.anInt204;
		modelOffset1 = itemDef.modelOffset1;
		modelOffset2 = itemDef.modelOffset2;
		editedModelColor = itemDef.editedModelColor;
		newModelColor = itemDef.newModelColor;
		ItemDef itemDef_1 = forID(certID);
		name = itemDef_1.name;
		membersObject = itemDef_1.membersObject;
		value = itemDef_1.value;
		String s = "a";
		char c = itemDef_1.name.charAt(0);
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
		stackable = true;
	}
	
	public void toLend() {
		ItemDef itemDef = forID(lentItemID);
		itemActions = new String[5];
		modelid = itemDef.modelid;
		modelOffset1 = itemDef.modelOffset1;
		modelRotationX = itemDef.modelRotationX;
		modelOffset2 = itemDef.modelOffset2;
		modelZoom = itemDef.modelZoom;
		modelRotationY = itemDef.modelRotationY;
		anInt204 = itemDef.anInt204;
		value = 0;
		ItemDef itemDef_1 = forID(lendID);
		anInt166 = itemDef_1.anInt166;
		editedModelColor = itemDef_1.editedModelColor;
		anInt185 = itemDef_1.anInt185;
		anInt188 = itemDef_1.anInt188;
		anInt173 = itemDef_1.anInt173;
		anInt175 = itemDef_1.anInt175;
		groundActions = itemDef_1.groundActions;
		anInt165 = itemDef_1.anInt165;
		name = itemDef_1.name;
		anInt200 = itemDef_1.anInt200;
		membersObject = itemDef_1.membersObject;
		anInt197 = itemDef_1.anInt197;
		anInt164 = itemDef_1.anInt164;
		anInt162 = itemDef_1.anInt162;
		newModelColor = itemDef_1.newModelColor;
		team = itemDef_1.team;
		if (itemDef_1.itemActions != null) {
			for (int i_33_ = 0; i_33_ < 4; i_33_++)
				itemActions[i_33_] = itemDef_1.itemActions[i_33_];
		}
		itemActions[4] = "Discard";
	}

	public static Sprite getSprite(int i, int j, int k) {
		if (k == 0) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);
			if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if (itemDef.stackIDs == null)
			j = -1;
		if (j > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];
			if (i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.method201(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		if (itemDef.lentItemID != -1) {
			sprite = getSprite(itemDef.lendID, 50, 0);
			if (sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Texture.method364();
		int k3 = itemDef.modelZoom;
		if (k == -1)
			k3 = (int) ((double) k3 * 1.5D);
		if (k > 0)
			k3 = (int) ((double) k3 * 1.04D);
		int l3 = Texture.anIntArray1470[itemDef.modelRotationY] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotationY] * k3 >> 16;
		model.method482(itemDef.modelRotationX, itemDef.anInt204, itemDef.modelRotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (sprite2.myPixels[i5 + j4 * 32] == 0)
					if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
		}
		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (sprite2.myPixels[j5 + k4 * 32] == 0)
						if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
			}
		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;
			}
		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (itemDef.lentItemID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (k == 0)
			mruNodes1.removeFromCache(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		if (itemDef.stackable)
			sprite2.maxWidth = 33;
		else
			sprite2.maxWidth = 32;
		sprite2.maxHeight = j;
		return sprite2;
	}

	public Model method201(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if (j != -1)
				return forID(j).method201(1);
		}
		Model model = (Model) mruNodes2.insertFromCache(id);
		if (model != null)
			return model;
		model = Model.method462(modelid);
		if (model == null)
			return null;
		if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
			model.method478(anInt167, anInt191, anInt192);
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.method476(editedModelColor[l], newModelColor[l]);
		}
		model.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
		model.aBoolean1659 = true;
		mruNodes2.removeFromCache(model, id);
		return model;
	}

	public Model method202(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if (j != -1)
				return forID(j).method202(1);
		}
		Model model = Model.method462(modelid);
		if (model == null)
			return null;
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.method476(editedModelColor[l], newModelColor[l]);
		}
		return model;
	}

	public ItemDef() {
		id = -1;
	}

	public byte aByte154;
	public int value;
	public int[] editedModelColor;
	public int id;
	static MRUNodes mruNodes1 = new MRUNodes(100);
	public static MRUNodes mruNodes2 = new MRUNodes(50);
	public int[] newModelColor;
	public boolean membersObject;
	public int anInt162;
	public int certTemplateID;
	public int anInt164;
	public int anInt165;
	public int anInt166;
	public int anInt167;
	public String groundActions[];
	public int modelOffset1;
	public String name;
	public static ItemDef[] cache;
	public int anInt173;
	public int modelid;
	public int anInt175;
	public boolean stackable;
	public String description;
	public int certID;
	public static int cacheIndex;
	public int modelZoom;
	public static boolean isMembers = true;
	public static Stream stream;
	public int anInt184;
	public int anInt185;
	public int anInt188;
	public String itemActions[];
	public int modelRotationY;
	public int anInt191;
	public int anInt192;
	public int[] stackIDs;
	public int modelOffset2;
	public static int[] streamIndices;
	public int anInt196;
	public int anInt197;
	public int modelRotationX;
	public int anInt200;
	public int[] stackAmounts;
	public int team;
	public static int totalItems;
	public int anInt204;
	public byte aByte205;
	public int lendID;
	public int lentItemID;
}
