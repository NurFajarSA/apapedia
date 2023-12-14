import 'package:get/get.dart';
import 'package:mobileapp/app/modules/profile/profile_controller.dart';

class ProfileBinding implements Bindings {
  @override
  void dependencies() {
    Get.put(ProfileController());
  }
}
