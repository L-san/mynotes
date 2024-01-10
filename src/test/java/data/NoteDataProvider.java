package data;

import org.testng.annotations.DataProvider;

public class NoteDataProvider {

    @DataProvider
    public Object[][] notesTitlesAndDescriptions(){
        return new Object[][]{
            {"<>","123"},
            {"&!","abc"},
            {"123","<>"},
            {"abc","123"},
            {"456","abc"},
            {"efg","efg"},
            {"%%%","#%%"},
            {"888","999"},
            {"hjk","&^%"}};
    }
}
