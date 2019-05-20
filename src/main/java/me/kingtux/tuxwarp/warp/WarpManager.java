package me.kingtux.tuxwarp.warp;

import me.kingtux.tuxorm.Dao;
import me.kingtux.tuxwarp.TuxWarp;

public class WarpManager {
    private TuxWarp plugin;
    private Dao<Warp, Long> dao;

    public WarpManager(TuxWarp plugin) {
        this.plugin = plugin;
        dao = plugin.getConnection().createDao(Warp.class);
    }

    public Dao<Warp, Long> getDao() {
        return dao;
    }
}
