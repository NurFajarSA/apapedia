import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/controller/home_controller.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final homeController = Get.find<HomeController>();
    return CustomScaffold(
        attr: ScaffoldAttribute(
          bottomNavigation: BottomNavigationBar(
            currentIndex: homeController.currentIndex.value,
            onTap: (index) => homeController.currentIndex.value = index,
            items: const [
              BottomNavigationBarItem(
                icon: Icon(Icons.home),
                label: 'Home',
              ),
              BottomNavigationBarItem(
                icon: Icon(Icons.person),
                label: 'Profile',
              ),
            ],
          ),
        ),
        appBar: AppBar(
          title: Text('Home'),
        ),
        body: Center(
            child: PrimaryButton(
          text: 'Go to Detail',
          onPressed: () {},
        )));
  }
}
