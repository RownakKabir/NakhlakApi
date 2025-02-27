import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Auth {
    String baseUrl="https://devapi.nakhlah.xyz";
    String token;
    String email=LoremIpsum.getInstance().getEmail();
    String name=LoremIpsum.getInstance().getName();
    String pass= "Akh@" +LoremIpsum.getInstance().getZipCode();


    @Test(priority = 0)
 public void  registrationShouldsuccesse(){

     Map<String, String> json = new HashMap<>();
     json.put( "email",email);
     json.put( "username", name);
     json.put("password",pass);

   token=    given()
              .baseUri(baseUrl)
             .header("content-type", "application/json")
             .body(json)
             .log().uri()
             .log().body()
             .when()
             .post("api/auth/local/register")
             .then()
             .log().body()
             .statusCode(200)
              .extract().jsonPath().getString("jwt");

 }



    public void  registrationShouldfail(){

        Map<String, String> json = new HashMap<>();
        json.put( "email",email);
        json.put("password",pass);
        given()
                .baseUri(baseUrl)
                .header("content-type", "application/json")
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("api/auth/local/register")
                .then()
                .log().body()
                .statusCode(200);

    }


    @Test(priority = 1)
    public void  loginShouldSuccesse(){

        Map<String, String> json = new HashMap<>();
        json.put(  "identifier" ,"noah23046");
        json.put( "password","noah23046@gianes.com");


        given()
                .baseUri(baseUrl)
                .header("content-type", "application/json")
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("api/auth/local?populate=*")
                .then()
                .log().body()
                .statusCode(200);

    }



    @Test(priority = 2)
    public void ForgetPasswordShouldSuccesse(){

        Map<String, String> json = new HashMap<>();
        json.put(  "email" ,email);



        given()
                .baseUri(baseUrl)
                .header("content-type", "application/json")
                .body(json)
                .log().uri()
                .log().body()
                .when()
                .post("api/auth/forgot-password")
                .then()
                .log().body()
                .statusCode(200);

    }





}
