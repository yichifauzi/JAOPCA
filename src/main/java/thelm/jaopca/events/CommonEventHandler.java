package thelm.jaopca.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.TagManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import thelm.jaopca.blocks.BlockFormType;
import thelm.jaopca.config.ConfigHandler;
import thelm.jaopca.data.DataCollector;
import thelm.jaopca.data.DataInjector;
import thelm.jaopca.fluids.FluidFormType;
import thelm.jaopca.forms.FormHandler;
import thelm.jaopca.forms.FormTypeHandler;
import thelm.jaopca.ingredients.IngredientSerializers;
import thelm.jaopca.items.ItemFormType;
import thelm.jaopca.materials.MaterialHandler;
import thelm.jaopca.modules.ModuleHandler;
import thelm.jaopca.utils.ApiImpl;
import thelm.jaopca.utils.MiscHelper;

public class CommonEventHandler {

	public static final CommonEventHandler INSTANCE = new CommonEventHandler();
	private static final Logger LOGGER = LogManager.getLogger();

	public static CommonEventHandler getInstance() {
		return INSTANCE;
	}

	@SubscribeEvent
	public void onConstruct(FMLConstructModEvent event) {
		MinecraftForge.EVENT_BUS.addListener(this::onAddReloadListener);
		ApiImpl.INSTANCE.init();
		DataInjector.init();
		BlockFormType.init();
		ItemFormType.init();
		FluidFormType.init();
		IngredientSerializers.init();
		DataCollector.collectData();
		ModuleHandler.findModules();
		ConfigHandler.setupMainConfig();
		MaterialHandler.findMaterials();
		ConfigHandler.setupMaterialConfigs();
		FormTypeHandler.setupGson();
		ConfigHandler.setupCustomFormConfig();
		ConfigHandler.setupModuleConfigsPre();
		FormHandler.collectForms();
		ModuleHandler.computeValidMaterials();
		FormHandler.computeValidMaterials();
		ConfigHandler.setupModuleConfigs();
		FormTypeHandler.registerMaterialForms();
		ModuleHandler.onMaterialComputeComplete();
	}

	@SubscribeEvent
	public void onCreativeModeTabRegister(CreativeModeTabEvent.Register event) {
		event.registerCreativeModeTab(new ResourceLocation("jaopca:tab"),
				builder->builder.
				title(Component.translatable("itemGroup.jaopca")).
				icon(()->new ItemStack(Items.GLOWSTONE_DUST)).
				displayItems(FormTypeHandler::addToCreativeModeTab).
				build());
	}

	@SubscribeEvent
	public void onCommonSetup(FMLCommonSetupEvent event) {
		ModuleHandler.onCommonSetup(event);
	}

	@SubscribeEvent
	public void onInterModEnqueue(InterModEnqueueEvent event) {
		ModuleHandler.onInterModEnqueue(event);
	}

	@SubscribeEvent
	public void onAddPackFinders(AddPackFindersEvent event) {
		if(event.getPackType() == PackType.SERVER_DATA) {
			event.addRepositorySource(DataInjector.PackFinder.INSTANCE);
		}
	}

	public void onAddReloadListener(AddReloadListenerEvent event) {
		MiscHelper.INSTANCE.setTagManager(event.getServerResources().listeners().stream().
				filter(l->l instanceof TagManager).findFirst().map(l->(TagManager)l).
				orElseThrow(()->new IllegalStateException("Tag manager not found.")));
	}

	public void onReloadApply(Class<?> clazz, Object object) {
		DataInjector.reloadInject(clazz, object);
	}
}
