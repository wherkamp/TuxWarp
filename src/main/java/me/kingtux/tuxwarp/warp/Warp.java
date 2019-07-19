package me.kingtux.tuxwarp.warp;

import me.kingtux.tuxorm.annotations.DBTable;
import me.kingtux.tuxorm.annotations.TableColumn;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

@DBTable
public class Warp {
    @TableColumn(autoIncrement = true, primary = true)
    private long id;
    @TableColumn
    private String name;
    @TableColumn
    private Location location;
    @TableColumn
    private OfflinePlayer creator;

    public Warp(String name, Location location, OfflinePlayer creator) {
        this.name = name;
        this.location = location;
        this.creator = creator;
    }
    public Warp(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public OfflinePlayer getCreator() {
        return creator;
    }

    public void setCreator(OfflinePlayer creator) {
        this.creator = creator;
    }
}

