package com.elearing.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.elearing.room.entity.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void register(User user);
   @Query("select * from user where username ==:username and  password ==:password")
    User login(String username,String password);
   @Query("select * from user where username==:username")
    User usernameExist(String username);
}
