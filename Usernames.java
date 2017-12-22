import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import net.sf.json.JSONObject;

public class Usernames
{
	private String APIKey = "880FBCC71FF799437920DE5F471650F4";
	
	public String readURL(String webservice) throws java.net.MalformedURLException, java.io.IOException
	{
		String result;
		URL service = new URL(webservice);
		
			result = IOUtils.toString(service, "UTF-8");
		
		return result;
    }
	
	public void name2ID(String var) throws MalformedURLException, IOException
	{
		try{
		String name2ID = readURL("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key="+APIKey+"&vanityurl=" + var);
		JSONObject x = JSONObject.fromObject(name2ID);
		JSONObject data = (JSONObject) x.get("response");
		String userID = "76561198002117604";//(String) data.get("steamid");
		ID2JSON(userID);
		}
		catch (Exception e){
		System.out.println("Error: invalid username");
		}
	}
	
	public void ID2JSON(String var) throws MalformedURLException, IOException
	{
		
		String JSonString = readURL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+APIKey+"&steamids=" + var);
		Profile prof = new Profile(JSonString);

	}
	
	
	
	public String name2IDRetString(String var) throws MalformedURLException, IOException
	{
		String jsonString;
		String name2ID = readURL("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/?key="+APIKey+"&vanityurl=" + var);
		try{
		JSONObject x = JSONObject.fromObject(name2ID);
		JSONObject data = (JSONObject) x.get("response");
		String userID = (String) data.get("steamid");
		jsonString= (String) ID2JSONRet(userID);
		}
		catch (Exception e){
			jsonString = "Invalid Username";
		}
		return jsonString;
	}
	
	public String ID2JSONRet(String var) throws MalformedURLException, IOException
	{
		String JSonString = readURL("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key="+APIKey+"&steamids=" + var);
//		Profile prof = new Profile(JSonString);
//		System.out.println(prof);
		return JSonString;
	}
}
