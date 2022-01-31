package ept.dic2.crud_person_app.requests;

public class Endpoints {

   public   static  final String KEY = "dfallou@ept.sn" ;

    private static final String ROOT_URL = "http://185.98.128.121/api/dguisse@ept.sn/";

    public static final String URL_CREATE_PERSON = ROOT_URL + "personnes";

    public static final String URL_READ_PERSON = ROOT_URL + "personnes";

    public static String URL_UPDATE_PERSON(String key, String email){
        return ROOT_URL + "personnes/" + email;
    }

    public static String URL_DELETE_PERSON(String key, String email){
        return ROOT_URL +  "personnes/" + email;
    }

}
