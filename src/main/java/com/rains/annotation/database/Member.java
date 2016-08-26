package com.rains.annotation.database;

/**
 * Created by Rains on 2016/8/23.
 */

@DBTable(name = "MEMBER")
public class Member {

    @SQLString(value = 30 , name = "first")
    private String firstName;
    @SQLString(50)
    private String lastName;

    @SQLInteger
    private int age;

    @SQLString(value = 30 , constraint = @Constraints(primaryKey = true))
    private String handle;

    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}
