/*    */ package me.orion;
/*    */ 
/*    */ import me.orion.commands.FriendCmd;
/*    */ import me.orion.friend.FriendRequestManager;
/*    */ import me.orion.listeners.JoinE;
/*    */ import me.orion.listeners.QuitE;
/*    */ import me.orion.utils.HFUserManager;
/*    */ import me.orion.utils.MessageUtil;
/*    */ import me.orion.utils.StorageUtil;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class HypixelFriends extends JavaPlugin {
/*    */   private FriendRequestManager friendRequestManager;
/*    */   private HFUserManager hfUserManager;
/*    */   
/*    */   public void onEnable() {
/* 21 */     loadUtils();
/* 22 */     loadConfig();
/* 23 */     loadCommands();
/* 24 */     loadListeners();
/*    */   }
/*    */   private MessageUtil messageUtil; private StorageUtil storageUtil;
/*    */   private void loadUtils() {
/* 28 */     this.friendRequestManager = new FriendRequestManager(this);
/* 29 */     this.storageUtil = new StorageUtil(this);
/* 30 */     this.hfUserManager = new HFUserManager(this);
/* 31 */     this.messageUtil = new MessageUtil();
/*    */   }
/*    */   
/*    */   private void loadConfig() {
/* 35 */     saveDefaultConfig();
/*    */   }
/*    */   
/*    */   private void loadCommands() {
/* 39 */     getCommand("friend").setExecutor((CommandExecutor)new FriendCmd(this));
/*    */   }
/*    */   
/*    */   private void loadListeners() {
/* 43 */     PluginManager pluginManager = getServer().getPluginManager();
/* 44 */     pluginManager.registerEvents((Listener)new JoinE(this), (Plugin)this);
/* 45 */     pluginManager.registerEvents((Listener)new QuitE(this), (Plugin)this);
/*    */   }
/*    */   
/*    */   public FriendRequestManager getFriendRequestManager() {
/* 49 */     return this.friendRequestManager;
/*    */   }
/*    */   
/*    */   public HFUserManager getHfUserManager() {
/* 53 */     return this.hfUserManager;
/*    */   }
/*    */   
/*    */   public boolean isInteger(String potentialInt) {
/*    */     try {
/* 58 */       Integer.parseInt(potentialInt);
/* 59 */       return true;
/* 60 */     } catch (NumberFormatException ignored) {
/* 61 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public MessageUtil getMessageUtil() {
/* 66 */     return this.messageUtil;
/*    */   }
/*    */   
/*    */   public StorageUtil getStorageUtil() {
/* 70 */     return this.storageUtil;
/*    */   }
/*    */ }