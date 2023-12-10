import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/data/services/user_service.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';
import 'package:mobileapp/routes/routes.dart';

class HomeController extends GetxController {
  var balance = 0.obs;
  final userService = Get.find<UserService>();

  @override
  void onInit() {
    super.onInit();
    var tempBalance = TbSharedPref.getUserLogin()?.balance;
    if (tempBalance != null) {
      balance.value = tempBalance;
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
    return balance.value.toString();
  }

  final topUpController = TextEditingController();
  final topUpFocusNode = FocusNode();

  Future<bool> confirmTopUp() async {
    if (topUpController.text.isEmpty) {
      return false;
    }
    var amount = int.tryParse(topUpController.text);
    if (amount == null) {
      return false;
    }
    var response = await userService.topUpBalance(amount);
    return response;
  }

  topUpValidator(String? value) {
    if (topUpController.text.isEmpty) {
      return 'Please enter amount';
    }
    var amount = int.tryParse(topUpController.text);
    if (amount == null) {
      return 'Please enter valid amount';
    }
    return null;
  }
}
