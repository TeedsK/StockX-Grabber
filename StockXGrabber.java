package GUI.Overlays.ItemCreation;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
/**
 * @author Teeds - Theo K
 */
public class StockXRequest {
    String search;
    CreationSearchArea area;
    
    /**
     * Creates a request search object
     * @param area the parent panel to add the found searches to
     * @param name the name of the item to search
     */
    public StockXRequest(CreationSearchArea area, String name) {
        this.search = name;
        this.area = area;
        run();
    }
    /**
     * Sends a request to StockX search and returns all products found
     */
    private void run() {
        try {
            String url = "https://stockx.com/api/browse?&_search="+search+"&dataType=product";
            String json = Jsoup.connect(url).ignoreContentType(true).execute().body();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(json);
            JSONObject jsonObject =  (JSONObject) obj;
            JSONArray groupOptions = (JSONArray) jsonObject.get("Products");
            Iterator<JSONObject> iterator = groupOptions.iterator();
            int x = 0;
            while (area.getStockXSearch().equals(search) && x < 20 && iterator.hasNext()) {
                JSONObject product = iterator.next();
                StockXProduct product_info = new StockXProduct((String) product.get("title"), (long) product.get("retailPrice"), (String) product.get("brand"), (String) product.get("shortDescription"), (String) product.get("colorway"), (String) product.get("styleId"), (String) product.get("releaseDate"));
                product_info.setSearched(search);
                product_info.setSmallImageUrl((String) ((JSONObject)product.get("media")).get("thumbUrl"));
                area.addSearchItem(product_info);
                x++;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
