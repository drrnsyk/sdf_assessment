package vttp2022.sdfassessment;

public class Calculate {
    
    // properties
    private String request;
    private String requestID;
    private float sum = 0;
    private float count = 0;

    // constructors
    public Calculate (String request) {
        this.request = request;
    }

    // methods
    public float average () {

        // checks 
        // System.out.println("This is the request received: " + request);

        String[] requestArray = request.split(" ");
        requestID = requestArray[0];
        String[] listOfInt = requestArray[1].split(",");

        // Checks
        // System.out.println("This is the request ID: " + requestID);
        // System.out.println("This is the list of integers: " + requestArray[1]);

        for (int i = 0; i < listOfInt.length; i++) {
            sum += Integer.parseInt(listOfInt[i]);
            count += 1.0;
        }

        // Checks
        // System.out.println("This is the count: " + count);
        // System.out.println("This is the sum: " + sum);

        return sum/count;
    }

    public String getRequestID () {
        return this.requestID;
    }

}
