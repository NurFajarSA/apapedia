import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/routes/routes.dart';

class HomeController extends GetxController {
  get currentIndex => _currentIndex;
  set currentIndex(value) => _currentIndex.value = value;
  final _currentIndex = 0.obs;
  void changeIndex(int index) {
    currentIndex = index;
  }

  void toCart() {
    Get.toNamed(Routes.CART);
  }

  void toProfile() {
    Get.toNamed(Routes.PROFILE);
  }

  String getBalance() {
    return 'Rp 100.000';
  }

  final topUpController = TextEditingController();
  final topUpFocusNode = FocusNode();

  void confirmTopUp() {}
}
