package service;

import model.Review;

import java.util.Date;
import java.util.Scanner;

public class ReviewService {
    public static void showReview(Review review){
        System.out.printf("%s - %s\n%s\n", review.getTitle(), review.getDate(), review.getContent());
    }
    public static Review addReview(){
        Scanner scanner = new Scanner(System.in);

        Review review = new Review();
        System.out.print("Title: "); review.setTitle(scanner.nextLine());
        System.out.println("Content: "); review.setContent(scanner.nextLine());
        review.setDate(new Date());

        return review;
    }
}
