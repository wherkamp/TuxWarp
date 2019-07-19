package me.kingtux.tuxwarp;

import dev.nitrocommand.bukkit.BukkitCommandCore;
import dev.nitrocommand.core.CommandCore;
import me.kingtux.tuxjsql.core.TuxJSQL;
import me.kingtux.tuxjsql.core.builders.SQLBuilder;
import me.kingtux.tuxorm.TOConnection;
import me.kingtux.tuxorm.bukkit.TOBukkit;
import me.kingtux.tuxwarp.command.WarpCommand;
import me.kingtux.tuxwarp.warp.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class TuxWarp extends JavaPlugin {
    private TOConnection connection;
    private BukkitCommandCore commandManager;

    private WarpManager warpManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        saveResource("db.properties", false);
        SQLBuilder builder = TuxJSQL.setup(getDBProperties());
        System.out.println("builder.getDataSource().isClosed() = " + builder.getDataSource().isClosed());
        connection = new TOConnection(builder);
        TOBukkit.registerSerializers(connection);
        commandManager = new BukkitCommandCore(this);
        commandManager.registerCommand(new WarpCommand(this));
        warpManager = new WarpManager(this);
    }

    private Properties getDBProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(getDataFolder(),"db.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    @Override
    public void onDisable() {
        connection.getBuilder().getDataSource().close();
        // Plugin shutdown logic
    }

    public TOConnection getConnection() {
        return connection;
    }

    public BukkitCommandCore getCommandManager() {
        return commandManager;
    }
}
