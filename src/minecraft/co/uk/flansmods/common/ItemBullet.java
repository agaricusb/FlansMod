package co.uk.flansmods.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/** Implemented from old source. */
public class ItemBullet extends Item
{
	public ItemBullet(int i, int j, int k, BulletType type1)
	{
		super(i);
		setIconIndex(j);
		colour = k;
		type = type1;
		setMaxDamage(type.roundsPerItem);
		setMaxStackSize(type.maxStackSize);
		type.item = this;
		setCreativeTab(FlansMod.tabFlanGuns);
	}

	public String getTextureFile()
	{
		return "/spriteSheets/bullets.png";
	}

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
    	return type.colour;
    }

	public int colour;
	public BulletType type;
}