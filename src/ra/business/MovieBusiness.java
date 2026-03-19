package ra.business;

import ra.entity.Movie;

import java.util.*;

public class MovieBusiness {
    private List<Movie> movies;

    private static MovieBusiness instance;

    public MovieBusiness() {
        this.movies = new ArrayList<>();
    }

    public static MovieBusiness getInstance() {
        if (instance == null){
            instance = new MovieBusiness();
        }
        return instance;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    // Hiển thị danh sách phim
    public void displayMovieList(){
        if (this.getMovies().isEmpty()){
            System.out.println("Danh sách phim trống!!");
            return;
        }
        System.out.println("============== DANH SÁCH PHIM =============");
        System.out.printf("Mã phim" + " | " + "Tên bộ phim" + " | Thời lượng phim" + " | Lượt xem\n");
        this.getMovies().forEach(Movie::displayData);
    }

    // Thêm mới phim
    public boolean addNewMovie(Scanner sc){
        Movie newMovie = new Movie();
        newMovie.inputData(sc, this.getMovies());
        return this.movies.add(newMovie);
    }

    // Cập nhật thông tin phim
    public void updateMovieInfo(Scanner sc){
        if (this.getMovies().isEmpty()) {
            System.out.println("Danh sách phim trống!!");
            return;
        }
        System.out.println("Nhập mã phim cần cập nhật: ");
        String inputId = sc.nextLine();
        Movie target = this.getMovieById(inputId);

        if(target == null){
            System.out.println("Không tìm thấy phim cần cập nhật");
            return;
        }

        System.out.println("Thông tin phim:");
        target.displayData();

        System.out.print("\nChọn tiêu chí cần cập nhật: " +
                "1. Tên phim\n" +
                "2. Thời lượng phim\n" +
                "3. Lượt xem\n" +
                "Lựa chọn: ");
        int udChoice = Integer.parseInt(sc.nextLine());
        switch (udChoice){
            case 1:
                // Cap nhat ten phim
                do{
                    System.out.print("Cập nhật tên phim: ");
                    String udName = sc.nextLine();
                    if(udName.isEmpty()){
                        System.out.println("Tên phim không được để trống, vui lòng nhập lại!!");
                    }else{
                        target.setMovieName(udName);
                        break;
                    }
                }while (true);
                break;
            case 2:
                // Cập nhật thời lượng phim
                do{
                    System.out.print("Cập nhật thời lượng phim: ");
                    int udDuration = Integer.parseInt(sc.nextLine());

                    if (udDuration <= 0){
                        System.out.println("Vui lòng nhập thời lượng phim lớn hơn 0");
                    }else {
                        target.setDuration(udDuration);
                        break;
                    }
                }while (true);
                break;
            case 3:
                // Cập nhật Lượt xem
                do{
                    System.out.println("Cập nhật lượt xem: ");
                    int udViews = Integer.parseInt(sc.nextLine());
                    if(udViews < 0){
                        System.out.println("Vui lòng nhập lượt xem lớn hơn hoặc băng 0!!");
                    }else{
                        target.setViews(udViews);
                        break;
                    }
                }while (true);
                break;
            default:
                System.out.println("Lựa chọn của bạn chưa hợp lệ!!");
                break;
        }

        System.out.println("Hoàn thành cập nhật thông tin phim!!\n");

    }

    // Tìm kiếm phim theo tên
    public void searchMovieByName(Scanner sc){
        if (this.getMovies().isEmpty()){
            System.out.println("Danh sách phim trống");
            return;
        }

        System.out.println("Nhập tên phim cần tìm: "); // tương đối
        String inputFilter = sc.nextLine();

        List<Movie> filterMovies = this.getMovies().stream().filter(m -> m.getMovieName().toLowerCase().contains(inputFilter.toLowerCase())).toList();
        if (filterMovies.isEmpty()){
            System.out.println("Không tìm thấy các phim có tên trên");
            return;
        }
        System.out.println("================== Phim cần tìm ============");
        filterMovies.forEach(Movie::displayData);
    }


    // Xóa phim
    public void deleteMovie(Scanner sc){
        if(this.getMovies().isEmpty()){
            System.out.println("Danh sách phim trống!!");
            return;
        }

        System.out.println("Nhập id phim cần xóa: ");
        String delId = sc.nextLine();
        Movie delTarget = getMovieById(delId);

        if (delTarget == null){
            System.out.println("Không tìm thấy phim có id cần xóa!!");
            return;
        }

        System.out.print("Xác nhận xóa phim " + delTarget.getMovieName() + "(y/n): ");
        String delChoice = sc.nextLine();
        if(delChoice.equalsIgnoreCase("y")){
            boolean delSuccess = this.getMovies().remove(delTarget);
            if(delSuccess){
                System.out.println("Xóa thành công phim\n");
            }else{
                System.out.println("Xoá phim thất bại\n");
            }
        }
    }


    // Sắp xếp phim theo lượt view giảm dần
    public void sortMovieList(){
        if (this.getMovies().isEmpty()){
            System.out.println("Danh sách phim trống!!");
            return;
        }

        this.movies.sort((m1, m2) -> m2.getViews() - m1.getViews());

        this.displayMovieList();
    }

    // Lọc danh sách phim có lượt xem >= 10000
    public void filterByView(){
        if (this.getMovies().isEmpty()){
            System.out.println("Danh sách phim trống!!");
            return;
        }

        List<Movie> filterMovie = this.getMovies().stream().filter(m -> m.getViews() >= 10000).toList();

        if (filterMovie.isEmpty()){
            System.out.println("Không có phim nào thỏa mãn");
            return;
        }
        System.out.println("Danh sách phim có lượt xem trên 10000: \n");
        filterMovie.forEach(Movie::displayData);
    }




    public Movie getMovieById(String id){
        Optional<Movie> target = this.getMovies().stream().filter(m -> m.getMovieId().equals(id)).findFirst();
        if (target.isEmpty()){
            return null;
        }
        return target.get();
    }
}
