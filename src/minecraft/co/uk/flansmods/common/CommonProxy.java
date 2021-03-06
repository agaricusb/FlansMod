package co.uk.flansmods.common;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import co.uk.flansmods.common.network.PacketBreakSound;
import co.uk.flansmods.common.network.PacketBuyWeapon;
import co.uk.flansmods.common.network.PacketParticleSpawn;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy
{

	
	public void load()
	{
		
	}
	
	// BEGIN ABRAR EDITS --------------------------------------
	
	public List<File> getContentList(Method method, ClassLoader classloader)
	{
		// this stuff is only done client side.
		List<File> contentPacks = new ArrayList<File>();
		for (File file : FlansMod.flanDir.listFiles())
		{
			if (file.isDirectory())
			{
				// Add the images to the classpath so they can be loaded
				FlansMod.log("Not client. images and models skipped.");
				// Add the directory to the content pack list
				FlansMod.log("Loaded content pack : " + file.getName());
				contentPacks.add(file);
			}
		}
		FlansMod.log("Loaded textures and models.");
		return contentPacks;
	}
	
	public List<File> getContentList()
	{
		return null;
	}	
	
	public void registerTileEntityRenderers() 
	{
	}
	
	public void loadDefaultGraphics()
	{
		FlansMod.log("Not client. Graphic loading skipped.");
	}
	
	public void loadContentPackGraphics(Method method, ClassLoader classloader)
	{
		FlansMod.log("Not client. ContentPack graphic loading skipped.");
	}
	
	public void loadKeyBindings()
	{
		FlansMod.log("Not client. Key Bindings skipped.");
	}
	
	public void doTutorialStuff(EntityPlayer player, EntityDriveable entityType)
	{
		// FlansMod.log("Tutorial skipped on server");
	}
	
	public void changeControlMode(EntityPlayer player)
	{
	}
	
	// --------------- END ABRAR EDITS ----------------------
	
	public boolean mouseControlEnabled()
	{
		return false;
	}
	
	// ------------------ PACKET SENDING OR NOT -------------
	
	public void playBlockBreakSound(int x, int y, int z, int blockID)
	{
		PacketDispatcher.sendPacketToAllPlayers(PacketBreakSound.buildBreakSoundPacket(x, y, z, blockID));
	}
	
	public void spawnParticle(String type, double x1, double y1, double z1, double x2, double y2, double z2, int number)
	{
		PacketDispatcher.sendPacketToAllPlayers(PacketParticleSpawn.buildParticleSpawnPacket(type, x1, y1, z1, x2, y2, z2, number));
	}
	
	// ---------------END PACKET SENDING OR NOT -------------	

	public void doTickStuff()
	{
		// overriden in client
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
	}

	/**
	 * Gets the client GUI element from ClientProxy.
	 * @param ID
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Object getClientGui(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	public Container getServerGui(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID) 
		{
			case 0: return new ContainerPlaneCrafting(player.inventory, world, x, y, z, false);
			case 1: return new ContainerPlaneCrafting(player.inventory, world, x, y, z, true);
			case 2: return new ContainerVehicleCrafting(player.inventory, world, x, y, z);
			case 3: return new ContainerPlaneMenu(player.inventory, world);
			case 4: return new ContainerPlaneMenu(player.inventory, world, true, (EntityDriveable)player.ridingEntity);
			case 6: return new ContainerPlaneInventory(player.inventory, world, (EntityDriveable)player.ridingEntity, 0);
			case 7: return new ContainerPlaneInventory(player.inventory, world, (EntityDriveable)player.ridingEntity, 1);
			case 8: return new ContainerPlaneMenu(player.inventory, world, true, (EntityDriveable)player.ridingEntity);
			case 9: return new ContainerPlaneInventory(player.inventory, world, (EntityDriveable)player.ridingEntity, 2);
		}
		return null;
	}
	
	public void openDriveableMenu(EntityPlayer player, World world, EntityDriveable driveable)
	{
		
	}

	public void loadBulletModel(String[] split, String shortName, BulletType type)
	{
		return;
	}

	public void loadMGModel(String[] split, String shortName, GunType type)
	{
		return;
	}

	public void loadAAGunModel(String[] split, String shortName, AAGunType type)
	{
		return;
	}

	public void loadVehicleModel(String[] split, String shortName, VehicleType type)
	{
		return;
	}
	
	public void loadPlaneModel(String[] split, String shortName, PlaneType type)
	{
		return;
	}
	
	public void loadSound(String contentPack, String type, String sound)
	{
		
	}
	
	public boolean isThePlayer(EntityPlayer player)
	{
		return false;
	}
	
	public void buyGun(GunBoxType type, int gun)
	{
		//Client only
	}

	public void buyAmmo(GunBoxType box, int ammo, int type)
	{
		//Client only
	}
	
	public List<DriveableType> getBlueprints(boolean vehicle)
	{
		return null;
	}
}