import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/home_binding.dart';
import 'package:mobileapp/app/modules/home/home_screen.dart';
import 'package:mobileapp/routes/routes.dart';

class Pages {
  static final routes = [
    GetPage(
      name: Routes.HOME,
      page: () => const HomeScreen(),
      binding: HomeBindings(),
    ),
  ];
}
