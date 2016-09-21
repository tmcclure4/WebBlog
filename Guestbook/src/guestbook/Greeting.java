package guestbook;

import java.util.Date;

 

import com.google.appengine.api.users.User;

import com.googlecode.objectify.annotation.Entity;

import com.googlecode.objectify.annotation.Id;

 

 

@Entity

public class Greeting implements Comparable<Greeting> {

    @Id Long id;

    User user;

    String content;

    Date date;
    
    String blog;

    private Greeting() {}

    public Greeting(User user, String content, String blog) {

        this.user = user;

        this.content = content;
        
        this.blog = blog;

        date = new Date();

    }

    public User getUser() {

        return user;

    }

    public String getContent() {

        return content;

    }
    public String getBlog(){
    	return blog;
    }
    public Date getDate(){
    	return date;
    }

    @Override

    public int compareTo(Greeting other) {

        if (date.after(other.date)) {

            return 1;

        } else if (date.before(other.date)) {

            return -1;

        }

        return 0;

    }

}