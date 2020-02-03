package integration.overridencommands;

import com.codeborne.selenide.SelenideElement;

public interface OverridenSelenideElement extends SelenideElement {
  void selectOptionByValue(String... value);
}
