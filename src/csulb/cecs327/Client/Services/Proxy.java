/**
 * The Proxy implements ProxyInterface class. The class is incomplete
 *
 * @author Oscar Morales-Ponce
 * @version 0.15
 * @since 2019-01-24
 */
package csulb.cecs327.Client.Services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import csulb.cecs327.Server.RPC.ClientCommunicationModule;

import java.util.TreeSet;


public class Proxy implements ProxyInterface {
    private ClientCommunicationModule client = null;
    private ClientRemoteRef crr;

    public Proxy(int portNumber) {
        this.client = new ClientCommunicationModule();
        client.connect(portNumber);
        this.crr = new ClientRemoteRef(client);
    }

    /**
     * This class creates a JSON object by referencing the catalog.json
     * The method then dynamically enters the parameters necessary for the method
     *
     * @param remoteMethod = name of method client wishes to find
     * @param param        = String array of parameters
     * @return - JSON Object of desired method
     */
    public JsonObject synchExecution(String remoteMethod, String[] param) {
        JsonObject jsonRequest = crr.getRemoteReference(remoteMethod);
        JsonObject jsonParam = jsonRequest.get("param").getAsJsonObject();

        TreeSet<String> entries = new TreeSet<>(jsonParam.keySet());
        int i = 0;
        for (String s : entries) {
            jsonParam.addProperty(s, param[i]);
            i++;
        }

        jsonRequest.add("param", jsonParam);

        JsonParser parser = new JsonParser();
        //String strRet =  this.dispacher.dispatch(jsonRequest.toString());
        System.out.println("Sending request: " + jsonRequest.toString());
        String strRet = client.sendRequest(jsonRequest.toString());
        System.out.println("Returning response from server to input stream: " + strRet);
        String myReturn = strRet.trim();
        return parser.parse(myReturn).getAsJsonObject();
    }

    /*
     * Executes the  remote method remoteMethod and returns without waiting
     * for the reply. It does similar to synchExecution but does not
     * return any value
     *
     */
    //Todo: implement this method
    public void asynchExecution(String remoteMethod, String[] param) {
        JsonObject jsonRequest = new JsonObject();
        JsonObject jsonParam = new JsonObject();
        jsonRequest.addProperty("remoteMethod", remoteMethod);
        jsonRequest.addProperty("object", "UserServices");

        jsonParam.addProperty("user", param[0]);
        jsonRequest.add("param", jsonParam);

        JsonParser parser = new JsonParser();
        System.out.println("Sending request: " + jsonRequest.toString());
        client.sendRequest(jsonRequest.toString());


    }
}


