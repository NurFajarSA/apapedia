import 'package:flutter/material.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
        attr: ScaffoldAttribute(backgroundColor: ColorsTheme.primary),
        body: Center(
          child: Image.asset(
            'assets/images/logo.png',
            width: 200,
            height: 200,
          ),
        ));
  }
}
