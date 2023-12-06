import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/catalogue/catalogue_page.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';
import 'package:mobileapp/app/modules/home/order/order_page.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/core/values/strings.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    final homeController = Get.find<HomeController>();
    return CustomScaffold(
        attr: ScaffoldAttribute(
          bottomNavigation: Obx(
            () => RistekBotNavBar(
              initialActiveIndex: homeController.currentIndex.value,
              onTap: (index) {
                homeController.changeIndex(index);
              },
              items: const [
                RistekBotNavItem(
                  icon: Icons.home,
                  text: 'Home',
                ),
                RistekBotNavItem(
                  icon: Icons.history,
                  text: 'Order History',
                ),
              ],
            ),
          ),
        ),
        appBar: AppBar(
          title: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Image.asset(
                'assets/images/logo_primary.png',
                fit: BoxFit.contain,
                height: 32,
              ),
              const SizedBox(width: 8),
              Text(
                Strings.appName,
                style: Theme.of(context).textTheme.headlineMedium,
              ),
            ],
          ),
          actions: [
            IconButton(
              icon: const Icon(Icons.shopping_cart, color: MyColors.secondary),
              onPressed: () {
                homeController.toCart();
              },
            ),
            IconButton(
              icon: const Icon(
                Icons.person_rounded,
                color: MyColors.secondary,
              ),
              onPressed: () {
                homeController.toProfile();
              },
            ),
          ],
        ),
        body: Obx(() {
          switch (homeController.currentIndex.value) {
            case 0:
              return const CataloguePage();
            case 1:
              return const OrderPage();
            default:
              return const CataloguePage();
          }
        }));
  }
}
