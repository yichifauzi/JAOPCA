package thelm.jaopca.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.api.IGameObjectHandler;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.cleanroommc.groovyscript.gameobjects.GameObjectHandlerManager;
import com.cleanroommc.groovyscript.sandbox.expand.ExpansionHelper;

import thelm.jaopca.api.forms.IForm;
import thelm.jaopca.api.materialforms.IMaterialFormInfo;
import thelm.jaopca.api.materials.IMaterial;
import thelm.jaopca.api.modules.IModuleData;
import thelm.jaopca.forms.FormHandler;
import thelm.jaopca.materials.MaterialHandler;
import thelm.jaopca.modules.ModuleHandler;

public class JAOPCAGroovyPlugin implements GroovyPlugin {

	@Override
	public String getModId() {
		return "jaopca";
	}

	@Override
	public void onCompatLoaded(GroovyContainer<?> container) {
		GameObjectHandlerManager.registerGameObjectHandler("jaopca", "module", IGameObjectHandler.wrapStringGetter(ModuleHandler::getModuleData));
		GameObjectHandlerManager.registerGameObjectHandler("jaopca", "form", IGameObjectHandler.wrapStringGetter(FormHandler::getForm));
		GameObjectHandlerManager.registerGameObjectHandler("jaopca", "material", IGameObjectHandler.wrapStringGetter(MaterialHandler::getMaterial));

		ExpansionHelper.mixinClass(IModuleData.class, ModuleDataExpansion.class);
		ExpansionHelper.mixinClass(IForm.class, FormExpansion.class);
		ExpansionHelper.mixinClass(IMaterial.class, MaterialExpansion.class);
		ExpansionHelper.mixinClass(IMaterialFormInfo.class, MaterialFormInfoExpansion.class);
	}
}