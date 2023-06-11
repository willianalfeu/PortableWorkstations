package me.lostwizzen.portableworkstations;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.lostwizzen.portableworkstations.constants.PortableWorkstationsConstants;
import me.lostwizzen.portableworkstations.setup.PortableWorkstationItemSetup;

public class PortableWorkstations extends JavaPlugin implements SlimefunAddon {

	private static PortableWorkstations instance;
	public ProtocolManager protocolManager;
	public Map<String, String> messages;

	@Override
	public void onEnable() {
		PortableWorkstationItemSetup.setup(this);
		System.out.println("---------------------------------------");
		System.out.println("--- PortableWorkstations is enabled ---");
		System.out.println("---------------------------------------");
	}

	private void loadLanguage() {
		File localesFolder = new File(getDataFolder() + "/locales");
		String locale = getConfig().getString("locale");
		try {
			if (!localesFolder.exists()) {
				localesFolder.mkdir();
				Files.copy(getInstance().getResource("en.yml"), new File(localesFolder, "en.yml").toPath());
				Files.copy(getInstance().getResource("pt-br.yml"), new File(localesFolder, "pt-br.yml").toPath());
			}
			File localeFile = new File(localesFolder, locale.concat(".yml"));
			if (!localeFile.exists()) {
				throw new Exception("The message file does not exists: "+localeFile.getAbsolutePath());
			}
			System.out.println("LocaleFile: "+ localeFile.getAbsolutePath());
			YamlConfiguration messagesYaml = YamlConfiguration.loadConfiguration(localeFile);
			System.out.println("menu: "+ messagesYaml.getString(PortableWorkstationsConstants.MENU_NAME));
			messages = messagesYaml.getKeys(true).stream()
					.collect(Collectors.toMap(key->key, key->messagesYaml.getString(key)));
			messages.keySet().forEach(System.out::println);
			System.out.println("Messages loaded - "+ messages.get(PortableWorkstationsConstants.MENU_NAME));
		} catch (Exception e) {
			System.err.println("Error loading the messages file: "+locale);
			getInstance().setEnabled(false);
			getInstance().getServer().getPluginManager().disablePlugin(this);
			e.printStackTrace();
		}
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		loadLanguage();
		protocolManager = ProtocolLibrary.getProtocolManager();
	}

	@Override
	public void onDisable() {
	}

	@Override
	public String getBugTrackerURL() {
		return null;
	}

	@Nonnull
	@Override
	public JavaPlugin getJavaPlugin() {
		return this;
	}

	public PortableWorkstations() {
		instance = this;
	}

	public static PortableWorkstations getInstance() {
		return instance;
	}
}