import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Application {
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Product> products = new ArrayList<>();
    private static final HashMap<Integer, ArrayList<String>> userProduct = new HashMap<>();
    private static final HashMap<Integer, ArrayList<String>> productUser = new HashMap<>();

    private static final Random random = new Random();


    public static void main(String[] args){
        initialize(products, users, productUser, userProduct);
        boolean play = true;
        int userChoice = 0;
        Scanner scanner = new Scanner(System.in);
        while (play){
            printMenu();
            userChoice = scanner.nextInt();
            switch (userChoice){
                case 1:
                    printUsers(users);
                    break;
                case 2:
                    printProducts(products);
                    break;
                case 3:
                    buyProduct();
                    break;
                case 4:
                    printUserProductList();
                    break;
                case 5:
                    printProductUserList();
                    break;
                case 6:
                    addNewUser();
                    break;
                case 7:
                    addNewProduct();
                    break;
                case 8:
                    printProductUserList();
                    break;
                case 9:
                    printProductUserList();
                    break;

                default:
                    System.out.println("Хибний вибір!");
            }
        }


        scanner.close();



    }

    public static void initialize(ArrayList<Product> products, ArrayList<User> users,
                                  HashMap<Integer, ArrayList<String>> productUser, HashMap<Integer, ArrayList<String>> userProduct){
         /*   products.add(new Product(0, "Картопля", 13));
            products.add(new Product(1, "Цибуля", 40));
            products.add(new Product(2, "Огірок", 80));



            users.add(new User(0, "Тимофій", "Мулярчук", 470));
            //users.add(new User(1, "Вадим", "Броварчук", 1200));
            users.add(new User(2, "Данило", "Кравчук", 30));

            for (int i = 0; i < 2; i++) {
                //productUser.put(i, new ArrayList<>());
                userProduct.put(i, new ArrayList<>());
            }
        for (int i = 0; i < 3; i++) {
            productUser.put(i, new ArrayList<>());
            //userProduct.put(i, new ArrayList<>());
        }*/

    }

    public static void printMenu(){
        System.out.println();
        System.out.println("1 - Display list of all users");
        System.out.println("2 - Display list of all products");
        System.out.println("3 - Buy product");
        System.out.println("4 - Display list of user products by user id");
        System.out.println("5 - Display list of users that bought product");
        System.out.println("<><><>MEDIUM SYSTEM REQUIREMENTS<><><>");
        System.out.println("6 - Add new user");
        System.out.println("7 - Add new product");
        System.out.println("8 - Delete user");
        System.out.println("9 - Delete product");
    }

    public static void printUsers(ArrayList<User> users){
        for (User user : users) {
            System.out.println("[ " + user.getId() + " " + user.getFirstName() + " " +
                    user.getLastName() + " " + user.getAmountOfMoney() + " ]");
        }
    }
    public static void printProducts(ArrayList<Product> products){
        for (Product product : products) {
            System.out.println("[ " + product.getId() + " " + product.getName() + " " +
                    product.getPrice() + " ]");
        }
    }

    public static void buyProduct(){
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();
        int productId = scanner.nextInt();

        fillDataList(productId, findProductNameById(productId), userId, findUserNameById(userId));
    }

    public static String findProductNameById(int id){
        for (Product product: products) {
            if (product.getId() == id)
                return product.getName();
        }
        return null;
    }

    public static String findUserNameById(int id){
        for (User user: users) {
            if (user.getId() == id)
                return user.getFirstName();

        }
        return null;
    }

    public static void fillDataList(int productId, String productName, int userId, String userName){
        if(users.get(userId).getAmountOfMoney() - products.get(productId).getPrice() >= 0){
            users.get(userId).setAmountOfMoney(users.get(userId).getAmountOfMoney() - products.get(productId).getPrice());
            userProduct.get(userId).add(productName);
            productUser.get(productId).add(userName);
            System.out.println("Purchase successful!");
        } else{
            System.out.println(userName + " (" + userId + ") " + " user does not have enough money");
        }
    }

    public static void printUserProductList(){
        for (int i = 0; i < userProduct.size(); i++) {
            System.out.println("id:" + "<" + users.get(i).getId() + ">" + " Name: " + users.get(i).getFirstName() + " ("+ users.get(i).amountOfMoney + ")->");
            formatPrintForStringArr(userProduct.get(i));
        }
    }

    public static void printProductUserList(){
        for (int i = 0; i < productUser.size(); i++) {
            System.out.println("id:" + "<" + products.get(i).getId() + ">" + " Name: " + products.get(i).getName() + " ("+ products.get(i).getPrice() + ")->");
            formatPrintForStringArr(productUser.get(i));
        }
    }

    public static void formatPrintForStringArr(ArrayList<String> arr){
        if (arr != null){
            for (String s : arr) {
                System.out.println("\t[ " + s + " ]");
            }
        }

    }

    public static void addNewUser(){
        Scanner scanner = new Scanner(System.in);
        int id = Math.abs(random.nextInt());
        System.out.print("Input first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Input last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Input amount of money name: ");
        float amountOfMoney = scanner.nextFloat();
        users.add(new User(id, firstName, lastName, amountOfMoney));

        userProduct.put(id, new ArrayList<>());
    }

    public static void addNewProduct(){
        Scanner scanner = new Scanner(System.in);
        int id = Math.abs(random.nextInt());
        System.out.print("Input name: ");
        String name = scanner.nextLine();
        System.out.print("Input price: ");
        float price = scanner.nextFloat();
        products.add(new Product(id, name, price));

        productUser.put(id, new ArrayList<>());
    }

}
