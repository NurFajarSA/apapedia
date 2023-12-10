import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';
import 'package:mobileapp/routes/routes.dart';

class HomeController extends GetxController {
  var balance = 0.0.obs;
  @override
  void onInit() {
    super.onInit();
    var tempBalance = TbSharedPref.getUserLogin()?.balance;
    if (tempBalance != null) {
      balance.value = tempBalance as double;
    }
  }

  get currentIndex => _currentIndex;
  get cartIsEmpty => true;
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
    return balance.value.toStringAsFixed(2);
  }

  final topUpController = TextEditingController();
  final topUpFocusNode = FocusNode();

  void confirmTopUp() {}
}
