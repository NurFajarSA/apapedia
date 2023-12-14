import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/controller/catalogue_controller.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';
import 'package:mobileapp/app/modules/home/controller/order_controller.dart';

class HomeBindings extends Bindings {
  @override
  void dependencies() {
    Get.put(HomeController());
    Get.put(CatalogueController());
    Get.put(OrderController());
  }
}
