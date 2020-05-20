/*    */ package me.orion.commands.subcommands;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.ClickEvent;
/*    */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*    */ import net.md_5.bungee.api.chat.HoverEvent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import me.orion.hf.HypixelFriends;
/*    */ import me.orion.hf.friend.Friend;
/*    */ import me.orion.hf.utils.HFUser;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class ListCmd extends SubCommand {
/*    */   public ListCmd(HypixelFriends plugin, Permission permission) {
/* 18 */     super(plugin, permission);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender user, String... args) {
/* 23 */     if (!validatePermissions(user))
/* 24 */       return;  HFUser hfUser = getPlugin().getHfUserManager().getUser((Player)user);
/* 25 */     int page = 1;
/*    */     
/* 27 */     if (args.length > 1) {
/* 28 */       if (getPlugin().isInteger(args[1]) && Integer.valueOf(args[1]).intValue() <= hfUser.getFriendList().getFriendsListSize() / 8) {
/* 29 */         page = Integer.valueOf(args[1]).intValue();
/*    */       } else {
/* 31 */         getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.invalid-page-number"));
/*    */         
/*    */         return;
/*    */       } 
/*    */     }
/* 36 */     TextComponent last = new TextComponent("                «");
/* 37 */     last.setColor(ChatColor.YELLOW);
/* 38 */     TextComponent next = new TextComponent("»");
/* 39 */     next.setColor(ChatColor.YELLOW);
/*    */     
/* 41 */     HoverEvent nextHoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&cClick to view page " + String.valueOf(Integer.valueOf(page + 1))))).create());
/* 42 */     ClickEvent nextClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f list " + String.valueOf(Integer.valueOf(page + 1)));
/*    */     
/* 44 */     HoverEvent lastHoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&cClick to view page " + String.valueOf(Integer.valueOf(page - 1))))).create());
/* 45 */     ClickEvent lastClickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f list " + String.valueOf(Integer.valueOf(page - 1)));
/*    */     
/* 47 */     next.setHoverEvent(nextHoverEvent);
/* 48 */     next.setClickEvent(nextClickEvent);
/*    */     
/* 50 */     last.setHoverEvent(lastHoverEvent);
/* 51 */     last.setClickEvent(lastClickEvent);
/*    */     
/* 53 */     TextComponent headerPage = new TextComponent(ChatColor.translateAlternateColorCodes('&', getPlugin().getConfig().getString("messages.list-page").replace("%1%", String.valueOf(page)).replace("%2%", String.valueOf((hfUser.getFriendList().getFriendsListSize() / 8 < 1) ? 1 : (hfUser.getFriendList().getFriendsListSize() / 8)))));
/*    */     
/* 55 */     for (String line : getPlugin().getConfig().getStringList("messages.list-header")) {
/* 56 */       getPlugin().getMessageUtil().message(user, line);
/*    */     }
/* 58 */     getPlugin().getMessageUtil().message((Player)user, new TextComponent(new BaseComponent[] { (page > 1) ? (BaseComponent)last : (BaseComponent)new TextComponent("                 "), (BaseComponent)headerPage, (BaseComponent)next }));
/*    */     
/* 60 */     if (hfUser.getFriendList().getFriendsListSize() <= 0) {
/* 61 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.no-friends"));
/*    */     } else {
/* 63 */       for (Friend friend : hfUser.getFriendList().getFriends(page))
/* 64 */         getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.list-format").replace("%0%", friend.getName()).replace("%1%", friend.isOnline() ? getPlugin().getConfig().getString("messages.list-status.online").replace("%0%", Bukkit.getPlayer(friend.getUniqueId()).getLocation().getWorld().getName()) : getPlugin().getConfig().getString("messages.list-status.offline"))); 
/*    */     } 
/* 66 */     getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.list-footer"));
/*    */   }
/*    */ }