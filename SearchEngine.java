import java.util.*;
import java.net.URI;
import java.io.IOException;

class Handler implements URLHandler {
    List<String> FullList = new ArrayList<String> ();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Search Engine Size: %d", FullList.size());
        } else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    FullList.add(parameters[1]);
                    return String.format("Search Engine Size: %d", FullList.size());
                }
            return String.format("Number incremented!");
        } else {
            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    //for loop, if contains(part of word) then add to new list
                    //List<String> SearchList = new ArrayList<String> ();
                    //String SearchList = "";
                    List<String> SearchList = new ArrayList<String> ();
                    for (int i=0; i<FullList.size(); i++) {
                        if (FullList.get(i).contains(parameters[1])) {
                            //SearchList.add(FullList.get(i));
                            //SearchList.concat(FullList.get(i)).concat(" ");
                            SearchList.add(FullList.get(i));
                        }
                    }
                    //return String.format("Search Results: %s", SearchList); //SearchList.toArray()
                    return String.format("Search Results: " + SearchList);
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
