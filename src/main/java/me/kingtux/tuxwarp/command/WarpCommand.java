package me.kingtux.tuxwarp.command;

import me.kingtux.tuxcommand.common.TuxCommand;
import me.kingtux.tuxcommand.common.annotations.BaseCommand;
import me.kingtux.tuxcommand.common.annotations.Command;
import me.kingtux.tuxcommand.common.annotations.SubCommand;
import me.kingtux.tuxwarp.TuxWarp;
import me.kingtux.tuxwarp.warp.Warp;
import org.bukkit.entity.Player;

import java.util.List;

@Command(aliases = "warp", description = "My Warp commnad", format = "/warp {MyWarp, create}")
public class WarpCommand implements TuxCommand {
    private TuxWarp warp;

    public WarpCommand(TuxWarp tuxWarp) {
        this.warp = tuxWarp;
    }

    @BaseCommand
    public void base(Player player, String[] s) {
        if (s.length == 0) {
            player.sendMessage("No Name Provided");
            return;
        }
        Warp w = warp.getWarpManager().getDao().fetchFirst("name", s[0]);
        if (w == null) {
            player.sendMessage("Warp Not Found");
            return;
        }
        player.sendMessage("ZOOM!");

        player.teleport(w.getLocation());
    }

    @SubCommand(subCommand = "create")
    public void create(Player player, String[] s) {
        if (s.length == 0) {
            player.sendMessage("No Name Provided");
            return;
        }
        if (warp.getWarpManager().getDao().fetchFirst("name", s[0]) != null) {
            player.sendMessage("Name already in use");
            return;
        }

        Warp w = new Warp(s[0], player.getLocation(), player);
        player.sendMessage("Warp Creating " + s[0]);
        warp.getWarpManager().getDao().create(w);
        player.sendMessage("Warp Created");
    }

    @SubCommand(subCommand = "list")
    public void list(Player player) {
        List<Warp> warps = warp.getWarpManager().getDao().fetchAll();
        for(Warp w1 : warps){
            player.sendMessage(w1.getName()+ " "+ w1.getCreator().getName());
        }
    }
}
