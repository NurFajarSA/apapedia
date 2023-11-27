import 'package:get/get.dart';
import 'package:mobileapp/app/modules/auth/signin/sign_controller.dart';

class SigninBinding implements Bindings {
  @override
  void dependencies() {
    Get.put(SigninController());
  }
}
