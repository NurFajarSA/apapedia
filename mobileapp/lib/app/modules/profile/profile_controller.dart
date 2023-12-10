import 'package:get/get.dart';
import 'package:mobileapp/app/data/services/auth_service.dart';
import 'package:mobileapp/routes/routes.dart';

class ProfileController extends GetxController {
  void goToEditProfile() {
    Get.toNamed(Routes.EDIT_PROFILE);
  }

  void logout() async {
    await Get.find<AuthService>().logout();
    Get.offAllNamed(Routes.LOGIN);
  }
}
