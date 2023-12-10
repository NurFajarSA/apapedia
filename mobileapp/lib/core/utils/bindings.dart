import 'package:get/get.dart';
import 'package:mobileapp/app/data/services/auth_service.dart';
import 'package:mobileapp/app/data/services/user_service.dart';

class InitBinding implements Bindings {
  @override
  void dependencies() {
    Get.put(AuthService());
    Get.put(UserService());
  }
}
