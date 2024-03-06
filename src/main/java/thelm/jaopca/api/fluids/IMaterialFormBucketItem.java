package thelm.jaopca.api.fluids;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import thelm.jaopca.api.materialforms.IMaterialForm;

public interface IMaterialFormBucketItem extends IMaterialForm {

	default Item toItem() {
		return (Item)this;
	}

	default void addToCreativeModeTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
		output.accept(toItem());
	}

	default void onRegisterCapabilities(RegisterCapabilitiesEvent event) {}
}
