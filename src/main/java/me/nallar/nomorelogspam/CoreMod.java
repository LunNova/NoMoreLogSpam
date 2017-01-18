package me.nallar.nomorelogspam;

import me.nallar.modpatcher.ModPatcher;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.*;

@IFMLLoadingPlugin.Name("@MOD_ID@")
@IFMLLoadingPlugin.MCVersion("@MC_VERSION@")
@IFMLLoadingPlugin.SortingIndex(1001)
public class CoreMod implements IFMLLoadingPlugin {
	static {
		ModPatcher.requireVersion("@MC_VERSION@.1");
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[0];
	}

	@Override
	public String getModContainerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return ModPatcher.getSetupClass();
	}

	private Boolean spongePresent;

	private boolean isSpongePresent() {
		if (spongePresent == null) {
			try {
				Class.forName("org.spongepowered.asm.mixin.MixinEnvironment", false, CoreMod.class.getClassLoader());
				spongePresent = true;
			} catch (ClassNotFoundException e) {
				spongePresent = false;
			}
		}

		return spongePresent;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		Log.trace("@MOD_NAME@ v@MOD_VERSION@ CoreMod initialised. Sponge present: " + isSpongePresent());

		ModPatcher.loadPatches(CoreMod.class.getResourceAsStream("/RIP Log Spam.xml"));
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
