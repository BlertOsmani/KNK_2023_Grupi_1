package Models;

public class Main {
    private static Session session;

    public static Session getSession(){
        return session;
    }
    public static void setSession(Session usersession){
        session = usersession;
    }
}
