
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class PokemonParser {

	public static String parseAbilities(String JSON) {
		JsonElement jelement = JsonParser.parseString(JSON);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonArray abilityArray = jobject.getAsJsonArray("abilities");
		String str = "";
		for(JsonElement el : abilityArray) {
			JsonObject ability = (JsonObject) el.getAsJsonObject().get("ability");
			JsonPrimitive name = (JsonPrimitive) ability.getAsJsonObject().get("name");
			str+=name.getAsString()+ ", ";
		}
		return "'s abilities: " + str.substring(0, str.length()-2) + "\n";
	}

	public static int parseBaseExp(String JSON) {
		JsonElement jelement = JsonParser.parseString(JSON);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonPrimitive exp = jobject.getAsJsonPrimitive("base_experience");
		String str =exp.getAsString();
		int res = 0;
		try {
			res = Integer.parseInt(str);
		}catch(NumberFormatException e) {}
		return res;
	}
}
