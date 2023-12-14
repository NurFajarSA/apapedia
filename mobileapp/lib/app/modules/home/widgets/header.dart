import 'package:flutter/material.dart';
import 'package:mobileapp/app/modules/home/catalogue/widget/apapay.dart';
import 'package:mobileapp/core/theme/colors.dart';

class Header extends StatelessWidget {
  const Header({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
        height: 90,
        color: MyColors.white,
        child: Stack(children: [
          Image.asset(
            'assets/images/header_bg.png',
            width: MediaQuery.of(context).size.width,
          ),
          Positioned(
              top: 20,
              width: MediaQuery.of(context).size.width,
              child: Container(
                  margin: EdgeInsets.symmetric(horizontal: 16),
                  child: Apapay()))
        ]));
  }
}
