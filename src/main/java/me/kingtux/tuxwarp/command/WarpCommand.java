package me.kingtux.tuxwarp.command;


import dev.nitrocommand.core.annotations.BaseCommand;
import dev.nitrocommand.core.annotations.CommandArgument;
import dev.nitrocommand.core.annotations.NitroCommand;
import dev.nitrocommand.core.annotations.SubCommand;
import me.kingtux.tuxwarp.TuxWarp;
import me.kingtux.tuxwarp.warp.Warp;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@NitroCommand(command = "warp", description = "My Warp commnad", format = "/warp {MyWarp, create}")
public class WarpCommand {
    private TuxWarp warp;

    public WarpCommand(TuxWarp tuxWarp) {
        this.warp = tuxWarp;
    }

    @BaseCommand
    public void base(CommandSender sender) {
        sender.sendMessage("Bro!");
    }

    @SubCommand(format = "{warp}")
    public void warp(Player player, @CommandArgument("warp") String warpName) {

        Warp w = warp.getWarpManager().getDao().fetchFirst("name", warpName);
        if (w == null) {
            player.sendMessage("Warp Not Found");
            return;
        }
        player.sendMessage("ZOOM!");

        player.teleport(w.getLocation());
    }

    @SubCommand(format = "create {name}")
    public void create(Player player, @CommandArgument("name") String name) {
        if (name ==null ) {
            player.sendMessage("No Name Provided");
            return;
        }
        if (warp.getWarpManager().getDao().fetchFirst("name", name) != null) {
            player.sendMessage("Name already in use");
            return;
        }

        Warp w = new Warp(name, player.getLocation(), player);
        player.sendMessage("Warp Creating " + name);
        warp.getWarpManager().getDao().create(w);
        player.sendMessage("Warp Created");
    }

    @SubCommand(format = "list")
    public void list(Player player) {
        List<Warp> warps = warp.getWarpManager().getDao().fetchAll();
        for (Warp w1 : warps) {
            player.sendMessage(w1.getName() + " " + w1.getCreator().getName());
        }
    }
}
