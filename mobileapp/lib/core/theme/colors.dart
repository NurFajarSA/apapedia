import 'package:flutter/material.dart';

class MyColors {
  static const Color primary = Color(0xFF5038BC);
  static const Color accent = Color(0xFF64E6FB);
  static const Color background = Color(0xFFFFFFFF);
  static const Color surface = Color(0xFFF8F8F8);
  static const Color secondary = Color(0xFF333333);
  static const Color secondaryVariant1 = Color(0xFFC424A3);
  static const Color secondaryVariant2 = Color(0xFFFFD668);

  static const Color error = Color(0xFFEB5757);
  static const Color errorBackground = Color(0xFFFFE0E0);
  static const Color success = Color(0xFF27AE60);
  static const Color successBackground = Color(0xFFD4EFDF);
  static const Color warning = Color(0xFFF7B500);
  static const Color warningBackground = Color(0xFFFDF0CC);

  static const Color greyDark = Color(0xFF4F4F4F);
  static const Color grey = Color(0xFF828282);
  static const Color greyLight = Color(0xFFBDBDBD);
  static const Color greyLighter = Color(0xFFE0E0E0);
  static const Color greyLightest = Color(0xFFF2F2F2);

  static const Color white = Color(0xFFFFFFFF);
  static const Color black = Color(0xFF000000);
  static const Color transparent = Color(0x00000000);
}

class MaterialColorGenerator {
  static MaterialColor from(Color color) {
    return MaterialColor(color.value, <int, Color>{
      50: color.withOpacity(0.1),
      100: color.withOpacity(0.2),
      200: color.withOpacity(0.3),
      300: color.withOpacity(0.4),
      400: color.withOpacity(0.5),
      500: color.withOpacity(0.6),
      600: color.withOpacity(0.7),
      700: color.withOpacity(0.8),
      800: color.withOpacity(0.9),
      900: color.withOpacity(1.0),
    });
  }
}
