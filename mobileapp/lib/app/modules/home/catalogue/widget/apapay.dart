import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';
import 'package:mobileapp/app/widgets/custom_textfield.dart';
import 'package:mobileapp/app/widgets/show_loading.dart';
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
          boxShadow: [
            BoxShadow(
              color: const Color.fromARGB(255, 130, 115, 156).withOpacity(0.2),
              spreadRadius: 18 / 4,
              blurRadius: 18 / 3,
              offset: const Offset(0, 18 / 5),
            )
          ],
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
          CustomTextfield(
            controller: homeController.topUpController,
            label: "Top Up",
            hint: "Amount",
            background: MyColors.white,
            validator: (value) => homeController.topUpValidator(value),
            keyboardType: TextInputType.number,
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
                  onPressed: () async {
                    showLoading(context);
                    await homeController.confirmTopUp().then((value) {
                      Get.back();
                      if (value) {
                        homeController.topUpController.clear();
                        homeController.topUpFocusNode.unfocus();
                        Get.back();
                        Get.snackbar(
                          'Success',
                          'Top up success',
                          backgroundColor: MyColors.success,
                          colorText: MyColors.white,
                        );
                      } else {
                        Get.snackbar(
                          'Failed',
                          'Top up failed',
                          backgroundColor: MyColors.error,
                          colorText: MyColors.white,
                        );
                      }
                    });
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
