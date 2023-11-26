import 'package:get/get.dart';
import 'package:mobileapp/app/modules/splash/controller/splash_controller.dart';

class SplashBindings extends Bindings {
  @override
  void dependencies() {
    Get.put(SplashController());
  }
}
