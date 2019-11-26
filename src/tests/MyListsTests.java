package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyListToNewFolder(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
            articlePageObject.closePopUp();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            myListsPageObject.openFolderByName(name_of_folder);
        }

        myListsPageObject.swipeByArticleToDelete(article_title);
    }


    @Test
public void testSaveTwoArticlesToMyListAndDeleteOneOfThem() {

    String search_line = "Large Hadron Collider";
    String article_title_1 = "LHCb experiment";
    String article_title_2 = "Large Hadron Collider";
    String name_of_folder = "Particle accelerators";

    SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(search_line);
    String expected_title = searchPageObject.getArticleWithSubstring(article_title_1);
    searchPageObject.clickByArticleWithSubstring(article_title_1);

    ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

    if (Platform.getInstance().isAndroid()) {
        articlePageObject.addArticleToMyListToNewFolder(name_of_folder);
    } else {
        articlePageObject.addArticlesToMySaved();
        articlePageObject.closePopUp();
    }
    articlePageObject.closeArticle();

    searchPageObject.initSearchInput();
    searchPageObject.typeSearchLine(search_line);
    searchPageObject.waitForSearchResultsAppear();

    searchPageObject.clickByArticleWithSubstring(article_title_2);

    if (Platform.getInstance().isAndroid()) {
        articlePageObject.addArticleToMyListToSavedFolder(name_of_folder);
    } else {
        articlePageObject.addArticlesToMySaved();
        articlePageObject.closePopUp();
    }
    articlePageObject.closeArticle();

    NavigationUI navigationUI = NavigationUIFactory.get(driver);
    navigationUI.clickMyList();

    MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

    if (Platform.getInstance().isAndroid()) {
        myListsPageObject.openFolderByName(name_of_folder);
    }
    myListsPageObject.swipeByArticleToDelete(article_title_2);
    String actual_title = searchPageObject.getArticleWithSubstring(article_title_1);
    assertEquals(
            "We see unexpected article",
            expected_title,
            actual_title
    );
}
}
