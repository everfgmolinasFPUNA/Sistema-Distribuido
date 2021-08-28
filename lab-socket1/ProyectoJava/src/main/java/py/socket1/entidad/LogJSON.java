package py.socket1.entidad;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LogJSON {
	
	public static String objetoString(Log p) {	
    	
		JSONObject obj = new JSONObject();
        obj.put("Accion", p.getAcción());
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

        Long accion = (Long) jsonObject.get("Accion");
        p.setAcción(accion);
        p.setDatos((String)jsonObject.get("Datos"));
        
        return p;
	}
	
	public static String ListLogs(List<Log> p) throws Exception {
		
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
        String temp;
        for(int i = 0 ; i < p.size() ; i++){
        	temp = p.get(i).toString();
        	list.add(temp);
        }
       // if(list.size() > 0) {
        	obj.put("Acción", list);
        //}
        

        return obj.toJSONString();
	}
	
}
