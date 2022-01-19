package com.example.test;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
interface UserDao {
    @Insert (onConflict = REPLACE)
    void insertUser(User user);

//    @Insert(onConflict = IGNORE)
//    Void insertOrReplaceEmploy(User user);

//    @Update(onConflict = REPLACE)
//    fun updateEmploy(user: User?)
//
//    @Query("DELETE FROM User")
//    fun deleteAll()

    @Query("SELECT * FROM user")
    List<User> getListUser();
}
