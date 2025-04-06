/*
This my apple interview and the question that I got asked

The interview was held in December 2024 for summer of 2025 swe role

The gist of the question is we have a dict where we have an iphone model as a key and the value is the routes of the model

So now there may be within the model multiple routes and so if the ending of one route is the same as the beginning of another route, we can now chain those rotes together

For example:

iphone 14 : ["USA", "SPAIN"]

and 

iphone 14 : ["SPAIN", "BRAZIL"]

Now becomes 

iphone 14 : [["USA", "SPAIN", "BRAZIL"]]

As there is an intersection between the end of the first list and the beginning of the second list

Also if we have:

iphone 13 : ["USA", "BRAZIL"]

and 

iphone 13 : ["ENGLAND", "BRAZIL"]

There is no intersection and so we return a list such as

iphone 13 : [["USA", "BRAZIL"], ["ENGLAND", "BRAZIL"]]

So the idea behind my code is that:

I will create a new map where we now store the key still as a string but the value is a list of list of strings

So what I will do is iterate over all the keys of the original map and add to the new map

So now if the key is not yet in the new map then we add the key and its value as a list of list of string

Now if the key does exist in the new map, then we call the helper function

The way the helper function works is that we take the current value that is stored in the new map and initialize a new list of list

We also get the current value of the key in the old map from this we keep track of its first and last values/strings/destinations

We iterate over the new maps values which is a list of lists, so we do for each list in the list of lists

We track the current list in the list we are on and track the values/strings/destinations of that list and compare it to the first value of the list from old map

If they are equal then we add the last value from the old map's list onto the list of the new map

Else we just add that list from the old map as a list into the list of lists of the new map

Then we just add all of these new updates into our new map

The time complexity is O(n * m) and so is the space complexity
 */

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class question {
    
    public Map<String, List<List<String>>> solution(Map<String, List<String>> routes){
        Map<String, List<List<String>>> updatedRoutes = new HashMap<>();

        for(String model : routes.keySet()){
            if(!updatedRoutes.containsKey(model)){
                List<List<String>> dst = new ArrayList<>();
                dst.add(new ArrayList<>(routes.get(model)));
                updatedRoutes.put(model, dst);
            }
            else{
                helper(model, routes, updatedRoutes);
            }
        }

        return updatedRoutes;
    }

    private void helper(String model, Map<String, List<String>> routes, Map<String, List<List<String>>> updatedRoutes){
        List<List<String>> update = updatedRoutes.get(model);
        List<List<String>> newRoutes = new ArrayList<>();

        List<String> curr = routes.get(model);   
        String loc = curr.get(0); 
        String next = curr.get(curr.size() - 1);  

        for (List<String> lis : update) {
            String dest = lis.get(lis.size() - 1);  
            if (dest.equals(loc)) lis.add(next); 
            else newRoutes.add(new ArrayList<>(curr));
        }

        update.addAll(newRoutes);
    }

    // test cases made by chatgpt
    /*
        From:

        dict = {
            iphone 13 : ["USA", "BRAZIL"]
            iphone 14 : ["USA", "SPAIN"]
            iphone 15 : ["SPAIN", "BRAZIL"]
            iphone 14 : ["SPAIN", "BRAZIL"]
            iphone 13 : ["ENGLAND", "BRAZIL"]
            iphone 15 : ["USA", "ENGLAND"]
        }

        Expected output:
            dict = {
                iphone 13: [["USA", "BRAZIL"], ["ENGLAND", "BRAZIL"]]
                iphone 14: [["USA", "SPAIN", "BRAZIL"]]
                iphone 15: [["SPAIN", "BRAZIL"], ["USA", "ENGLAND"]]
            }
     */
    public static void main(String[] args) {
        question q = new question();

        Map<String, List<String>> input1 = new HashMap<>();
        input1.put("iphone 13", Arrays.asList("USA", "BRAZIL"));
        input1.put("iphone 14", Arrays.asList("USA", "SPAIN"));
        input1.put("iphone 15", Arrays.asList("SPAIN", "BRAZIL"));

        Map<String, List<String>> input2 = new HashMap<>();
        input2.put("iphone 14", Arrays.asList("SPAIN", "BRAZIL"));

        Map<String, List<String>> input3 = new HashMap<>();
        input3.put("iphone 13", Arrays.asList("ENGLAND", "BRAZIL"));

        Map<String, List<String>> input4 = new HashMap<>();
        input4.put("iphone 15", Arrays.asList("USA", "ENGLAND"));

        Map<String, List<List<String>>> result = q.solution(input1);

        for (Map<String, List<String>> input : List.of(input2, input3, input4)) {
            for (String model : input.keySet()) {
                q.helper(model, input, result);
            }
        }

        for (String key : result.keySet()) {
            System.out.println(key + ": " + result.get(key));
        }
    }
}
