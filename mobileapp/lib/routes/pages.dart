import 'package:get/get.dart';
import 'package:mobileapp/app/modules/auth/register/register_binding.dart';
import 'package:mobileapp/app/modules/auth/register/register_page.dart';
import 'package:mobileapp/app/modules/auth/signin/signin_binding.dart';
import 'package:mobileapp/app/modules/auth/signin/signin_page.dart';
import 'package:mobileapp/app/modules/home/home_binding.dart';
import 'package:mobileapp/app/modules/home/home_page.dart';
import 'package:mobileapp/app/modules/splash/splash_binding.dart';
import 'package:mobileapp/app/modules/splash/splash_page.dart';
import 'package:mobileapp/routes/routes.dart';

class Pages {
  static final routes = [
    GetPage(
      name: Routes.HOME,
      page: () => const HomePage(),
      binding: HomeBindings(),
    ),
    GetPage(
        name: Routes.SPLASH,
        page: () => const SplashPage(),
        binding: SplashBindings()),
    GetPage(
        name: Routes.LOGIN,
        page: () => const SigninPage(),
        binding: SigninBinding()),
    GetPage(
        name: Routes.REGISTER,
        page: () => const RegisterPage(),
        binding: RegisterBinding()),
  ];
}
