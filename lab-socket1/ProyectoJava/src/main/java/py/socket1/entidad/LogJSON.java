package py.socket1.entidad;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LogJSON {
	
	public static String objetoString(Log p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("Acci�n", p.getAcci�n());
        obj.put("nombreAcci�n", p.getNombreAcci�n());
        obj.put("IP", p.getIPAddress());
        obj.put("Puerto", p.getPort());
        obj.put("Datos", p.getDatos());
        
        return obj.toJSONString();
    }
	
	public static Log stringObjeto(String str) throws Exception {
    	Log p = new Log();
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;

        int accion = (int) jsonObject.get("Acci�n");
        p.setAcci�n(accion);
        p.setDatos((String)jsonObject.get("Datos"));
        
        return p;
	}
	
}
