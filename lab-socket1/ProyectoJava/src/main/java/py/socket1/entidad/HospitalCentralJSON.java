package py.socket1.entidad;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HospitalCentralJSON {


    public static void main(String[] args) throws Exception {
    	byte a = 3;
    	System.out.println(a);
    }
    
    public static String objetoString(HospitalCentral p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("nroHospital", p.getNroHospital());
        obj.put("nombre", p.getNombreHospital());        

        return obj.toJSONString();
    }
    
    
    public static HospitalCentral stringObjeto(String str) throws Exception {
    	HospitalCentral p = new HospitalCentral();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        Long nroHospital = (Long) jsonObject.get("nroHospital");
        p.setNroHospital(nroHospital);
        p.setNombreHospital((String)jsonObject.get("nombre"));
        
        JSONArray msg = (JSONArray) jsonObject.get("resumen");
        Iterator<String> iterator = msg.iterator();
        while (iterator.hasNext()) {
        	p.resumen.add(iterator.next());
        }
        
        return p;
	}

}
