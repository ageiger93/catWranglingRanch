package server.model.players.packets;


import server.model.players.Client;
import server.model.players.PacketType;
import server.Server;
import java.net.URL;
import server.model.npcs.*;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import server.model.items.ItemAssistant;
import server.model.shops.ShopAssistant;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.util.Misc;
import server.model.npcs.NPC;
import server.model.players.skills.Summoning;
import server.util.Stream;
import server.util.MadTurnipConnection;
import server.model.players.skills.*;
import server.event.EventManager;
import server.event.Event; 
import server.model.players.PlayerSave;
import server.model.players.PlayerHandler;
import server.event.EventContainer;
import server.model.minigames.WarriorsGuild;
import server.model.minigames.Gambling;



public class IdleLogout implements PacketType {
	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		//if (!c.playerName.equalsIgnoreCase("Sabsabi"))
			//c.logout();
			switch (packetType) {
            case 202:
            if (c.underAttackBy > 0 || c.underAttackBy2 > 0) {
                return;
            } else {
               /// c.logout();
               //Misc.println(c.playerName + " Was Kicked for being Idle.");
            }
            break;
        }
	}
}