/*    */ package me.orion.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import me.orion.HypixelFriends;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ 
/*    */ public class StorageUtil
/*    */ {
/*    */   private HypixelFriends plugin;
/*    */   
/*    */   public StorageUtil(HypixelFriends plugin) {
/* 15 */     this.plugin = plugin;
/*    */   }
/*    */   
/*    */   void createFile(String name) {
/* 19 */     File file = new File(this.plugin.getDataFolder(), "data/" + name + ".yml");
/*    */     
/* 21 */     if (file.exists())
/*    */       return; 
/* 23 */     file.getParentFile().mkdirs();
/*    */     
/*    */     try {
/* 26 */       file.createNewFile();
/* 27 */     } catch (IOException e) {
/* 28 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void reloadFile(FileConfiguration config, String name) {
/*    */     try {
/* 34 */       config.load(new File(this.plugin.getDataFolder(), "data/" + name + ".yml"));
/* 35 */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/* 36 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void saveFile(FileConfiguration config, String name) {
/*    */     try {
/* 42 */       config.save(new File(this.plugin.getDataFolder(), "data/" + name + ".yml"));
/* 43 */     } catch (IOException e) {
/* 44 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   boolean fileExists(String name) {
/* 49 */     return (new File(this.plugin.getDataFolder(), "data/" + name + ".yml")).exists();
/*    */   }
/*    */   
/*    */   public FileConfiguration getFile(String name) {
/* 53 */     return (FileConfiguration)YamlConfiguration.loadConfiguration(new File(this.plugin.getDataFolder(), "data/" + name + ".yml"));
/*    */   }
/*    */ }