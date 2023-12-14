import 'package:get/get.dart';
import 'package:mobileapp/app/modules/auth/register/register_controller.dart';

class RegisterBinding implements Bindings {
  @override
  void dependencies() {
    Get.put(RegisterController());
  }
}
