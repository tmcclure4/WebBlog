package guestbook;


import java.util.ArrayList;

import com.google.appengine.api.users.User;

import com.googlecode.objectify.annotation.Entity;

import com.googlecode.objectify.annotation.Id;

@Entity
public class EmailSingle implements Comparable<EmailSingle>{
@Id Long id;

String email;



public EmailSingle(){
	email = "zygchess@yahoo.com";
}
	public EmailSingle(User user){
	
		email = user.getEmail();
	}
	public String getEmail(){
		return email;
	}
	@Override
	public int compareTo(EmailSingle o) {
		return 0;
	}
	Long getId(){
		return id;
	}


}