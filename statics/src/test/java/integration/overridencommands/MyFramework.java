package integration.overridencommands;

import com.codeborne.selenide.commands.Commands;
import com.codeborne.selenide.impl.ElementFinder;
import org.openqa.selenium.By;

import java.util.concurrent.atomic.AtomicInteger;

import static com.codeborne.selenide.WebDriverRunner.driver;

public class MyFramework {
  static AtomicInteger tripleClickCounter = new AtomicInteger();
  static AtomicInteger quadrupleClickCounter = new AtomicInteger();

  public static void setUp() {
    Commands.getInstance().add("selectOptionByValue", new OverridenSelectByValue());
  }

  /**
   * Replacement for standard Selenide `$` method.
   *
   * @param selector CSS selector
   *
   * @return MySelenideElement - an "advanced" version of `SelenideElement` with more custom methods
   */
  public static OverridenSelenideElement $_(String selector) {
    return ElementFinder.wrap(driver(), OverridenSelenideElement.class, null, By.cssSelector(selector), 0);
  }
}
