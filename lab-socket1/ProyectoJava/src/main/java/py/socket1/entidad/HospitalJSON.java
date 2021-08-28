package py.socket1.entidad;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HospitalJSON {


    public static void main(String[] args) throws Exception {
    	byte a = 3;
    	System.out.println(a);
    }
    
    public static String objetoString(Hospital p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("nroCama", p.getNroCama());
        obj.put("estado", p.getEstado());
        obj.put("nroHospital", p.getNroHospital());

        return obj.toJSONString();
    }
    
    
    public static Hospital stringObjeto(String str) throws Exception {
    	Hospital p = new Hospital();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long nroHospital = (Long) jsonObject.get("nroHospital");
        p.setNroHospital(nroHospital);
        p.setEstado((Long)jsonObject.get("estado"));
        p.setNroCama((Long)jsonObject.get("nroCama"));
        
        return p;
	}

}
