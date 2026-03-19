package ra.presentation;

import ra.business.MovieBusiness;

import java.util.Scanner;

public class MovieManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MovieBusiness movieBusiness = MovieBusiness.getInstance();

        int choice = 0;
        do {
            System.out.println("\n************** QUẢN LÝ DANH MỤC PHIM ***************");
            System.out.print("1. Hiển thị danh sách toàn bộ phim\n" +
                    "2. Thêm mới phim\n" +
                    "3. Cập nhật thông tin phim theo mã phim\n" +
                    "4. Xóa phim theo mã phim\n" +
                    "5. Tìm kiếm phim theo tên\n" +
                    "6. Lọc danh sách các phim thịnh hành\n" +
                    "7. Sắp xếp danh sách phim giảm dần theo lượt xem\n" +
                    "8. Thoát\n" +
                    "Lựa chọn: ");
            try {
                choice = sc.nextInt();
            }catch (NumberFormatException e){
                System.out.println(e.toString());
            }

            switch (choice){
                case 1:
                    // Hien thi danh sach phim
                    movieBusiness.displayMovieList();
                    break;
                case 2:
                    // Them moi phim
                    String addChoice;
                    do {
                        boolean addSuccess = movieBusiness.addNewMovie(sc);
                        if(!addSuccess){
                            System.out.println("Thêm mới phim thất bại");
                            break;
                        }
                        System.out.print("Tiếp tục thêm phim mới (y/n): ");
                        addChoice = sc.nextLine().toLowerCase();
                        switch (addChoice){
                            case "y":
                                break;
                            case "n":
                                System.out.println("Dừng thêm phim mới");
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ");
                                break;
                        }
                    }while (!addChoice.equals("n"));
                    break;
                case 3:
                    // Cap nhat phim
                    sc.nextLine();
                    movieBusiness.updateMovieInfo(sc);
                    break;
                case 4:
                    sc.nextLine();
                    // Xoa phim
                    movieBusiness.deleteMovie(sc);
                    break;
                case 5:
                    sc.nextLine();
                    // Tim kiem phim theo ten
                    movieBusiness.searchMovieByName(sc);
                    break;
                case 6:

                    // Loc danh sach phim hot
                    movieBusiness.filterByView();
                    break;
                case 7:
                    // Sap xep danh sach phim giam dan theo views
                    movieBusiness.sortMovieList();
                    break;
                case 8:
                    sc.close();
                    System.out.println("==========================================================");
                    System.out.println("Thoát chương trình!!");
                    break;
                default:
                    break;
            }
        }while(choice != 8);

    }
}
