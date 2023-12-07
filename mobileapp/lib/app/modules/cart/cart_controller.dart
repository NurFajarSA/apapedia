import 'package:get/get.dart';
import 'package:mobileapp/routes/routes.dart';

class CartController extends GetxController {
  void checkout() {
    Get.toNamed(Routes.CHECKOUT_CART);
  }
}
