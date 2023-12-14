import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/data/services/auth_service.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';
import 'package:mobileapp/routes/routes.dart';

class SigninController extends GetxController {
  @override
  void dispose() {
    _usernameEmailController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

  final authService = Get.find<AuthService>();

  final _formKey = GlobalKey<FormState>();
  final _usernameEmailController = TextEditingController();
  final _passwordController = TextEditingController();

  get formKey => _formKey;
  get usernameEmailController => _usernameEmailController;
  get passwordController => _passwordController;

  isValidEmailorUsername(String? val) {
    if (val == null || val.isEmpty) {
      return "Email/Username is required";
    } else if (val.contains("@")) {
      if (!GetUtils.isEmail(val)) {
        return "Email is not valid";
      }
    } else if (val.length < 6) {
      return "Username must be at least 6 characters";
    }
    return null;
  }

  _isValid() {
    if (_formKey.currentState!.validate()) {
      return true;
    }
    return false;
  }

  Future<bool> signIn() async {
    if (_isValid()) {
      return await _signIn(
          usernameEmail: usernameEmailController.text,
          password: passwordController.text);
    }
    return false;
  }

  Future<bool> _signIn({required usernameEmail, required password}) async {
    var response = await authService.signIn(
        usernameEmail: usernameEmail, password: password);
    return response;
  }

  void toSignUp() {
    Get.toNamed(Routes.REGISTER);
  }

  void signInGuest() {
    TbSharedPref.setGuestLogin();
    Get.offAllNamed(Routes.HOME);
  }

  isValidPassword(String? val) {
    if (val == null || val.isEmpty) {
      return "Password is required";
    } else if (val.length < 8) {
      return "Password must be at least 8 characters";
    }
    return null;
  }
}
