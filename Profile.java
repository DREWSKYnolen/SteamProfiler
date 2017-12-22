import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Profile
{
	String[] status = {"Offline","Online","Busy","Away","Snooze","Looking to Trade","Looking to Play"};
	String steamID;
	int lastLogOff;
	String logOffString;
	String userName;
	String profileURL;
	String avatarURL;
	int onlineStatus;
	String realName;
	int timeCreated;
	String accountCreatedString;
	String countryCode;
	String stateCode;
	String gameExtraInfo;
	String gameID;
	
	public Profile(String profileInput)
	{
		JSONObject x = JSONObject.fromObject(profileInput);
		JSONObject temp = (JSONObject) x.get("response");
		JSONArray temp2 = (JSONArray) temp.get("players");
		JSONObject data = new JSONObject();
		if (temp2.size() == 0)
		{
			steamID =  "";
			lastLogOff = 0;
			logOffString = "";
			userName = "";
			profileURL = "";
			avatarURL = "";
			onlineStatus = 0;
			realName = "";
		}
		else{
		for (int i = 0; i<temp2.size(); i++)
		{
			data = temp2.getJSONObject(i); 
		}
		
		steamID =  (String) data.get("steamid");
		lastLogOff = (int) data.getInt("lastlogoff");
		logOffString = unixTimeConvert(lastLogOff);
		userName = (String) data.get("personaname");
		profileURL = (String) data.get("profileurl");
		avatarURL = (String) data.get("avatarfull");
		onlineStatus = (int) data.get("personastate");
		realName = (String) data.get("realname");
		
		try{
		timeCreated = (int) data.getDouble("timecreated");
		accountCreatedString = unixTimeConvert(timeCreated);
		}
		catch (Exception e){
			timeCreated = 0;
			accountCreatedString = "";
		}
		try{		
			countryCode = (String) data.get("loccountrycode");		}
		catch (NullPointerException e){
			countryCode = "";
		}
		catch (Exception e)
		{
			System.out.println("error: " + e.getMessage());
		}
		accountCreatedString = unixTimeConvert(timeCreated);
		try{		
			stateCode = (String) data.get("locstatecode");
		}
		catch (NullPointerException e){
			stateCode = "";
		}
		catch (Exception e)
		{
			System.out.println("error: " + e.getMessage());
		}
		accountCreatedString = unixTimeConvert(timeCreated);
		try{		
			gameExtraInfo = (String) data.get("gameextrainfo");
		}
		catch (NullPointerException e){
			gameExtraInfo = "";
		}
		catch (Exception e)
		{
			System.out.println("error: " + e.getMessage());
		}
		try{		
			gameID = (String) data.get("gameid");
		}
		catch (NullPointerException e){
			gameExtraInfo = "";
		}
		catch (Exception e)
		{
			System.out.println("error: " + e.getMessage());
		}
		}//else
		
		
	}
	
	public String unixTimeConvert(int unixTime) //David Hoffman - StackOverflow
	{
		Date date = new Date(unixTime*1000L); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	public static String readURL(String webservice) throws java.net.MalformedURLException, java.io.IOException
	{
		URL service = new URL(webservice);
		
		String result = IOUtils.toString(service, "UTF-8");
		return result;
    }
	
	
	public String toString() {
		String result = "";
		
		result+= "steamID=" + steamID + "\nlogOffString="
				+ logOffString + "\nuserName=" + userName + "\nprofileURL=" + profileURL + "\navatarURL=" + avatarURL;
		result += "\nOnline Status: " + status[onlineStatus];
		if (timeCreated != 0)
			result +="\naccountCreatedString: " + accountCreatedString;
		if (realName != null)
			result+="\nrealName=" + realName; 
		if (countryCode !=null)
			result += "\ncountryCode: " + countryCode;
		if (stateCode != null)
			result+= "\nstateCode: " + stateCode;
		if (gameExtraInfo != null)
			result+= "\ngameExtraInfo: " + gameExtraInfo;
		if (gameID != null)
			result+= "\ngameID: " + gameID;
		return result;
	}

	public String getSteamID() {
		return steamID;
	}

	public String getLogOffString() {
		return logOffString;
	}

	public String getUserName() {
		return userName;
	}

	public String getProfileURL() {
		return profileURL;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public int getOnlineStatus() {
		return onlineStatus;
	}

	public String getRealName() {
		return realName;
	}

	public String getAccountCreatedString() {
		return accountCreatedString;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public int getTimeCreated() {
		return timeCreated;
	}

	public String getGameID() {
		return gameID;
	}

	public String getGameExtraInfo() {
		return gameExtraInfo;
	}
	
	
}