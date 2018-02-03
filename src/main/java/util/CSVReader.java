package util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {

    
    private static final String MASTER_FILE_PATH = "resources/master.csv";

    public static String getValueForKind(int kindCode) throws IOException
    {
	Reader in = new FileReader(MASTER_FILE_PATH);
	Iterable<CSVRecord> records = CSVFormat.newFormat(';').parse(in);
	for (CSVRecord record : records) {
	    int code = Integer.parseInt( record.get(0) );
	    if(code == kindCode)
	    {
		return record.get(1);
	    }
	}
	
	return null;
	   
    }
    
}
