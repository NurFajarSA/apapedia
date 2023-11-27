import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/auth/signin/sign_controller.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class SigninPage extends StatelessWidget {
  const SigninPage({super.key});

  @override
  Widget build(BuildContext context) {
    final signinController = Get.find<SigninController>();

    return CustomScaffold(
        attr: ScaffoldAttribute(backgroundColor: ColorsTheme.background),
        body: Center(
            child: Stack(
          children: [
            // Image.asset(
            //   "assets/images/auth_bg.png",
            //   width: MediaQuery.sizeOf(context).width,
            //   fit: BoxFit.cover,
            // ),
            Center(
              child: Container(
                padding: const EdgeInsets.all(20),
                width: MediaQuery.sizeOf(context).width - 40,
                height: 350,
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
                child: Column(children: [
                  Text("Sign In",
                      style: Theme.of(context).textTheme.headlineLarge),
                  const SizedBox(height: 20),
                  Form(
                    key: signinController.formKey,
                    autovalidateMode: AutovalidateMode.onUserInteraction,
                    child: Column(
                      children: [
                        InputField(
                          focusNode: signinController.emailFocusNode,
                          label: "Email/Username",
                          hintText: "username@mail.com",
                          controller: signinController.emailController,
                          validator: (val) =>
                              signinController.isValidEmailorUsername(val),
                        ),
                        const SizedBox(height: 20),
                        InputField(
                          focusNode: signinController.passwordFocusNode,
                          label: "Password",
                          hintText: "Enter your password",
                          controller: signinController.passwordController,
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 20),
                  PrimaryButton(
                    text: "Sign In",
                    onPressed: () {
                      signinController.signIn();
                    },
                  ),
                  const SizedBox(height: 14),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text("Don't have an account? ",
                          style: Theme.of(context).textTheme.bodySmall!),
                      GestureDetector(
                        onTap: () {
                          signinController.toSignUp();
                        },
                        child: Text("Create one",
                            style: Theme.of(context).textTheme.bodySmall),
                      ),
                    ],
                  ),
                ]),
              ),
            ),
          ],
        )));
  }
}
