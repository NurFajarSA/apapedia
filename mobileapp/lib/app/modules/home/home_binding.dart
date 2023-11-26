import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';

class HomeBindings extends Bindings {
  @override
  void dependencies() {
    Get.put(HomeController());
  }
}
