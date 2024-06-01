import java.util.ArrayList; // array handling class
import java.util.Arrays;

import java.io.File; // file handling class
import java.io.FileWriter;

import java.io.IOException; // handle errors class

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postEmergency;
    private String postType;

    // constructor to initalise values
    // ref: https://www.w3schools.com/java/java_constructors.asp
    // ref: https://www.w3schools.com/java/ref_keyword_this.asp
    public Post(int postID, String postTitle, String postBody, String[] postTags, String postEmergency, String postType) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postEmergency = postEmergency;
        this.postType = postType;
    }

    public boolean addPost()
    {
        // addPost validation
        
        // condition 1: title minimum 10 characters and maximum 250
        // check postTitle length
        if (postTitle.length() < 10 || postTitle.length() > 250) {
            System.out.println("POST ERROR: postTitle character length < 10 or > 250");
            // exit the function if postAdded returns false
            // ref: https://www.quora.com/How-do-I-exit-a-method-in-Java
            // ref: https://www.w3schools.com/java/ref_keyword_return.asp

            return false;
        }

        // condition 1: check first 5 characters are not numbers/special charss
        // ref: https://www.w3schools.com/java/ref_string_charat.asp
        // ref: https://www.javatpoint.com/post/java-character-isletter-method
        for (int i = 0; i < 5; i++) {
            char character = postTitle.charAt(i);
            if (!(Character.isLetter(character) || (Character.isWhitespace(character)))) {
                System.out.println("POST ERROR: One of first 5 characters of postBody is NOT letter");
                return false;
            }
        }


        // condition 2: post body minimum 250 characters
        // check postBody length
        if (postBody.length() < 250) {
            System.out.println("POST ERROR: postBody character length < 250");
            return false;
        }


        // condition 3: minimum of 2 tags, maximum of 5 tags | minimum of 2 characters and a maximum of 10 characters | should not include any upper-case letters
        if (postTags.length < 2 || postTags.length > 5) {
            System.out.println("POST ERROR: postTags count < 2 or > 5");
            return false;
        }
        // ref: https://www.w3schools.com/java/java_arrays_loop.asphttps://www.w3schools.com/java/java_arrays_loop.asp
        for (String tag : postTags) {

            //check tag character count
            if (tag.length() < 0 || tag.length() > 10)  {
                System.out.println("ERROR: tag item length < 0 or > 10");
                return false;
            }
            //check for uppercase letters
            // ref: https://www.geeksforgeeks.org/how-to-check-if-a-string-contains-only-lowercase-letters-in-java/
            if (!tag.matches("[a-z]+")) {
                System.out.println("POST ERROR: tag item contains uppercase letter or does not fit into a-z alphabet range");
                return false;
            }
        }


        // condition 4: can be classified into "Very Difficult", "Difficult", "Easy" | if "Easy", then no more than 3 tags | if "Difficult" or "Very Difficult", have a minimum of 300 characters in post body
        if (postType.equals("Easy") && postTags.length > 3) {
            System.out.println("POST ERROR: postType is Easy AND tag count > 3");
            return false;
        }

        if ((postType.equals("Difficult") || postType.equals("Very Difficult")) && postBody.length() < 300) {
            System.out.println("POST ERROR: postType is Difficult or Very Difficult and postBody length is < 300");
            return false;
        }


        // condition 5: can be classified into "Immediately Needed", "Highly Needed", "Ordinary" | if "Easy" then cannot be "Immediately Needed", "Highly Needed" | if "Very Difficult", "Difficult" then cannot be "Ordinary"
        if ((postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed")) && postType.equals("Easy")) {
            System.out.println("POST ERROR: postEmergency is Immediately Needed or Highly Needed AND postType is Easy");
            return false;
        }

        if ((postType.equals("Difficult") || postType.equals("Very Difficult")) && postEmergency.equals("Ordinary")) {
            System.out.println("POST ERROR: postType is Difficult or Very Difficult AND postEmergency is Ordinary");
            return false;
        }

        // if validation passes, return true
        return true;
    }

    // create file
    // https://www.w3schools.com/java/java_class_methods.asp
    // ref: https://www.w3schools.com/java/java_files_create.asp
    public boolean createFile() {
        try {
            File postFile = new File("postid-" + postID + ".txt");
            if (postFile.createNewFile()) {
                System.out.println("Post created " + postFile.getName());
            }
            else {
                System.out.println("A post with that ID already exists.");
                return false;
            }
        } 
        // if any errors during file creation
        catch (IOException e) {
            System.out.println("SYSTEM ERROR: An error has occured.");
            // show location where error occured
            // ref: https://docs.oracle.com/javase/6/docs/api/java/lang/Throwable.html#printStackTrace()
            e.printStackTrace();
            return false;
        } 
        return true;
    }

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter("postid-" + postID + ".txt");

            // ensure each line is on a new one https://stackoverflow.com/questions/41789470/files-write-appending-new-lines-in-a-text-file
            String lineSeperator = System.getProperty("line.separator");
            
            // write post content
            myWriter.write("PostID: " + postID);

            myWriter.write(lineSeperator);

            myWriter.write("Post Title: " + postTitle);

            myWriter.write(lineSeperator);

            myWriter.write("Post Body: " + postBody);

            myWriter.write(lineSeperator);

            myWriter.write("Post Tags: " + Arrays.toString((postTags)));

            myWriter.write(lineSeperator);

            myWriter.write("Post Emergency: " + postEmergency);

            myWriter.write(lineSeperator);

            myWriter.write("Post Type: " + postType);

            myWriter.close();
            System.out.println("SYSTEM INFO: File was written to!");
        }
        // if any errors writing to file
        catch (IOException e) {
            System.out.println("SYSTEM ERROR: An error has occured.");
            e.printStackTrace();
        }
    }
}
