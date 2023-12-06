import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/profile/profile_controller.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class ProfilePage extends StatelessWidget {
  const ProfilePage({super.key});

  @override
  Widget build(BuildContext context) {
    final profileController = Get.find<ProfileController>();
    return Scaffold(
        appBar: AppBar(
          title: Text('Profile'),
          centerTitle: true,
        ),
        bottomNavigationBar: Container(
          padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
          height: 120,
          decoration: BoxDecoration(
            color: MyColors.white,
            boxShadow: BoxShadowDecorator().defaultShadow(context),
          ),
          child: Column(
            children: [
              PrimaryButton(
                width: double.infinity,
                height: 48,
                backgroundColor: Theme.of(context).colorScheme.error,
                text: 'Logout',
                onPressed: () {},
              ),
              SizedBox(height: 16),
              SecondaryButton(
                width: double.infinity,
                height: 48,
                backgroundColor: Theme.of(context).colorScheme.error,
                text: 'Delete Account',
                onPressed: () {},
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
                    CircleAvatar(
                      radius: 30,
                      backgroundImage: NetworkImage(
                          'https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250'),
                    ),
                    SizedBox(width: 16),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'John Doe',
                            style: Theme.of(context).textTheme.headlineMedium,
                            overflow: TextOverflow.fade,
                          ),
                          SizedBox(height: 4),
                          Text('@username',
                              style: Theme.of(context).textTheme.bodyLarge),
                        ],
                      ),
                    )
                  ],
                ),
                SizedBox(height: 16),
                Text('Email', style: Theme.of(context).textTheme.bodyLarge),
                SizedBox(height: 4),
                Text('johndoe@gmail.com',
                    style: Theme.of(context).textTheme.headlineMedium),
                SizedBox(height: 16),
                Text(
                  'Alamat',
                  style: Theme.of(context).textTheme.bodyLarge,
                ),
                SizedBox(height: 4),
                Text('Jl. Jalan No. 1',
                    style: Theme.of(context).textTheme.headlineMedium),
                SizedBox(height: 16),
                PrimaryButton(
                  width: double.infinity,
                  height: 48,
                  text: 'Edit Profile',
                  onPressed: () {
                    profileController.goToEditProfile();
                  },
                ),
              ],
            ),
          ),
        ));
  }
}
