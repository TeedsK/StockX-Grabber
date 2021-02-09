import java.util.ArrayList;

public class StockXItem {
    String itemUrl;
    String name;
    String size;
    String imageUrl;
    ArrayList<String> shoe_sizes = new ArrayList<String>();
    ArrayList<Double> bid_prices = new ArrayList<Double>();
    ArrayList<Double> ask_prices = new ArrayList<Double>();
    StockXGrabber device;
    public StockXItem(StockXGrabber device) {
        this.device = device;
    }
    public StockXItem(StockXGrabber device, String url) {
        this.itemUrl = url.replace("https://stockx.com/", "");
    }
    public StockXItem(StockXGrabber device, String url, String name) {
        this.name = name;
        this.itemUrl = url.replace("https://stockx.com/", "");
    }
    public StockXItem(StockXGrabber device, String url, String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.itemUrl = url.replace("https://stockx.com/", "");
    }
    public String getItemUrl() {
        return itemUrl;
    }
    public String getName() {
        return name;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void getBidPrice() {
        boolean shoeSize = shoe_sizes.size() == 0;
        for(String[] sizes : device.getBids(itemUrl)) {
            if(shoeSize) {
                this.shoe_sizes.add(sizes[0]);
            }
           
            this.bid_prices.add(Double.parseDouble(sizes[1]));
        }
    }
    public void getAskPrice() {
        boolean shoeSize = shoe_sizes.size() != 0;
        for(String[] sizes : device.getAsks(itemUrl)) {
            if(shoeSize) {
                this.shoe_sizes.add(sizes[0]);
            }
            this.ask_prices.add(Double.parseDouble(sizes[1]));
        }
    }
}
