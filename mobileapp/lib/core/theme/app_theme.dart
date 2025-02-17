import 'package:flutter/material.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/core/theme/text_theme.dart';

class AppTheme {
  static ThemeData apapediaTheme = ThemeData(
    brightness: Brightness.light,
    useMaterial3: true,
    colorScheme: ColorScheme.fromSwatch(
      brightness: Brightness.light,
      primarySwatch: MaterialColorGenerator.from(MyColors.primary),
      accentColor: MaterialColorGenerator.from(MyColors.accent),
      errorColor: MaterialColorGenerator.from(MyColors.error),
      backgroundColor: MaterialColorGenerator.from(MyColors.background),
      cardColor: MaterialColorGenerator.from(MyColors.surface),
    ),
    appBarTheme: const AppBarTheme(
      backgroundColor: MyColors.background,
      elevation: 0,
      centerTitle: true,
      titleTextStyle: TextStyle(
        fontSize: 20,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.15,
        fontFamily: 'Poppins',
        color: MyColors.secondary,
      ),
      iconTheme: IconThemeData(
        color: MyColors.primary,
      ),
    ),
    bottomNavigationBarTheme: const BottomNavigationBarThemeData(
      backgroundColor: MyColors.background,
      elevation: 0,
      selectedItemColor: MyColors.primary,
      unselectedItemColor: MyColors.greyLight,
      selectedLabelStyle: TextStyle(
        fontSize: 12,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.4,
        fontFamily: 'Poppins',
      ),
      unselectedLabelStyle: TextStyle(
        fontSize: 12,
        fontWeight: FontWeight.w400,
        letterSpacing: 0.4,
        fontFamily: 'Poppins',
      ),
    ),
    elevatedButtonTheme: ElevatedButtonThemeData(
      style: ElevatedButton.styleFrom(
        backgroundColor: MyColors.primary,
        foregroundColor: MyColors.surface,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
        ),
        textStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
          letterSpacing: 0.4,
          fontFamily: 'Poppins',
        ),
      ),
    ),
    textButtonTheme: TextButtonThemeData(
      style: TextButton.styleFrom(
        backgroundColor: MyColors.primary,
        textStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
          letterSpacing: 0.4,
          fontFamily: 'Poppins',
        ),
      ),
    ),
    outlinedButtonTheme: OutlinedButtonThemeData(
      style: OutlinedButton.styleFrom(
        backgroundColor: MyColors.primary,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
        ),
        textStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
          letterSpacing: 0.4,
          fontFamily: 'Poppins',
        ),
      ),
    ),
    textTheme: TextThemeApapedia.textThemeApapedia,
    shadowColor: MyColors.greyLighter,
  );
}
