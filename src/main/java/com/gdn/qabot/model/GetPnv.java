package com.gdn.qabot.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.gdn.qabot.controller.QaBotController;
/**
 * Created by avinash.t
 */
public class GetPnv extends QaBotController{

	public String getPnv(String host,String inputFromController)
	{
		String line = null;
		String line2 = null;

	try{
		String pnvUser=inputFromController;
		APP_LOGS.debug("name"+pnvUser);

		String script = "/Users/avinash.t/Avinash/PNVCreation/getPnv.sh";
        String targetTemp = script.concat(" ").concat(pnvUser).concat(" ").concat(host);



        String target = new String(targetTemp);
		APP_LOGS.debug("Target"+target);

        Runtime rt = Runtime.getRuntime();
        boolean proc = rt.exec(target).waitFor(5, TimeUnit.SECONDS);
		
			
				 String fileName = "/Users/avinash.t/Avinash/PNVCreation/pnv.txt";

			        // This will reference one line at a time
			         line2 = null;

			       
			            // FileReader reads text files in the default encoding.
			            FileReader fileReader = 
			                new FileReader(fileName);

			            // Always wrap FileReader in BufferedReader.
			            BufferedReader bufferedReader = 
			                new BufferedReader(fileReader);

			            while((line2 = bufferedReader.readLine()) != null) {
			                APP_LOGS.debug("Inside File:"+line2);
			                line=line2;
			            }   

			            // Always close files.
			            bufferedReader.close();    

			
	}
	 catch (Throwable t)
    {
        t.printStackTrace();
    }
    APP_LOGS.debug("returning answer:"+line);

    return line;

	}
	
}
