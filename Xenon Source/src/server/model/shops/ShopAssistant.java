package server.model.shops;

import server.Config;
import server.Server;
import server.model.items.Item;
import server.model.players.Client;

public class ShopAssistant {

	private Client c;
	
	public ShopAssistant(Client client) {
		this.c = client;
	}
	
	/**
	*Shops
	**/
	
	public void openShop(int ShopID){
		c.getItems().resetItems(3823);
		resetShop(ShopID);
		c.isShopping = true;
		c.myShopId = ShopID;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(Server.shopHandler.ShopName[ShopID], 3901);
	}

	public boolean shopSellsItem(int itemID) {
                for (int i = 0; i < Server.shopHandler.ShopItems.length; i++) {
                        if(itemID == (Server.shopHandler.ShopItems[c.myShopId][i] - 1)) {
                                return true;
                        }
                }
                return false;
        }
	
	public void updatePlayerShop() {
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (Server.playerHandler.players[i] != null) {
				if (Server.playerHandler.players[i].isShopping == true && Server.playerHandler.players[i].myShopId == c.myShopId && i != c.playerId) {
					Server.playerHandler.players[i].updateShop = true;
				}
			}
		}
	}
	
	
	public void updateshop(int i){
		resetShop(i);
	}
	
	public void resetShop(int ShopID) {
		synchronized(c) {
			int TotalItems = 0;
			for (int i = 0; i < Server.shopHandler.MaxShopItems; i++) {
				if (Server.shopHandler.ShopItems[ShopID][i] > 0) {
					TotalItems++;
				}
			}
			if (TotalItems > Server.shopHandler.MaxShopItems) {
				TotalItems = Server.shopHandler.MaxShopItems;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
 			int TotalCount = 0;
			for (int i = 0; i < Server.shopHandler.ShopItems.length; i++) {
				if (Server.shopHandler.ShopItems[ShopID][i] > 0 || i <= Server.shopHandler.ShopItemsStandard[ShopID]) {
					if (Server.shopHandler.ShopItemsN[ShopID][i] > 254) {
						c.getOutStream().writeByte(255); 					
						c.getOutStream().writeDWord_v2(Server.shopHandler.ShopItemsN[ShopID][i]);	
					} else {
						c.getOutStream().writeByte(Server.shopHandler.ShopItemsN[ShopID][i]);
					}
					if (Server.shopHandler.ShopItems[ShopID][i] > Config.ITEM_LIMIT || Server.shopHandler.ShopItems[ShopID][i] < 0) {
						Server.shopHandler.ShopItems[ShopID][i] = Config.ITEM_LIMIT;
					}
					c.getOutStream().writeWordBigEndianA(Server.shopHandler.ShopItems[ShopID][i]);
					TotalCount++;
				}
				if (TotalCount > TotalItems) {
					break;
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();	
		}
	}
	
	
	public double getItemShopValue(int ItemID, int Type, int fromSlot) {
	if(c.myShopId == 7390){
			return c.myShopClient.playerShopP[fromSlot];
		}
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = Server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		
		TotPrice = ShopValue;

		if (Server.shopHandler.ShopBModifier[c.myShopId] == 1) {
			TotPrice *= 1; 
			TotPrice *= 1;
			if (Type == 1) {
				TotPrice *= 1; 
			}
		} else if (Type == 1) {
			TotPrice *= 1; 
		}
		return TotPrice;
	}
	
	public void openPlayerShop(Client o){	
		if(o == null || o.properLogout)
			return;	
		c.getItems().resetItems(3823);
		resetShop(o);
		c.myShopClient = o;
		c.myShopId = 7390;
		c.isShopping = true;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(o.playerName+"'s Shop!", 3901);
	}
	public int[] fixArray(int[] array){
		int arrayPos = 0;
		int[] newArray = new int[array.length];
		for(int x = 0; x < array.length; x++){
			if(array[x] != 0){
				newArray[arrayPos] = array[x];
				arrayPos++;
			}
		}
		return newArray;
	}

	public void fixShop(Client o){
		o.playerShop = fixArray(o.playerShop);
		o.playerShopN = fixArray(o.playerShopN);
		o.playerShopP = fixArray(o.playerShopP);	
	}

	public void resetShop(Client o) {
		synchronized(c) {
			fixShop(o);
			for (int x = 0; x < 10; x++) {
				if (o.playerShopN[x] <= 0) {
					o.playerShop[x] = 0;
				}
			}
			int TotalItems = 0;
			for (int i = 0; i < 10; i++) {
				if (o.playerShop[i] > 0) {
					TotalItems++;
				}
			}
			if (TotalItems > 10) {
				TotalItems = 10;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
 			int TotalCount = 0;
			for (int i = 0; i < o.playerShop.length; i++) {
				if (o.playerShop[i] > 0) {
					if (o.playerShopN[i] > 254) {
						c.getOutStream().writeByte(255); 					
						c.getOutStream().writeDWord_v2(o.playerShopN[i]);	
					} else {
						c.getOutStream().writeByte(o.playerShopN[i]);
					}
					c.getOutStream().writeWordBigEndianA((o.playerShop[i]+1));
					TotalCount++;
				}
				if (TotalCount > TotalItems) {
					break;
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();	
		}
	}
	
	public int getItemShopValue(int itemId) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == itemId) {
					return (int)Server.itemHandler.ItemList[i].ShopValue;
				}
			}	
		}
		return 0;
	}
	
	
	
	/**
	*buy item from shop (Shop Price)
	**/
	
	public void buyFromShopPrice(int removeId, int removeSlot){
		int ShopValue = (int)Math.floor(getItemShopValue(removeId, 0, removeSlot));
		ShopValue *= 1;
		String ShopAdd = "";
		if (c.myShopId == 7390 && c.myShopClient != null && !c.myShopClient.playerName.equals(c.playerName)) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + c.myShopClient.playerShopP[removeSlot] + " coins.");
			return;
		}else if (c.myShopId == 7390 && c.myShopClient != null && c.myShopClient.playerName.equals(c.playerName)) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + c.playerShopP[removeSlot] + " coins.");
			return;
		}

	if(c.myShopId == 7390){
		c.sendMessage("You choose your price when using POS.");
		return;
	}

	

		
		if (c.myShopId == 18) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " PC Points.");
			return;
		}
		if (c.myShopId == 73) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " PvP Points.");
			return;
		}
		if (c.myShopId == 74) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " PvP Points.");
			return;
		}
		if (c.myShopId == 10) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Donator Points.");
			return;
		}
		if (c.myShopId == 15) {
			c.sendMessage("This item current costs " + c.getItems().getUntradePrice(removeId) + " coins.");
			return;
		}
		if (c.myShopId == 48) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " SlayerPoints.");
			return;
		}
		if (c.myShopId == 95) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " PF Points.");
			return;
		}
		if (c.myShopId == 20) {
         c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Vote Points.");
         return;
		 }
		 if (c.myShopId == 29) {
         c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Tokkul.");
         return;
		 }
		 if (c.myShopId == 11) {
         c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Level Points.");
         return;
      }
		if (c.myShopId == 84) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Dungeoneering Points.");
			return;
		}
		if (c.myShopId == 85) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Dungeoneering Points.");
			return;
		}
		if (ShopValue >= 1000 && ShopValue < 1000000) {
			ShopAdd = " (" + (ShopValue / 1000) + "K)";
		} else if (ShopValue >= 1000000) {
			ShopAdd = " (" + (ShopValue / 1000000) + " million)";
		}
		c.sendMessage(c.getItems().getItemName(removeId)+": currently costs "+ShopValue+" coins"+ShopAdd);
	}
	
public int getSpecialItemValue(int id) {
		switch (id) {
			case 17293:
			return 1250;
			case 17295:
			return 1350;
			case 6914:
			case 15610:
			case 15612:
			case 15616:
			case 15618:
			case 15220:
			case 4513:
			case 4514:
			case 4515:
			case 4516:
			return 100;
			case 12435:
			case 12434:
			case 4503:
			case 4504:
			case 4505:
			case 4506:
			case 4507:
			return 50;
			case 16955:
			return 2850;
			case 16953:
			return 1750;
			case 2570:
			return 10;
			case 14641:
			case 14642:
			case 4508:
			case 4509:
			case 4510:
			case 4511:
			case 4512:
			return 200;
			case 11728:
			return 120;
			case 15332:
			return 30;
			case 15308:
			case 15312:
			case 15316:
			case 15320:
			case 15324:
			return 10;
			case 15328:
			return 25;
			case 16951:
			return 1800;
			case 16403:
			return 2650;
			case 16401:
			return 2450;
			case 15602:
			return 600;
			case 16399:
			return 2100;
			case 16909:
			return 5900;
			case 15600:
			return 700;
			case 15604:
			return 700;
			case 16907:
			return 2650;
			case 16905:
			return 2450;
			
			case 16711:
			case 17259:
			case 16667:
			return 3000;
			case 16709:
			case 17257:
			case 16665:
			return 1500;
			
			case 16663:
			return 2300;
			case 17361:
			return 2550;
			case 17359:
			return 2300;
			case 17357:
			return 550;
			
			case 17061:
			case 17193:
			case 17339:
			return 2400;
			case 6916:
			case 6918:
			case 6920:
			case 6922:
			case 6924:
			return 50;
			case 11663:
			case 11664:
			case 11665:
			case 8842:
			return 30;
			
			case 8839:
			case 8840:
			return 40;
			case 12675:
			case 12681:
			case 12679:
			case 14637:
			case 12677:
			case 12667:
			case 6570:
			case 10548:
			case 19748:
			case 19749:
			case 19757:
			return 150;
			case 15608:
			case 15614:
			case 15620:
case 19272:
case 19275:
case 19278:
case 19281:
case 19284:
case 19287:
case 19290:
case 19293:
case 19296:
case 19299:
case 19302:
case 19305:
case 13107:
case 13109:
case 13111:
case 13113:
case 13115:


			return 250;
			case 11730:
			return 600;
			case 16357:
			case 16291:
			return 500;
			
		
			case 13858:
			case 13861:
			case 13864:
			case 13884:
			case 13890:
			case 13896:
			case 13887:
			case 13893:
			case 13860:
case 13863:
case 13866:
case 13872:
case 13875:
case 13878:
case 13877:
case 13898:
case 13897:
case 13886:
case 13892:
case 13889:
case 13895:
case 13907:
case 13869:
case 13902:
case 13905:

			return 1000;
case 13876:
case 13870:
case 13873:
return 650;
case 17273:
return 250;
case 13883:
case 13879:
			return 5;
			case 16755:
			return 1490;
			case 17237:
			case 16865:
			return 1750;
			case 16931:
			case 17171:
			return 370;
			case 13899:
			case 11718:
			case 11720:
			case 11722:
			case 11724:
			case 11726:
			return 1000;
			case 10499:
			case 8850:
			case 16407:
			case 17021:
			case 17019:
			return 20;
			case 16411:
			case 17025:
			return 30;
			case 8845:
			return 5;
			case 8846:
			return 10;
			case 8847:
			return 15;
			case 8848:
			case 15771:
			case 16405:
			return 20;
			case 8849:
			return 25;
			case 10551:
			case 10531:
			case 10533:
			case 10534:
			case 6889:
			return 50;
			case 10555:
			return 125;
			case 15438:
			return 425;
			
case 13904:
case 13901:
			case 2581:
			case 2577:
			return 150;
			case 1419:
			return 500;
			case 17017:
			return 1450;
			case 12210:
			case 12213:
			case 12216:
			case 12219:
			case 12222:
			case 15018:
			case 15019:
			case 15020:
			case 15021:
			return 100;
			case 14936:
			return 300;
			case 14938:
			return 250;
			case 4716:
			case 4718:
			case 4720:
			case 4722:
			return 125;
			case 10887:
			return 250;
			case 18334:
			case 18333:
			case 18335:
			return 690;
			case 19669:
			return 725;
			case 18361:
			return 3000;
			case 13358:
			case 13360:
			case 13362:
			return 4000;
			case 19709:
			return 500;
			case 13355:
			case 13354:
			case 13352:
			case 13348:
			case 13346:
			case 13350:
			return 4000;
			case 16707:
			return 750;
			case 17255:
			return 825;
			case 16685:
			return 800;
			case 16355:
			return 200;
			case 16971:
			return 600;
			case 16289:
			return 150;
			case 18739:
			case 18740:
			case 18741:
			case 18742:
			return 100;
			case 18747:
			return 250;
			case 16425:
			return 4000;
			case 16423:
			case 19780:
			return 2850;
			case 6585:
			return 15;
			case 15051:
			return 300;
			case 4151:
			return 50;
			case 15039:
			return 2650;
			case 15389:
			return 30;
			case 15272:
			return 10;
			case 7453:
			case 7454:
			case 7455:
			case 7456:
			case 7457:
			case 7458:
			case 7459:
			case 7460:
			case 7461:
			case 7462:
			return 35;
			case 15441:
			case 15442:
			case 15443:
			case 15444:
			return 275;
			case 13263:
			return 500;
			case 15492:
			return 1250;
			case 12862:
			return 200;
			
			
			case 11235:
			return 175;
			
			case 10696:
				return 60;
			case 15241:
			return 750;
			
			
			/**
			*Start of VotePointShop
			*/
			case 989:
			return 1;
			case 10334:
			case 10330:
			case 10332:
			case 10336:
			case 10342:
			case 10338:
			case 10340:
			case 10344:
			case 10350:
			case 10348:
			case 10346:
			case 10352:
			return 15;
			case 19336:
			case 19337:
			case 19338:
			case 19341:
			case 19342:
			case 19343:
			return 5;
			case 4566:
			case 4565:
			case 7668:
			case 14595:
			case 14603:
			case 1037:
			return 10;
			case 1050:
			return 60;
			case 14605:
			case 14602:
			case 2528:
			return 5;
			/**
			*End of vote point shop
			*/
			/**
			*Zombie Shop Start
			*/
			case 7594:
			return 1000;
			case 13660:
			return 600;
			case 15825:
			return 600;
			case 9920:
			return 800;
			/**
			*End of zombie Shop
			**/
			/**
			*Start of Level Point Shop
			**/
			case 11694:
			return 500;
			case 11696:
			return 200;
			case 11698:
			return 500;
			case 11700:
			return 200;
			case 13734:
			return 800;
			case 13736:
			return 850;
			case 13738:
			return 1250;
			case 13744:
			return 1250;
			case 13742:
			return 1250;
			case 14484:
			return 500;
			case 18349:
			return 1250;
			case 18353:
			return 1000;
			case 18351:
			return 1000;
			case 18355:
			return 700;
			case 18357:
			return 300;
			case 18359:
			return 375;
			case 20072:
			return 50;
			case 11732:
			return 20;
			case 14499:
			return 75;
			case 14497:
			return 75;
			case 19785:
			return 100;
			case 19786:
			return 100;
			case 15449:
			return 25;
			case 15454:
			return 25;
			case 15459:
			return 40;
			case 15464:
			return 40;
			/**
			*End of level point shop
			**/
			
		}
		return 2147483647;
	}
	
	
	
	/**
	*Sell item to shop (Shop Price)
	**/
	public void sellToShopPrice(int removeId, int removeSlot) {
		for (int i : Config.ITEM_SELLABLE) {

			
			if (i == removeId) {
				c.sendMessage("You can't sell "+c.getItems().getItemName(removeId).toLowerCase()+".");
				return;
			} 

		}
		boolean IsIn = false;
		if (Server.shopHandler.ShopSModifier[c.myShopId] > 1) {
			for (int j = 0; j <= Server.shopHandler.ShopItemsStandard[c.myShopId]; j++) {
				if (removeId == (Server.shopHandler.ShopItems[c.myShopId][j] - 1)) {
					IsIn = true;
					break;
				}
			}
		} else {
			IsIn = true;
		}
		if (IsIn == false) {
			c.sendMessage("You can't sell "+c.getItems().getItemName(removeId).toLowerCase()+" to this store.");
		} else {
			int ShopValue = (int)Math.floor(getItemShopValue(removeId, 1, removeSlot));
			String ShopAdd = "";
			if (ShopValue >= 1000 && ShopValue < 1000000) {
				ShopAdd = " (" + (ShopValue / 1000) + "K)";
			} else if (ShopValue >= 1000000) {
				ShopAdd = " (" + (ShopValue / 1000000) + " million)";
			}
			c.sendMessage(c.getItems().getItemName(removeId)+": shop will buy for "+ShopValue+" coins"+ShopAdd);
		}
	}
	
	
	
	public boolean sellItem(int itemID, int fromSlot, int amount) {
	if(c.myShopId == 7390){
			for (int i : Config.ITEM_TRADEABLE)  {
				if(i == itemID) {
					c.sendMessage("You can't sell this item.");
					return false;
				}		
			}
			if(c.playerName.equals(c.myShopClient.playerName)){
			c.sellingId = itemID;
			c.sellingN = amount;
			c.sellingS = fromSlot;
			c.xInterfaceId = 7390;
			c.outStream.createFrame(27);
			}else{
				c.sendMessage("You can only sell items on your own store.");
			}
			return true;
		}
			if(c.inTrade) {
            		c.sendMessage("You cant sell items to the shop while your in trade!");
           		return false;
            		}
		if (c.myShopId == 14)
			return false;
		for (int i : Config.ITEM_SELLABLE) {
			if (i == itemID) {
				c.sendMessage("You can't sell "+c.getItems().getItemName(itemID).toLowerCase()+".");
				return false;
			} 
		}
		
		if (amount > 0 && itemID == (c.playerItems[fromSlot] - 1)) {
			if (Server.shopHandler.ShopSModifier[c.myShopId] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= Server.shopHandler.ShopItemsStandard[c.myShopId]; i++) {
					if (itemID == (Server.shopHandler.ShopItems[c.myShopId][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					c.sendMessage("You can't sell "+c.getItems().getItemName(itemID).toLowerCase()+" to this store.");
					return false;
				}
			}

			if (amount > c.playerItemsN[fromSlot] && (Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == true || Item.itemStackable[(c.playerItems[fromSlot] - 1)] == true)) {
				amount = c.playerItemsN[fromSlot];
			} else if (amount > c.getItems().getItemAmount(itemID) && Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == false && Item.itemStackable[(c.playerItems[fromSlot] - 1)] == false) {
				amount = c.getItems().getItemAmount(itemID);
			}
			//double ShopValue;
			//double TotPrice;
			int TotPrice2 = 0;
			//int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(getItemShopValue(itemID, 1, fromSlot));
				if (c.getItems().freeSlots() > 0 || c.getItems().playerHasItem(995)) {
					if (Item.itemIsNote[itemID] == false) {
						c.getItems().deleteItem(itemID, c.getItems().getItemSlot(itemID), 1);
					} else {
						c.getItems().deleteItem(itemID, fromSlot, 1);
					}
					c.getItems().addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					c.sendMessage("You don't have enough space in your inventory.");
					break;
				}
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return true;
	}
	
public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = c.getItems().getUnnotedItem(itemID);
		}
		for (int i = 0; i < Server.shopHandler.ShopItems.length; i++) {
			if ((Server.shopHandler.ShopItems[c.myShopId][i] - 1) == itemID) {
				Server.shopHandler.ShopItemsN[c.myShopId][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < Server.shopHandler.ShopItems.length; i++) {
				if (Server.shopHandler.ShopItems[c.myShopId][i] == 0) {
					Server.shopHandler.ShopItems[c.myShopId][i] = (itemID + 1);
					Server.shopHandler.ShopItemsN[c.myShopId][i] = amount;
					Server.shopHandler.ShopItemsDelay[c.myShopId][i] = 0;
					break;
				}
			}
		}
		return true;
	}
	
	public long buyDelay;
	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if(System.currentTimeMillis() - buyDelay < 0) {
			return false;
		}
		if (c.myShopId == 10) {
                                handleOtherShop(itemID);
                                return false;
                        }
		if (c.myShopId == 95) {
                                handleOtherShop(itemID);
                                return false;
                        }
		if(c.myShopId == 7390 && c.myShopClient != null && !c.myShopClient.properLogout && !c.playerName.equals(c.myShopClient.playerName)){
			int bought = 0;
			int price = c.myShopClient.playerShopP[fromSlot];
			if(amount > c.myShopClient.playerShopN[fromSlot])
				amount = c.myShopClient.playerShopN[fromSlot];
			for(int x = 0; x < amount; x++){
				if(c.getItems().playerHasItem(995, c.myShopClient.playerShopP[fromSlot]) && c.getItems().freeSlots() > 0){
					c.getItems().deleteItem2(995, c.myShopClient.playerShopP[fromSlot]);
					c.getItems().addItem(c.myShopClient.playerShop[fromSlot], 1);
					c.myShopClient.playerShopN[fromSlot]--;
					c.myShopClient.playerCollect += c.myShopClient.playerShopP[fromSlot];
					if(c.myShopClient.playerShopN[fromSlot] == 0){
						c.myShopClient.playerShop[fromSlot] = 0;
						c.myShopClient.playerShopP[fromSlot] = 0;
					}
					bought++;
				}else{
					c.sendMessage("Not enought space or money.");
					break;
				}
			}
			if(bought > 0){
			resetShop(c.myShopClient);
			c.getItems().resetItems(3823);;
			c.sendMessage("You just bought "+bought+" "+c.getItems().getItemName(itemID)+" for "+ (bought*price));
			c.myShopClient.sendMessage(c.playerName+" has bought "+bought+" "+c.getItems().getItemName(itemID)+" from you!");
			c.myShopClient.sendMessage("You now have "+c.myShopClient.playerCollect+" coins to collect.");
			}
			return false;
		}else if(c.myShopId == 7390 && c.myShopClient != null && !c.myShopClient.properLogout && c.playerName.equals(c.myShopClient.playerName)){
			if(amount > c.myShopClient.playerShopN[fromSlot])
				amount = c.myShopClient.playerShopN[fromSlot];
			for(int x = 0; x < amount; x++){
				if(c.getItems().freeSlots() > 0){
					c.getItems().addItem(c.myShopClient.playerShop[fromSlot], 1);
					c.myShopClient.playerShopN[fromSlot]--;
					if(c.myShopClient.playerShopN[fromSlot] == 0){
						c.myShopClient.playerShop[fromSlot] = 0;
						c.myShopClient.playerShopP[fromSlot] = 0;
						fixShop(c);
					}
				}else{
					c.sendMessage("Not enought space.");
					break;
				}
			}
			resetShop(c.myShopClient);
			c.getItems().resetItems(3823);
			return false;
		}else if(c.myShopId == 7390){
			return false;
		}

		if (c.myShopId == 14) {
			skillBuy(itemID);
			return false;

		} else if (c.myShopId == 15) {
			buyVoid(itemID);
			return false;		
		
		} else if (c.myShopId == 1) {
			buyVoid(itemID);
			return false;
                }
		if(itemID != itemID) {
			c.sendMessage("Don't dupe or you will be IP Banned");
			return false;
		}

		if(!shopSellsItem(itemID))
			return false;

		if (amount > 0) {
			if (amount > Server.shopHandler.ShopItemsN[c.myShopId][fromSlot]) {
				amount = Server.shopHandler.ShopItemsN[c.myShopId][fromSlot];
			}
			//double ShopValue;
			//double TotPrice;
			int TotPrice2 = 0;
			//int Overstock;
			int Slot = 0;
			int Slot1 = 0;//Tokkul
			int Slot2 = 0;//Pking Points
			int Slot3 = 0;//Donator Gold

			if (c.myShopId == 18) {
				handleOtherShop(itemID);
				return false;
			}	
			if (c.myShopId == 20) {
                                handleOtherShop(itemID);
                                return false;
                        }
		  if (c.myShopId == 11) {
                                handleOtherShop(itemID);
                                return false;
                        }
			if (c.myShopId == 73) {
				handleOtherShop(itemID);
				return false;
			}	
			if (c.myShopId == 74) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 48) {
				handleOtherShop(itemID);
				return false;
			}	
			if (c.myShopId == 84) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 85) {
				handleOtherShop(itemID);
				return false;
			}			
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(getItemShopValue(itemID, 0, fromSlot));
				Slot = c.getItems().getItemSlot(995);
				Slot1 = c.getItems().getItemSlot(6529);
				Slot3 = c.getItems().getItemSlot(5555);
				if (Slot == -1 && c.myShopId != 11 && c.myShopId != 29 && c.myShopId != 30 && c.myShopId != 31 && c.myShopId != 47) {
					c.sendMessage("You don't have enough coins.");
					break;
				}
				if(Slot1 == -1 && c.myShopId == 29 || c.myShopId == 30 || c.myShopId == 31) {
					c.sendMessage("You don't have enough tokkul.");
					break;
				}
				if(Slot3 == -1 && c.myShopId == 11) {
					c.sendMessage("You don't have enough donator gold.");
					break;
				}
			
                if(TotPrice2 <= 1) {
                	TotPrice2 = (int)Math.floor(getItemShopValue(itemID, 0, fromSlot));
                	TotPrice2 *= 1.66;
                }
                if(c.myShopId == 29 || c.myShopId == 30 || c.myShopId == 31) {
                	if (c.playerItemsN[Slot1] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(6529, c.getItems().getItemSlot(6529), TotPrice2);
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough tokkul.");
						break;
					}
                }
                else if(c.myShopId == 20) {//Checked
                        if (c.votePoints >= TotPrice2) {
                                                if (c.getItems().freeSlots() > 0) {
                                                        buyDelay = System.currentTimeMillis();
                                                        c.votePoints -= TotPrice2;
                                                        c.getItems().addItem(itemID, 1);
                                                        Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
                                                        Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
                                                        if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
                                                                Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
                                                        }
                                                } else {
                                                        c.sendMessage("You don't have enough space in your inventory.");
                                                        break;
                                                }
                                        } else {
                                                c.sendMessage("You don't have enough Vote Points.");
                                                break;
                                        }
                }
				else if(c.myShopId == 11) {//Checked
                        if (c.lvlPoints >= TotPrice2) {
                                                if (c.getItems().freeSlots() > 0) {
                                                        buyDelay = System.currentTimeMillis();
                                                        c.lvlPoints -= TotPrice2;
                                                        c.getItems().addItem(itemID, 1);
                                                        Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
                                                        Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
                                                        if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
                                                                Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
                                                        }
                                                } else {
                                                        c.sendMessage("You don't have enough space in your inventory.");
                                                        break;
                                                }
                                        } else {
                                                c.sendMessage("You don't have enough lvl Points.");
                                                break;
                                        }
                }
				else if(c.myShopId == 47) {
                	if (c.pkPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.pkPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough PvP Points Points.");
						break;
					}
                }
                else if(c.myShopId == 48) {
                	if (c.SPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.SPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Slayer Points.");
						break;
					}
                }
				else if(c.myShopId == 84) {
                	if (c.dungPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.dungPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Dungeoneering Points.");
						break;
					}
                }
				else if(c.myShopId == 85) {
                	if (c.dungPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.dungPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Dungeoneering Points.");
						break;
					}
                }
                else if(c.myShopId == 18) {
                	if (c.pcPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.pcPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Pest Points.");
						break;
					}
                }
                else if(c.myShopId == 11) {//Checked
                	if (c.playerItemsN[Slot3] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(5555, c.getItems().getItemSlot(5555), TotPrice2);
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough donator gold.");
						break;
					}
                }
                else if(c.myShopId != 11 && c.myShopId != 29 || c.myShopId != 30 || c.myShopId != 31 || c.myShopId != 47) {
					if (c.playerItemsN[Slot] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(995, c.getItems().getItemSlot(995), TotPrice2);
							c.getItems().addItem(itemID, 1);
							Server.shopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							Server.shopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > Server.shopHandler.ShopItemsStandard[c.myShopId]) {
								Server.shopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough coins.");
						break;
					}
                }
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return false;
	}	

		public void handleOtherShop(int itemID) {
			if (c.myShopId == 17) {
				if (c.magePoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.magePoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);	
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough points to buy this item.");			
				}
			} else if (c.myShopId == 95) {
				if (c.pfPoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.pfPoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough PF points to buy this item.");			
				}
			} else if (c.myShopId == 10) {
                                if (c.donorPoints >= getSpecialItemValue(itemID)) {
                                        if (c.getItems().freeSlots() > 0){
                                                c.donorPoints -= getSpecialItemValue(itemID);
                                                c.getItems().addItem(itemID,1);
                                                c.getItems().resetItems(3823);
                                        }
                                } else {
                                        c.sendMessage("You do not have enough Donator points to buy this item.");                        
                                }
			} else if (c.myShopId == 18) {
				if (c.pcPoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.pcPoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough points to buy this item.");			
				}		
		
			}
			if (c.myShopId == 73) {
				if (c.pkPoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.pkPoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough PvP Points points to buy this item.");			
				}

			}
			if (c.myShopId == 74) {
				if (c.pkPoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.pkPoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough PvP Points points to buy this item.");			
				}

			}
			if (c.myShopId == 48) {
				if (c.SPoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.SPoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough Slayer Points to buy this item.");			
				}

			}
			if (c.myShopId == 20) {
                                if (c.votePoints >= getSpecialItemValue(itemID)) {
                                        if (c.getItems().freeSlots() > 0){
                                                c.votePoints -= getSpecialItemValue(itemID);
                                                c.getItems().addItem(itemID,1);
                                                c.getItems().resetItems(3823);
                                        }
                                } else {
                                        c.sendMessage("You do not have enough lvl points to buy this item.");                       
                                }

                        }
			if (c.myShopId == 11) {//Checked
                                if (c.lvlPoints >= getSpecialItemValue(itemID)) {
                                        if (c.getItems().freeSlots() > 0){
                                                c.lvlPoints -= getSpecialItemValue(itemID);
                                                c.getItems().addItem(itemID,1);
                                                c.getItems().resetItems(3823);
                                        }
                                } else {
                                        c.sendMessage("You do not have enough lvl points to buy this item.");                       
                                }

                        }
			if (c.myShopId == 84) {
				if (c.dungPoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.dungPoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough Dungeoneering Points to buy this item.");			
				}

			}
			if (c.myShopId == 85) {
				if (c.dungPoints >= getSpecialItemValue(itemID)) {
					if (c.getItems().freeSlots() > 0){
						c.dungPoints -= getSpecialItemValue(itemID);
						c.getItems().addItem(itemID,1);
						c.getItems().resetItems(3823);
					}
				} else {
					c.sendMessage("You do not have enough Dungeoneering Points to buy this item.");			
				}

			}
		}
		
		public void openSkillCape() {
			int capes = get99Count();
			if (capes > 1)
				capes = 1;
			else
				capes = 0;
			c.myShopId = 14;
			setupSkillCapes(capes, get99Count());		
		}
		
		
		
		/*public int[][] skillCapes = {{0,9747,4319,2679},{1,2683,4329,2685},{2,2680,4359,2682},{3,2701,4341,2703},{4,2686,4351,2688},{5,2689,4347,2691},{6,2692,4343,2691},
									{7,2737,4325,2733},{8,2734,4353,2736},{9,2716,4337,2718},{10,2728,4335,2730},{11,2695,4321,2697},{12,2713,4327,2715},{13,2725,4357,2727},
									{14,2722,4345,2724},{15,2707,4339,2709},{16,2704,4317,2706},{17,2710,4361,2712},{18,2719,4355,2721},{19,2737,4331,2739},{20,2698,4333,2700}};*/
		public int[] skillCapes = {9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765,9948,12169,18509};
		public int get99Count() {
			int count = 0;
			for (int j = 0; j < c.playerLevel.length; j++) {
				if (c.getLevelForXP(c.playerXP[j]) >= 99) {
					count++;				
				}			
			}		
			return count;
		}
		
		public void setupSkillCapes(int capes, int capes2) {
			synchronized(c) {
				c.getItems().resetItems(3823);
				c.isShopping = true;
				c.myShopId = 14;
				c.getPA().sendFrame248(3824, 3822);
				c.getPA().sendFrame126("Skillcape Shop", 3901);
				
				int TotalItems = 0;
				TotalItems = capes2;
				if (TotalItems > Server.shopHandler.MaxShopItems) {
					TotalItems = Server.shopHandler.MaxShopItems;
				}
				c.getOutStream().createFrameVarSizeWord(53);
				c.getOutStream().writeWord(3900);
				c.getOutStream().writeWord(TotalItems);
				int TotalCount = 0;
				for (int i = 0; i < 24; i++) {
					if (c.getLevelForXP(c.playerXP[i]) < 99)
						continue;
					c.getOutStream().writeByte(1);
					c.getOutStream().writeWordBigEndianA(skillCapes[i] + 2);
					TotalCount++;
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();	
			}
		}
		
		public void skillBuy(int item) {
			int nn = get99Count();
			if (nn > 1)
				nn = 1;
			else
				nn = 0;			
			for (int j = 0; j < skillCapes.length; j++) {
				if (skillCapes[j] == item || skillCapes[j]+1 == item) {
					if (c.getItems().freeSlots() > 1) {
						if (c.getItems().playerHasItem(995,99000)) {
							if (c.getLevelForXP(c.playerXP[j]) >= 99) {
								c.getItems().deleteItem(995, c.getItems().getItemSlot(995), 99000);
								c.getItems().addItem(skillCapes[j] + nn,1);
								c.getItems().addItem(skillCapes[j] + 2,1);
							} else {
								c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
							}
						} else {
							c.sendMessage("You need 99k to buy this item.");
						}
					} else {
						c.sendMessage("You must have at least 1 inventory spaces to buy this item.");					
					}				
				}
				/*if (skillCapes[j][1 + nn] == item) {
					if (c.getItems().freeSlots() >= 1) {
						if (c.getItems().playerHasItem(995,99000)) {
							if (c.getLevelForXP(c.playerXP[j]) >= 99) {
								c.getItems().deleteItem(995, c.getItems().getItemSlot(995), 99000);
								c.getItems().addItem(skillCapes[j] + nn,1);
								c.getItems().addItem(skillCapes[j] + 2,1);
							} else {
								c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
							}
						} else {
							c.sendMessage("You need 99k to buy this item.");
						}
					} else {
						c.sendMessage("You must have at least 1 inventory spaces to buy this item.");					
					}
					break;				
				}*/			
			}
			c.getItems().resetItems(3823);			
		}
		
		public void openVoid() {
			/*synchronized(c) {
				c.getItems().resetItems(3823);
				c.isShopping = true;
				c.myShopId = 15;
				c.getPA().sendFrame248(3824, 3822);
				c.getPA().sendFrame126("Void Recovery", 3901);
				
				int TotalItems = 5;
				c.getOutStream().createFrameVarSizeWord(53);
				c.getOutStream().writeWord(3900);
				c.getOutStream().writeWord(TotalItems);
				for (int i = 0; i < c.voidStatus.length; i++) {
					c.getOutStream().writeByte(c.voidStatus[i]);
					c.getOutStream().writeWordBigEndianA(2519 + i * 2);
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();	
			}*/		
		}

		public void buyVoid(int item) {
			/*if (item > 2527 || item < 2518)
				return;
			//c.sendMessage("" + item);
			if (c.voidStatus[(item-2518)/2] > 0) {
				if (c.getItems().freeSlots() >= 1) {
					if (c.getItems().playerHasItem(995,c.getItems().getUntradePrice(item))) {
						c.voidStatus[(item-2518)/2]--;
						c.getItems().deleteItem(995,c.getItems().getItemSlot(995), c.getItems().getUntradePrice(item));
						c.getItems().addItem(item,1);
						openVoid();
					} else {
						c.sendMessage("This item costs " + c.getItems().getUntradePrice(item) + " coins to rebuy.");				
					}
				} else {
					c.sendMessage("I should have a free inventory space.");
				}
			} else {
				c.sendMessage("I don't need to recover this item from the void knights.");
			}*/
		}


}

