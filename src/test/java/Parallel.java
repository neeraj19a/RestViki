import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by NEERAJ on 5/1/2019.
 */
public class Parallel extends Thread {

    /*trueCount variable to keep count of Flag hd:true*/

    public static int trueCount = 0;
    /*falseCount variable to keep count of Flag hd:false*/
    public static int falseCount = 0;
    String moreFlag;
    public int pageNumber;

    public Parallel(int i) {
        pageNumber = i;
    }

    @Override
    public void run() {
        RestAssured.baseURI = "http://api.viki.io";
        moreFlag = "";
        System.out.println("Running Page-->"+pageNumber);
        Response response = given().
                when().log().path().
                get("/v4/videos.json?app=100250a&per_page=" + 10 + "&page=" + pageNumber + "").
                then().
                statusCode(200).
                extract().response();

        System.out.println("More flag-->" + response.path("more").toString());

        moreFlag = response.path("more").toString();
        for (int page =0; page<10; page++) {
            if (response.path("response["+page+"].flags.hd").toString().equals("true")) {
                trueCount++;
            } else {
                falseCount++;
            }

        }
        System.out.println("Total Final HD content is-->" + trueCount);
        System.out.println("Total Final Non HD content is-->" + falseCount);
    }

}


