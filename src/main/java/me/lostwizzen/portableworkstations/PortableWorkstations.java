package me.lostwizzen.portableworkstations;

import java.io.File;

import javax.annotation.Nonnull;

import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.lostwizzen.portableworkstations.setup.PortableWorkstationItemSetup;

public class PortableWorkstations extends JavaPlugin implements SlimefunAddon {

	public ProtocolManager protocolManager;
	
    @Override
    public void onEnable() {
        PortableWorkstationItemSetup.setup(this);
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        System.out.println("---------------------------------------");
        System.out.println("--- PortableWorkstations is enabled ---");
        System.out.println("---------------------------------------");
    }
    
    @Override
    public void onLoad() {
    	super.onLoad();
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

    private static PortableWorkstations instance;

    public PortableWorkstations() {
        instance = this;
    }

    public static PortableWorkstations getInstance() {
        return instance;
    }
}