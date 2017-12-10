package ElementalCreepers.smalldeadguy.elementalcreepers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

@Mod(modid = "ElementalCreepers", name = "ElementalCreepers", version = "3.0")
//@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ElementalCreepers {

	@Instance
	public static ElementalCreepers instance;

	@SidedProxy(clientSide="smalldeadguy.elementalcreepers.ClientProxy", serverSide="smalldeadguy.elementalcreepers.CommonProxy")
	public static CommonProxy proxy;

	public static int waterCreeperSpawn = 5;
	public static int fireCreeperSpawn = 5;
	public static int iceCreeperSpawn = 5;
	public static int electricCreeperSpawn = 5;
	public static int earthCreeperSpawn = 5;
	public static int psychicCreeperSpawn = 5;
	public static int cookieCreeperSpawn = 1;
	public static int magmaCreeperSpawn = 3;
	public static int friendlyCreeperSpawn = 1;
	public static int illusionCreeperSpawn = 3;
	public static int lightCreeperSpawn = 2;
	public static int darkCreeperSpawn = 5;
	public static int reverseCreeperSpawn = 3;
	public static int spiderCreeperSpawn = 4;

	public static int waterCreeperRadius = 4;
	public static int fireCreeperRadius = 6;
	public static int iceCreeperRadius = 12;
	public static int electricCreeperRadius = 5;
	public static int earthCreeperRadius = 8;
	public static int psychicCreeperRadius = 5;
	public static int psychicCreeperPower = 8;
	public static int cookieCreeperAmount = 25;
	public static int magmaCreeperRadius = 3;
	public static int ghostCreeperRadius = 5;
	public static int ghostCreeperChance = 35;
	public static int lightCreeperRadius = 4;
	public static int darkCreeperRadius = 12;
	public static int reverseCreeperRadius = 8;
	public static int spiderCreeperRadius = 12;
	
	public static boolean spawnNormalCreepers = true;
	
	public static List<Class<? extends EntityElementalCreeper>> creepers = new ArrayList<Class<? extends EntityElementalCreeper>>();
	

	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		waterCreeperSpawn = config.get("waterCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		fireCreeperSpawn = config.get("fireCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		iceCreeperSpawn = config.get("iceCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		electricCreeperSpawn = config.get("electricCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		earthCreeperSpawn = config.get("earthCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		psychicCreeperSpawn = config.get("psychicCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		cookieCreeperSpawn = config.get("cookieCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		magmaCreeperSpawn = config.get("magmaCreeperSpawn", Configuration.CATEGORY_GENERAL, 3).getInt();
		friendlyCreeperSpawn = config.get("friendlyCreeperSpawn", Configuration.CATEGORY_GENERAL, 1).getInt();
		illusionCreeperSpawn = config.get("illusionCreeperSpawn", Configuration.CATEGORY_GENERAL, 3).getInt();
		lightCreeperSpawn = config.get("lightCreeperSpawn", Configuration.CATEGORY_GENERAL, 2).getInt();
		darkCreeperSpawn = config.get("darkCreeperSpawn", Configuration.CATEGORY_GENERAL, 5).getInt();
		reverseCreeperSpawn = config.get("reverseCreeperSpawn", Configuration.CATEGORY_GENERAL, 3).getInt();
		spiderCreeperSpawn = config.get("spiderCreeperSpawn", Configuration.CATEGORY_GENERAL, 4).getInt();

		waterCreeperRadius = config.get("waterCreeperRadius", Configuration.CATEGORY_GENERAL, 4).getInt();
		fireCreeperRadius = config.get("fireCreeperRadius", Configuration.CATEGORY_GENERAL, 6).getInt();
		iceCreeperRadius = config.get("iceCreeperRadius", Configuration.CATEGORY_GENERAL, 12).getInt();
		electricCreeperRadius = config.get("electricCreeperRadius", Configuration.CATEGORY_GENERAL, 5).getInt();
		earthCreeperRadius = config.get("earthCreeperRadius", Configuration.CATEGORY_GENERAL, 8).getInt();
		psychicCreeperRadius = config.get("psychicCreeperRadius", Configuration.CATEGORY_GENERAL, 5).getInt();
		psychicCreeperPower = config.get("psychicCreeperPower", Configuration.CATEGORY_GENERAL, 8).getInt();
		cookieCreeperAmount = config.get("cookieCreeperAmount", Configuration.CATEGORY_GENERAL, 25).getInt();
		magmaCreeperRadius = config.get("magmaCreeperRadius", Configuration.CATEGORY_GENERAL, 3).getInt();
		ghostCreeperRadius = config.get("ghostCreeperRadius", Configuration.CATEGORY_GENERAL, 5).getInt();
		ghostCreeperChance = config.get("ghostCreeperChance", Configuration.CATEGORY_GENERAL, 35).getInt();
		lightCreeperRadius = config.get("lightCreeperRadius", Configuration.CATEGORY_GENERAL, 4).getInt();
		darkCreeperRadius = config.get("darkCreeperRadius", Configuration.CATEGORY_GENERAL, 12).getInt();
		reverseCreeperRadius = config.get("reverseCreeperRadius", Configuration.CATEGORY_GENERAL, 8).getInt();
		spiderCreeperRadius = config.get("spiderCreeperRadius", Configuration.CATEGORY_GENERAL, 12).getInt();
		
		spawnNormalCreepers = config.get("spawnNormalCreepers", Configuration.CATEGORY_GENERAL, true).getBoolean(true);

		config.save();
	}
	
	public void registerCreeper(Class<? extends Entity> creeper, String name, int id, Color colour, Object[] eggRecipe, int spawnRate, int minGroup, int maxGroup, BiomeGenBase[] biomes) {
		registerCreeper(creeper, name, id, new Color(894731), colour, eggRecipe, spawnRate, minGroup, maxGroup, biomes);
	}
	
	public void registerCreeper(Class<? extends Entity> creeper, String name, int id, Color colour1, Color colour2, Object[] eggRecipe, int spawnRate, int minGroup, int maxGroup, BiomeGenBase[] biomes) {
		if(EntityElementalCreeper.class.isAssignableFrom(creeper)) creepers.add((Class<? extends EntityElementalCreeper>) creeper);
		
		EntityRegistry.registerModEntity(creeper, name, id, this, 128, 1, true);
		EntityList.IDtoClassMapping.put(id, creeper);
		if(eggRecipe != null) EntityList.entityEggs.put(id, new EntityEgg(MinecraftServer.getServer().getEntityWorld(), (double)id, (double)colour1.getRGB(), (double)colour2.getRGB()));
		
		if(eggRecipe != null) GameRegistry.addShapelessRecipe(new ItemStack(Items.spawn_egg, 1, id), eggRecipe);
		LanguageRegistry.instance().addStringLocalization("entity.ElementalCreepers." + name + ".name", name.replaceAll("([A-Z])", " $1").trim());
		
		if(spawnRate > 0) EntityRegistry.addSpawn("ElementalCreepers." + name, spawnRate, minGroup, maxGroup, EnumCreatureType.monster, biomes);
	}


	public void Init(FMLInitializationEvent event) {
		BiomeGenBase[] normalBiomes = new BiomeGenBase[] { BiomeGenBase.beach, BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.extremeHills,
				BiomeGenBase.extremeHillsEdge, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.iceMountains, BiomeGenBase.icePlains,
				BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.ocean, BiomeGenBase.plains, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver,
				BiomeGenBase.river, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.taigaHills
		};
		
		registerCreeper(EntityWaterCreeper.class, "WaterCreeper", 1, new Color(59, 115, 205), new Object[] {Items.water_bucket, new ItemStack(Items.spawn_egg, 1, 50)}, waterCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityFireCreeper.class, "FireCreeper", 2, new Color(227, 111, 24), new Object[] {Items.flint_and_steel, new ItemStack(Items.spawn_egg, 1, 50)}, fireCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityIceCreeper.class, "IceCreeper", 3, Color.white, new Object[] {Items.snowball, new ItemStack(Items.spawn_egg, 1, 50)}, iceCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityElectricCreeper.class, "ElectricCreeper", 4, new Color(251, 234, 57), new Object[] {Items.redstone, new ItemStack(Items.spawn_egg, 1, 50)}, electricCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityEarthCreeper.class, "EarthCreeper", 5, new Color(93, 50, 0), new Object[] {Blocks.dirt, new ItemStack(Items.spawn_egg, 1, 50)}, earthCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityPsychicCreeper.class, "PsychicCreeper", 6, new Color(121, 51, 142), new Object[] {Items.feather, new ItemStack(Items.spawn_egg, 1, 50)}, psychicCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityCookieCreeper.class, "CookieCreeper", 7, new Color(202, 147, 98), new Object[] {Items.cookie, new ItemStack(Items.spawn_egg, 1, 50)}, cookieCreeperSpawn, 1, 2, normalBiomes);
		registerCreeper(EntityMagmaCreeper.class, "MagmaCreeper", 8, new Color(165, 0, 16), new Object[] {Items.lava_bucket, new ItemStack(Items.spawn_egg, 1, 50)}, magmaCreeperSpawn, 1, 2, new BiomeGenBase[] {BiomeGenBase.hell});
		registerCreeper(EntityGhostCreeper.class, "GhostCreeper", 9, null, null, 0, 0, 0, null);
		registerCreeper(EntityFriendlyCreeper.class, "FriendlyCreeper", 10, new Color(215, 113, 211), new Object[] {Items.sugar, new ItemStack(Items.spawn_egg, 1, 50)}, friendlyCreeperSpawn, 1, 1, normalBiomes);
		registerCreeper(EntityIllusionCreeper.class, "IllusionCreeper", 11, new Color(158, 158, 158), new Object[] {Items.compass, new ItemStack(Items.spawn_egg, 1, 50)}, illusionCreeperSpawn, 1, 1, normalBiomes);
		registerCreeper(EntityFakeIllusionCreeper.class, "FakeIllusionCreeper", 12, null, null, 0, 0, 0, null);
		registerCreeper(EntityLightCreeper.class, "LightCreeper", 13, new Color(255, 244, 125), new Object[] {Items.glowstone_dust, new ItemStack(Items.spawn_egg, 1, 50)}, lightCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityDarkCreeper.class, "DarkCreeper", 14, new Color(50, 50, 50), new Object[] {Items.coal, new ItemStack(Items.spawn_egg, 1, 50)}, darkCreeperSpawn, 1, 3, normalBiomes);
		registerCreeper(EntityReverseCreeper.class, "ReverseCreeper", 15, Color.black, new Color(894731), new Object[] {Items.ender_eye, new ItemStack(Items.spawn_egg, 1, 50)}, reverseCreeperSpawn, 1, 1, normalBiomes);
		registerCreeper(EntitySpiderCreeper.class, "SpiderCreeper", 16, Color.red, new Object[] {Items.string, new ItemStack(Items.spawn_egg, 1, 50)}, spiderCreeperSpawn, 1, 3, normalBiomes);
		
		proxy.registerRenderers();
		
		MinecraftForge.EVENT_BUS.register(this);
		
		// Ignore this bit
		//TotallyNotAprilFools.nothingToSeeHere();
	}
	
	//@ForgeSubscribe
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityCreeper && !(event.entity instanceof EntityElementalCreeper) && !spawnNormalCreepers && event.isCancelable()) {
			event.setCanceled(true);
		}
		
		// Ignore this bit, too
		//TotallyNotAprilFools.stillNothingToSeeHere(event);
	}
}