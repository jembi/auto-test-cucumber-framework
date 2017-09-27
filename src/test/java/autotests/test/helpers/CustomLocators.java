package autotests.test.helpers;

public class CustomLocators {

    public static String getXpathForAnyElementWithTextEquals(String text) {
        return "//*[text()='" + text + "']";
    }

    public static String getXpathForElementTypeWithTextEquals(String elementType, String text) {
        return "//" + elementType + "[text()='" + text + "']";
    }

    public static String getXpathForAnyElementWithTextContaining(String text) {
        return "//*[contains(.,'" + text + "')]";
    }

    public static String getXpathForElementTypeWithTextContaining(String elementType,String text) {
        return "//" + elementType + "[contains(text(),'" + text + "')]";
    }
}
