import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';
import 'package:mobileapp/core/values/strings.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final homeController = Get.find<HomeController>();
    return CustomScaffold(
        attr: ScaffoldAttribute(
          bottomNavigation: RistekBotNavBar(
            onTap: (index) {
              homeController.currentIndex.value = index;
            },
            items: const [
              RistekBotNavItem(
                icon: Icons.home,
                text: 'Home',
              ),
              RistekBotNavItem(
                icon: Icons.shopping_cart,
                text: 'Cart',
              ),
              RistekBotNavItem(
                icon: Icons.history,
                text: 'Order History',
              ),
              RistekBotNavItem(
                icon: Icons.person,
                text: 'Profile',
              ),
            ],
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
        ),
        body: Center(
            child: PrimaryButton(
          text: 'Go to Detail',
          onPressed: () {},
        )));
  }
}
