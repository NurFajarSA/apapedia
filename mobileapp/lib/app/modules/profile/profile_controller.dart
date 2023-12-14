import 'package:get/get.dart';
import 'package:mobileapp/app/data/models/user.dart';
import 'package:mobileapp/app/data/services/auth_service.dart';
import 'package:mobileapp/app/data/services/user_service.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';
import 'package:mobileapp/routes/routes.dart';

class ProfileController extends GetxController {
  var isLogin = false.obs;
  User? user;
  var username = 'Belum Login'.obs;
  var name = 'Belum Login'.obs;
  var email = 'Belum Login'.obs;
  var address = 'Belum Login'.obs;

  @override
  void onInit() {
    super.onInit();
    isLogin.value = TbSharedPref.getUserLogin() != null;
    user = TbSharedPref.getUserLogin();
    if (user != null) {
      username.value = user!.username;
      name.value = user!.name;
      email.value = user!.email;
      address.value = user!.address;
    }
  }

  void refreshUser() {
    user = TbSharedPref.getUserLogin();
    if (user != null) {
      username.value = user!.username;
      name.value = user!.name;
      email.value = user!.email;
      address.value = user!.address;
    }
  }

  void goToEditProfile() {
    Get.toNamed(Routes.EDIT_PROFILE);
  }

  void logout() async {
    await Get.find<AuthService>().logout();
    Get.offAllNamed(Routes.LOGIN);
  }

  Future<bool> deleteAccount() async {
    return await Get.find<UserService>().deleteAccount();
  }
}
