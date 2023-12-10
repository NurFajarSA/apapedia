import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/profile/edit_profile/edit_profile_controller.dart';
import 'package:mobileapp/app/widgets/custom_textfield.dart';
import 'package:mobileapp/app/widgets/show_loading.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class EditProfilePage extends StatelessWidget {
  const EditProfilePage({super.key});

  @override
  Widget build(BuildContext context) {
    final editProfileController = Get.find<EditProfileController>();
    return Scaffold(
      appBar: AppBar(
        title: Text('Edit Profile'),
        centerTitle: true,
      ),
      bottomNavigationBar: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        height: 60,
        decoration: BoxDecoration(
          color: MyColors.white,
          boxShadow: BoxShadowDecorator().defaultShadow(context),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            // button buy
            Expanded(
              child: SecondaryButton(
                onPressed: () {
                  Get.back();
                },
                child: Center(
                  child: Text(
                    'Cancel',
                    style: Theme.of(context).textTheme.labelLarge?.copyWith(
                          color: MyColors.primary,
                        ),
                  ),
                ),
              ),
            ),
            const SizedBox(width: 8),
            Expanded(
              child: PrimaryButton(
                onPressed: () async {
                  showLoading(context);
                  await editProfileController.updateProfile();
                },
                child: Center(
                  child: Text(
                    'Save',
                    style: Theme.of(context).textTheme.labelLarge?.copyWith(
                          color: MyColors.white,
                        ),
                  ),
                ),
              ),
            )
          ],
        ),
      ),
      body: Container(
        width: MediaQuery.of(context).size.width,
        margin: const EdgeInsets.all(24),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Form(
              key: editProfileController.formKey,
              autovalidateMode: AutovalidateMode.onUserInteraction,
              child: Column(
                children: [
                  CustomTextfield(
                    label: "Full Name",
                    hint: "John Doe",
                    isMandatory: true,
                    background: MyColors.background,
                    controller: editProfileController.nameController,
                    validator: (val) => editProfileController.isValidName(val),
                  ),
                  const SizedBox(height: 20),
                  CustomTextfield(
                    label: "Alamat",
                    hint: "Jl. Jalan",
                    isMandatory: true,
                    background: MyColors.background,
                    controller: editProfileController.addressController,
                    validator: (val) =>
                        editProfileController.isValidAddress(val),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
