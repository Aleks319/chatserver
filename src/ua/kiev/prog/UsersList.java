package ua.kiev.prog;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class UsersList implements Iterable<User>{
    private static final UsersList userList = new UsersList();

    private final List<User> list = new LinkedList<>();

    private UsersList() {

    }

    public static UsersList getInstance() {
        return userList;
    }

    public synchronized void addUser(User user) {
        list.add(user);
    }

    public User getUser(int n) {
        return list.get(n);
    }

    public User getUser(String name) {
        for (User us : list) {
            if(us.getLogin().equals(name))
                return us;
        }
        return null;
    }

    public String getListUsers() {
        StringBuilder sb = new StringBuilder();
        for(User u: list) {
            sb.append(u.getLogin() + "\n");
        }
        return sb.toString();
    }

    public boolean isUserExist (String login) {
        for (User us : list) {
            if(us.getLogin().equals(login))
                return true;
        }
        return false;
    }


    public Iterator<User> iterator() {
        return list.iterator();
    }
}