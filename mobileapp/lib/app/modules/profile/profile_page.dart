import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/profile/profile_controller.dart';
import 'package:mobileapp/app/widgets/show_loading.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/routes/routes.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class ProfilePage extends StatelessWidget {
  const ProfilePage({super.key});

  @override
  Widget build(BuildContext context) {
    final profileController = Get.find<ProfileController>();
    return Scaffold(
        appBar: AppBar(
          title: const Text('Profile'),
          centerTitle: true,
        ),
        bottomNavigationBar: Container(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
          height: profileController.user != null ? 120 : 60,
          decoration: BoxDecoration(
            color: MyColors.white,
            boxShadow: BoxShadowDecorator().defaultShadow(context),
          ),
          child: Column(
            children: [
              if (profileController.user != null)
                PrimaryButton(
                  width: double.infinity,
                  height: 48,
                  backgroundColor: Theme.of(context).colorScheme.error,
                  text: 'Logout',
                  onPressed: () {
                    profileController.logout();
                  },
                ),
              if (profileController.user != null) const SizedBox(height: 16),
              if (profileController.user != null)
                SecondaryButton(
                  width: double.infinity,
                  height: 48,
                  backgroundColor: Theme.of(context).colorScheme.error,
                  text: 'Delete Account',
                  onPressed: () async {
                    await showConfirmDialog(context);
                  },
                ),
              if (profileController.user == null)
                PrimaryButton(
                  width: double.infinity,
                  height: 48,
                  text: 'Login',
                  onPressed: () {
                    Get.offAllNamed(Routes.LOGIN);
                  },
                ),
            ],
          ),
        ),
        body: SingleChildScrollView(
          child: Container(
            width: MediaQuery.of(context).size.width,
            margin: const EdgeInsets.all(24),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    const CircleAvatar(
                      radius: 30,
                      backgroundImage: NetworkImage(
                          'https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250'),
                    ),
                    const SizedBox(width: 16),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Obx(() => Text(
                                profileController.name.value,
                                style:
                                    Theme.of(context).textTheme.headlineMedium,
                                overflow: TextOverflow.fade,
                              )),
                          const SizedBox(height: 4),
                          Obx(() => Text("@${profileController.username.value}",
                              style: Theme.of(context).textTheme.bodyLarge)),
                        ],
                      ),
                    )
                  ],
                ),
                const SizedBox(height: 16),
                Text('Email', style: Theme.of(context).textTheme.bodyLarge),
                const SizedBox(height: 4),
                Obx(() => Text(profileController.email.value,
                    style: Theme.of(context).textTheme.headlineMedium)),
                const SizedBox(height: 16),
                Text(
                  'Alamat',
                  style: Theme.of(context).textTheme.bodyLarge,
                ),
                const SizedBox(height: 4),
                Obx(() => Text(profileController.address.value,
                    style: Theme.of(context).textTheme.headlineMedium)),
                const SizedBox(height: 16),
                PrimaryButton(
                  width: double.infinity,
                  height: 48,
                  text: 'Edit Profile',
                  onPressed: () {
                    if (profileController.user != null) {
                      profileController.goToEditProfile();
                    } else {
                      Get.snackbar(
                        'Error',
                        'Please login first',
                        snackPosition: SnackPosition.BOTTOM,
                      );
                    }
                  },
                ),
              ],
            ),
          ),
        ));
  }

  showConfirmDialog(BuildContext context) async {
    return await Get.defaultDialog(
      title: 'Delete Account',
      content: const Text('Are you sure want to delete your account?'),
      textConfirm: 'Yes',
      textCancel: 'No',
      buttonColor: MyColors.error,
      confirmTextColor: MyColors.errorBackground,
      cancelTextColor: MyColors.white,
      radius: 8,
      onConfirm: () async {
        showLoading(context);
        await Get.find<ProfileController>().deleteAccount().then((value) {
          Get.back();
          if (value) {
            Get.offAllNamed(Routes.LOGIN);
            Get.snackbar(
              'Success',
              'Delete account successful',
              snackPosition: SnackPosition.BOTTOM,
            );
          } else {
            Get.back();
            Get.snackbar(
              'Error',
              'Delete account failed',
              snackPosition: SnackPosition.BOTTOM,
            );
          }
        });
      },
    );
  }
}
