import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/core/theme/text_theme.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class Apapay extends StatelessWidget {
  const Apapay({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: const EdgeInsets.symmetric(
          horizontal: 20,
          vertical: 12,
        ),
        decoration: BoxDecoration(
          color: MyColors.white,
          boxShadow: BoxShadowDecorator().defaultShadow(context),
          borderRadius: BorderRadius.circular(8),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            const Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text('Apapay', style: TextThemeApapedia.labelMedium),
                Text('Rp 100.000', style: TextThemeApapedia.headlineMedium),
              ],
            ),
            // top up button
            GestureDetector(
              onTap: () {
                Get.showSnackbar(
                  const GetSnackBar(
                    title: 'Top Up',
                    message: 'Under Development',
                    duration: Duration(seconds: 2),
                  ),
                );
              },
              child: const Column(
                children: [
                  Icon(
                    Icons.add_circle_outline,
                    color: MyColors.primary,
                  ),
                  Text(
                    'Top Up',
                    style: TextStyle(
                      fontSize: 12,
                      fontWeight: FontWeight.w600,
                      color: MyColors.primary,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ));
  }
}
