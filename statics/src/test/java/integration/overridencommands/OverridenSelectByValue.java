package integration.overridencommands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

class OverridenSelectByValue implements Command<OverridenSelenideElement> {
  @Override
  public OverridenSelenideElement execute(SelenideElement proxy, WebElementSource locator, Object[] args) {
    String valueToSet = ((String[])(args[0]))[0];
    proxy.click();
    $("#menu-").should(appear).findAll("li").findBy(attribute("data-value", valueToSet)).click();
    return (OverridenSelenideElement) proxy;
  }
}
