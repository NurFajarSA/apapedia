import 'dart:async';

import 'package:get/get.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';
import 'package:mobileapp/routes/routes.dart';

class SplashController extends GetxController {
  @override
  void onInit() {
    super.onInit();
    user = TbSharedPref.getUserLogin();
    launchApp();
  }

  var user;

  launchApp() {
    Timer(const Duration(seconds: 3), () {
      if (user != null) {
        Get.offAllNamed(Routes.HOME);
        return;
      }
      Get.offAllNamed(Routes.LOGIN);
    });
  }
}
