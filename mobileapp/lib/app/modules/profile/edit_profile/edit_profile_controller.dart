import 'package:flutter/widgets.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/data/services/user_service.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';

class EditProfileController extends GetxController {
  @override
  void onInit() {
    super.onInit();
    var user = TbSharedPref.getUserLogin();
    if (user != null) {
      _nameController.text = user.name;
      _addressController.text = user.address;
      _emailController.text = user.email;
      _usernameController.text = user.username;
    }
  }

  get formKey => _formKey;
  get nameController => _nameController;
  get addressController => _addressController;
  get emailController => _emailController;
  get usernameController => _usernameController;
  get passwordController => _passwordController;

  final _nameController = TextEditingController();
  final _addressController = TextEditingController();
  final _emailController = TextEditingController();
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  final _formKey = GlobalKey<FormState>();
  final userService = Get.find<UserService>();

  isValidName(String? val) {
    if (val == null || val.isEmpty) {
      return 'Name cannot be empty';
    }
  }

  isValidAddress(String? val) {
    if (val == null || val.isEmpty) {
      return 'Address cannot be empty';
    }
  }

  updateProfile() async {
    if (_formKey.currentState!.validate()) {
      var response = await userService.updateProfile(
        _nameController.text,
        _addressController.text,
        _emailController.text,
        _usernameController.text,
        _passwordController.text,
      );
      return response;
    }
  }

  isValidEmail(String? val) {
    if (val == null || val.isEmpty) {
      return 'Email cannot be empty';
    }
    if (!GetUtils.isEmail(val)) {
      return 'Email is not valid';
    }
  }

  isValidUsername(String? val) {
    if (val == null || val.isEmpty) {
      return 'Username cannot be empty';
    }
    if (val.length < 6) {
      return 'Username must be at least 6 characters';
    }
  }

  isValidPassword(String? val) {
    if (val == null || val.isEmpty) {
      return 'Password cannot be empty';
    }
    if (val.length < 8) {
      return 'Password must be at least 8 characters';
    }
  }
}
