package cscie55.hw8;
/* Adapted from code in "Java Programming", Chapter 20
   by Yakov Fain
   Reference "Java8 Resources" [http://courses.dce.harvard.edu/~cscie55/Java8Resources.html]
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BeerFest {
    public  static class Beer {
        public final String name;
        public final String country;
        private float price;

        public Beer(String name, String country,float price){
            this.name=name;
            this.country=country;
            this.price=price;
        }

        public String toString(){
            return "Country: " + country +  " Name: " + name + ", price: " + price;
        }
        public float getPrice() {
            return price;
        }
        public void setPrice(float price) {
            this.price = price;
        }
    }
    public static List<Beer> beerQuery(List<Beer> beerList, Predicate <Beer> predicate) {
        List<Beer> result = beerList.stream()
        // ToDo Select Beer's that meet the predicate
                .filter( predicate ).collect(Collectors.<Beer>toList());
        return result;
    }
    static List<Beer> loadCellar(){
        List<Beer> beerStock = new ArrayList<>();

        beerStock.add(new Beer("Stella", "Belgium", 7.75f));
        beerStock.add(new Beer("Sam Adams", "USA", 7.00f));
        beerStock.add(new Beer("Obolon", "Ukraine", 4.00f));
        beerStock.add(new Beer("Bud Light", "USA", 5.00f));
        beerStock.add(new Beer("Yuengling", "USA", 5.50f));
        beerStock.add(new Beer("Leffe Blonde", "Belgium", 8.75f));
        beerStock.add(new Beer("Chimay Blue", "Belgium", 10.00f));
        beerStock.add(new Beer("Brooklyn Lager", "USA", 8.25f));

        return beerStock;
    }
    static Predicate<Beer> priceRangeQuery(Float priceLowest, Float priceHighest) {
        // ToDo: compose and return a Predicate that will
        //       express the selection criterion
        return b -> b.getPrice() >= priceLowest && b.getPrice() <= priceHighest;
    }
    static Predicate<Beer> countryQuery(String countryName) {
        // ToDo: compose and return a Predicate that will
        //       express the selection criterion
        return b -> b.country.equals(countryName);
    }
    public static void main(String argv[]) {
        List<Beer> beerList = loadCellar();
        // Call beerQuery with a predicate for selecting a country
        beerQuery(beerList, countryQuery(((argv.length != 0  && argv[0] != null) ? argv[0] : "USA"))).stream().forEach(System.out::println);
        // Call beerQuery with a predicate for a price range
        beerQuery(beerList, priceRangeQuery(((argv.length >= 3 && argv[1] != null) ? Float.parseFloat(argv[1]) : 8.50f),
                ((argv.length >= 3 && argv[2] != null) ? Float.parseFloat(argv[2]) : 10.00f))).stream().forEach(System.out::println);
    }
}

