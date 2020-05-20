/*    */ package me.orion.utils;
/*    */ 
/*    */ import me.orion.friend.FriendList;
/*    */ 
/*    */ public class HFUser {
/*    */   private boolean allowingRequests;
/*    */   private FriendList friendList;
/*    */   private int friendLimit;
/*    */   
/*    */   HFUser(FriendList friendList, boolean allowingRequests, int friendLimit) {
/* 11 */     this.allowingRequests = allowingRequests;
/* 12 */     this.friendLimit = friendLimit;
/* 13 */     this.friendList = friendList;
/*    */   }
/*    */   
/*    */   public void toggleRequests() {
/* 17 */     this.allowingRequests = !this.allowingRequests;
/*    */   }
/*    */   
/*    */   public boolean hasReachedFriendLimit() {
/* 21 */     return (getFriendList().getFriendsListSize() >= this.friendLimit);
/*    */   }
/*    */   
/*    */   public boolean isAllowingRequests() {
/* 25 */     return this.allowingRequests;
/*    */   }
/*    */   
/*    */   public FriendList getFriendList() {
/* 29 */     return this.friendList;
/*    */   }
/*    */   
/*    */   public int getFriendLimit() {
/* 33 */     return this.friendLimit;
/*    */   }
/*    */ }