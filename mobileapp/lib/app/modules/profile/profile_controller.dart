import 'package:get/get.dart';
import 'package:mobileapp/routes/routes.dart';

class ProfileController extends GetxController {
  void goToEditProfile() {
    Get.toNamed(Routes.EDIT_PROFILE);
  }
}
