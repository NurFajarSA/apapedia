import 'package:get/get.dart';
import 'package:mobileapp/app/modules/cart/cart_controller.dart';

class CartBinding implements Bindings {
  @override
  void dependencies() {
    Get.put(CartController());
  }
}
