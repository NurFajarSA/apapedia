import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/data/services/auth_service.dart';

class RegisterController extends GetxController {
  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    _confirmPasswordController.dispose();
    _addressController.dispose();
    _usernameController.dispose();
    _nameController.dispose();
    super.dispose();
  }

  final authService = Get.find<AuthService>();

  final _formKey = GlobalKey<FormState>();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _confirmPasswordController = TextEditingController();
  final _addressController = TextEditingController();
  final _usernameController = TextEditingController();
  final _nameController = TextEditingController();

  get formKey => _formKey;
  get emailController => _emailController;
  get passwordController => _passwordController;
  get confirmPasswordController => _confirmPasswordController;
  get addressController => _addressController;
  get usernameController => _usernameController;
  get nameController => _nameController;

  isValidEmail(String? val) {
    if (val == null || val.isEmpty) {
      return "Email is required";
    } else if (!GetUtils.isEmail(val)) {
      return "Email is not valid";
    }
    return null;
  }

  register() async {
    if (_isValid()) {
      return await authService.register(
          email: emailController.text,
          password: passwordController.text,
          name: nameController.text,
          username: usernameController.text,
          address: addressController.text);
    }
  }

  void toSignIn() {
    Get.back();
  }

  bool _isValid() {
    if (_formKey.currentState!.validate()) {
      return true;
    }
    return false;
  }

  String? isPasswordMatch(String? val) {
    if (passwordController.text == val) {
      return null;
    }
    return "Password doesn't match";
  }

  isEmpty(String? val) {
    if (val == null || val.isEmpty) {
      return "This field is required";
    }
    return null;
  }

  isValidPassword(String? val) {
    isEmpty(val);
    if (val!.length < 8) {
      return "Password must be at least 8 characters";
    }
    return null;
  }
}
