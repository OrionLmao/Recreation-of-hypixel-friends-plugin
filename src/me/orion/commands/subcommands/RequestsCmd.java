/*    */ package me.orion.commands.subcommands;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.ClickEvent;
/*    */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*    */ import net.md_5.bungee.api.chat.HoverEvent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import me.orion.hf.HypixelFriends;
/*    */ import me.orion.hf.friend.FriendRequest;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class RequestsCmd extends SubCommand {
/*    */   public RequestsCmd(HypixelFriends plugin, Permission permission) {
/* 17 */     super(plugin, permission);
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(CommandSender user, String... args) {
/* 22 */     if (!validatePermissions(user))
/*    */       return; 
/* 24 */     for (String line : getPlugin().getConfig().getStringList("messages.requests")) {
/* 25 */       getPlugin().getMessageUtil().message(user, line.replace("%0%", String.valueOf(1)).replace("%1%", String.valueOf(1)));
/*    */     }
/* 27 */     for (FriendRequest request : getPlugin().getFriendRequestManager().getFriendRequests(((Player)user).getUniqueId())) {
/* 28 */       TextComponent username = new TextComponent(ChatColor.translateAlternateColorCodes('&', "&eFrom " + (request.getSender().isOnline() ? Bukkit.getPlayer(request.getSender().getUniqueId()).getDisplayName() : request.getSender().getName())));
/*    */       
/* 30 */       TextComponent optionAccept = new TextComponent(ChatColor.translateAlternateColorCodes('&', " " + getPlugin().getConfig().getString("messages.request-option-accept")));
/* 31 */       ClickEvent clickEventAccept = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f accept " + request.getSender().getName());
/* 32 */       HoverEvent hoverEventAccept = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&bClick to accept the friend request"))).create());
/*    */       
/* 34 */       optionAccept.setClickEvent(clickEventAccept);
/* 35 */       optionAccept.setHoverEvent(hoverEventAccept);
/*    */       
/* 37 */       TextComponent optionDeny = new TextComponent(ChatColor.translateAlternateColorCodes('&', " " + getPlugin().getConfig().getString("messages.request-option-deny")));
/* 38 */       ClickEvent clickEventDeny = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f deny " + request.getSender().getName());
/* 39 */       HoverEvent hoverEventDeny = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&bClick to deny the friend request"))).create());
/*    */       
/* 41 */       optionDeny.setClickEvent(clickEventDeny);
/* 42 */       optionDeny.setHoverEvent(hoverEventDeny);
/*    */       
/* 44 */       getPlugin().getMessageUtil().message((Player)user, new TextComponent(new BaseComponent[] { (BaseComponent)username, (BaseComponent)optionAccept, (BaseComponent)optionDeny }));
/*    */     } 
/*    */   }
/*    */ }