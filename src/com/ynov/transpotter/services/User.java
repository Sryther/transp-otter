package com.ynov.transpotter.services;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<User> users = new ArrayList<User>();

    public User(List<User> users) {
	super();
	this.users = users;
    }

    public User() {
	super();
    }

    public User get(int index) {
	return this.users.get(index);
    }

    public void remove(int index) {
	this.users.remove(index);
    }

    public int add(User user) {
	this.users.add(user);
	return this.users.size() - 1;
    }

    public int size() {
	return this.users.size();
    }
}