import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/auth/signin/sign_controller.dart';
import 'package:mobileapp/app/widgets/custom_textfield.dart';
import 'package:mobileapp/app/widgets/show_loading.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/routes/routes.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class SigninPage extends StatelessWidget {
  const SigninPage({super.key});

  @override
  Widget build(BuildContext context) {
    final signinController = Get.find<SigninController>();

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
            Column(mainAxisAlignment: MainAxisAlignment.center, children: [
              Center(
                child: Container(
                  padding: const EdgeInsets.all(20),
                  width: MediaQuery.sizeOf(context).width - 40,
                  height: 360,
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
                      Text("Sign In",
                          style: Theme.of(context).textTheme.headlineLarge),
                      const SizedBox(height: 20),
                      Form(
                        key: signinController.formKey,
                        autovalidateMode: AutovalidateMode.onUserInteraction,
                        child: Column(
                          children: [
                            CustomTextfield(
                              label: "Email/Username",
                              hint: "username@mail.com",
                              isMandatory: true,
                              background: MyColors.background,
                              controller:
                                  signinController.usernameEmailController,
                              validator: (val) =>
                                  signinController.isValidEmailorUsername(val),
                            ),
                            const SizedBox(height: 20),
                            CustomTextfield(
                              label: "Password",
                              hint: "Enter your password",
                              obscureText: true,
                              isMandatory: true,
                              background: MyColors.background,
                              controller: signinController.passwordController,
                              validator: (val) =>
                                  signinController.isValidPassword(val),
                            ),
                          ],
                        ),
                      ),
                      const SizedBox(height: 20),
                      PrimaryButton(
                        // width full
                        width: MediaQuery.sizeOf(context).width - 40,
                        padding: const EdgeInsets.symmetric(vertical: 10),
                        text: "Sign In",
                        onPressed: () async {
                          try {
                            showLoading(context);
                            await signinController.signIn().then((value) {
                              if (value) {
                                Get.offAllNamed(Routes.HOME);
                                Get.snackbar(
                                  "Success",
                                  "Sign in successful",
                                  snackPosition: SnackPosition.BOTTOM,
                                );
                              } else {
                                Get.back();
                                Get.snackbar(
                                  "Error",
                                  "Sign in failed",
                                  snackPosition: SnackPosition.BOTTOM,
                                );
                              }
                            });
                          } catch (e) {
                            Get.snackbar("Error", e.toString());
                          }
                        },
                      ),
                      const SizedBox(height: 14),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text("Don't have an account? ",
                              style: Theme.of(context).textTheme.bodyMedium!),
                          GestureDetector(
                              onTap: () {
                                signinController.toSignUp();
                              },
                              child: Text("Create one",
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
              ),
              const SizedBox(height: 20),
              SecondaryButton(
                // width full
                width: MediaQuery.sizeOf(context).width - 40,
                padding: const EdgeInsets.symmetric(vertical: 10),
                text: "Continue as Guest",
                onPressed: () {
                  signinController.signInGuest();
                },
              ),
            ]),
          ],
        )));
  }
}
