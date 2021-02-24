package com.example.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.dynamodb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    public User save(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User getUserById(String userid) {
        return dynamoDBMapper.load(User.class, userid);
    }

    public String delete(String userId) {
        User newUser = dynamoDBMapper.load(User.class, userId);
        if(newUser!=null){
        dynamoDBMapper.delete(newUser);
        return "User Deleted!";}
        else {
            return "User doesn't exist";
        }
    }

    public String update(String userid, User user) {
//        dynamoDBMapper.save(user,
//                new DynamoDBSaveExpression()
//                        .withExpectedEntry("userid",
//                                new ExpectedAttributeValue(
//                                        new AttributeValue().withS(userid)
//                                )));
//        return userid;
        User newUser = dynamoDBMapper.load(User.class, userid);
        if(newUser == null) return "User doesn't exist";
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUserName(user.getUserName());
        newUser.setEmailId(user.getEmailId());
        dynamoDBMapper.save(newUser);
        return "User updated";
    }
}
