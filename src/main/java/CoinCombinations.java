import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class CoinCombinations {
  public static void main(String[] args) {

    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/detector", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/detector.vtl");

      String userInputtedNumber = request.queryParams("userInput");

      String coins = coinCombination(userInputtedNumber);
      model.put("coins", coins);
      model.put("userInput", userInputtedNumber);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

public static String coinCombination(String userInput) {

    Integer amountChange =  Integer.parseInt(userInput);
    HashMap<String, Integer> change = new HashMap<>();

    change.put("quarters", 25);
    change.put("dimes", 10);
    change.put("nickels", 5);
    change.put("pennies", 1);

    Integer valueQuarters = change.get("quarters");
    Integer valueDimes = change.get("dimes");
    Integer valueNickels = change.get("nickels");
    Integer valuePennies = change.get("pennies");

    Integer sumQuarters = 0;
    Integer sumDimes = 0;
    Integer sumNickels = 0;
    Integer sumPennies = 0;

      while(amountChange >= 25){
      amountChange = (amountChange - valueQuarters);
      sumQuarters++;
    }

    while(amountChange >= 10){
      amountChange = (amountChange - valueDimes);
      sumDimes++;
    }
    while(amountChange >= 5){
      amountChange = (amountChange - valueNickels);
      sumNickels++;
    }
    while(amountChange >= 1){
      amountChange = (amountChange - valuePennies);
      sumPennies++;
    }

    String output = String.format("%d quarters, %d dimes, %d nickels, and %d pennies.", sumQuarters, sumDimes, sumNickels, sumPennies);

    return output;
  }
}
