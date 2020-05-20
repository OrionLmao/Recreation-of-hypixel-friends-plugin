/*    */ package me.orion.friend;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class FriendList
/*    */ {
/*    */   private List<Friend> friends;
/*    */   
/*    */   public FriendList(List<Friend> friends) {
/* 13 */     this.friends = friends;
/*    */   }
/*    */   
/*    */   public int getFriendsListSize() {
/* 17 */     return this.friends.size();
/*    */   }
/*    */   
/*    */   public boolean contains(Player user) {
/* 21 */     return this.friends.contains(new Friend(user.getUniqueId()));
/*    */   }
/*    */   
/*    */   void addFriend(Friend friend) {
/* 25 */     this.friends.add(friend);
/*    */   }
/*    */   
/*    */   public void removeFriend(OfflinePlayer player) {
/* 29 */     this.friends.remove(new Friend(player.getUniqueId()));
/*    */   }
/*    */   
/*    */   public void clear() {
/* 33 */     this.friends.clear();
/*    */   }
/*    */   
/*    */   public List<Friend> getAllFriends() {
/* 37 */     return this.friends;
/*    */   }
/*    */   
/*    */   public List<Friend> getFriends(int page) {
/* 41 */     List<Friend> friends = new ArrayList<>();
/*    */     
/* 43 */     if (this.friends.isEmpty()) {
/* 44 */       return friends;
/*    */     }
/* 46 */     int pageSize = 8;
/* 47 */     for (int i = ((page - 1) * pageSize > this.friends.size()) ? 0 : ((page - 1) * pageSize); i < ((pageSize * page > this.friends.size()) ? this.friends.size() : (pageSize * page)); i++) {
/* 48 */       friends.add(this.friends.get(i));
/*    */     }
/* 50 */     return friends;
/*    */   }
/*    */ }