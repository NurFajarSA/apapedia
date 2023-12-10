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
    }
  }

  get formKey => _formKey;
  get nameController => _nameController;
  get addressController => _addressController;

  final _nameController = TextEditingController();
  final _addressController = TextEditingController();
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
      );
      return response;
    }
  }
}
