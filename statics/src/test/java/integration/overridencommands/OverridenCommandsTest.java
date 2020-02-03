package integration.overridencommands;

import com.codeborne.selenide.commands.Commands;
import integration.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static integration.overridencommands.MyFramework.$_;

class OverridenCommandsTest extends IntegrationTest {
  @BeforeEach
  void setUpFramework() {
    MyFramework.setUp();
    useProxy(false);
  }

  @Test
  void userCanOverrideCommandForCustomElement() {
    open("https://material-ui.com/discover-more/showcase/");
    // this one passes
    $_("div.MuiSelect-selectMenu").selectOptionByValue("similarWebVisits");

  }
  @Test
  void userCanOverrideCommandForCustomElementOnly() {
    open("https://material-ui.com/discover-more/showcase/");
    // this one almost passes, but shouldn't. It crashes on casting the object, but the value is chosen,
    // because the overriden command is being used instead of the original `selectOptionByValue` command
    $(".MuiSelect-selectMenu").selectOptionByValue("stars");
  }

  @Test
  void userCanOverrideCommandForCustomElementWithoutBreakingOriginalCommand() {
    open("https://www.w3schools.com/css/css_form.asp");
    // this one fails, but should pass. One would expect the framework to use original `selectOptionByValue` command,
    // but the overriden one is used, which makes no sense for the native `select` dropdown.
    $("#country").selectOptionByValue("usa");
  }

  @AfterEach
  void resetSelenideDefaultCommands() {
    Commands.getInstance().resetDefaults();
  }
}
