package thelm.jaopca.api.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import thelm.jaopca.api.EnumOreType;
import thelm.jaopca.api.IOreEntry;
import thelm.jaopca.api.JAOPCAApi;

public class Utils {

	public static final HashMap<String,ItemStack> CACHE = Maps.<String,ItemStack>newHashMap();
	public static final LinkedList<String> MOD_IDS = Lists.<String>newLinkedList();

	public static ItemStack getOreStack(String name, int amount) {
		if(CACHE.containsKey(name)) {
			ItemStack ret = CACHE.get(name).copy();
			ret.stackSize = amount;
			return ret;
		}

		ItemStack ret = null;
		if(!OreDictionary.getOres(name, false).isEmpty()) {
			List<ItemStack> list = OreDictionary.getOres(name, false);
			ret = getPreferredStack(list);
		}

		if(ret != null) {
			CACHE.put(name, ret.copy());
			ret.stackSize = amount;
		}

		return ret;
	}

	public static ItemStack getOreStack(String prefix, IOreEntry entry, int amount) {
		if(CACHE.containsKey(prefix+entry.getOreName())) {
			ItemStack ret = CACHE.get(prefix+entry.getOreName()).copy();
			ret.stackSize = amount;
			return ret;
		}

		if(JAOPCAApi.BLOCKS_TABLE.contains(prefix, entry.getOreName())) {
			Block b = JAOPCAApi.BLOCKS_TABLE.get(prefix, entry.getOreName());
			CACHE.put(prefix+entry.getOreName(), new ItemStack(b, 1, 0));
			return new ItemStack(b, amount, 0);
		}

		if(JAOPCAApi.ITEMS_TABLE.contains(prefix, entry.getOreName())) {
			Item i = JAOPCAApi.ITEMS_TABLE.get(prefix, entry.getOreName());
			CACHE.put(prefix+entry.getOreName(), new ItemStack(i, 1, 0));
			return new ItemStack(i, amount, 0);
		}

		return getOreStack(prefix+entry.getOreName(), amount);
	}

	public static ItemStack getJAOPCAOrOreStack(String prefix, String fallback, IOreEntry entry, int amount) {
		if(CACHE.containsKey(prefix+entry.getOreName())) {
			ItemStack ret = CACHE.get(prefix+entry.getOreName()).copy();
			ret.stackSize = amount;
			return ret;
		}

		if(JAOPCAApi.BLOCKS_TABLE.contains(prefix, entry.getOreName())) {
			Block b = JAOPCAApi.BLOCKS_TABLE.get(prefix, entry.getOreName());
			CACHE.put(prefix+entry.getOreName(), new ItemStack(b, 1, 0));
			return new ItemStack(b, amount, 0);
		}

		if(JAOPCAApi.ITEMS_TABLE.contains(prefix, entry.getOreName())) {
			Item i = JAOPCAApi.ITEMS_TABLE.get(prefix, entry.getOreName());
			CACHE.put(prefix+entry.getOreName(), new ItemStack(i, 1, 0));
			return new ItemStack(i, amount, 0);
		}

		return getOreStack(fallback, entry, amount);
	}

	public static ItemStack getOreStackExtra(String prefix, IOreEntry entry, int amount) {
		if(CACHE.containsKey(prefix+entry.getExtra())) {
			ItemStack ret = CACHE.get(prefix+entry.getExtra()).copy();
			ret.stackSize = amount;
			return ret;
		}

		if(JAOPCAApi.BLOCKS_TABLE.contains(prefix, entry.getExtra())) {
			Block b = JAOPCAApi.BLOCKS_TABLE.get(prefix, entry.getExtra());
			CACHE.put(prefix+entry.getExtra(), new ItemStack(b, 1, 0));
			return new ItemStack(b, amount, 0);
		}

		if(JAOPCAApi.ITEMS_TABLE.contains(prefix, entry.getExtra())) {
			Item i = JAOPCAApi.ITEMS_TABLE.get(prefix, entry.getExtra());
			CACHE.put(prefix+entry.getExtra(), new ItemStack(i, 1, 0));
			return new ItemStack(i, amount, 0);
		}

		return getOreStack(prefix+entry.getExtra(), amount);
	}

	public static ItemStack getJAOPCAOrOreStackExtra(String prefix, String fallback, IOreEntry entry, int amount) {
		if(CACHE.containsKey(prefix+entry.getExtra())) {
			ItemStack ret = CACHE.get(prefix+entry.getExtra()).copy();
			ret.stackSize = amount;
			return ret;
		}

		if(JAOPCAApi.BLOCKS_TABLE.contains(prefix, entry.getExtra())) {
			Block b = JAOPCAApi.BLOCKS_TABLE.get(prefix, entry.getExtra());
			CACHE.put(prefix+entry.getExtra(), new ItemStack(b, 1, 0));
			return new ItemStack(b, amount, 0);
		}

		if(JAOPCAApi.ITEMS_TABLE.contains(prefix, entry.getExtra())) {
			Item i = JAOPCAApi.ITEMS_TABLE.get(prefix, entry.getExtra());
			CACHE.put(prefix+entry.getExtra(), new ItemStack(i, 1, 0));
			return new ItemStack(i, amount, 0);
		}

		return getOreStackExtra(fallback, entry, amount);
	}

	public static ItemStack getOreStackSecondExtra(String prefix, IOreEntry entry, int amount) {
		if(CACHE.containsKey(prefix+entry.getSecondExtra())) {
			ItemStack ret = CACHE.get(prefix+entry.getSecondExtra()).copy();
			ret.stackSize = amount;
			return ret;
		}

		if(JAOPCAApi.BLOCKS_TABLE.contains(prefix, entry.getSecondExtra())) {
			Block b = JAOPCAApi.BLOCKS_TABLE.get(prefix, entry.getSecondExtra());
			CACHE.put(prefix+entry.getSecondExtra(), new ItemStack(b, 1, 0));
			return new ItemStack(b, amount, 0);
		}

		if(JAOPCAApi.ITEMS_TABLE.contains(prefix, entry.getSecondExtra())) {
			Item i = JAOPCAApi.ITEMS_TABLE.get(prefix, entry.getSecondExtra());
			CACHE.put(prefix+entry.getSecondExtra(), new ItemStack(i, 1, 0));
			return new ItemStack(i, amount, 0);
		}

		return getOreStack(prefix+entry.getSecondExtra(), amount);
	}

	public static ItemStack getJAOPCAOrOreStackSecondExtra(String prefix, String fallback, IOreEntry entry, int amount) {
		if(CACHE.containsKey(prefix+entry.getSecondExtra())) {
			ItemStack ret = CACHE.get(prefix+entry.getSecondExtra()).copy();
			ret.stackSize = amount;
			return ret;
		}

		if(JAOPCAApi.BLOCKS_TABLE.contains(prefix, entry.getSecondExtra())) {
			Block b = JAOPCAApi.BLOCKS_TABLE.get(prefix, entry.getSecondExtra());
			CACHE.put(prefix+entry.getSecondExtra(), new ItemStack(b, 1, 0));
			return new ItemStack(b, amount, 0);
		}

		if(JAOPCAApi.ITEMS_TABLE.contains(prefix, entry.getSecondExtra())) {
			Item i = JAOPCAApi.ITEMS_TABLE.get(prefix, entry.getSecondExtra());
			CACHE.put(prefix+entry.getSecondExtra(), new ItemStack(i, 1, 0));
			return new ItemStack(i, amount, 0);
		}

		return getOreStackSecondExtra(fallback, entry, amount);
	}

	public static int energyI(IOreEntry entry, double energy) {
		return (int)(entry.getEnergyModifier()*energy);
	}

	public static void addSmelting(ItemStack input, ItemStack output, float xp) {
		if(FurnaceRecipes.instance().getSmeltingResult(input) == null) {
			GameRegistry.addSmelting(input.copy(), output.copy(), xp);
		}
	}

	public static ItemStack resizeStack(ItemStack stack, int size) {
		if(stack != null) {
			ItemStack ret = stack.copy();
			ret.stackSize = size;
			return ret;
		}
		return null;
	}

	public static boolean doesOreNameExist(String name) {
		return !OreDictionary.getOres(name, false).isEmpty();
	}

	public static ItemStack getPreferredStack(Iterable<ItemStack> itemList) {
		ItemStack ret = null;
		int index = Integer.MAX_VALUE;

		for(ItemStack stack : itemList) {
			if(stack == null || stack.getItem() == null) {
				continue;
			}

			String modId = stack.getItem().getRegistryName().getResourceDomain();
			int idex = MOD_IDS.indexOf(modId);

			if(idex < index) {
				index = idex;
				ret = stack;
			}
		}

		return ret.copy();
	}

	public static String to_under_score(String camelCase) {
		if(StringUtils.isEmpty(camelCase)) {
			return "";
		}

		String[] strings = StringUtils.splitByCharacterTypeCamelCase(camelCase);
		StringBuilder ret = new StringBuilder();
		for(int i = 0; i < strings.length; i++) {
			ret.append(StringUtils.uncapitalize(strings[i]));
			if(i < strings.length-1) {
				ret.append('_');
			}
		}
		return ret.toString();
	}

	public static String toPascal(String under_score) {
		if(StringUtils.isEmpty(under_score)) {
			return "";
		}

		String[] strings = StringUtils.split(under_score, '_');
		StringBuilder ret = new StringBuilder();
		for(int i = 0; i < strings.length; i++) {
			ret.append(StringUtils.capitalize(strings[i]));
		}
		return ret.toString();
	}

	public static String toCamelCase(String under_score) {
		return StringUtils.uncapitalize(toPascal(under_score));
	}

	public static String toLowerCase(String s) {
		return s.toLowerCase(Locale.US);
	}

	public static String toSpaceSeparated(String camelCase) {
		if(StringUtils.isEmpty(camelCase)) {
			return "";
		}

		String[] strings = StringUtils.splitByCharacterTypeCamelCase(camelCase);
		StringBuilder ret = new StringBuilder();
		for(int i = 0; i < strings.length; i++) {
			ret.append(StringUtils.capitalize(strings[i]));
			if(i < strings.length-1) {
				ret.append(' ');
			}
		}
		return ret.toString();
	}

	public static ItemStack parseItemStack(String input) {
		try {
			Item item;
			int meta=0, amount=1;
			String[] split0 = input.split("@");
			item = Item.REGISTRY.getObject(new ResourceLocation(split0[0]));
			if(split0.length == 2) {
				String[] split1 = split0[1].split("x");
				meta = Integer.parseInt(split1[0]);
				if(split1.length == 2) {
					amount = Integer.parseInt(split1[1]);
				}
			}
			return new ItemStack(item, amount, meta);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static EnumOreType oreNameToType(String oreName) {
		return JAOPCAApi.ORE_ENTRY_LIST.stream().filter(entry->entry.getOreName().equals(oreName)).findAny().get().getOreType();
	}
}