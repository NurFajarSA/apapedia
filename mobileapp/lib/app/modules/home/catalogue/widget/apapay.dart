import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/core/theme/text_theme.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class Apapay extends StatelessWidget {
  const Apapay({super.key});

  @override
  Widget build(BuildContext context) {
    var homeController = Get.find<HomeController>();
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
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Text('Apapay', style: TextThemeApapedia.labelMedium),
                Text('Rp ${homeController.getBalance()}',
                    style: TextThemeApapedia.headlineMedium),
              ],
            ),
            // top up button
            GestureDetector(
              onTap: () {
                showDialog(
                  context: context,
                  builder: (context) => dialogTopUp(context),
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

Widget dialogTopUp(BuildContext context) {
  var homeController = Get.find<HomeController>();
  return Dialog(
    // backgroundColor: MyColors.white,
    child: Container(
      decoration: BoxDecoration(
        color: MyColors.white,
        borderRadius: BorderRadius.circular(16),
      ),
      padding: const EdgeInsets.all(16),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Text(
            'Top Up',
            style: TextThemeApapedia.headlineMedium,
          ),
          const SizedBox(height: 16),
          InputField(
            controller: homeController.topUpController,
            focusNode: homeController.topUpFocusNode,
            hintText: 'Input amount',
          ),
          const SizedBox(height: 16),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Expanded(
                child: SecondaryButton(
                  text: 'Cancel',
                  onPressed: () {
                    homeController.topUpController.clear();
                    homeController.topUpFocusNode.unfocus();
                    Get.back();
                  },
                ),
              ),
              const SizedBox(width: 16),
              Expanded(
                child: PrimaryButton(
                  text: 'Top Up',
                  onPressed: () {
                    homeController.confirmTopUp();
                  },
                ),
              ),
            ],
          ),
        ],
      ),
    ),
  );
}
