import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Stopwatch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class StockXGrabber {
    private WebDriver drive() {
        System.setProperty("webdriver.chrome.driver", "lib//selenium//chromedriver//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("user-agent=Mozilla");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--mute-audio");
        options.addArguments("--window-size=1920x1080");
        // options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins-discovery");
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.addArguments("disable-infobars");
        options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }

    public StockXGrabber() throws IOException {
        test2();
        
        // WebDriver driver = drive();

        // Scanner scan = new Scanner(System.in);
        // System.out.println("What stockx Item?");
        // String input = scan.nextLine();
        // String url = "https://stockx.com/search?s=" + input.replace(" ", "%20") + "&num=100";
        
        // .timeout(0).ignoreHttpErrors(true)
        // .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
        // .header("Host", "stockx.com")
        // .header("Connection", "keep-alive")
        // .header("Cache-Control", "max-age=0")
        // .header("Origin", "https://stockx.com/")
        // .header("Upgrade-Insecure-Requests", "1")
        // .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.48 Safari/537.36")
        // .header("Content-Type", "application/x-www-form-urlencoded")
        // .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
        // .referrer(url)
        // .header("Accept-Encoding", "gzip, deflate, br")
        // .header("Accept-Language", "en-US,en;q=0.8")
        // .get()
        // .body()
        // .text();
        // String url = "https://stockx.com/air-jordan-1-retro-high-silver-toe-w";
        // System.out.println(url);
        // WebClient webClient2 = new WebClient();

        // HtmlPage myPage = webClient2.getPage(url);
        // Document document = Jsoup.connect(url)
        // .userAgent("Mozilla")
        // .ignoreHttpErrors(true)
        // .timeout(5000)
        // .get();

        // System.out.println(document.body().text());
        // Element Item_Name = document.select("h1.name").first();
        // System.out.println("Item Name : " + Item_Name.text());

        // ArrayList<String> downServers = new ArrayList<>();
        // Element table = document.select("table").get(0); //select the first table.
        // Elements rows = table.select("tr");

        // for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
        //     Element row = rows.get(i);
        //     Elements cols = row.select("td");
        //     System.out.println(row.text());
        //     // if (cols.get(7).text().equals("down")) {
        //     //     downServers.add(cols.get(5).text());
        //     // }
        // }

        // System.out.println(document);
        // driver.get(url);
        // List<WebElement> ele = driver.findElements(By.className("css-8iq8tu-PrimaryText.e1inh05x0"));
        // System.out.println(driver.getPageSource());
        // driver.quit();
        // while(true) {
        //     try {
        //         System.out.println("title = " + eles.text());
        //         Thread.sleep(10);
        //     } catch(Exception e) {}
        // }
        // Element eles = document.select("h2.ProductTemplateGridCell__BaseText-sc-1yrb6b3-0.ProductTemplateGridCell__Name-sc-1yrb6b3-2.fTNuqO").first();
        // Elements elements = document.body().select("*");
        // for (Element element : elements) {
        //     System.out.println(element.text());
        // }
        // for(Element e : eles) {
        //     System.out.println(e.text());
        //     System.out.println("1");
        // }
        // System.out.println(document.body().text());
    }
    private void sleepTime(int time) {
        try {
            Thread.sleep(time);
        } catch(Exception e) {}
    }

    WebDriver driver;
    public ArrayList<String[]>  getAsks(String product) {
        driver.get("https://stockx.com/buy/" + product);
        waitPage();
        waitForPageItemToLoad();
        ArrayList<String[]> items = new ArrayList<String[]>();
        List<WebElement> buyPrices = driver.findElements(By.className("tile-subvalue"));
        List<WebElement> buySizes = driver.findElements(By.className("tile-value"));
        for(int x = 0; x < buySizes.size(); x++) {
            String[] item = new String[2];
            item[0] = buySizes.get(x).getText();
            item[1] = buyPrices.get(x * 2).getText();
            items.add(item);
        }
        return items;
    }

    private WebElement waitForPageItemToLoad() {
        while(true) {
            WebElement item_Name;
            try {
                item_Name = driver.findElement(By.className("proxima-heading"));
                if(item_Name != null) {
                    return item_Name;
                }
                sleepTime(5);
            } catch(org.openqa.selenium.NoSuchElementException e) {}
        }
    }

    private void clickUnderstand() {
        while(true) {
            try {
                if(driver.findElement(By.tagName("body")).getText().contains("I Understand")) {
                    return;
                }
                sleepTime(5);
            } catch(org.openqa.selenium.NoSuchElementException e) {}
        }
    }

    public void getItems(String search) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        driver.get("https://stockx.com/search?s=" + search.trim().replace(" ", "%20"));
        // sleepTime(5000);
        int total_items_amount = numberOfItemsFromSearch();
        System.out.println("Possible Item Amount: " + total_items_amount);
        // ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> urls = new ArrayList<String>();
        // List<WebElement> item_names = driver.findElements(By.className("css-8iq8tu-PrimaryText.e1inh05x0"));
        
        List<WebElement> tiles = driver.findElements(By.xpath("//div[@data-testid='product-tile']"));
       
        for(int x = 0; x < tiles.size(); x++) {
            // if(x > 20) {
            //     break;
            // }
            String url = tiles.get(x).findElement(By.xpath(".//a")).getAttribute("href");
            String image = tiles.get(x).findElement(By.xpath(".//a/div[1]/div/div/picture/img")).getAttribute("src");
            String name = tiles.get(x).findElement(By.xpath(".//a/div[2]/div[1]")).getText();
            System.out.println(url);
            StockXItem item = new StockXItem(this, url, name, image);
            urls.add(url);
            panel.addSearchItem(item);
        }
        stopwatch.stop();
        long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("Time in milliseconds "+millis);
        System.out.println("that took: " + stopwatch);
        System.out.println("-------------------");
        // List<WebElement> urlsPart2 = urlsPart1.findElements(By.className("tile css-yrcab6-Tile e1yt6rrx0"));
    }


    private int numberOfItemsFromSearch() {
        while(true) {
            List<WebElement> maximum_items = driver.findElements(By.xpath("//b[@data-testid='search-result-count']"));
            int total_items_amount = 0;
            for(WebElement e : maximum_items) {
                String text = e.getText();
                if(text != null && !text.trim().equals("")) {
                    int count = Integer.parseInt(e.getText());
                    if(count > 0) {
                        return total_items_amount;
                    }
                }
            }
        }
    }

    public ArrayList<String[]> getBids(String product) {
        driver.get("https://stockx.com/sell/" + product);
        clickUnderstand();

        driver.findElement(By.xpath("//*[contains(text(), 'I Understand')]")).click();
        waitForPageItemToLoad();
        ArrayList<String[]> items = new ArrayList<String[]>();
        List<WebElement> sellPrice = driver.findElements(By.className("tile-subvalue"));
        List<WebElement> sellSize = driver.findElements(By.className("tile-value"));
        System.out.println("price size " + sellPrice.size() + " | size size " + sellSize.size());
        for(int x = 0; x < sellSize.size(); x++) {
            String[] item = new String[2];
            item[0] = sellSize.get(x).getText();
            item[1] = sellPrice.get(x * 2).getText();
            items.add(item);
        }
        return items;
    }
    private void test2() {
        driver = drive();
        driver.get("https://stockx.com/");
        humanVerification();
        System.out.println("Starting!");
        getItems("mocha");
    }
    private void humanVerification() {
        waitPage();
        if(driver.getTitle().equals("Access to this page has been denied.")) {
            System.out.println("Captcha Process Required!");
            List<WebElement> iframes;
            while(true) {
                try {
                    iframes = driver.findElements(By.tagName("iframe"));
                    if(iframes.size() > 0) {
                        break;
                    }
                } catch(java.lang.IndexOutOfBoundsException e) {} 
            }
            Actions action = new Actions(driver);
            action.clickAndHold(iframes.get(0)).build().perform();
            try {
                Thread.sleep(15000);
            } catch(Exception e) {}
            action.moveToElement(iframes.get(0)).release();
            while(driver.getTitle().equals("Access to this page has been denied.")) {
                System.out.println(driver.getTitle());
                try {
                    Thread.sleep(100);
                } catch(Exception e) {}
            }
        }
    }

    public void captureScreenShot() {
        File src= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);       
        try {
            org.apache.commons.io.FileUtils.copyFile(src, new File("src/images/"+System.currentTimeMillis()+".png"));
        } catch(Exception err1) {} 
    }
    
    private void waitPage() {
        int loadingTime = 0;
        while(!pageLoad()) {
            if(driver.toString().contains("null")) {
                return;
            }
            if(loadingTime > 200) {
                return;
            }
            loadingTime++;
            try {Thread.sleep(100);} catch(Exception err1) {}
        }
    }
    
    //Source https://stackoverflow.com/questions/11001030/how-i-can-check-whether-a-page-is-loaded-completely-or-not-in-web-driver
    private boolean pageLoad() {
        try {
            return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        } catch(Exception err1) {
            return false;
        }
    }

    
}