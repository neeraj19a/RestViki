import java.net.UnknownHostException;

/**
 * Created by NEERAJ on 5/1/2019.
 */
class MainClass {

    public static void main(String args[]) throws UnknownHostException {

        MainClass mainClass = new MainClass();
        mainClass.createThreads();
    }

    public void createThreads() {
        int pageNumber = 1;
        //create 200 Thread using for loop

        for (pageNumber = 1; pageNumber <= 200; pageNumber++) {
            Parallel temp = new Parallel(pageNumber);
            temp.start();

        }
    }
}
