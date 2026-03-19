package ra.entity;

import java.util.List;
import java.util.Scanner;

public class Movie {
    private String movieId;
    private String movieName;
    private int duration;
    private int views;

    public Movie() {
    }

    public Movie(String movieId, String movieName, int duration, int views) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.duration = duration;
        this.views = views;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }


    public void inputData(Scanner sc, List<Movie> movies){
        sc.nextLine();
        System.out.println("==== NHẬP THÔNG TIN PHIM ====");
        String id = "MOV" + String.format("%03d", movies.size()+1);
        this.movieId = id;
        // Ten Phim
        do{
            System.out.print("Tên phim: ");
            String inputName = sc.nextLine();
            if(inputName.isEmpty()){
                System.out.println("Tên phim không được để trống, vui lòng nhập lại!!");
            }else{
                this.setMovieName(inputName);
                break;
            }
        }while (true);

        // Thời lượng phim
        do{
            System.out.print("Thời lượng phim: ");
            int inputDuration = Integer.parseInt(sc.nextLine());
            if (inputDuration <= 0){
                System.out.println("Vui lòng nhập thời lượng phim lớn hơn 0");
            }else {
                this.setDuration(inputDuration);
                break;
            }
        }while (true);

        // Lượt xem
        do{
            System.out.print("Lượt xem: ");
            int inputViews = Integer.parseInt(sc.nextLine());
            if(inputViews < 0){
                System.out.println("Vui lòng nhập lượt xem lớn hơn hoặc băng 0!!");
            }else{
                this.setViews(inputViews);
                break;
            }
        }while (true);

        System.out.println("Hoàn thành nhập thông tin phim");
    }

    public void displayData(){
        System.out.printf( this.getMovieId() + " | " + this.getMovieName() + " | " + this.getDuration() + " phút | " + this.getViews() + "\n");
    }
}
