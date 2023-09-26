
import java.util.Objects;
import java.util.Scanner;

public class MuthootFincorpONE {


    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
////        sc.nextLine();
//        System.out.println(sc.nextLine());
        Scanner sc = new Scanner(System.in);

        int sum = 0;
        while (true) {
            String num = sc.nextLine();
            if (Objects.equals(num, "#")) {
                break;
            }
            sum += Integer.parseInt(num);
            System.out.println(sum);
        }
    }
    // API, confirmOrder(List<Item> item, UserDetail, Payment), validation
    // 3rd party API call
    // userDetail => authorizaton, blocked
    // Item(price, quantity, id) => > InventoryManagement() = checkInventory()(call) => check if order can be fulfilled
    // if (!canBeFulfilled) {throw Error}
    // Simple Validations of Payment data
    // razorpay API(payment) => response
    // if (APId fails) {throw Error}
    // if (responseIsOkay) {
    //     if (!InventoryManagement.checkInventoryAndUpdate()) {PaymentsService.initiateRefund()}   }
    // success;
//    domain.com/users/login
//    domain.com/payments/
//    domain.com/order/ POST
//    domain.com/order/status/:id GET
//    domain.com/order/confirmOrder POST

}
