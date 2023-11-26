import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/home_binding.dart';
import 'package:mobileapp/app/modules/home/home_screen.dart';
import 'package:mobileapp/app/modules/splash/splash_binding.dart';
import 'package:mobileapp/app/modules/splash/splash_screen.dart';
import 'package:mobileapp/routes/routes.dart';

class Pages {
  static final routes = [
    GetPage(
      name: Routes.HOME,
      page: () => const HomeScreen(),
      binding: HomeBindings(),
    ),
    GetPage(
        name: Routes.SPLASH,
        page: () => const SplashScreen(),
        binding: SplashBindings())
  ];
}
