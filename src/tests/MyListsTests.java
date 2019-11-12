package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList(){

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();

        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        articlePageObject.addArticleToMyListToNewFolder(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteOneOfThem(){

        String search_line = "Large Hadron Collider";
        String article_title_1 = "Large Hadron Collider";
        String article_title_2 = "LHCb experiment";
        String name_of_folder = "Particle accelerators";

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.clickByArticleWithSubstring(article_title_1);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToMyListToNewFolder(name_of_folder);
        articlePageObject.closeArticle();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.clickByArticleWithSubstring(article_title_2);
        articlePageObject.waitForTitleElement();
        articlePageObject.addArticleToMyListToSavedFolder(name_of_folder);
        articlePageObject.closeArticle();

        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.clickMyList();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title_2);
        myListsPageObject.waitForArticleAndClick(article_title_1);
        String actual_title = articlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title",
                article_title_1,
                actual_title
        );












    }

}
