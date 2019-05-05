import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by NEERAJ on 5/1/2019.
 */
public class RestAPIViki {

    public org.apache.log4j.Logger log = Logger.getLogger(RestAPIViki.class);

    public RestAPIViki() {

        org.apache.log4j.BasicConfigurator.configure();
        String ret = "";
        ret = System.getProperty("file.separator");
        System.setProperty("file.separator", ret);
        PropertyConfigurator.configure(".." + ret + "RestViki" + ret + "src" + ret + "main" + ret + "log4j.properties");
    }

   /* Running Test case with RestAssured , 1st Test case with 10 response per page
   */
   @Test
    public void testVikiAPI10PerPage(Method m) {
        RestAssured.baseURI = "http://api.viki.io";

        //Using reflections to get Test Case Name
        log.info("Running Test case-->" + m.getName());
        System.out.println("Running Test case-->" + m.getName());

        int trueCount = 0;
        int falseCount = 0;
        int pageNumber = 1;

        /*variable moreFlag to store the value of "more" from each response Object*/
        String moreFlag = "";

        do {
            int page = 10;

            Response response = given().
                    when().
                    get("/v4/videos.json?app=100250a&per_page=" + page + "&page=" + pageNumber + "").
                    then().
                    statusCode(200).
                    extract().response();
            log.info("pageNumber-->" + pageNumber);
            System.out.println("pageNumber-->" + pageNumber);

            log.info("More flag-->" + response.path("more").toString());
            System.out.println("More flag-->" + response.path("more").toString());

            moreFlag = response.path("more").toString();
            for (int i = 0; i < page; i++) {

                log.info("Response flag hd-->" + response.path("response[" + i + "].flags.hd").toString());
                System.out.println("Response flag hd-->" + response.path("response[" + i + "].flags.hd").toString());

                if (response.path("response[" + i + "].flags.hd").toString().equals("true")) {
                    trueCount++;
                } else {
                    falseCount++;
                }
            }
        pageNumber++;

        }
        while (moreFlag.equalsIgnoreCase("true"));
        log.info("Total HD content is-->" + trueCount);
        System.out.println("Total HD content is-->" + trueCount);

        log.info("Total Non HD content is-->" + falseCount);
        System.out.println("Total Non HD content is-->" + falseCount);

    }

    /*Running Test case with RestAssured , 1st Test case with 50 response per page
    */
    @Test
    public void testVikiAPI50PerPage(Method m) {
        RestAssured.baseURI = "http://api.viki.io";

        log.info("Running Test case-->" + m.getName());
        System.out.println("Running Test case-->" + m.getName());

        int trueCount = 0;
        int falseCount = 0;
        int pageNumber = 1;

        String moreFlag = "";
        do {
            int page = 50;

            Response response = given().
                    when().
                    get("/v4/videos.json?app=100250a&per_page=" + page + "&page=" + pageNumber + "").
                    then().
                    statusCode(200).
                    extract().response();

            log.info("pageNumber-->" + pageNumber);
            System.out.println("pageNumber-->" + pageNumber);

            log.info("More flag-->" + response.path("more").toString());
            System.out.println("More flag-->" + response.path("more").toString());

            moreFlag = response.path("more").toString();
            for (int i = 0; i < page; i++) {
                log.info("Response flag hd-->" + response.path("response[" + i + "].flags.hd").toString());
                System.out.println("Response flag hd-->" + response.path("response[" + i + "].flags.hd").toString());

                if (response.path("response[" + i + "].flags.hd").toString().equals("true")) {
                    trueCount++;
                } else {
                    falseCount++;
                }
            }
            pageNumber++;

        }
        while (moreFlag.equalsIgnoreCase("true"));

        log.info("Total HD content is-->" + trueCount);
        System.out.println("Total HD content is-->" + trueCount);

        log.info("Total Non HD content is-->" + falseCount);
        System.out.println("Total Non HD content is-->" + falseCount);
    }

}
