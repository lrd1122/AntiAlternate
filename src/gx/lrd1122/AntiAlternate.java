package gx.lrd1122;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiAlternate extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("[AntiAlternate] 插件已成功加载,Bug反馈/定制插件+QQ 1794325461");
        Bukkit.getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("[AntiAlternate] 插件已成功卸载,Bug反馈+Q 1794325461");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(NumberUtils.isNumber(args[0]))
        {
            getConfig().set("AlternateLimit", args[0]);
            saveConfig();
            reloadConfig();
        }
        return true;
    }

    @EventHandler
    public void PlayerJoinGame(PlayerJoinEvent event)
    {
        int kicklimit = 0;
        String ip = event.getPlayer().getAddress().getAddress().getHostAddress();
        for (Player players : Bukkit.getOnlinePlayers())
        {
            if(players != event.getPlayer() && players.getAddress().getAddress().getHostAddress().equals(ip))
            {
                kicklimit++;
            }
        }
        if(kicklimit> getConfig().getInt("AlternateLimit"))
        {
            event.getPlayer().kickPlayer(getConfig().getString("KickReason"));
        }
    }
}
