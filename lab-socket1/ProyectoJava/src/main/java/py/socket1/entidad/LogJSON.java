package py.socket1.entidad;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LogJSON {
	
	public static String objetoString(Log p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("Acción", p.getAcción());
        obj.put("nombreAcción", p.getNombreAcción());
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

        int accion = (int) jsonObject.get("Acción");
        p.setAcción(accion);
        p.setDatos((String)jsonObject.get("Datos"));
        
        return p;
	}
	
}
