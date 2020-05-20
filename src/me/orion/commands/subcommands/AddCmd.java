/*     */ package me.orion.commands.subcommands;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import me.orion.hf.HypixelFriends;
/*     */ import me.orion.hf.friend.Friend;
/*     */ import me.orion.hf.friend.FriendRequest;
/*     */ import me.orion.hf.utils.HFUser;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.permissions.Permission;
/*     */ 
/*     */ public class AddCmd extends SubCommand {
/*     */   public AddCmd(HypixelFriends plugin, Permission permission) {
/*  20 */     super(plugin, permission);
/*     */   }
/*     */   
/*     */   public void execute(CommandSender user, String... args) {
/*     */     String potentialFriendsName;
/*  25 */     if (!validatePermissions(user))
/*  26 */       return;  HFUser hfUser = getPlugin().getHfUserManager().getUser((Player)user);
/*     */     
/*  28 */     if (hfUser.hasReachedFriendLimit()) {
/*  29 */       for (String line : getPlugin().getConfig().getStringList("messages.max-friends")) {
/*  30 */         getPlugin().getMessageUtil().message(user, line.replace("%0%", String.valueOf(hfUser.getFriendLimit())));
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  36 */     if (args.length == 1) {
/*  37 */       if (args[0].equalsIgnoreCase("add")) {
/*  38 */         getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.invalid-arguments").replace("%0%", "friend add <name>"));
/*     */         
/*     */         return;
/*     */       } 
/*  42 */       potentialFriendsName = args[0];
/*     */     } else {
/*  44 */       potentialFriendsName = args[1];
/*     */     } 
/*  46 */     if (potentialFriendsName.length() > 16 || potentialFriendsName.replaceAll("[a-zA-Z0-9_]", "").length() > 0) {
/*  47 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.invalid-input").replace("%0%", potentialFriendsName));
/*     */       
/*     */       return;
/*     */     } 
/*  51 */     if (potentialFriendsName.equalsIgnoreCase(user.getName())) {
/*  52 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.cannot-friend-yourself"));
/*     */       
/*     */       return;
/*     */     } 
/*     */     OfflinePlayer potentialFriend;
/*  57 */     if ((potentialFriend = getPlugin().getServer().getOfflinePlayer(potentialFriendsName)) == null || !potentialFriend.hasPlayedBefore()) {
/*  58 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getString("messages.player-not-found").replace("%0%", potentialFriendsName));
/*     */       
/*     */       return;
/*     */     } 
/*  62 */     for (Friend friend : getPlugin().getHfUserManager().getUser((Player)user).getFriendList().getAllFriends()) {
/*  63 */       if (friend.getUniqueId().equals(potentialFriend.getUniqueId())) {
/*  64 */         getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.already-friends"));
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  69 */     if (getPlugin().getStorageUtil().getFile(potentialFriend.getUniqueId().toString()).getBoolean("friends-list-full")) {
/*  70 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.request-not-allowed"));
/*     */       
/*     */       return;
/*     */     } 
/*  74 */     if (!getPlugin().getStorageUtil().getFile(potentialFriend.getUniqueId().toString()).getBoolean("preferences.allow-friend-requests")) {
/*  75 */       getPlugin().getMessageUtil().message(user, getPlugin().getConfig().getStringList("messages.request-not-allowed"));
/*     */       
/*     */       return;
/*     */     } 
/*  79 */     getPlugin().getFriendRequestManager().addRequest(new FriendRequest(((Player)user).getUniqueId(), potentialFriend.getUniqueId(), getPlugin().getConfig().getInt("options.friend-add-timeout")));
/*     */     
/*  81 */     if (potentialFriend.isOnline()) {
/*  82 */       for (String line : getPlugin().getConfig().getStringList("messages.friend-request-header")) {
/*  83 */         getPlugin().getMessageUtil().message((CommandSender)Bukkit.getPlayer(potentialFriend.getUniqueId()), line.replace("%0%", ((Player)user).getDisplayName()));
/*     */       }
/*  85 */       TextComponent optionsMessage = new TextComponent(ChatColor.translateAlternateColorCodes('&', getPlugin().getConfig().getString("messages.friend-request-options")));
/*     */       
/*  87 */       TextComponent optionAccept = new TextComponent(ChatColor.translateAlternateColorCodes('&', getPlugin().getConfig().getString("messages.request-option-accept")));
/*  88 */       ClickEvent clickEventAccept = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f accept " + user.getName());
/*  89 */       HoverEvent hoverEventAccept = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&bClick to accept the friend request"))).create());
/*     */       
/*  91 */       optionAccept.setClickEvent(clickEventAccept);
/*  92 */       optionAccept.setHoverEvent(hoverEventAccept);
/*     */       
/*  94 */       TextComponent optionDeny = new TextComponent(ChatColor.translateAlternateColorCodes('&', getPlugin().getConfig().getString("messages.request-option-deny")));
/*  95 */       ClickEvent clickEventDeny = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f deny " + user.getName());
/*  96 */       HoverEvent hoverEventDeny = new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&bClick to deny the friend request"))).create());
/*     */       
/*  98 */       optionDeny.setClickEvent(clickEventDeny);
/*  99 */       optionDeny.setHoverEvent(hoverEventDeny);
/*     */       
/* 101 */       TextComponent spacer = new TextComponent(ChatColor.translateAlternateColorCodes('&', getPlugin().getConfig().getString("messages.spacer")));
/*     */       
/* 103 */       getPlugin().getMessageUtil().message(Bukkit.getPlayer(potentialFriend.getUniqueId()), new TextComponent(new BaseComponent[] { (BaseComponent)optionsMessage, (BaseComponent)optionAccept, (BaseComponent)spacer, (BaseComponent)optionDeny }));
/*     */       
/* 105 */       getPlugin().getMessageUtil().message((CommandSender)Bukkit.getPlayer(potentialFriend.getUniqueId()), getPlugin().getConfig().getStringList("messages.friend-request-footer"));
/*     */     } 
/*     */     
/* 108 */     for (String line : getPlugin().getConfig().getStringList("messages.request-sent"))
/* 109 */       getPlugin().getMessageUtil().message(user, line.replace("%0%", potentialFriend.getName()).replace("%1%", String.valueOf(getPlugin().getConfig().getInt("options.friend-add-timeout")))); 
/*     */   }
/*     */ }
