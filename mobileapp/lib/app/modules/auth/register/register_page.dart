import 'dart:io';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/auth/register/register_controller.dart';
import 'package:mobileapp/app/widgets/custom_textfield.dart';
import 'package:mobileapp/app/widgets/show_loading.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/routes/routes.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class RegisterPage extends StatelessWidget {
  const RegisterPage({super.key});

  @override
  Widget build(BuildContext context) {
    final registerController = Get.find<RegisterController>();

    return CustomScaffold(
        attr: ScaffoldAttribute(backgroundColor: MyColors.background),
        body: Center(
            child: Stack(
          children: [
            Image.asset(
              "assets/images/sigin_bg.png",
              width: MediaQuery.sizeOf(context).width,
              fit: BoxFit.cover,
            ),
            Center(
              child: Container(
                padding: const EdgeInsets.all(20),
                width: MediaQuery.sizeOf(context).width - 40,
                height: MediaQuery.sizeOf(context).height * 0.77,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(8),
                  color: Theme.of(context).cardColor,
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black.withOpacity(0.2),
                      blurRadius: 10,
                      offset: const Offset(0, 5),
                    ),
                  ],
                ),
                child: SingleChildScrollView(
                  child: Column(children: [
                    Text("Register",
                        style: Theme.of(context).textTheme.headlineLarge),
                    const SizedBox(height: 20),
                    Form(
                      key: registerController.formKey,
                      autovalidateMode: AutovalidateMode.onUserInteraction,
                      child: Column(
                        children: [
                          CustomTextfield(
                            label: "Name",
                            hint: "Your Name",
                            background: MyColors.background,
                            controller: registerController.nameController,
                            validator: (val) => registerController.isEmpty(val),
                          ),
                          const SizedBox(height: 20),
                          CustomTextfield(
                            label: "Username",
                            hint: "Your Username",
                            background: MyColors.background,
                            controller: registerController.usernameController,
                            validator: (val) => registerController.isEmpty(val),
                          ),
                          const SizedBox(height: 20),
                          CustomTextfield(
                            label: "Email",
                            hint: "yourmail@mail.com",
                            background: MyColors.background,
                            controller: registerController.emailController,
                            validator: (val) =>
                                registerController.isValidEmail(val),
                          ),
                          const SizedBox(height: 20),
                          CustomTextfield(
                            label: "Address",
                            hint: "Your Address",
                            background: MyColors.background,
                            controller: registerController.addressController,
                            validator: (val) => registerController.isEmpty(val),
                          ),
                          const SizedBox(height: 20),
                          CustomTextfield(
                            label: "Password",
                            hint: "Enter your password",
                            obscureText: true,
                            background: MyColors.background,
                            controller: registerController.passwordController,
                            validator: (val) =>
                                registerController.isValidPassword(val),
                          ),
                          const SizedBox(height: 20),
                          CustomTextfield(
                            label: "Confirm Password",
                            hint: "Enter your password",
                            obscureText: true,
                            background: MyColors.background,
                            controller:
                                registerController.confirmPasswordController,
                            validator: (val) =>
                                registerController.isPasswordMatch(val),
                          ),
                        ],
                      ),
                    ),
                    const SizedBox(height: 20),
                    PrimaryButton(
                      // width full
                      width: MediaQuery.sizeOf(context).width - 40,
                      padding: const EdgeInsets.symmetric(vertical: 10),
                      text: "Register",
                      onPressed: () async {
                        showLoading(context);
                        await registerController.register().then((value) {
                          if (value.statusCode == HttpStatus.ok) {
                            Get.offAllNamed(Routes.LOGIN);
                            Get.snackbar(
                              "Success",
                              "Register successful! Please login using your account",
                              snackPosition: SnackPosition.BOTTOM,
                            );
                          } else {
                            Get.back();
                            Get.snackbar(
                              "Error",
                              "Register failed",
                              snackPosition: SnackPosition.BOTTOM,
                            );
                          }
                        });
                      },
                    ),
                    const SizedBox(height: 14),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text("Have an account?",
                            style: Theme.of(context).textTheme.bodyMedium!),
                        GestureDetector(
                            onTap: () {
                              registerController.toSignIn();
                            },
                            child: Text(" Sign In",
                                style: Theme.of(context)
                                    .textTheme
                                    .bodyMedium
                                    ?.copyWith(
                                        color:
                                            Theme.of(context).primaryColor))),
                      ],
                    ),
                  ]),
                ),
              ),
            )
          ],
        )));
  }
}
