import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/core/theme/app_theme.dart';
import 'package:mobileapp/core/utils/bindings.dart';
import 'package:mobileapp/routes/pages.dart';
import 'package:mobileapp/routes/routes.dart';

class Apapedia extends StatelessWidget {
  const Apapedia({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'Apapedia',
      theme: AppTheme.apapediaTheme,
      initialRoute: Routes.SPLASH,
      getPages: Pages.routes,
      initialBinding: InitBinding(),
    );
  }
}
