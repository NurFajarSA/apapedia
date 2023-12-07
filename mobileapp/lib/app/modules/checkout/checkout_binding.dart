import 'package:get/get.dart';
import 'package:mobileapp/app/modules/checkout/checkout_controller.dart';

class CheckoutBinding implements Bindings {
  @override
  void dependencies() {
    Get.put(CheckoutController());
  }
}
