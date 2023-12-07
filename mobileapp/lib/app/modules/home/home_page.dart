import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/catalogue/catalogue_page.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';
import 'package:mobileapp/app/modules/home/order/order_page.dart';
import 'package:mobileapp/app/modules/home/widgets/header.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/core/theme/text_theme.dart';
import 'package:mobileapp/core/values/strings.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    final homeController = Get.find<HomeController>();
    return DefaultTabController(
      length: 2,
      child: CustomScaffold(
          attr: ScaffoldAttribute(),
          appBar: AppBar(
            backgroundColor: MyColors.primary,
            bottom: tabBar(),
            title: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Image.asset(
                  'assets/images/logo.png',
                  fit: BoxFit.contain,
                  height: 32,
                ),
                const SizedBox(width: 8),
                Text(Strings.appName,
                    style: Theme.of(context).textTheme.headlineMedium?.copyWith(
                          color: MyColors.white,
                        )),
              ],
            ),
            actions: [
              if (homeController.cartIsEmpty)
                IconButton(
                  icon: const Icon(Icons.shopping_cart, color: MyColors.white),
                  onPressed: () {
                    homeController.toCart();
                  },
                ),
              if (!homeController.cartIsEmpty)
                Stack(
                  children: [
                    IconButton(
                      icon: const Icon(Icons.shopping_cart,
                          color: MyColors.white),
                      onPressed: () {
                        homeController.toCart();
                      },
                    ),
                    Positioned(
                      top: 8,
                      right: 10,
                      child: Container(
                        padding: const EdgeInsets.all(2),
                        decoration: BoxDecoration(
                          color: MyColors.error,
                          shape: BoxShape.circle,
                        ),
                        constraints: const BoxConstraints(
                          minWidth: 10,
                          minHeight: 10,
                        ),
                      ),
                    )
                  ],
                ),
              IconButton(
                icon: const Icon(
                  Icons.person_rounded,
                  color: MyColors.white,
                ),
                onPressed: () {
                  homeController.toProfile();
                },
              ),
            ],
          ),
          body: const TabBarView(children: [
            CataloguePage(),
            OrderPage(),
          ])),
    );
  }
}

PreferredSize tabBar() {
  return PreferredSize(
    preferredSize: const Size.fromHeight(124),
    child: Column(
      children: [
        TabBar(
          tabs: const [
            Tab(text: 'Catalogue'),
            Tab(text: 'Order History'),
          ],
          indicator: const UnderlineTabIndicator(
            borderSide: BorderSide(
              color: MyColors.white,
              width: 2,
            ),
            insets: EdgeInsets.symmetric(
              horizontal: 16.0,
              vertical: 8.0,
            ),
          ),
          labelColor: MyColors.white,
          unselectedLabelColor: MyColors.white.withOpacity(0.8),
          unselectedLabelStyle: TextThemeApapedia.bodyMedium,
          indicatorColor: MyColors.white,
          indicatorWeight: 1.0,
        ),
        Header(),
      ],
    ),
  );
}
