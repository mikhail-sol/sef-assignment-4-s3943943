import static org.junit.jupiter.api.Assertions.*; // junit 5 testing class
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PostTest {

    // setup instance variables for entire class
    int postID;
    String postTitle;
    String postBody;
    String[] postTags;
    String[] postEmergency;
    String[] postTypes;

    // before each test, intialise initial variables to ensure consistent starting data
    // ref: https://stackoverflow.com/questions/50934397/junit-variables-inside-before-not-found
    // ref: https://stackoverflow.com/questions/1200621/how-do-i-declare-and-initialize-an-array-in-java
    @BeforeEach
    public void setVariables() {
        postID = 1;
        postTitle = "Introduction to Java";
        postBody = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec.";
        postTags = new String[] {"java", "program", "tutorial"};
        postEmergency = new String[] {"Immediately Needed", "Highly Needed", "Ordinary"};
        postTypes = new String[] {"Very Difficult", "Difficult", "Easy"};
    }

    // getter methods to access variables for CommentTest class
    public int getPostID() {
        return postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public String[] getPostTags() {
        return postTags;
    }

    public String[] getPostEmergency() {
        return postEmergency;
    }

    public String[] getPostTypes() {
        return postTypes;
    }

    // test @postTitle first 5 chars alphabetical [pass]
    @Test
    public void testPostTitle_Valid() {
        postTitle = "Po tr testing123567";
        // create comment object with default constructor values (postID = 1) (postEmergency[2] = "Ordinary") (postType[2] = "Easy")
        Post post = new Post(postID, postTitle, postBody, postTags, postEmergency[2], postTypes[2]);
        // check if adding a new post returns true or false (pass or fail)
        assertTrue(post.addPost());
        post.writeToFile();
    }

    // test @postTitle first 5 chars alphabetical [fail]
    @Test
    public void testPostTitle_Invalid() {
        postTitle = "AB123 testing123567";
        Post post = new Post(postID, postTitle, postBody, postTags, postEmergency[2], postTypes[2]);
        assertTrue(post.addPost());    
    }

    // test @postBody length [pass]
    @Test
    public void testPostBody_Valid() {
        // 250 characters long
        postBody = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretiums";
        Post post = new Post(postID, postTitle, postBody, postTags, postEmergency[2], postTypes[2]);
        assertTrue(post.addPost());    
    }

    // test @postBody [fail]
    @Test
    public void testPostBody_Invalid() {
        // 249 characters long
        postBody = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium";
        Post post = new Post(postID, postTitle, postBody, postTags, postEmergency[2], postTypes[2]);
        assertTrue(post.addPost());    
    }

    // test @postTypes [Easy] & @postEmergency [Ordinary] combination [pass]
    @Test
    public void testPostEmergency_Valid() {
        // postTypes[0, 1, 2] = "Very Difficult", "Difficult", "Easy"
        // postEmergency[2] =  "Ordinary"
        Post post = new Post(postID, postTitle, postBody, postTags, postEmergency[2], postTypes[2]);
        assertTrue(post.addPost());    
    }

    // test @postTypes [Very Difficult] & @postEmergency [Ordinary] combination [fail]
    @Test
    public void testPostEmergency_Invalid1() {
        // postTypes[0] = "Very Difficult", "Difficult", "Easy"
        // postEmergency[2] = "Ordinary"
        Post post = new Post(postID, postTitle, postBody, postTags, postEmergency[2], postTypes[0]);
        assertTrue(post.addPost());    
    }

    // test @postTypes [Difficult] & @postEmergency [Ordinary] combination [fail]
    @Test
    public void testPostEmergency_Invalid2() {
        // postTypes[1] = "Difficult"
        // postEmergency[2] = "Ordinary"
        Post post = new Post(postID, postTitle, postBody, postTags, postEmergency[2], postTypes[1]);
        assertTrue(post.addPost());    
    }


}